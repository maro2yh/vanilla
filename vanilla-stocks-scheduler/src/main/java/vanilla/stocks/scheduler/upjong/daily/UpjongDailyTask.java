package vanilla.stocks.scheduler.upjong.daily;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import vanilla.commons.util.calendar.VanillaCalendarUtils;
import vanilla.stocks.html.naver.UpjongPage;
import vanilla.stocks.scheduler.system.error.SystemError;
import vanilla.stocks.scheduler.system.error.SystemErrorRepository;

@Slf4j
@Component
public class UpjongDailyTask {

    @Autowired
    private UpjongDailyRepository upjongDailyRepository;
    
    @Autowired
    private SystemErrorRepository systemErrorRepository;

    @Scheduled(cron = "0 0 16 * * *")
    public void execute() {
        log.debug("Start the 'UpjongDaily' scheduler.");

        String date = VanillaCalendarUtils.now("yyyyMMdd");
        List<Map<String, Object>> list = null;

        try {
            list = new UpjongPage().getList();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            systemErrorRepository.save(new SystemError(e.getMessage()));
            return;
        }
        
        for (Map<String, Object> map : list) {
            upjongDailyRepository.save(new UpjongDaily(date, map));
        }

        log.debug("Completed the 'UpjongDaily' scheduler.");
    }
}
