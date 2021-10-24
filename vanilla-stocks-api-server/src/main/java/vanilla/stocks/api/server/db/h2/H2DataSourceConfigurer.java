package vanilla.stocks.api.server.db.h2;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;
import vanilla.stocks.api.server.PropertiesName;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "vanilla.stocks.api.server.db.h2" }, transactionManagerRef = "transactionManagerH2", entityManagerFactoryRef = "entityManagerFactoryH2")
@MapperScan(basePackages = { "vanilla.stocks.api.server.db.h2.sqlmapper" })
@Slf4j
public class H2DataSourceConfigurer {

    @Autowired
    private ApplicationContext applicationContext;
    
    @Bean(name = "dataSourceH2", destroyMethod = "close")
    public DataSource dataSourceH2() {
        String appHome = System.getProperty(PropertiesName.APP_HOME);
        String jdbcUrl = String.format("jdbc:log4jdbc:h2:file:%s/h2/vanilla-stocks;AUTO_SERVER=TRUE", appHome);
        log.info("JDBC for hibernate.. {}", jdbcUrl);

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        dataSource.setUsername("vanilla");
        dataSource.setPassword("");
        dataSource.setConnectionTestQuery("SELECT 1");
        return dataSource;
    }
    
    @Bean(name = "entityManagerFactoryH2")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSourceH2") DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan(new String[] { "vanilla.stocks.api.server.db.h2" });

        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProperties.setProperty("hibernate.id.new_generator_mapping", "false");
        hibernateProperties.setProperty("hibernate.show_sql", "false");
        hibernateProperties.setProperty("hibernate.format_sql", "false");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(hibernateProperties);

        return entityManagerFactoryBean;
    }
    
    @Bean(name = "transactionManagerH2")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactoryH2") final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    @Bean(name = "sqlSessionFactoryH2")
    @Primary
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("dataSourceH2") DataSource dataSource) throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("vanilla.stocks.api.server.db.h2");
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:vanilla/stocks/api/server/db/h2/**/**SqlMapper.xml"));
        factoryBean.setVfs(SpringBootVFS.class);
        return factoryBean;
    }
    
    @Bean(name = "sqlSessionH2")
    @Primary
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
