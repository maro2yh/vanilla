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

import vanilla.stocks.common.util.VanillaStringUtils;

public class ThemePage {

    public List<Map<String, Object>> getList() throws IOException {
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        int page = 1;
        
        do {
            String url = String.format(URLs.THEME_LIST, page);
            Document doc = Jsoup.connect(url).get();
            Elements divEls = doc.select("#contentarea_left");
            
            if (divEls == null || divEls.size() == 0) {
                break;
            }
            
            Element divEl = divEls.get(0);
            Element tableEl = divEl.child(2);
            
            if (tableEl == null) {
                break;
            }
            
            Elements trEls = tableEl.select("tr");
            
            if (trEls == null || trEls.size() == 0) {
                break;
            }

            for (Element trEl : trEls) {
                if (trEl.childrenSize() != 8) {
                    continue;
                }

                Element nameEl = trEl.child(0);

                Element nameLinkEl = nameEl.child(0);
                String name = nameLinkEl.text().trim();
                String href = nameLinkEl.attr("href");
                int noIndex = href.indexOf("&no=");
                String no = href.substring((noIndex + "&no=".length())).trim();

                Element rateEl = trEl.child(1);
                Float changeRate = VanillaStringUtils.toFloat(rateEl.text().trim());
                
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("no", no);
                map.put("name", name);
                map.put("changeRate", changeRate);
                
                list.add(map);
            }
            
            if (trEls.size() < 40) {
                break;
            }
            
            page++;
        } while (true);
        
        return list;
    }
    
    public List<Map<String, Object>> getItems(String no) throws IOException {
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        String url = String.format(URLs.THEME_ITEMS, no);
        Document doc = Jsoup.connect(url).get();
        
        Elements divEls = doc.select(".box_type_l");
        Element divEl = divEls.get(1);
        Element tableEl = divEl.child(0);
        Element tbodyEl = tableEl.child(3);
        Elements trEls = tbodyEl.select("tr");
        
        for (Element trEl : trEls) {
            Elements tdEls = trEl.select("td");
            
            if (tdEls.size() != 11) {
                continue;
            }
            
            Element linkEl = tdEls.get(0).select("a").get(0);
            String name = linkEl.text().trim();
            String href = linkEl.attr("href");
            int codeIndex = href.indexOf("code=");
            String code = href.substring((codeIndex + "code=".length())).trim();
            
            Element priceEl = tdEls.get(2);
            long price = VanillaStringUtils.toLong(priceEl.text().trim());
            
            Element changeEl = tdEls.get(3);
            long change = VanillaStringUtils.toLong(changeEl.text().trim());
            
            Element changeRateEl = tdEls.get(4);
            float changeRate = VanillaStringUtils.toFloat(changeRateEl.text().trim());
            
            Element volumeEl = tdEls.get(7);
            long volume = VanillaStringUtils.toLong(volumeEl.text().trim());
            
            Element previousVolumeEl = tdEls.get(9);
            long previousVolume = VanillaStringUtils.toLong(previousVolumeEl.text().trim());
            
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("name", name);
            item.put("code", code);
            item.put("price", price);
            item.put("change", change);
            item.put("changeRate", changeRate);
            item.put("volume", volume);
            item.put("previousVolume", previousVolume);
            
            list.add(item);
        }
        
        return list;
    }
}
