package vanilla.commons.util.mybatis;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public class MybatisFunctions {

    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(Object obj) {
        if (obj instanceof String) {
            return obj != null && !"".equals(obj.toString().trim());
        } else if (obj instanceof List) {
            return obj != null && !((List) obj).isEmpty();
        } else if (obj instanceof Map) {
            return obj != null && !((Map) obj).isEmpty();
        } else if (obj instanceof Object[]) {
            return obj != null && Array.getLength(obj) != 0;
        } else {
            return obj != null;
        }
    }
    
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object obj) {
        if (obj instanceof String) {
            return obj == null || "".equals(obj.toString());
        } else if (obj instanceof List) {
            return obj == null || ((List) obj).isEmpty();
        } else if (obj instanceof Map) {
            return obj == null || ((Map) obj).isEmpty();
        } else if (obj instanceof Object[]) {
            return obj == null || Array.getLength(obj) == 0;
        } else {
            return obj == null;
        }
    }
    
    public static boolean equalsIgnoreCase(String str, String compare) {
        if (str == null) {
            return false;
        } else if (compare != "" && str.equals("")) {
            return false;
        }
        
        return str.equalsIgnoreCase(compare);
    }
    
    public static boolean isY(String str) {
        if (str == null) {
            return false;
        }
        
        return str.equalsIgnoreCase("y");
    }
    
    public static boolean isN(String str) {
        if (str == null) {
            return false;
        }
        
        return str.equalsIgnoreCase("n");
    }
    
    public static boolean isTrue(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        
        return str.equalsIgnoreCase("true");
    }
    
    public static boolean isFalse(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        
        return str.equalsIgnoreCase("false");
    }
}
