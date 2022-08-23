package vanilla.stocks.html.naver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import vanilla.commons.util.string.VanillaStringUtils;

public class InvestorRankPage {
    
    public List<Map<String, Object>> getList(String market, String investor, String type) throws IOException {
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        
        String sosok = market.equalsIgnoreCase("kospi") ? "01" : "02";
        String investorGubun = investor.equalsIgnoreCase("foreigner") ? "9000" : "1000";
        String url = String.format(URLs.INVESTOR_TOP, sosok, investorGubun, type);
        
        Document doc = Jsoup.connect(url).get();
        Elements dateEls = doc.select(".sise_guide_date");
//        String date1 = dateEls.get(0).text().replaceAll("\\.", "");
        Elements tableEls = doc.select("table");
//        Element tableEl = tableEls.get(1);
//        Elements trEls = tableEl.select("tr");
//        int rank = 0;
//
//        for (int i = 2; i < trEls.size(); i++) {
//            Elements tdEls = trEls.get(i).select("td");
//
//            if (tdEls.size() != 4) {
//                continue;
//            }
//
//            Element linkEl = tdEls.get(0).select("a").get(0);
//            String name = linkEl.text().trim();
//            String href = linkEl.attr("href");
//            int codeIndex = href.indexOf("code=");
//            String code = href.substring((codeIndex + "code=".length())).trim();
//
//            long trade = VanillaStringUtils.toLong(tdEls.get(1).text());
//            long amount = VanillaStringUtils.toLong(tdEls.get(2).text());
//            long volume = VanillaStringUtils.toLong(tdEls.get(3).text());
//
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("date", date1);
//            map.put("rank", ++rank);
//            map.put("name", name);
//            map.put("code", code);
//            map.put("trade", trade);
//            map.put("amount", amount);
//            map.put("volume", volume);
//            
//            list.add(map);
//        }
        
        String date2 = dateEls.get(1).text().replaceAll("\\.", "");
        Element tableEl = tableEls.get(3);
        Elements trEls = tableEl.select("tr");
        int rank = 0;

        for (int i = 2; i < trEls.size(); i++) {
            Elements tdEls = trEls.get(i).select("td");

            if (tdEls.size() != 4) {
                continue;
            }

            Element linkEl = tdEls.get(0).select("a").get(0);
            String name = linkEl.text().trim();
            String href = linkEl.attr("href");
            int codeIndex = href.indexOf("code=");
            String code = href.substring((codeIndex + "code=".length())).trim();

            long trade = VanillaStringUtils.toLong(tdEls.get(1).text());
            long amount = VanillaStringUtils.toLong(tdEls.get(2).text());
            long volume = VanillaStringUtils.toLong(tdEls.get(3).text());

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("date", date2);
            map.put("rank", ++rank);
            map.put("name", name);
            map.put("code", code);
            map.put("trade", trade);
            map.put("amount", amount);
            map.put("volume", volume);
            
            list.add(map);
        }
        
        return list;
    }
}
