package vanilla.os.collector.service.application.info;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CreateApplicationInfoConfig {
    
    @Autowired
    private ApplicationIDJpa applicationIDJpa;
    
    @Autowired
    private ApplicationInfoJpa applicationInfoJpa;
    
    @Bean(name = "applicationID")
    public ApplicationID applicationID() {
        ApplicationID applicationID = null;
        
        if (applicationIDJpa.count() <= 0) {
            applicationID = new ApplicationID(UUID.randomUUID().toString(), System.currentTimeMillis());
            applicationIDJpa.save(applicationID);
            log.info("Create a new application id. [{}]", applicationID.getId());
        } else {
            applicationID = applicationIDJpa.findAll().get(0);
        }
        
        return applicationID;
    }

    @Bean
    public ApplicationInfo applicationInfo(@Qualifier("applicationID") ApplicationID applicationID) {
        ApplicationInfo info = new ApplicationInfo();
        
        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
        String jvmName = bean.getName();
        int pid = Integer.parseInt(jvmName.split("@")[0]);
        
        InetAddress local = null;
        String localAddress = "";
        
        try {
            local = InetAddress.getLocalHost();
            localAddress = local.getHostAddress();
        } catch (UnknownHostException e) {
            localAddress = "unknown";
        }
        
        info.setPid(pid);
        info.setStartTimestamp(System.currentTimeMillis());
        info.setName(System.getProperty("name"));
        info.setVersion(System.getProperty("version"));
        info.setLocalAddress(localAddress);
        info.setHome(System.getProperty("home"));
        info.setLogPath(System.getProperty("log.path"));
        info.setLogFilename(System.getProperty("log.filename"));
        info.setLogLevel(System.getProperty("log.level"));
        info.setLogMaxHistory(System.getProperty("log.max-history"));
        info.setLogMaxSize(System.getProperty("log.max-size"));
        info.setLogMode(System.getProperty("log.mode"));
        info.setJavaVersion(SystemUtils.JAVA_VERSION);
        
        applicationInfoJpa.save(info);
        
        StringBuffer logging = new StringBuffer();
        logging.append("[===== APPLICATION INFORMATION =====]").append("\n");
        logging.append("ID              : ").append(applicationID.getId()).append("\n");
        logging.append("NAME            : ").append(info.getName()).append("\n");
        logging.append("VERSION         : ").append(info.getVersion()).append("\n");
        logging.append("PID             : ").append(info.getPid()).append("\n");
        logging.append("HOME            : ").append(info.getHome()).append("\n");
        logging.append("LOCAL ADDRESS   : ").append(info.getLocalAddress()).append("\n");
        logging.append("LOG FILE        : ").append(info.getLogPath()).append(System.getProperty("file.separator")).append(info.getLogFilename()).append("\n");
        logging.append("LOG LEVEL       : ").append(info.getLogLevel()).append("\n");
        logging.append("LOG MAX HISTORY : ").append(info.getLogMaxHistory()).append("\n");
        logging.append("LOG MAX SIZE    : ").append(info.getLogMaxSize()).append("\n");
        logging.append("JAVA VERSION    : ").append(info.getJavaVersion()).append("\n");
        
        log.info(logging.toString());
        
        return info;
    }
}
