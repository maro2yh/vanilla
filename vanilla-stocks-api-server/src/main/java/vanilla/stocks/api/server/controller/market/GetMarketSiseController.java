package vanilla.stocks.api.server.controller.market;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vanilla.stocks.html.naver.MarketSisePage;

@RestController
public class GetMarketSiseController {

    @GetMapping("/market/sise")
    public Object get() throws IOException {
        return new MarketSisePage().get();
    }
}
