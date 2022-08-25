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

import vanilla.commons.util.calendar.VanillaCalendarUtils;
import vanilla.commons.util.string.VanillaStringUtils;

public class MarketDailyPage {

    public List<Map<String, Object>> get(String market, String startDate, String endDate) throws IOException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        long startDateL = VanillaStringUtils.toLong(startDate);
        long endDateL = VanillaStringUtils.toLong(endDate);
        int page = 1;
        boolean isEnd = false;

        do {
            String url = String.format(URLs.MARKET_DAILY, market.toUpperCase(), page);
            Document doc = Jsoup.connect(url).get();
            Elements tableEls = doc.select("table");
            Element tableEl = tableEls.get(0);
            Elements trEls = tableEl.select("tr");

            for (int i = 2; i < trEls.size(); i++) {
                Element trEl = trEls.get(i);
                Elements tdEls = trEl.select("td");

                if (tdEls.size() != 6) {
                    continue;
                }

                String date = tdEls.get(0).text().trim();

                try {
                    date = VanillaCalendarUtils.changeFormat(date, "yyyy.MM.dd", "yyyyMMdd");
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

                float jisu = VanillaStringUtils.toFloat(tdEls.get(1).text());
                float change = VanillaStringUtils.toFloat(tdEls.get(2).text());
                float changeRate = VanillaStringUtils.toFloat(tdEls.get(3).text());
                long volume = VanillaStringUtils.toLong(tdEls.get(4).text());
                long price = VanillaStringUtils.toLong(tdEls.get(5).text());

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("date", date);
                map.put("market", market.toUpperCase());
                map.put("jisu", jisu);
                map.put("changeValue", change);
                map.put("changeRate", changeRate);
                map.put("tradeVolume", volume);
                map.put("tradePrice", price);

                list.add(map);
            }
            
            page++;
        } while (!isEnd);

        return list;
    }
}
