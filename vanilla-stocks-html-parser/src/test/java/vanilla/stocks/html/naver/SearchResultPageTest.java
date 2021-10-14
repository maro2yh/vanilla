package vanilla.stocks.html.naver;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SearchResultPageTest {

    @Test
    public void testGet() {
        try {
            List<Map<String, Object>> searchResults = new SearchResultPage().get("펄어비스");
            
            for (Map<String, Object> searchResult : searchResults) {
                System.out.println(searchResult.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
