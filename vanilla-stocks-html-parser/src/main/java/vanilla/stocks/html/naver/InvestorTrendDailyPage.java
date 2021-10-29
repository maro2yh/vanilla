package vanilla.stocks.html.naver;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import vanilla.stocks.common.util.VanillaCalendarUtils;
import vanilla.stocks.common.util.VanillaStringUtils;

public class InvestorTrendDailyPage {

    public List<Map<String, Object>> get(String market, String startDate, String endDate) throws IOException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        String sosok = market.equalsIgnoreCase("kospi") ? "01" : "02";
        long startDateL = VanillaStringUtils.toLong(startDate);
        long endDateL = VanillaStringUtils.toLong(endDate);
        int page = 1;
        boolean isEnd = false;

        do {
            String url = String.format(URLs.INVESTOR_TREND_DAILY, startDate, sosok, page);
            Document doc = Jsoup.connect(url).get();
            Element tableEl = doc.select(".type_1").get(0);
            Elements trEls = tableEl.select("tr");

            for (int i = 3; i < trEls.size(); i++) {
                Elements tdEls = trEls.get(i).select("td");

                if (tdEls == null || tdEls.size() == 0 || tdEls.size() != 11) {
                    continue;
                }

                String date = tdEls.get(0).text();
                
                try {
                    date = VanillaCalendarUtils.changeFormat(date, "yy.MM.dd", "yyyyMMdd");
                } catch (ParseException e) {
                    throw new IOException(e);
                }
                
                long dateL = VanillaStringUtils.toLong(date);
                
                if (dateL > startDateL) {
                    continue;
                }
                
                if (dateL < endDateL) {
                    isEnd = true;
                    break;
                }
                
                long investor1 = VanillaStringUtils.toLong(tdEls.get(1).text());
                long investor2 = VanillaStringUtils.toLong(tdEls.get(2).text());
                long investor3 = VanillaStringUtils.toLong(tdEls.get(3).text());
                long investor4 = VanillaStringUtils.toLong(tdEls.get(4).text());
                long investor5 = VanillaStringUtils.toLong(tdEls.get(5).text());
                long investor6 = VanillaStringUtils.toLong(tdEls.get(6).text());
                long investor7 = VanillaStringUtils.toLong(tdEls.get(7).text());
                long investor8 = VanillaStringUtils.toLong(tdEls.get(8).text());
                long investor9 = VanillaStringUtils.toLong(tdEls.get(9).text());

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("date", date);
                map.put("market", market);
                map.put("personal", investor1); // 개인
                map.put("foreginer", investor2); // 외국인
                map.put("agency", investor3); // 기관
                map.put("investment_company", investor4); // 금융투자
                map.put("insurance_company", investor5); // 보험
                map.put("private_equity", investor6); //사모펀드
                map.put("bank", investor7); // 은행
                map.put("etc_institution", investor8); // 기타 기관
                map.put("pension_fund", investor9); // 연기금등

                list.add(map);
            }
            
            page++;
        } while (!isEnd);

        return list;
    }
}
