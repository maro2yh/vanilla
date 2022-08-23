package vanilla.stocks.html.naver;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

public class MarketSisePageTest {

    @Test
    public void testGet() {
        try {
            Map<String, Object> siseMap = new MarketSisePage().get("kospi");
            System.out.println(siseMap.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
