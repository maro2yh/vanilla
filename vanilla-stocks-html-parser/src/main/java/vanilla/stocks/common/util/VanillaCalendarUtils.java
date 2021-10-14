package vanilla.stocks.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class VanillaCalendarUtils {
    
    public static String DEFAULT_FORMAT = "YYYYMMddHHmmss";

    public static String now() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    public static String now(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(Calendar.getInstance().getTime());
    }
    
    public static long currentTimeMillis() throws ParseException {
        return dateToMilliseconds(now(), DEFAULT_FORMAT);
    }

    public static int nowYear() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY");
        return VanillaStringUtils.toInteger(dateFormat.format(Calendar.getInstance().getTime()));
    }

    public static int nowMonth() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
        return VanillaStringUtils.toInteger(dateFormat.format(Calendar.getInstance().getTime()));
    }

    public static int nowHours() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
        return VanillaStringUtils.toInteger(dateFormat.format(Calendar.getInstance().getTime()));
    }

    public static int nowMinutes() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm");
        return VanillaStringUtils.toInteger(dateFormat.format(Calendar.getInstance().getTime()));
    }

    public static String amount(int field, int amount, String returnForamt) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(field, amount);
        SimpleDateFormat dateFormat = new SimpleDateFormat(returnForamt);
        return dateFormat.format(calendar.getTime());
    }
    
    public static String changeFormat(String dateStr, String oldFormat, String newFormat) throws ParseException {
        if (dateStr.isEmpty()) {
            return "";
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(oldFormat);
        
        Date parseDate = dateFormat.parse(dateStr);
        dateFormat.applyPattern(newFormat);
        return dateFormat.format(parseDate);
    }
    
    public static String millisecondsToFormat(long ms, String newFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(newFormat);
        Date date = new Date(ms);
        return dateFormat.format(date);
    }
    
    public static long dateToMilliseconds(String dateStr, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        
        Date parseDate = dateFormat.parse(dateStr);
        return parseDate.getTime();
    }
    
    public static boolean greaterThanTimeMillis(long from, long to, long check) {
        long time = from - to;
        return time >= check;
    }
    
    public static boolean lessThanTimeMillis(long from, long to, long check) {
        long time = from - to;
        return time < check;
    }

    /**
     * 두 날짜가 같은 연월인지 확인
     * 
     * @param date1 YYYYMM 포맷 이상
     * @param date2 YYYYMM 포맷 이상
     * @return 같은 연월이면 true, 아니면 false
     */
    public static boolean equalsYearMonth(String date1, String date2) {
        date1 = date1.replace(".", "");
        date2 = date2.replace(".", "");

        date1 = date1.substring(0, 6);
        date2 = date2.substring(0, 6);

        return date1.equals(date2);
    }

    /**
     * date1이 현재 날짜와 같은지 확인
     * 
     * @param date1 YYYYMMdd 포맷 이상
     * @return 날짜가 같으면 true, 아니면 false
     */
    public static boolean equalsToday(String date1) {
        date1 = date1.replace(".", "");
        date1 = date1.substring(0, 8);

        String date2 = now("YYYYMMdd");

        return date1.equals(date2);
    }
    
    public static boolean equalsTwoDate(String date1, String date2) {
        date1 = date1.replace(".", "");
        date1 = date1.substring(0, 8);
        
        date2 = date2.replace(".", "");
        date2 = date2.substring(0, 8);
        
        return date1.equals(date2);
    }

    /**
     * date1이 date2보다 이전의 연월인지 확인
     * 
     * @param date1 YYYYMM 포맷 이상
     * @param date2 YYYYMM 포맷 이상
     * @return date1 < date2이면 true, 아니면 false
     */
    public static boolean lessThanMonth(String date1, String date2) {
        date1 = date1.replace(".", "");
        date2 = date2.replace(".", "");

        date1 = date1.substring(0, 6);
        date2 = date2.substring(0, 6);

        int date1Int = Integer.parseInt(date1);
        int date2Int = Integer.parseInt(date2);

        return date1Int < date2Int;
    }

    /**
     * date1이 date2보다 이후의 연월인지 확인
     * 
     * @param date1 YYYYMM 포맷 이상
     * @param date2 YYYYMM 포맷 이상
     * @return date1 > date2이면 true, 아니면 false
     */
    public static boolean greaterThanMonth(String date1, String date2) {
        date1 = date1.replace(".", "");
        date2 = date2.replace(".", "");

        date1 = date1.substring(0, 6);
        date2 = date2.substring(0, 6);

        int date1Int = Integer.parseInt(date1);
        int date2Int = Integer.parseInt(date2);

        return date1Int > date2Int;
    }

    /**
     * date1이 date2보다 이후의 일자인지 확인
     * 
     * @param date1 YYYYMMdd
     * @param date2 YYYYMMdd
     * @return date1 > date2이면 true, 아니면 false
     */
    public static boolean greaterThanDate(String date1, String date2) {
        date1 = date1.replace(".", "");
        date2 = date2.replace(".", "");

        date1 = date1.substring(0, 8);
        date2 = date2.substring(0, 8);

        int date1Int = Integer.parseInt(date1);
        int date2Int = Integer.parseInt(date2);

        return date1Int > date2Int;
    }

    /**
     * date1이 date2보다 이전의 일자인지 확인
     * 
     * @param date1 YYYYMMdd
     * @param date2 YYYYMMdd
     * @return date1 < date2이면 true, 아니면 false
     */
    public static boolean lessThanDate(String date1, String date2) {
        date1 = date1.replace(".", "");
        date2 = date2.replace(".", "");

        date1 = date1.substring(0, 8);
        date2 = date2.substring(0, 8);

        int date1Int = Integer.parseInt(date1);
        int date2Int = Integer.parseInt(date2);

        return date1Int < date2Int;
    }

    /**
     * date1이 date2보다 이전의 시간인지 확인
     * 
     * @param date1 YYYYMMddHHmm 이상
     * @param date2 YYYYMMddHHmm 이상
     * @return date1 < date2이면 true, 아니면 false
     */
    public static boolean lessThanHoursAndMinutes(String date1, String date2) {
        date1 = date1.substring(0, 12);
        date2 = date2.substring(0, 12);

        long date1Int = Long.parseLong(date1);
        long date2Int = Long.parseLong(date2);

        return date1Int < date2Int;
    }

    /**
     * date1이 현재시간보다 이전의 시간인지 확인
     * 
     * @param date1 YYYYMMddHHmmss
     * @return date1 < 현재시간보다 작으면 true, 아니면 false
     */
    public static boolean lessThanHoursAndMinutesCurrent(String date1) {
        date1 = date1.substring(0, 12);
        String current = now("YYYYMMddHHmm");

        long date1Int = Long.parseLong(date1);
        long currentInt = Long.parseLong(current);

        return date1Int < currentInt;
    }
}
