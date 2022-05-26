package vanilla.utils;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MybatisOrder {

    private List<String> orders = null;
    
    public MybatisOrder() {
        orders = new ArrayList<String>();
    }
    
    public MybatisOrder add(String order) {
        orders.add(order);
        return this;
    }
}
