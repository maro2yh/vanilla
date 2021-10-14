package vanilla.stocks.html.naver;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import vanilla.stocks.common.util.VanillaCalendarUtils;

public class ItemInvestorDailyTrendPageTest {

    public void testGet1() {
        String startDate = VanillaCalendarUtils.now("yyyyMMdd");
        String endDate = VanillaCalendarUtils.amount(Calendar.DAY_OF_MONTH, -20, "yyyyMMdd");

        try {
            List<Map<String, Object>> list = new ItemInvestorDailyTrendPage().get("051910", startDate, endDate);

            for (Map<String, Object> map : list) {
                System.out.println(map.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
    
    @Test
    public void testGet2() {
        try {
            List<Map<String, Object>> list = new ItemInvestorDailyTrendPage().get("051910", 20);

            for (Map<String, Object> map : list) {
                System.out.println(map.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
