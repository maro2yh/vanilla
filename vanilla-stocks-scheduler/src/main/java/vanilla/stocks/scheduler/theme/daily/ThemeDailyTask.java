package vanilla.stocks.scheduler.theme.daily;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import vanilla.commons.util.calendar.VanillaCalendarUtils;
import vanilla.stocks.scheduler.theme.status.ThemeStatus;
import vanilla.stocks.scheduler.theme.status.ThemeStatusRepository;

@Slf4j
@Component
public class ThemeDailyTask {

    @Autowired
    private ThemeStatusRepository themeStatusRepository;
    
    @Autowired
    private ThemeDailyRepository themeDailyRepository;
    
    @Scheduled(cron = "0 0 16 * * *")
    public void execute() {
        log.debug("Start the 'ThemeDaily' scheduler.");
        
        String createdDate = VanillaCalendarUtils.now("yyyyMMdd");
        List<ThemeStatus> list = themeStatusRepository.findAll();
        
        for (ThemeStatus themeStatus : list) {
            themeDailyRepository.save(new ThemeDaily(createdDate, themeStatus));
        }
        
        log.debug("Completed the 'ThemeDaily' scheduler.");
    }
}
