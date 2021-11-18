package vanilla.stocks.api.server.service.investor.daily;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vanilla.stocks.api.server.config.SqlMapperWhere;
import vanilla.stocks.html.naver.InvestorTrendDailyPage;

@RestController
@Service
public class GetInvestorDailyController {

    @Autowired
    private InvestorDailyRepository investorDailyRepository;

    @Autowired
    private InvestorDailySqlMapper investorDailySqlMapper;

    @GetMapping("/{market}/investor/daily")
    public Object getDaily(@PathVariable String market, @RequestParam(value = "searchFromDate", required = true) String searchFromDate,
            @RequestParam(value = "searchToDate", required = true) String searchToDate) throws IOException {

        InvestorDaily dailyData = investorDailyRepository.findOneByDateAndMarket(searchToDate, market);

        if (dailyData == null) {
            String parseStartDate = searchToDate;
            String parseEndDate = searchFromDate;

            SqlMapperWhere where = new SqlMapperWhere().add("market", market).add("date", searchToDate);
            InvestorDaily lastDailyData = investorDailySqlMapper.findOneLastDate(where);

            if (lastDailyData != null && StringUtils.isNotEmpty(lastDailyData.getDate())) {
                parseEndDate = lastDailyData.getDate();
            }

            List<Map<String, Object>> list = new InvestorTrendDailyPage().get(market, parseStartDate, parseEndDate);

            for (Map<String, Object> map : list) {
                investorDailyRepository.save(new InvestorDaily(map));
            }
        }

        SqlMapperWhere where = new SqlMapperWhere().add("market", market).add("searchFromDate", searchFromDate).add("searchToDate", searchToDate);
        return investorDailySqlMapper.findAll(where);
    }
}
