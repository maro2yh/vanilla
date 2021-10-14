package vanilla.stocks.html.naver;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import vanilla.stocks.common.util.VanillaStringUtils;

public class SearchResultPage {

    public List<Map<String, Object>> get(String searchText) throws IOException {
        try {
            List<Map<String, Object>> searchResults = new ArrayList<>();
            
            String encodedText = URLEncoder.encode(searchText, "euc-kr");
            String url = String.format(URLs.SEARCH, encodedText);
            Document doc = Jsoup.connect(url).get();
            
            Elements divEls = doc.select(".section_search");
            Element divEl = divEls.get(0);

            Element tableEl = divEl.child(0);
            Element tbodyEl = tableEl.child(3);
            Elements trEls = tbodyEl.select("tr");

            for (Element trEl : trEls) {
                if (trEl.childrenSize() != 8) {
                    continue;
                }

                Elements tdEls = trEl.select("td");

                Element linkEl = tdEls.get(0).select("a").get(0);
                String name = linkEl.text().trim();
                String href = linkEl.attr("href");
                int codeIndex = href.indexOf("code=");
                String code = href.substring((codeIndex + "code=".length())).trim();

                Element priceEl = tdEls.get(1);
                long price = VanillaStringUtils.toLong(priceEl.text().trim());

                Element changeEl = tdEls.get(2);
                long change = VanillaStringUtils.toLong(changeEl.text().trim());

                Element changeRatetEl = tdEls.get(3);
                float changeRate = VanillaStringUtils.toFloat(changeRatetEl.text().trim());

                Element volumeEl = tdEls.get(6);
                long volume = VanillaStringUtils.toLong(volumeEl.text().trim());
                
                Map<String, Object> map = new HashMap<>();
                map.put("name", name);
                map.put("code", code);
                map.put("price", price);
                map.put("change", change);
                map.put("changeRate", changeRate);
                map.put("volume", volume);
                searchResults.add(map);
            }
            
            return searchResults;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
