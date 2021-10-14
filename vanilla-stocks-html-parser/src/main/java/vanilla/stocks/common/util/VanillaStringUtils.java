package vanilla.stocks.common.util;

import org.apache.commons.lang3.StringUtils;

public class VanillaStringUtils {

    public static Integer toInteger(String text) {
        return toInteger(text, null);
    }

    public static Integer toInteger(String text, Integer def) {
        if (StringUtils.isEmpty(text) || text.equals("-")) {
            return def;
        }

        String number = "";

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '.') {
                break;
            }

            if (Character.isDigit(text.charAt(i))) {
                number += text.charAt(i);
            }
        }

        if (text.startsWith("-")) {
            number = "-" + number;
        }

        if (!number.isEmpty()) {
            return Integer.parseInt(number);
        } else {
            return def;
        }
    }

    public static Long toLong(String text) {
        return toLong(text, null);
    }

    public static Long toLong(String text, Long def) {
        if (StringUtils.isEmpty(text) || text.equals("-")) {
            return def;
        }

        String number = "";

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '.') {
                break;
            }

            if (Character.isDigit(text.charAt(i))) {
                number += text.charAt(i);
            }
        }

        if (text.startsWith("-")) {
            number = "-" + number;
        }

        if (!number.isEmpty()) {
            return Long.parseLong(number);
        } else {
            return def;
        }
    }

    public static Double toDouble(String text) {
        return toDouble(text, null);
    }

    public static Double toDouble(String text, Double def) {
        if (StringUtils.isEmpty(text) || text.equals("-")) {
            return def;
        }

        String number = "";

        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                number += text.charAt(i);
            } else if (text.charAt(i) == '.') {
                number += text.charAt(i);
            }
        }

        if (text.startsWith("-")) {
            number = "-" + number;
        }

        try {
            if (!number.isEmpty()) {
                return Double.parseDouble(number);
            } else {
                return def;
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }
    
    public static Float toFloat(String text) {
        return toFloat(text, null);
    }

    public static Float toFloat(String text, Float def) {
        if (StringUtils.isEmpty(text) || text.equals("-")) {
            return def;
        }

        String number = "";

        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                number += text.charAt(i);
            } else if (text.charAt(i) == '.') {
                number += text.charAt(i);
            }
        }

        if (text.startsWith("-")) {
            number = "-" + number;
        }

        try {
            if (!number.isEmpty()) {
                return Float.parseFloat(number);
            } else {
                return def;
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }
}
