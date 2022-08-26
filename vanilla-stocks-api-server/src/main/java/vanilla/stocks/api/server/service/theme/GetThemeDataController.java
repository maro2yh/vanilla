package vanilla.stocks.api.server.service.theme;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vanilla.stocks.html.naver.ThemePage;

@RestController
public class GetThemeDataController {

    @GetMapping("/theme")
    public Object getThemeList(@RequestParam(value = "limit", required = false) String limit) throws IOException {
        List<Map<String, Object>> list = new ThemePage().getList();
        
        if (StringUtils.isNotEmpty(limit)) {
            return list.subList(0, Integer.parseInt(limit));
        } else {
            return list;
        }
    }
    
    @GetMapping("/theme/{no}")
    public Object getThemeItemList(@PathVariable String no) throws IOException {
        return new ThemePage().getItems(no);
    }
}
