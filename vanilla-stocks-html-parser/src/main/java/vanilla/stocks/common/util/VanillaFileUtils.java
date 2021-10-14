package vanilla.stocks.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class VanillaFileUtils {

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static File getFile(boolean newFile, String... paths) throws IOException {
        String path = "";

        for (int i = 0; i < paths.length; i++) {
            if (i > 0) {
                path += FILE_SEPARATOR;
            }

            path += paths[i];
        }

        File file = new File(path);

        if (newFile && !file.isFile()) {
            file.createNewFile();
        }

        return file;
    }

    public static File getFile(String... paths) {
        String path = "";

        for (int i = 0; i < paths.length; i++) {
            if (i > 0) {
                path += FILE_SEPARATOR;
            }

            path += paths[i];
        }

        return new File(path);
    }

    public static boolean isFile(String filePath) {
        return new File(filePath).isFile();
    }

    public static boolean isFile(String dirPath, String fileName) {
        File dir = new File(dirPath);
        String[] fileNames = dir.list();
        boolean isFile = false;

        for (String name : fileNames) {
            if (name.equals(fileName)) {
                isFile = true;
                break;
            }
        }

        return isFile;
    }

    public static boolean isWindowExecutableFile(String fileName) {
        return fileName.endsWith(".exe");
    }

    public static String getDirPath(boolean mkdirs, String... strs) {
        String path = "";

        for (int i = 0; i < strs.length; i++) {
            if (i > 0) {
                path += FILE_SEPARATOR;
            }

            path += strs[i];
        }

        File dir = new File(path);

        if (!dir.isDirectory()) {
            dir.mkdirs();
        }

        return path;
    }

    public static String getName(String filePath) {
        int fileSeperatorLast = filePath.lastIndexOf(FILE_SEPARATOR);

//        if (fileSeperatorLast == -1) {
//            fileSeperatorLast = filePath.lastIndexOf("\\");
//        }

        return filePath.substring(fileSeperatorLast + 1);
    }

    public static File mkdirs(String path) {
        File dir = new File(path);

        if (!dir.isDirectory()) {
            dir.mkdirs();
        }

        return dir;
    }

    public static File mkdirs(String... paths) {
        String path = "";

        for (int i = 0; i < paths.length; i++) {
            if (i > 0) {
                path += FILE_SEPARATOR;
            }

            path += paths[i];
        }

        File dir = new File(path);

        if (!dir.isDirectory()) {
            dir.mkdirs();
        }

        return dir;
    }

    public static String readFile(String filePath) throws IOException {
        return readFile(new File(filePath));
    }

    public static String readFile(File file) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader bReader = null;

        try {
            String s;
            bReader = new BufferedReader(new FileReader(file));

            // 더이상 읽어들일게 없을 때까지 읽어들이게 합니다.
            while ((s = bReader.readLine()) != null) {
                sb.append(s);
            }
        } finally {
            if (bReader != null) {
                bReader.close();
            }
        }

        return sb.toString();
    }

    public static List<String> readFileToList(File file) throws IOException {
        List<String> lines = new ArrayList<String>();
        BufferedReader bufReader = null;

        try {
            FileReader filereader = new FileReader(file);
            bufReader = new BufferedReader(filereader);
            String line = "";

            while ((line = bufReader.readLine()) != null) {
                lines.add(line);
            }
        } finally {
            if (bufReader != null) {
                bufReader.close();
            }
        }

        return lines;
    }

    public static String readBinaryFile(String filePath) throws IOException {
        return readBinaryFile(new File(filePath));
    }

    public static String readBinaryFile(File file) throws IOException {
        StringBuffer sb = new StringBuffer();
        final int bufferSize = 512;
        final byte[] buffer = new byte[bufferSize];

        // open the file
        FileInputStream stream = null;

        try {
            stream = new FileInputStream(file);
            int bytesRead;

            // read a block
            while ((bytesRead = stream.read(buffer)) > 0) {
                // append the block as hex
                for (int i = 0; i < bytesRead; i++) {
                    sb.append(String.format("%02X", buffer[i]));
                }
            }
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        return sb.toString();
    }

    public static boolean copy(String from, String to, boolean overwrite) throws IOException {
        File toFile = new File(to);

        if (!overwrite) {
            if (toFile.isFile()) {
                return false;
            }
        }

        Path sourceFilePath = Paths.get(from);
        Path targetFilePath = Paths.get(to);

        Files.copy(sourceFilePath, targetFilePath, StandardCopyOption.REPLACE_EXISTING);

        return true;
    }

    public static boolean copy(File source, File target, boolean overwrite) throws IOException {
        return copy(source.getAbsolutePath(), target.getAbsolutePath(), overwrite);
    }

    public static void move(String from, String to) throws IOException {
        Files.move(Paths.get(from), Paths.get(to), StandardCopyOption.COPY_ATTRIBUTES);
    }

    public static void delete(String filePath) throws IOException {
        File file = new File(filePath);
        delete(file);
    }

    public static void delete(File file) throws IOException {
        FileUtils.forceDelete(file);
    }
}
