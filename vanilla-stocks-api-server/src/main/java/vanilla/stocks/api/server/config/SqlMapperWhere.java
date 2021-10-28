package vanilla.stocks.api.server.config;

import java.util.HashMap;
import java.util.Map;

public class SqlMapperWhere {

    private Map<String, Object> data;
    
    public SqlMapperWhere() {
        this.data = new HashMap<String, Object>();
    }
    
    public SqlMapperWhere(Map<String, Object> data) {
        this.data = data;
    }
    
    public SqlMapperWhere(String key, Object value) {
        this.data = new HashMap<String, Object>();
        this.data.put(key, value);
    }
    
    public SqlMapperWhere add(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
    
    public SqlMapperWhere remove(String key) {
        this.data.remove(key);
        return this;
    }
    
    public SqlMapperWhere clear() {
        this.data.clear();
        return this;
    }
}
