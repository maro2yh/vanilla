package vanilla.stocks.scheduler.investor.daily;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import vanilla.commons.util.calendar.VanillaCalendarUtils;
import vanilla.stocks.html.naver.InvestorTrendDailyPage;
import vanilla.stocks.scheduler.task.IVanillaSchedule;
import vanilla.stocks.scheduler.task.VanillaScheduleScan;

@Slf4j
@Component
@VanillaScheduleScan(name = "InvestorDaily", type = "cron", cron = "0 0 16 * * *", startUpRun = true)
public class InvestorDailyTask implements Runnable, IVanillaSchedule {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private ScheduledFuture<?> scheduledFuture;
    
    private boolean running;
    
    @Autowired
    private InvestorDailyRepository investorDailyRepository;
    
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
        log.debug("Start the 'InvestorDaily' scheduler.");
        
        String startDate = VanillaCalendarUtils.now("yyyyMMdd");
        String endDate = VanillaCalendarUtils.amount(Calendar.DAY_OF_MONTH, -20, "yyyyMMdd");
        
        if (investorDailyRepository.countByDateAndMarket(startDate, "KOSPI") > 0) {
            endDate = VanillaCalendarUtils.amount(Calendar.DAY_OF_MONTH, -1, "yyyyMMdd");
        }
        
        List<Map<String, Object>> list = null;
        
        try {
            list = new InvestorTrendDailyPage().get("KOSPI", startDate, endDate);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return;
        }
        
        for (Map<String, Object> map : list) {
            investorDailyRepository.save(new InvestorDaily(map));
        }
        
        list = null;
        endDate = VanillaCalendarUtils.amount(Calendar.DAY_OF_MONTH, -20, "yyyyMMdd");
        
        if (investorDailyRepository.countByDateAndMarket(startDate, "KOSDAQ") > 0) {
            endDate = VanillaCalendarUtils.amount(Calendar.DAY_OF_MONTH, -1, "yyyyMMdd");
        }
        
        try {
            list = new InvestorTrendDailyPage().get("KOSDAQ", startDate, endDate);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return;
        }
        
        for (Map<String, Object> map : list) {
            investorDailyRepository.save(new InvestorDaily(map));
        }
        
        log.debug("Completed the 'InvestorDaily' scheduler.");
    }
}
