package vanilla.commons.util.string;

import org.apache.commons.lang3.StringUtils;

public class VanillaStringUtils {

    public static Integer toInteger(Object text) {
        return toInteger(text, null);
    }

    public static Integer toInteger(Object text, Integer def) {
        if (text == null || StringUtils.isEmpty(String.valueOf(text)) || text.equals("-")) {
            return def;
        }

        String str = String.valueOf(text);
        String number = "";

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') {
                break;
            }

            if (Character.isDigit(str.charAt(i))) {
                number += str.charAt(i);
            }
        }

        if (str.startsWith("-")) {
            number = "-" + number;
        }

        if (!number.isEmpty()) {
            return Integer.parseInt(number);
        } else {
            return def;
        }
    }

    public static Long toLong(Object text) {
        return toLong(text, null);
    }

    public static Long toLong(Object text, Long def) {
        if (text == null || StringUtils.isEmpty(String.valueOf(text)) || text.equals("-")) {
            return def;
        }

        String str = String.valueOf(text);
        String number = "";

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') {
                break;
            }

            if (Character.isDigit(str.charAt(i))) {
                number += str.charAt(i);
            }
        }

        if (str.startsWith("-")) {
            number = "-" + number;
        }

        if (!number.isEmpty()) {
            return Long.parseLong(number);
        } else {
            return def;
        }
    }

    public static Double toDouble(Object text) {
        return toDouble(text, null);
    }

    public static Double toDouble(Object text, Double def) {
        if (text == null || StringUtils.isEmpty(String.valueOf(text)) || text.equals("-")) {
            return def;
        }

        String str = String.valueOf(text);
        String number = "";

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                number += str.charAt(i);
            } else if (str.charAt(i) == '.') {
                number += str.charAt(i);
            }
        }

        if (str.startsWith("-")) {
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

    public static Float toFloat(Object text) {
        return toFloat(text, null);
    }

    public static Float toFloat(Object text, Float def) {
        if (text == null || StringUtils.isEmpty(String.valueOf(text)) || text.equals("-")) {
            return def;
        }

        String str = String.valueOf(text);
        String number = "";

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                number += str.charAt(i);
            } else if (str.charAt(i) == '.') {
                number += str.charAt(i);
            }
        }

        if (str.startsWith("-")) {
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
