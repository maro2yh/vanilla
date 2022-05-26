package vanilla.utils;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MybatisWhere {

    private Map<String, Object> data = new HashMap<String, Object>();
    
    public MybatisWhere add(String key, Object value) {
        data.put(key, value);
        return this;
    }
}
