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

public class ItemTotalInfoPage {

    public Map<String, Object> get(String code) throws IOException {
        Map<String, Object> totalMap = new HashMap<String, Object>();
        String url = String.format(URLs.ITEM_TOTAL_INFO, code);
        Document doc = Jsoup.connect(url).get();
        
        String summary = getSummary(doc);
        Map<String, Object> siseMap = getSise(doc);
        Map<String, Object> investmentInfoMap = getInvestmentInfo(doc);
        Map<String, Object> corporateEarnings = getCorporateEarnings(doc);
        
        totalMap.put("summary", summary);
        totalMap.put("sise", siseMap);
        totalMap.put("investmentInfo", investmentInfoMap);
        totalMap.put("corporateEarnings", corporateEarnings);
        
        return totalMap;
    }
    
    private String getSummary(Document doc) {
        Elements divs = doc.select("#summary_info");
        Element div = divs.get(0);
        Elements ps = div.select("p");
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < ps.size(); i++) {
            Element p = ps.get(i);
            sb.append(p.text()).append("\n");
        }
        
        return sb.toString();
    }
    
    private Map<String, Object> getSise(Document doc) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        Elements divs = doc.select(".today");
        Element div = divs.get(0);
        Elements ps = div.select("p");
        Element p = ps.get(0);
        Elements ems = p.select("em");
        Element em = ems.get(0);
        Elements spans = em.select("span");
        Element span = spans.get(0);
        long now = VanillaStringUtils.toLong(span.text());
        
        p = ps.get(1);
        ems = p.select("em");
        em = ems.get(0);
        spans = em.select("span");
        span = spans.get(1);
        long change = VanillaStringUtils.toLong(span.text());
        
        em = ems.get(1);
        spans = em.select("span");
        span = spans.get(0);
        String updown = span.text().trim();
        
        span = spans.get(1);
        float changeRate = VanillaStringUtils.toFloat(span.text());
        
        if (updown.equals("-")) {
            changeRate = -changeRate;
        }
        
        map.put("now", now);
        map.put("change", change);
        map.put("changeRate", changeRate);
        
        Elements tables = doc.select("table");
        Elements trs = tables.get(0).select("tr");
        Element tr = trs.get(0);
        Elements tds = tr.select("td");
        Element td = tds.get(0);
        spans = td.select("span");
        long previous = VanillaStringUtils.toLong(spans.get(1).text());
        
        td = tds.get(1);
        spans = td.select("span");
        long high = VanillaStringUtils.toLong(spans.get(1).text());
        
        td = tds.get(2);
        spans = td.select("span");
        long volume = VanillaStringUtils.toLong(spans.get(1).text());
        
        tr = trs.get(1);
        tds = tr.select("td");
        td = tds.get(0);
        spans = td.select("span");
        long open = VanillaStringUtils.toLong(spans.get(1).text());
        
        td = tds.get(1);
        spans = td.select("span");
        long low = VanillaStringUtils.toLong(spans.get(1).text());
        
        td = tds.get(2);
        spans = td.select("span");
        long amount = VanillaStringUtils.toLong(spans.get(1).text());
        
        map.put("previous", previous);
        map.put("high", high);
        map.put("volume", volume);
        map.put("open", open);
        map.put("low", low);
        map.put("amount", amount);
        
        return map;
    }
    
    private Map<String, Object> getInvestmentInfo(Document doc) {
        Map<String, Object> map = new HashMap<String, Object>();
        Elements divs = doc.select("#tab_con1");
        Element div = divs.get(0);
        Elements tables = div.select("table");
        Element table = tables.get(0);
        Elements trs = table.select("tr");
        Element tr = trs.get(0);
        Elements tds = tr.select("td");
        Element td = tds.get(0);
        Elements ems = td.select("em");
        Element em = ems.get(0);
        String marketCap = em.text().trim();
        
        tr = trs.get(1);
        tds = tr.select("td");
        td = tds.get(0);
        ems = td.select("em");
        em = ems.get(0);
        String marketRank = em.text().trim();
        
        tr = trs.get(2);
        tds = tr.select("td");
        td = tds.get(0);
        ems = td.select("em");
        em = ems.get(0);
        long shares = VanillaStringUtils.toLong(em.text());
        
        tr = trs.get(3);
        tds = tr.select("td");
        td = tds.get(0);
        ems = td.select("em");
        em = ems.get(0);
        long parValue = VanillaStringUtils.toLong(em.text());
        
        table = tables.get(1);
        trs = table.select("tr");
        tr = trs.get(0);
        tds = tr.select("td");
        td = tds.get(0);
        ems = td.select("em");
        em = ems.get(0);
        long foreignerLimitShares = VanillaStringUtils.toLong(em.text());
        
        tr = trs.get(1);
        tds = tr.select("td");
        td = tds.get(0);
        ems = td.select("em");
        em = ems.get(0);
        long foreignerHaveShares = VanillaStringUtils.toLong(em.text());
        
        tr = trs.get(2);
        tds = tr.select("td");
        td = tds.get(0);
        ems = td.select("em");
        em = ems.get(0);
        float foreignerHaveRate = VanillaStringUtils.toFloat(em.text());
        
        table = tables.get(2);
        trs = table.select("tr");
        tr = trs.get(0);
        tds = tr.select("td");
        td = tds.get(0);
        Elements spans = td.select("span");
        Element span = spans.get(0);
        String investmentOpinion = span.text().trim();
        
        ems = td.select("em");
        em = ems.get(1);
        long investmentGoal = VanillaStringUtils.toLong(em.text());
        
        tr = trs.get(1);
        tds = tr.select("td");
        td = tds.get(0);
        ems = td.select("em");
        em = ems.get(0);
        long high52Weeksr = VanillaStringUtils.toLong(em.text());
        
        em = ems.get(1);
        long low52Weeks = VanillaStringUtils.toLong(em.text());
        
        table = tables.get(3);
        trs = table.select("tr");
        tr = trs.get(0);
        tds = tr.select("td");
        td = tds.get(0);
        ems = td.select("em");
        em = ems.get(0);
        float per = VanillaStringUtils.toFloat(em.text());
        
        em = ems.get(1);
        long eps = VanillaStringUtils.toLong(em.text());
        
        tr = trs.get(1);
        tds = tr.select("td");
        td = tds.get(0);
        ems = td.select("em");
        em = ems.get(0);
        float expectaionPer = VanillaStringUtils.toFloat(em.text());
        
        em = ems.get(1);
        long expectaionEps = VanillaStringUtils.toLong(em.text());
        
        tr = trs.get(2);
        tds = tr.select("td");
        td = tds.get(0);
        ems = td.select("em");
        em = ems.get(0);
        float pbr = VanillaStringUtils.toFloat(em.text());
        
        em = ems.get(1);
        long bps = VanillaStringUtils.toLong(em.text());
        
        map.put("marketCap", marketCap);
        map.put("marketRank", marketRank);
        map.put("shares", shares);
        map.put("parValue", parValue);
        map.put("foreignerLimitShares", foreignerLimitShares);
        map.put("foreignerHaveShares", foreignerHaveShares);
        map.put("foreignerHaveRate", foreignerHaveRate);
        map.put("investmentOpinion", investmentOpinion);
        map.put("investmentGoal", investmentGoal);
        map.put("high52Weeksr", high52Weeksr);
        map.put("low52Weeks", low52Weeks);
        map.put("per", per);
        map.put("eps", eps);
        map.put("expectaionPer", expectaionPer);
        map.put("expectaionEps", expectaionEps);
        map.put("pbr", pbr);
        map.put("bps", bps);
        
        return map;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Map<String, Object> getCorporateEarnings(Document doc) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        Elements tables = doc.select("table");
        Element table = tables.get(3);
        Elements trs = table.select("tr");
        Element tr = trs.get(1);
        Elements ths = tr.select("th");
        
        for (int i = 0; i < ths.size(); i++) {
            Element th = ths.get(i);
            String date = th.text();
            
            try {
                date = VanillaCalendarUtils.changeFormat(date, "yyyy.MM", "yyyyMM");
            } catch (ParseException e) {
                throw new IOException(e);
            }
            
            Map<String, Object> earningsMap = new HashMap<String, Object>();
            earningsMap.put("date", date);
            
            list.add(earningsMap);
        }
        
        tr = trs.get(3);
        Elements tds = tr.select("td");
        
        for (int i = 0; i < tds.size(); i++) {
            Element td = tds.get(i);
            long totalSales = VanillaStringUtils.toLong(td.text(), 0l);
            
            Map<String, Object> earningsMap = (Map) list.get(i);
            earningsMap.put("totalSales", totalSales);
        }
        
        tr = trs.get(4);
        tds = tr.select("td");
        
        for (int i = 0; i < tds.size(); i++) {
            Element td = tds.get(i);
            long operatingProfit = VanillaStringUtils.toLong(td.text(), 0l);
            
            Map<String, Object> earningsMap = (Map) list.get(i);
            earningsMap.put("operatingProfit", operatingProfit);
        }
        
        tr = trs.get(5);
        tds = tr.select("td");
        
        for (int i = 0; i < tds.size(); i++) {
            Element td = tds.get(i);
            long netProfit = VanillaStringUtils.toLong(td.text(), 0l);
            
            Map<String, Object> earningsMap = (Map) list.get(i);
            earningsMap.put("netProfit", netProfit);
        }
        
        tr = trs.get(6);
        tds = tr.select("td");
        
        for (int i = 0; i < tds.size(); i++) {
            Element td = tds.get(i);
            float operatingProfitRate = VanillaStringUtils.toFloat(td.text(), 0f);
            
            Map<String, Object> earningsMap = (Map) list.get(i);
            earningsMap.put("operatingProfitRate", operatingProfitRate);
        }
        
        tr = trs.get(7);
        tds = tr.select("td");
        
        for (int i = 0; i < tds.size(); i++) {
            Element td = tds.get(i);
            float netProfitRate = VanillaStringUtils.toFloat(td.text(), 0f);
            
            Map<String, Object> earningsMap = (Map) list.get(i);
            earningsMap.put("netProfitRate", netProfitRate);
        }
        
        tr = trs.get(8);
        tds = tr.select("td");
        
        for (int i = 0; i < tds.size(); i++) {
            Element td = tds.get(i);
            float roe = VanillaStringUtils.toFloat(td.text(), 0f);
            
            Map<String, Object> earningsMap = (Map) list.get(i);
            earningsMap.put("roe", roe);
        }
        
        tr = trs.get(9);
        tds = tr.select("td");
        
        for (int i = 0; i < tds.size(); i++) {
            Element td = tds.get(i);
            float debtRate = VanillaStringUtils.toFloat(td.text(), 0f);
            
            Map<String, Object> earningsMap = (Map) list.get(i);
            earningsMap.put("debtRate", debtRate);
        }
        
        tr = trs.get(10);
        tds = tr.select("td");
        
        for (int i = 0; i < tds.size(); i++) {
            Element td = tds.get(i);
            float quickRate = VanillaStringUtils.toFloat(td.text(), 0f);
            
            Map<String, Object> earningsMap = (Map) list.get(i);
            earningsMap.put("quickRate", quickRate);
        }
        
        tr = trs.get(11);
        tds = tr.select("td");
        
        for (int i = 0; i < tds.size(); i++) {
            Element td = tds.get(i);
            float reserveRate = VanillaStringUtils.toFloat(td.text(), 0f);
            
            Map<String, Object> earningsMap = (Map) list.get(i);
            earningsMap.put("reserveRate", reserveRate);
        }
        
        tr = trs.get(12);
        tds = tr.select("td");
        
        for (int i = 0; i < tds.size(); i++) {
            Element td = tds.get(i);
            long eps = VanillaStringUtils.toLong(td.text(), 0l);
            
            Map<String, Object> earningsMap = (Map) list.get(i);
            earningsMap.put("eps", eps);
        }
        
        tr = trs.get(13);
        tds = tr.select("td");
        
        for (int i = 0; i < tds.size(); i++) {
            Element td = tds.get(i);
            float per = VanillaStringUtils.toFloat(td.text(), 0f);
            
            Map<String, Object> earningsMap = (Map) list.get(i);
            earningsMap.put("per", per);
        }
        
        tr = trs.get(14);
        tds = tr.select("td");
        
        for (int i = 0; i < tds.size(); i++) {
            Element td = tds.get(i);
            long bps = VanillaStringUtils.toLong(td.text(), 0l);
            
            Map<String, Object> earningsMap = (Map) list.get(i);
            earningsMap.put("bps", bps);
        }
        
        tr = trs.get(15);
        tds = tr.select("td");
        
        for (int i = 0; i < tds.size(); i++) {
            Element td = tds.get(i);
            float pbr = VanillaStringUtils.toFloat(td.text(), 0f);
            
            Map<String, Object> earningsMap = (Map) list.get(i);
            earningsMap.put("pbr", pbr);
        }
        
        tr = trs.get(16);
        tds = tr.select("td");
        
        for (int i = 0; i < tds.size(); i++) {
            Element td = tds.get(i);
            long dividend = VanillaStringUtils.toLong(td.text(), 0l);
            
            Map<String, Object> earningsMap = (Map) list.get(i);
            earningsMap.put("dividend", dividend);
        }
        
        tr = trs.get(17);
        tds = tr.select("td");
        
        for (int i = 0; i < tds.size(); i++) {
            Element td = tds.get(i);
            float dividendRate = VanillaStringUtils.toFloat(td.text(), 0f);
            
            Map<String, Object> earningsMap = (Map) list.get(i);
            earningsMap.put("dividendRate", dividendRate);
        }
        
        List<Map<String, Object>> year = list.subList(0, 4);
        List<Map<String, Object>> quater = list.subList(4, list.size());
        
        map.put("year", year);
        map.put("quater", quater);
        
        return map;
    }
}
