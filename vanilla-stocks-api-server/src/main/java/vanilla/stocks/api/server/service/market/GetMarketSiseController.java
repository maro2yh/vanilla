package vanilla.stocks.api.server.service.market;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vanilla.commons.util.calendar.VanillaCalendarUtils;
import vanilla.stocks.html.naver.MarketSisePage;

@RestController
public class GetMarketSiseController {
    
    @Autowired
    private GetMarketDailyService getMarketDailyService;

    @GetMapping("/market/sise")
    public Object getSise(@RequestParam(value = "code", required = true) String code) throws IOException {
        return new MarketSisePage().get(code);
    }

    @GetMapping("/market/daily")
    public Object getDaily(@RequestParam(value = "code", required = true) String code, @RequestParam(value = "searchFromDate", required = true) String searchFromDate,
            @RequestParam(value = "searchToDate", required = true) String searchToDate) throws IOException {
        List<MarketDaily> list = getMarketDailyService.getData(code, searchFromDate, searchToDate);
        
        if (!list.isEmpty()) {
            MarketDaily daily = list.get(list.size() - 1);
            String today = VanillaCalendarUtils.now("yyyyMMdd");
            
            if (today.equals(daily.getDate())) {
                getMarketDailyService.updateTodayData(code);
            }
        }
        
        return list;
    }
}
