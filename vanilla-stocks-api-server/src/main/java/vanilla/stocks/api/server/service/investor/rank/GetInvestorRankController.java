package vanilla.stocks.api.server.service.investor.rank;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import vanilla.commons.util.string.VanillaStringUtils;
import vanilla.stocks.html.naver.InvestorRankPage;
import vanilla.stocks.html.naver.ItemTotalInfoPage;

@RestController
public class GetInvestorRankController {

    @SuppressWarnings("unchecked")
    @GetMapping("/{market}/{investor}/rank")
    public Object get(@PathVariable String market, @PathVariable String investor) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> list = new InvestorRankPage().getList(market, investor, "buy");
        
        for (Map<String, Object> map : list) {
            String code = String.valueOf(map.get("code"));
            Map<String, Object> item = new ItemTotalInfoPage().get(code);
            Map<String, Object> sise = objectMapper.convertValue(item.get("sise"), Map.class);
            map.put("price", VanillaStringUtils.toLong(String.valueOf(sise.get("now"))));
            map.put("changeRate", VanillaStringUtils.toFloat(String.valueOf(sise.get("changeRate"))));
        }
        
        return null;
    }
}
