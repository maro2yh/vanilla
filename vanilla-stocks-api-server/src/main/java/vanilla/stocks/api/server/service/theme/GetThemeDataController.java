package vanilla.stocks.api.server.service.theme;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vanilla.stocks.html.naver.ThemePage;

@RestController
public class GetThemeDataController {

    @GetMapping("/theme")
    public Object getThemeList() throws IOException {
        return new ThemePage().getList();
    }
    
    @GetMapping("/theme/{no}")
    public Object getThemeItemList(@PathVariable String no) throws IOException {
        return new ThemePage().getItems(no);
    }
}
