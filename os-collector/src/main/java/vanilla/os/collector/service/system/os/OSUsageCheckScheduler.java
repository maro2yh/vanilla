package vanilla.os.collector.service.system.os;

import java.util.concurrent.ScheduledFuture;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

@Service
@Slf4j
public class OSUsageCheckScheduler implements Runnable {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private ScheduledFuture<?> future;

    private boolean running = false;

    private SystemInfo si = new SystemInfo();
    private HardwareAbstractionLayer hw = si.getHardware();
    private CentralProcessor cpu = hw.getProcessor();
    long[] prevTicks = new long[TickType.values().length];

    public void start() {
        start(1000);
    }

    public void start(long period) {
        if (running) {
            log.warn("The 'OSUsageCheckScheduler' has been already running.");
            return;
        }

        future = threadPoolTaskScheduler.scheduleAtFixedRate(this, period);
        running = true;
        log.info("The 'OSUsageCheckScheduler' has been started.");
    }

    @PreDestroy
    public void stop() {
        if (running) {
            if (future != null) {
                future.cancel(false);
                future = null;
            }

            running = false;
            log.info("The 'OSUsageCheckScheduler' has been stopped.");
        }
    }

    @Override
    public void run() {
        double cpuLoad = cpu.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
        prevTicks = cpu.getSystemCpuLoadTicks();

        GlobalMemory memory = hw.getMemory();
        long memoryUsed = memory.getTotal() - memory.getAvailable();
        double memoryUsage = ((double) memoryUsed / memory.getTotal()) * 100;

        if (log.isDebugEnabled()) {
            StringBuffer logging = new StringBuffer();
            logging.append("[===== OS PERFORMENCE =====]").append("\n");
            logging.append("CPU    : ").append(String.format("%.2f", cpuLoad)).append("%").append("\n");
            logging.append("MEMORY : ").append(String.format("%.2f", memoryUsage)).append("%").append("\n");
//            logging.append("MEMORY TOTAL : ").append(String.format("%.2f", NumberUtils.toGB(memory.getTotal()))).append("GB").append("\n");

            log.debug(logging.toString());
        }
    }
}
