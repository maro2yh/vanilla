package vanilla.stocks.scheduler.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import vanilla.commons.util.calendar.VanillaCalendarUtils;
import vanilla.stocks.scheduler.db.upjong.daily.UpjongDaily;
import vanilla.stocks.scheduler.db.upjong.daily.UpjongDailyRepository;
import vanilla.stocks.scheduler.db.upjong.status.UpjongStatus;
import vanilla.stocks.scheduler.db.upjong.status.UpjongStatusRepository;

@Slf4j
@Component
public class UpjongDailyTask {
    
    @Autowired
    private UpjongStatusRepository upjongStatusRepository;
    
    @Autowired
    private UpjongDailyRepository upjongDailyRepository;

    @Scheduled(cron = "0 5 16 * * *")
    public void execute() {
        log.debug("Start the 'UpjongDaily' scheduler.");
        
        String createdDate = VanillaCalendarUtils.now("yyyyMMdd");
        List<UpjongStatus> list = upjongStatusRepository.findAll();
        
        for (UpjongStatus upjongStatus : list) {
            upjongDailyRepository.save(new UpjongDaily(createdDate, upjongStatus));
        }
        
        log.debug("Completed the 'UpjongDaily' scheduler.");
    }
}
