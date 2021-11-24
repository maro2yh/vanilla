package vanilla.stocks.ebestapi.res;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ResFileHelperTest {

    @Test
    public void test() {
        ResFileHelper resFileHelper = ResFileHelper.newInstance("C:\\eBEST\\xingAPI\\Res");

        try {
            resFileHelper.read();
            File[] files = resFileHelper.getFiles();

            for (File file : files) {
                ResFile resFile = new ResFile(file).load();

                System.out.println("r===== res file read =====");
                System.out.println("File : " + file.getAbsolutePath());
                System.out.println("res Name : " + resFile.getResName());
                System.out.println("Description : " + resFile.getDescription());

                Map<String, List<ResFileData>> requestDatasMap = resFile.getRequestDatasMap();
                Iterator<String> requestKeys = requestDatasMap.keySet().iterator();

                while (requestKeys.hasNext()) {
                    String key = requestKeys.next();
                    List<ResFileData> datas = requestDatasMap.get(key);

                    System.out.println("");
                    System.out.println(key);

                    for (ResFileData data : datas) {
                        System.out.println("\t" + data.getDescription() + ", " + data.getColumn() + ", " + data.getType() + ", " + data.getLength());
                    }
                }

                Map<String, List<ResFileData>> responseDatasMap = resFile.getResponseDatasMap();
                Iterator<String> responseKeys = responseDatasMap.keySet().iterator();

                while (responseKeys.hasNext()) {
                    String key = responseKeys.next();
                    List<ResFileData> datas = responseDatasMap.get(key);

                    System.out.println("");
                    System.out.println(key);

                    for (ResFileData data : datas) {
                        System.out.println("\t" + data.getDescription() + ", " + data.getColumn() + ", " + data.getType() + ", " + data.getLength());
                    }
                }
                
                System.out.println("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
