package vanilla.stocks.html.naver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import vanilla.commons.util.string.VanillaStringUtils;

public class MarketSisePage {

    public Map<String, Object> get(String code) throws IOException {
        Map<String, Object> siseMap = new HashMap<String, Object>();
        String url = String.format(URLs.MARKET_SISE, code.toUpperCase());
        Document doc = Jsoup.connect(url).get();
        
        // 현재 지수 구하기
        Elements divEls = doc.select("div[id=quotient]");
        Element currentEl = divEls.get(0);
        Elements nowValueEls = currentEl.select("em[id=now_value]");
        Element nowValueEl = nowValueEls.get(0);
        
        siseMap.put("market", code);
        siseMap.put("jisu", VanillaStringUtils.toFloat(nowValueEl.text()));
        
        // 현재 지수의 등락 값과 등락율 구하기
        Elements spanEls = currentEl.select("span");
        String spanEl0Text = spanEls.get(0).text();
        String spanEl1Text = spanEls.get(1).text();
        String spanEl2Text = spanEls.get(2).text();
        spanEl0Text = spanEl0Text.replaceAll(spanEl1Text, "");
        spanEl0Text = spanEl0Text.replaceAll(spanEl2Text, "");
        siseMap.put("changeRate", VanillaStringUtils.toFloat(spanEl0Text));
        siseMap.put("changeValue", VanillaStringUtils.toFloat(spanEls.get(1).text()));
        
        Elements tableEls = doc.select("table[class=table_kos_index]");
        Element tableEl = tableEls.get(0);
        Elements tdEls = tableEl.select("td");
        
        for (int i = 0; i < tdEls.size(); i++) {
            if (i == 0) {
                // 거래량
                siseMap.put("tradeVolume", VanillaStringUtils.toLong(tdEls.get(i).text()));
            } else if (i == 1) {
                // 거래대금
                long tradePrice = VanillaStringUtils.toLong(tdEls.get(i).text());
                siseMap.put("tradePrice", tradePrice / 100);
            } else if (i == 2) {
                // 장중 최고
                siseMap.put("high", VanillaStringUtils.toFloat(tdEls.get(i).text()));
            } else if (i == 3) {
                // 장중 최저
                siseMap.put("low", VanillaStringUtils.toFloat(tdEls.get(i).text()));
            } else if (i == 4) {
                // 52주 최고
                siseMap.put("week52High", VanillaStringUtils.toFloat(tdEls.get(i).text()));
            } else if (i == 5) {
                // 52주 최저
                siseMap.put("week52Low", VanillaStringUtils.toFloat(tdEls.get(i).text()));
            } else if (i == 6) {
                Elements liEls = tdEls.get(i).select("li");
                
                for (int j = 0; j < liEls.size(); j++) {
                    spanEls = liEls.get(j).select("span");
                    
                    if (j == 0) {
                        // 상한가 종목
                        siseMap.put("upLimitCount", VanillaStringUtils.toInteger(spanEls.get(1).text()));
                    } else if (j == 1) {
                        // 상승 종목
                        siseMap.put("upCount", VanillaStringUtils.toInteger(spanEls.get(1).text()));
                    } else if (j == 2) {
                        // 보합 종목
                        siseMap.put("sameCount", VanillaStringUtils.toInteger(spanEls.get(1).text()));
                    } else if (j == 3) {
                        // 하락 종목
                        siseMap.put("downCount", VanillaStringUtils.toInteger(spanEls.get(1).text()));
                    } else if (j == 4) {
                        // 하한가 종목
                        siseMap.put("downLimitCount", VanillaStringUtils.toInteger(spanEls.get(1).text()));
                    }
                }
            }
        }
        
        Element dlEl = doc.select("dl[class=lst_kos_info]").first();
        Elements ddEls = dlEl.select("dd");
        
        for (int i = 0; i < ddEls.size(); i++) {
            Element spanEl = ddEls.get(i).select("span").first();
            
            if (i == 0) {
                // 개인 거래대금
                siseMap.put("personalTradePrice", VanillaStringUtils.toInteger(spanEl.text()));
            } else if (i == 1) {
                // 외국인 거래대금
                siseMap.put("foreignerTradePrice", VanillaStringUtils.toInteger(spanEl.text()));
            } else if (i == 2) {
                // 기관 거래대금
                siseMap.put("agencyTradePrice", VanillaStringUtils.toInteger(spanEl.text()));
            }
        }
        
        siseMap.put("tradePriceUnit", 100000000);
        siseMap.put("tradeVolumeUnit", 1000);
        
        return siseMap;
    }
}
