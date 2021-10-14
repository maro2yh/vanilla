package vanilla.stocks.html.naver;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class UpjongPageTest {

//    @Test
    public void testGetList() {
        try {
            List<Map<String, Object>> upjongList = new UpjongPage().getList();
            
            for (Map<String, Object> upjong : upjongList) {
                System.out.println(upjong.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetItems() {
        try {
            List<Map<String, Object>> items = new UpjongPage().getItems("316");
            
            for (Map<String, Object> item : items) {
                System.out.println(item.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
