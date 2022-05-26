package vanilla.os.collector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Bootstrap {

    public static void initializer() {
        System.setProperty("name", "Vanilla OS Collector");
        System.setProperty("version", "0.0.1");

        String home = System.getProperty("home");

        if (StringUtils.isEmpty(home)) {
            home = new File(".").getAbsolutePath();

            if (home.endsWith(".")) {
                home = home.substring(0, home.length() - 1);
            }

            System.setProperty("home", home);
        } else {
            File homeDir = new File(home);

            if (!homeDir.isDirectory()) {
                throw new RuntimeException("The home directory setting is incorrect.");
            }
        }

        String logPath = System.getProperty("log.path");

        if (StringUtils.isEmpty(logPath)) {
            logPath = home + "/logs";
            System.setProperty("log.path", logPath);
        }

        File logDir = new File(logPath);

        if (!logDir.isDirectory()) {
            logDir.mkdirs();
        }

        String logFileName = System.getProperty("log.filename");

        if (StringUtils.isEmpty(logFileName)) {
            logFileName = "os-collector.log";
            System.setProperty("log.filename", logFileName);
        }

        String logLevel = System.getProperty("log.level");

        if (StringUtils.isEmpty(logLevel)) {
            logLevel = "INFO";
            System.setProperty("log.level", logLevel);
        } else {
            if (!logLevel.equalsIgnoreCase("DEBUG") && !logLevel.equalsIgnoreCase("INFO") && !logLevel.equalsIgnoreCase("WARN") && !logLevel.equals("ERROR")) {
                throw new RuntimeException("The log level setting is incorrect.");
            }
        }

        String logMaxHistory = System.getProperty("log.max-history");

        if (StringUtils.isEmpty(logMaxHistory)) {
            logMaxHistory = "30";
            System.setProperty("log.max-history", logMaxHistory);
        } else {
            try {
                int history = Integer.parseInt(logMaxHistory);
                
                if (history < 0) {
                    throw new RuntimeException("The log max-history setting is incorrect.");
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException("The log max-history setting is incorrect.");
            }
        }

        String logMaxSize = System.getProperty("log.max-size");

        if (StringUtils.isEmpty(logMaxSize)) {
            logMaxSize = "300MB";
            System.setProperty("log.max-size", logMaxSize);
        } else {
            try {
                int size = Integer.parseInt(logMaxSize);
                
                if (size < 100) {
                    throw new RuntimeException("The log max-size setting is incorrect.");
                }
                
                System.setProperty("log.max-size", logMaxSize + "MB");
            } catch (NumberFormatException e) {
                throw new RuntimeException("The log max-size setting is incorrect.");
            }
        }

        String logMode = System.getProperty("log.mode");

        if (StringUtils.isEmpty(logMode)) {
            logMode = "file";
            System.setProperty("log.mode", logMode);
        } else {
            if (!logMode.equalsIgnoreCase("CONSOLE") && !logMode.equalsIgnoreCase("FILE")) {
                throw new RuntimeException("The log mode setting is incorrect.");
            }
        }

        if (logLevel.equalsIgnoreCase("DEBUG")) {
            System.setProperty("log.sql", "INFO");
        } else {
            System.setProperty("log.sql", "ERROR");
        }
        
        System.setProperty("spring.main.allow-bean-definition-overriding", "true");
        System.setProperty("spring.jackson.serialization.FAIL_ON_EMPTY_BEANS", "false");
//        System.setProperty("spring.main.banner-mode", "off");
    }

    public static void main(String[] args) throws Exception {
        Bootstrap.initializer();
        
        String home = System.getProperty("home");
        String logMode = System.getProperty("log.mode");
        String loggingFile = "logging-file.xml";
        
        if (logMode.equalsIgnoreCase("console")) {
            loggingFile = "logging-console.xml";
        }
        
        List<String> argList = new ArrayList<String>();
        argList.add("--spring.banner.location=file:" + home + "banner.txt");
        argList.add("--logging.config=classpath:" + loggingFile);
        
        ConfigurableApplicationContext context = SpringApplication.run(Bootstrap.class, argList.toArray(new String[argList.size()]));
//        context.getBean(SystemInfoCollectService.class).getSystem().toPrint();
//        context.getBean(ApplicationPerformanceScheduler.class).start();
    }
}
