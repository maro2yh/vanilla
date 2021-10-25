package vanilla.stocks.scheduler.market.status;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import vanilla.stocks.html.naver.MarketSisePage;
import vanilla.stocks.scheduler.system.error.SystemError;
import vanilla.stocks.scheduler.system.error.SystemErrorRepository;

@Slf4j
@Component
public class MarketStatusTask {
    
    @Autowired
    private MarketStatusRepository marketSiseRepository;
    
    @Autowired
    private SystemErrorRepository systemErrorRepository;

    @Scheduled(cron = "0 0/5 9-15 * * *")
    public void execute() {
        log.debug("Start the 'MarketStatus' scheduler.");
        
        Map<String, Map<String, Object>> sise = null;
        
        try {
            sise = new MarketSisePage().get();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            systemErrorRepository.save(new SystemError(e.getMessage()));
            return;
        }
        
        Map<String, Object> kospi = sise.get("kospi");
        Map<String, Object> kosdaq = sise.get("kosdaq");
        
        marketSiseRepository.save(new MarketStatus("KOSPI", kospi));
        marketSiseRepository.save(new MarketStatus("KOSDAQ", kosdaq));
        
        log.debug("Completed the 'MarketStatus' scheduler.");
    }
}
