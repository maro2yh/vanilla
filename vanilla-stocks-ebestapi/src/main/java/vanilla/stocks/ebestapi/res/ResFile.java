package vanilla.stocks.ebestapi.res;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResFile {

    private final String FEED = ".FEED";
    private final String FUNC = ".FUNC";
    private final String END_FUNCTION_MAP = "END_FUNCTION_MAP";
    private final String BEGIN_DATA_MAP = "BEGIN_DATA_MAP";
    private final String END_DATA_MAP = "END_DATA_MAP";
    private final String INBLOCK = "INBLOCK";
    private final String OUTBLOCK = "OUTBLOCK";
    private final String BEGIN = "BEGIN";
    private final String END = "END";

    @NonNull
    private File file;
    private String resName;
    private String description;
    
    private Map<String, List<ResFileData>> requestDatasMap = new LinkedHashMap<String, List<ResFileData>>();
    private Map<String, List<ResFileData>> responseDatasMap = new LinkedHashMap<String, List<ResFileData>>();

    public String getResName() {
        return resName;
    }

    public String getDescription() {
        return description;
    }

    public ResFile load() throws IOException {
        resName = file.getName().split("\\.")[0];
        BufferedReader bReader = null;

        try {
            bReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "MS949"));
            String readLine;
            boolean beginDataMapStart = false;
            boolean inblockStart = false;
            boolean outblockStart = false;
            boolean dataStart = false;
            String blockName = "";
            List<ResFileData> fileDataList = null;

            while ((readLine = bReader.readLine()) != null) {
                if ("".equals(readLine.trim())) {
                    continue;
                }
                
                String line = readLine.replace("\t", "").replace(" ", "");
                String[] datas = line.split(",");
                
                if (line.toUpperCase().startsWith(FEED) || line.toUpperCase().startsWith(FUNC)) {
                    this.description = datas[1];
                } else if (line.toUpperCase().startsWith(BEGIN_DATA_MAP)) {
                    beginDataMapStart = true;
                } else if (beginDataMapStart && line.toUpperCase().startsWith(END_DATA_MAP)) {
                    beginDataMapStart = false;
                } else if (line.toUpperCase().startsWith(END_FUNCTION_MAP)) {
                    // 파일 끝
                    break;
                } else if (beginDataMapStart && line.toUpperCase().contains(INBLOCK)) {
                    inblockStart = true;
                    outblockStart = false;
                    blockName = datas[0];
                    fileDataList = new ArrayList<ResFileData>();
                } else if (beginDataMapStart && line.toUpperCase().contains(OUTBLOCK)) {
                    inblockStart = false;
                    outblockStart = true;
                    blockName = datas[0];
                    fileDataList = new ArrayList<ResFileData>();
                } else if (beginDataMapStart && line.equalsIgnoreCase(BEGIN)) {
                    dataStart = true;
                } else if (beginDataMapStart && line.equalsIgnoreCase(END)) {
                    if (inblockStart) {
                        requestDatasMap.put(blockName, fileDataList);
                    } else if (outblockStart) {
                        responseDatasMap.put(blockName, fileDataList);
                    }
                    
                    inblockStart = false;
                    outblockStart = false;
                    dataStart = false;
                } else if (beginDataMapStart && (inblockStart || outblockStart) && dataStart) {
                    fileDataList.add(new ResFileData().set(datas));
                }
            }
        } finally {
            if (bReader != null) {
                bReader.close();
            }
        }
        
        return this;
    }

    public Map<String, List<ResFileData>> getRequestDatasMap() {
        return requestDatasMap;
    }

    public Map<String, List<ResFileData>> getResponseDatasMap() {
        return responseDatasMap;
    }
}
