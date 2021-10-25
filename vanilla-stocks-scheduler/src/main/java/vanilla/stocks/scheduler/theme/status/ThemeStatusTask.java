package vanilla.stocks.scheduler.theme.status;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import vanilla.commons.util.calendar.VanillaCalendarUtils;
import vanilla.stocks.html.naver.ThemePage;
import vanilla.stocks.scheduler.system.error.SystemError;
import vanilla.stocks.scheduler.system.error.SystemErrorRepository;

@Slf4j
@Component
public class ThemeStatusTask {
    
    @Autowired
    private ThemeStatusRepository themeStatusRepository;
    
    @Autowired
    private ThemeStatusSqlMapper themeStatusSqlMapper;
    
    @Autowired
    private SystemErrorRepository systemErrorRepository;

    @Scheduled(cron = "0 0/5 9-15 * * *")
    public void execute() {
        log.debug("Start the 'ThemeStatus' scheduler.");
        
        String updateDate = VanillaCalendarUtils.now("yyyyMMdd");
        List<Map<String, Object>> list = null;
        
        try {
            list = new ThemePage().getList();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            systemErrorRepository.save(new SystemError(e.getMessage()));
            return;
        }
        
        for (Map<String, Object> map : list) {
            themeStatusRepository.save(new ThemeStatus(map, updateDate));
        }
        
        themeStatusSqlMapper.deleteNotUpdated(updateDate);
        
        log.debug("Completed the 'ThemeStatus' scheduler.");
    }
}
