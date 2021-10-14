package vanilla.stocks.html.naver;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

public class MarketSisePageTest {

    @Test
    public void testGet() {
        try {
            Map<String, Map<String, Object>> market = new MarketSisePage().get();
            Map<String, Object> kospi = market.get("kospi");
            Map<String, Object> kosdaq = market.get("kosdaq");
            
            System.out.println("kospi " + kospi.toString());
            System.out.println("kosdaq " + kosdaq.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
