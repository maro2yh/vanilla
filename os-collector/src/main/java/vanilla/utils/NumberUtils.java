package vanilla.utils;

public class NumberUtils {

    public static double toKB(long bt) {
        return (double) bt / 1024;
    }
    
    public static double toMB(long bt) {
        return (double) bt / (1024 * 1024);
    }
    
    public static double toGB(long bt) {
        return (double) bt / (1024 * 1024 * 1024);
    }
}
