package vanilla.stocks.html.naver;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class InvestorRankPageTest {

    @Test
    public void testGetList() {
        try {
            List<Map<String, Object>> list = new InvestorRankPage().getList("kospi", "foreigner", "buy");
            System.out.println("===== 외국인 순매수 (KOSPI) =====");
            for (Map<String, Object> map : list) {
                System.out.println(map.toString());
            }
            
            list = new InvestorRankPage().getList("kospi", "foreigner", "sell");
            System.out.println("===== 외국인 순매도 (KOSPI) =====");
            for (Map<String, Object> map : list) {
                System.out.println(map.toString());
            }
            
            list = new InvestorRankPage().getList("kosdaq", "foreigner", "buy");
            System.out.println("===== 외국인 순매수 (KOSDAQ) =====");
            for (Map<String, Object> map : list) {
                System.out.println(map.toString());
            }
            
            list = new InvestorRankPage().getList("kosdaq", "foreigner", "sell");
            System.out.println("===== 외국인 순매도 (KOSDAQ) =====");
            for (Map<String, Object> map : list) {
                System.out.println(map.toString());
            }
            
            list = new InvestorRankPage().getList("kospi", "agency", "buy");
            System.out.println("===== 기관 순매수 (KOSPI) =====");
            for (Map<String, Object> map : list) {
                System.out.println(map.toString());
            }
            
            list = new InvestorRankPage().getList("kospi", "agency", "sell");
            System.out.println("===== 기관 순매도 (KOSPI) =====");
            for (Map<String, Object> map : list) {
                System.out.println(map.toString());
            }
            
            list = new InvestorRankPage().getList("kosdaq", "agency", "buy");
            System.out.println("===== 기관 순매수 (KOSDAQ) =====");
            for (Map<String, Object> map : list) {
                System.out.println(map.toString());
            }
            
            list = new InvestorRankPage().getList("kosdaq", "agency", "sell");
            System.out.println("===== 기관 순매도 (KOSDAQ) =====");
            for (Map<String, Object> map : list) {
                System.out.println(map.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
