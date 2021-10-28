package vanilla.stocks.api.server.service.market;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vanilla.stocks.html.naver.MarketSisePage;

@RestController
public class GetMarketSiseController {
    
    @Autowired
    private GetMarketDailyService getMarketDailyService;

    @GetMapping("/market/sise")
    public Object get() throws IOException {
        return new MarketSisePage().get();
    }

    @GetMapping("/market/daily")
    public Object getDaily(@RequestParam(value = "market", required = true) String market, @RequestParam(value = "searchFromDate", required = true) String searchFromDate,
            @RequestParam(value = "searchToDate", required = true) String searchToDate) throws IOException {
        return getMarketDailyService.getData(market, searchFromDate, searchToDate);
    }
}