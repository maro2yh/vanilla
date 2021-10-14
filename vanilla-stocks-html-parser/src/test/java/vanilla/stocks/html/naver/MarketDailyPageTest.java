package vanilla.stocks.html.naver;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import vanilla.stocks.common.util.VanillaCalendarUtils;

public class MarketDailyPageTest {

    @Test
    public void testGet() {
        String startDate = VanillaCalendarUtils.now("yyyyMMdd");
        String endDate = VanillaCalendarUtils.amount(Calendar.DAY_OF_MONTH, -30, "yyyyMMdd");

        try {
            List<Map<String, Object>> list = new MarketDailyPage().get("kospi", startDate, endDate);

            for (Map<String, Object> map : list) {
                System.out.println(map.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
        
        try {
            List<Map<String, Object>> list = new MarketDailyPage().get("kosdaq", startDate, endDate);

            for (Map<String, Object> map : list) {
                System.out.println(map.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
