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

public class UpjongPage {

    public List<Map<String, Object>> getList() throws IOException {
        List<Map<String, Object>> upjongList = new ArrayList<Map<String,Object>>();
        Document doc = Jsoup.connect(URLs.UPJONG_LIST).get();
        Elements divEls = doc.select("#contentarea_left");
        
        if (divEls == null || divEls.size() == 0) {
            throw new IOException("Not found the tag id #contentarea_left.");
        }
        
        Element divEl = divEls.get(0);
        Element tableEl = divEl.child(2);
        Elements trEls = tableEl.select("tr");
        
        for (Element trEl : trEls) {
            if (trEl.childrenSize() != 7) {
                continue;
            }
            
            Element nameEl = trEl.child(0);
            Element nameLinkEl = nameEl.child(0);
            String name = nameLinkEl.text().trim();
            String href = nameLinkEl.attr("href");
            int noIndex = href.indexOf("&no=");
            String no = href.substring((noIndex + "&no=".length())).trim();
            
            Element changeRateEl = trEl.child(1);
            float changeRate = VanillaStringUtils.toFloat(changeRateEl.text().trim());
            
            Element totalCountEl = trEl.child(2);
            int totalCount = VanillaStringUtils.toInteger(totalCountEl.text().trim());
            
            Element upCountEl = trEl.child(3);
            int upCount = VanillaStringUtils.toInteger(upCountEl.text().trim());
            
            Element sameCountEl = trEl.child(4);
            int sameCount = VanillaStringUtils.toInteger(sameCountEl.text().trim());
            
            Element downCountEl = trEl.child(5);
            int downCount = VanillaStringUtils.toInteger(downCountEl.text().trim());
            
            Map<String, Object> upjong = new HashMap<String, Object>();
            upjong.put("no", no);
            upjong.put("name", name);
            upjong.put("changeRate", changeRate);
            upjong.put("totalCount", totalCount);
            upjong.put("upCount", upCount);
            upjong.put("sameCount", sameCount);
            upjong.put("downCount", downCount);
            
            upjongList.add(upjong);
        }
        
        return upjongList;
    }
    
    public List<Map<String, Object>> getItems(String no) throws IOException {
        List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
        String url = String.format(URLs.UPJONG_ITEMS, no);
        Document doc = Jsoup.connect(url).get();
        Elements divEls = doc.select(".box_type_l");
        Element divEl = divEls.get(1);
        Element tableEl = divEl.child(0);
        Element tbodyEl = tableEl.child(3);
        Elements trEls = tbodyEl.select("tr");
        
        for (Element trEl : trEls) {
            Elements tdEls = trEl.select("td");
            
            if (tdEls.size() != 10) {
                continue;
            }
            
            Element linkEl = tdEls.get(0).select("a").get(0);
            String name = linkEl.text().trim();
            String href = linkEl.attr("href");
            int codeIndex = href.indexOf("code=");
            String code = href.substring((codeIndex + "code=".length())).trim();
            
            Element priceEl = tdEls.get(1);
            long price = VanillaStringUtils.toLong(priceEl.text().trim());
            
            Element changeEl = tdEls.get(2);
            long change = VanillaStringUtils.toLong(changeEl.text().trim());
            
            Element changeRateEl = tdEls.get(3);
            float changeRate = VanillaStringUtils.toFloat(changeRateEl.text().trim());
            
            Element volumeEl = tdEls.get(6);
            long volume = VanillaStringUtils.toLong(volumeEl.text().trim());
            
            Element previousVolumeEl = tdEls.get(8);
            long previousVolume = VanillaStringUtils.toLong(previousVolumeEl.text().trim());
            
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("name", name);
            item.put("code", code);
            item.put("price", price);
            item.put("change", change);
            item.put("changeRate", changeRate);
            item.put("volume", volume);
            item.put("previousVolume", previousVolume);
            
            items.add(item);
        }
        
        return items;
    }
}
