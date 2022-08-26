package vanilla.stocks.api.server.service.upjong;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vanilla.stocks.html.naver.UpjongPage;

@RestController
public class GetUpjongDataController {

    @GetMapping("/upjong")
    public Object getUpjongList(@RequestParam(value = "limit", required = false) String limit) throws IOException {
        List<Map<String, Object>> list = new UpjongPage().getList();
        
        if (StringUtils.isNotEmpty(limit)) {
            return list.subList(0, Integer.parseInt(limit));
        } else {
            return list;
        }
    }
    
    @GetMapping("/upjong/{no}")
    public Object getUpjongItemList(@PathVariable String no) throws IOException {
        return new UpjongPage().getItems(no);
    }
}
