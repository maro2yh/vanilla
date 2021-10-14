package vanilla.stocks.html.naver;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ThemePageTest {

//    @Test
    public void testGetList() {
        try {
            List<Map<String, Object>> list = new ThemePage().getList();
            
            for (Map<String, Object> map : list) {
                System.out.println(map.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetItems() {
        try {
            List<Map<String, Object>> list = new ThemePage().getItems("177");
            
            for (Map<String, Object> map : list) {
                System.out.println(map.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
