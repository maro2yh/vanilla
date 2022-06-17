package vanilla.stocks.ebestapi.res;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResFileHelper {

    private static ResFileHelper instance;
    private String resRootPath;
    private File[] files;

    public static ResFileHelper newInstance(String resRootPath) {
        instance = new ResFileHelper(resRootPath);
        return instance;
    }

    public static ResFileHelper getInstance() {
        return instance;
    }

    public ResFileHelper(String resRootPath) {
        this.resRootPath = resRootPath;
    }

    public String getResRootPath() {
        return resRootPath;
    }

    public void setRootResPath(String resRootPath) {
        this.resRootPath = resRootPath;
    }
    
    public File[] getFiles() {
        return files;
    }

    public void read() throws IOException {
        if (resRootPath == null) {
            throw new IOException("res root path is null.");
        }

        File resRootDir = new File(resRootPath);

        if (!resRootDir.isDirectory()) {
            throw new IOException("Not found res directory.");
        }

        files = resRootDir.listFiles(file -> file.isFile() && !Pattern.compile("^([\\S]+(_[1-2]\\.(?i)(res))$)").matcher(file.getName()).matches());

        if (files == null || files.length == 0) {
            throw new IOException("Not found res files.");
        }

//        for (File file : files) {
//            ResFileData resFileData = new ResFile(file).load();
//            resFileDataRepository.putResFileData(resFileData.getName(), resFileData);
//        }
    }
}
