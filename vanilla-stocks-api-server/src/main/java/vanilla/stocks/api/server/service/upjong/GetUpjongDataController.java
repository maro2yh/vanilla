package vanilla.stocks.api.server.service.upjong;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vanilla.stocks.html.naver.UpjongPage;

@RestController
public class GetUpjongDataController {

    @GetMapping("/upjong")
    public Object getUpjongList() throws IOException {
        return new UpjongPage().getList();
    }
    
    @GetMapping("/upjong/{no}")
    public Object getUpjongItemList(@PathVariable String no) throws IOException {
        return new UpjongPage().getItems(no);
    }
}
