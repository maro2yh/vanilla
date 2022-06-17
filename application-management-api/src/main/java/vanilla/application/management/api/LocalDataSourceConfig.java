package vanilla.application.management.api;

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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "vanilla.application.management.api" }, transactionManagerRef = "transactionManager", entityManagerFactoryRef = "entityManagerFactory")
@MapperScan(basePackages = { "vanilla.application.management.api" })
public class LocalDataSourceConfig {

    @Autowired
    private ApplicationContext applicationContext;
    
    @Bean(name = "dataSource", destroyMethod = "close")
    public DataSource dataSource() {
        String home = System.getProperty("home");
        String jdbcUrl = String.format("jdbc:log4jdbc:h2:file:%s/db/applications_management;AUTO_SERVER=TRUE", home);

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setConnectionTestQuery("SELECT 1");
        return dataSource;
    }
    
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan(new String[] { "vanilla.application.management.api" });

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
    
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("vanilla.application.management.api");
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:vanilla/application/management/api/**/**SqlMapper.xml"));
        factoryBean.setVfs(SpringBootVFS.class);
        return factoryBean;
    }
    
    @Bean(name = "sqlSession")
    @Primary
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
