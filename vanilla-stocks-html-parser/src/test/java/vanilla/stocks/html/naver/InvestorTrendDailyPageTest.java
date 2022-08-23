package vanilla.stocks.html.naver;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import vanilla.commons.util.calendar.VanillaCalendarUtils;

public class InvestorTrendDailyPageTest {

    @Test
    public void testGetList() {
        String startDate = VanillaCalendarUtils.now("yyyyMMdd");
        String endDate = VanillaCalendarUtils.amount(Calendar.DAY_OF_MONTH, -30, "yyyyMMdd");
        
        try {
            List<Map<String, Object>> list = new InvestorTrendDailyPage().get("KOSPI", startDate, endDate);
            
            for (Map<String, Object> map : list) {
                System.out.println(map.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
