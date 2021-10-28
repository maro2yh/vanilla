package vanilla.stocks.api.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import vanilla.commons.util.file.VanillaFileUtils;

@SpringBootApplication
public class ServerStarter {
    public static void main(String[] args) throws Exception {
        String homeDirPath = System.getProperty(PropertiesName.APP_HOME);
        File confDir = VanillaFileUtils.getFile(homeDirPath, "conf");

        if (!confDir.isDirectory()) {
            throw new Exception("The application's 'conf' directory could not be found.");
        }

        File confServerFile = VanillaFileUtils.getFile(homeDirPath, "conf", "server.yml");

        if (!confServerFile.isFile()) {
            throw new Exception("The application's configuration file could not be found.");
        }

        Logger yamlLogger = (Logger) LoggerFactory.getLogger("org.springframework.beans.factory.config.YamlPropertiesFactoryBean");
        yamlLogger.setLevel(Level.OFF);
        
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new FileSystemResource(confServerFile));
        Properties serverProps = yaml.getObject();

        if (StringUtils.isEmpty(serverProps.getProperty(PropertiesName.LOG_PATH))) {
            File logDir = VanillaFileUtils.mkdirs(homeDirPath, "logs");
            serverProps.setProperty(PropertiesName.LOG_PATH, logDir.getAbsolutePath());
        }

        boolean shoqSql = Boolean.parseBoolean(serverProps.getProperty(PropertiesName.LOG_SHOW_SQL));

        System.setProperty(PropertiesName.LOG_PATH, serverProps.getProperty(PropertiesName.LOG_PATH));
        System.setProperty(PropertiesName.LOG_FILE_NAME, serverProps.getProperty(PropertiesName.LOG_FILE_NAME));
        System.setProperty(PropertiesName.LOG_FILE_EXTENTION, serverProps.getProperty(PropertiesName.LOG_FILE_EXTENTION));
        System.setProperty(PropertiesName.LOG_LEVEL, serverProps.getProperty(PropertiesName.LOG_LEVEL));
        System.setProperty(PropertiesName.LOG_MAX_HISTORY, serverProps.getProperty(PropertiesName.LOG_MAX_HISTORY));
        System.setProperty(PropertiesName.LOG_MAX_SIZE, serverProps.getProperty(PropertiesName.LOG_MAX_SIZE));
        System.setProperty(PropertiesName.LOG_SQL, shoqSql ? "debug" : "warn");
        
//        System.setProperty("spring.main.banner-mode", "off"); // 스프링 배너 출력 삭제
        System.setProperty("spring.main.allow-bean-definition-overriding", "true");
        System.setProperty("spring.jackson.serialization.FAIL_ON_EMPTY_BEANS", "false");
        
        String profiles = serverProps.getProperty("spring.profiles.active");
        String loggingFileName = "logging-file.xml";
        
        if (StringUtils.isEmpty(profiles) || profiles.toLowerCase().contains("local")) {
            loggingFileName = "logging-console.xml";
        }

        List<String> argsList = new ArrayList<String>();
        argsList.add("--spring.banner.location=classpath:banner.txt");
        argsList.add("--spring.config.location=" + confServerFile.getAbsolutePath());
        argsList.add("--logging.config=classpath:" + loggingFileName);
        
        SpringApplication.run(ServerStarter.class, argsList.toArray(new String[argsList.size()]));
    }
}
