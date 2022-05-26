package vanilla.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MybatisObject {

    private MybatisWhere where;
    private MybatisOrder orders;
    private MybatisPaging paging;
}
