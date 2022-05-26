package vanilla.os.collector.service.application.performance;

import java.util.concurrent.ScheduledFuture;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.software.os.OSProcess;
import vanilla.os.collector.service.application.info.ApplicationInfo;

@Service
@Slf4j
public class ApplicationPerformanceScheduler implements Runnable {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private ScheduledFuture<?> future;
    
    @Autowired
    private ApplicationInfo applicationInfo;
    
    private boolean running = false;
    
    public void start() {
        start(1000);
    }

    public void start(long period) {
        if (running) {
            log.warn("The 'ApplicationPerformanceScheduler' has been already running.");
            return;
        }
        
        future = threadPoolTaskScheduler.scheduleAtFixedRate(this, period);
        running = true;
        log.info("The 'ApplicationPerformanceScheduler' has been started.");
    }

    @PreDestroy
    public void stop() {
        if (running) {
            if (future != null) {
                future.cancel(false);
                future = null;
            }

            running = false;
            log.info("The 'ApplicationPerformanceScheduler' has been stopped.");
        }
    }
    
    @Override
    public void run() {
        OSProcess process = new SystemInfo().getOperatingSystem().getProcess(applicationInfo.getPid());
        double cpu = process.getProcessCpuLoadCumulative();
        long usedMemory = process.getResidentSetSize() + process.getVirtualSize();

        if (log.isDebugEnabled()) {
            StringBuffer logging = new StringBuffer();
            logging.append("[===== APPLICATION PERFORMANCE =====]").append("\n");
            logging.append("CPU              : ").append(cpu).append("\n");
            logging.append("MEMORY USED      : ").append(usedMemory).append("\n");
            
            log.debug(logging.toString());
        }
    }
}
