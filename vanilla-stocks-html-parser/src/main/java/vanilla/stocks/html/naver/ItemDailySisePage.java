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

public class ItemDailySisePage {

    public List<Map<String, Object>> get(String code, String startDate, String endDate) throws IOException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int page = 1;
        boolean isEnd = false;
        long startDateL = VanillaStringUtils.toLong(startDate);
        long endDateL = VanillaStringUtils.toLong(endDate);

        do {
            String url = String.format(URLs.ITEM_DAILY_SISE, code, page);
            Document doc = Jsoup.connect(url).get();
            Elements tables = doc.select("table");
            Element table = tables.get(0);
            Elements trs = table.select("tr");
            
            for (int i = 2; i < trs.size(); i++) {
                Element tr = trs.get(i);
                Elements tds = tr.select("td");
                
                if (tds.size() != 7) {
                    continue;
                }
                
                String date = tds.get(0).text();
                
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
                
                long close = VanillaStringUtils.toLong(tds.get(1).text(), 0L);
                long change = VanillaStringUtils.toLong(tds.get(2).text(), 0L);
                long open = VanillaStringUtils.toLong(tds.get(3).text(), 0L);
                long high = VanillaStringUtils.toLong(tds.get(4).text(), 0L);
                long low = VanillaStringUtils.toLong(tds.get(5).text(), 0L);
                long volume = VanillaStringUtils.toLong(tds.get(6).text(), 0L);
                
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("date", date);
                map.put("close", close);
                map.put("change", change);
                map.put("open", open);
                map.put("high", high);
                map.put("low", low);
                map.put("volume", volume);
                
                list.add(map);
            }
            
            page++;
        } while (!isEnd);
        
        return list;
    }
    
    public List<Map<String, Object>> get(String code, int days) throws IOException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int page = 1;
        boolean isEnd = false;
        
        do {
            String url = String.format(URLs.ITEM_DAILY_SISE, code, page);
            Document doc = Jsoup.connect(url).get();
            Elements tables = doc.select("table");
            Element table = tables.get(0);
            Elements trs = table.select("tr");
            
            for (int i = 2; i < trs.size(); i++) {
                if (list.size() == days) {
                    isEnd = true;
                    break;
                }
                
                Element tr = trs.get(i);
                Elements tds = tr.select("td");
                
                if (tds.size() != 7) {
                    continue;
                }
                
                String date = tds.get(0).text();
                
                try {
                    date = VanillaCalendarUtils.changeFormat(date, "yyyy.MM.dd", "yyyyMMdd");
                } catch (ParseException e) {
                    throw new IOException(e);
                }
                
                long close = VanillaStringUtils.toLong(tds.get(1).text(), 0L);
                long change = VanillaStringUtils.toLong(tds.get(2).text(), 0L);
                long open = VanillaStringUtils.toLong(tds.get(3).text(), 0L);
                long high = VanillaStringUtils.toLong(tds.get(4).text(), 0L);
                long low = VanillaStringUtils.toLong(tds.get(5).text(), 0L);
                long volume = VanillaStringUtils.toLong(tds.get(6).text(), 0L);
                
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("date", date);
                map.put("close", close);
                map.put("change", change);
                map.put("open", open);
                map.put("high", high);
                map.put("low", low);
                map.put("volume", volume);
                
                list.add(map);
            }
            
            page++;
        } while (!isEnd);
        
        return list;
    }
}
