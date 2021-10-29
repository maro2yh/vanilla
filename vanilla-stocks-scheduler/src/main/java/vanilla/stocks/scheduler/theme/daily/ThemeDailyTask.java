package vanilla.stocks.scheduler.theme.daily;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import vanilla.commons.util.calendar.VanillaCalendarUtils;
import vanilla.stocks.html.naver.ThemePage;
import vanilla.stocks.scheduler.system.error.SystemError;
import vanilla.stocks.scheduler.system.error.SystemErrorRepository;
import vanilla.stocks.scheduler.task.IVanillaSchedule;
import vanilla.stocks.scheduler.task.VanillaScheduleScan;

@Slf4j
@Component
@VanillaScheduleScan(name = "ThemeDaily", type = "cron", cron = "0 0 16 * * *")
public class ThemeDailyTask implements Runnable, IVanillaSchedule {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private ScheduledFuture<?> scheduledFuture;
    
    private boolean running;
    
    @Autowired
    private ThemeDailyRepository themeDailyRepository;
    
    @Autowired
    private SystemErrorRepository systemErrorRepository;
    
    @Override
    public void start(int time) {
        if (running && scheduledFuture != null) {
            log.warn("The scheduler was already started.");
            return;
        }
        
        scheduledFuture = threadPoolTaskScheduler.scheduleAtFixedRate(this, (time * 1000));
        this.running = true;
        log.info("The scheduler has been started with {} seconds period.", (time * 1000));
    }
    
    @Override
    public void start(String cron) {
        if (running && scheduledFuture != null) {
            log.warn("The scheduler was already started.");
            return;
        }
        
        scheduledFuture = threadPoolTaskScheduler.schedule(this, new CronTrigger(cron));
        this.running = true;
        log.info("The scheduler has been started with {} cron expression.", cron);
    }

    @Override
    public void stop() {
        if (running) {
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
                scheduledFuture = null;
            }

            running = false;
            log.info("The scheduler has been stopped.");
        }
    }
    
    @Override
    public boolean isRunning() {
        return running;
    }
    
    @Override
    public void run() {
        log.debug("Start the 'ThemeDaily' scheduler.");
        
        String date = VanillaCalendarUtils.now("yyyyMMdd");
        List<Map<String, Object>> list = null;
        
        try {
            list = new ThemePage().getList();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            systemErrorRepository.save(new SystemError(e.getMessage()));
            return;
        }
        
        for (Map<String, Object> map : list) {
            themeDailyRepository.save(new ThemeDaily(date, map));
        }
        
        log.debug("Completed the 'ThemeDaily' scheduler.");
    }
}
