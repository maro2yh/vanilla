package vanilla.stocks.html.naver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import vanilla.stocks.common.util.VanillaStringUtils;

public class MarketSisePage {

    public Map<String, Map<String, Object>> get() throws IOException {
        Document doc = Jsoup.connect(URLs.MARKET_SISE).get();
        
        float kospiJisu = getJisu(doc, "kospi");
        float kosdaqJisu = getJisu(doc, "kosdaq");
        
        float kospiChange = getChange(doc, "kospi");
        float kosdaqChange = getChange(doc, "kosdaq");
        
        float kospiChangeRate = getChangeRate(doc, "kospi");
        float kosdaqChangeRate = getChangeRate(doc, "kosdaq");
        
        Map<String, Map<String, Object>> market = new HashMap<String, Map<String, Object>>();
        Map<String, Object> jisu = new HashMap<String, Object>();
        jisu.put("jisu", kospiJisu);
        jisu.put("change", kospiChange);
        jisu.put("changeRate", kospiChangeRate);
        market.put("kospi", jisu);
        
        jisu = new HashMap<String, Object>();
        jisu.put("jisu", kosdaqJisu);
        jisu.put("change", kosdaqChange);
        jisu.put("changeRate", kosdaqChangeRate);
        market.put("kosdaq", jisu);
        
        return market;
    }
    
    private float getJisu(Document doc, String market) throws IOException {
        String tagId = market.equalsIgnoreCase("kospi") ? "#KOSPI_now" : "#KOSDAQ_now";
        Elements nowEls = doc.select(tagId);

        if (nowEls == null) {
            throw new IOException(tagId + " could not be found.");
        }

        Element nowEl = nowEls.get(0);
        String nowText = nowEl.text();

        try {
            return VanillaStringUtils.toFloat(nowText);
        } catch (NumberFormatException e) {
            throw new IOException("NumberFormatException '#kospi_now - " + e.getMessage() + "'");
        }
    }
    
    private float getChange(Document doc, String market) throws IOException {
        String tagId = market.equalsIgnoreCase("kospi") ? "#KOSPI_change" : "#KOSDAQ_change";
        Elements changeEls = doc.select(tagId);

        if (changeEls == null) {
            throw new IOException(tagId + " could not be found.");
        }

        Element changeEl = changeEls.get(0);
        String changeText = changeEl.text();
        String[] textSplits = changeText.split(" ");

        if (textSplits == null || textSplits.length == 0) {
            throw new IOException("Can not html parsing. - " + changeText);
        }

        String changeJisu = textSplits[0];
        return VanillaStringUtils.toFloat(changeJisu);
    }
    
    private float getChangeRate(Document doc, String market) throws IOException {
        String tagId = market.equalsIgnoreCase("kospi") ? "#KOSPI_change" : "#KOSDAQ_change";
        Elements changeEls = doc.select(tagId);

        if (changeEls == null) {
            throw new IOException(tagId + " could not be found.");
        }

        Element changeEl = changeEls.get(0);
        String changeText = changeEl.text();
        String[] textSplits = changeText.split(" ");

        if (textSplits == null || textSplits.length == 0) {
            throw new IOException("Can not html parsing. - " + changeText);
        }

        String updownPercentText = textSplits[1];
        textSplits = updownPercentText.split("%");

        if (textSplits == null || textSplits.length == 0) {
            throw new IOException("Can not html parsing. - " + changeText);
        }

        updownPercentText = textSplits[0];
        return VanillaStringUtils.toFloat(updownPercentText);
    }
}
