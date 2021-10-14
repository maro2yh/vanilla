package vanilla.stocks.html.naver;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class ItemTotalInfoPageTest {

    @Test
    public void testGet() {
        try {
            Map<String, Object> totalMap = new ItemTotalInfoPage().get("051910");
            
            String summary = (String) totalMap.get("summary");
            System.out.println("기업개요 \n" + summary);
            
            Map<String, Object> siseMap = (Map) totalMap.get("sise");
            System.out.println("시세 : " + siseMap.toString());
            
            System.out.println("");
            
            Map<String, Object> investmentInfoMap = (Map) totalMap.get("investmentInfo");
            System.out.println("투자정보 : " + investmentInfoMap.toString());
            
            Map<String, Object> corporateEarnings = (Map) totalMap.get("corporateEarnings");
            List<Map<String, Object>> year = (List) corporateEarnings.get("year");
            List<Map<String, Object>> quater = (List) corporateEarnings.get("quater");
            
            System.out.println("");
            System.out.println("연간 실적");
            for (Map<String, Object> map : year) {
                System.out.println(map.toString());
            }
            
            System.out.println("분기 실적");
            for (Map<String, Object> map : quater) {
                System.out.println(map.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
