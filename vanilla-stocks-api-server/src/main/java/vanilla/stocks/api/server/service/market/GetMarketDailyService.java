package vanilla.stocks.api.server.service.market;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vanilla.stocks.api.server.config.SqlMapperWhere;
import vanilla.stocks.html.naver.MarketDailyPage;

@Service
public class GetMarketDailyService {

    @Autowired
    private MarketDailySqlMapper marketDailySqlMapper;
    
    @Autowired
    private MarketDailyRepository marketDailyRepository;
    
    public List<MarketDaily> getData(String market, String searchFromDate, String searchToDate) throws IOException {
        SqlMapperWhere where = new SqlMapperWhere("name", market).add("date", searchToDate);
        MarketDaily dailyData = marketDailySqlMapper.findOne(where);
        
        if (dailyData == null || StringUtils.isEmpty(dailyData.getDate())) {
            String parseFromDate = searchFromDate;
            String parseToDate = searchToDate;
            MarketDaily lastDailyData = marketDailySqlMapper.findOneLastDate(where);
            
            if (lastDailyData != null && StringUtils.isNotEmpty(lastDailyData.getDate())) {
                parseFromDate = lastDailyData.getDate();
            }
            
            List<Map<String, Object>> list = new MarketDailyPage().get(market, parseToDate, parseFromDate);
            
            for (Map<String, Object> map : list) {
                marketDailyRepository.save(new MarketDaily(map));
            }
        }
        
        where.clear().add("name", market).add("searchFromDate", searchFromDate).add("searchToDate", searchToDate);
        
        return marketDailySqlMapper.findAll(where);
    }
}
