package vanilla.stocks.scheduler.theme.status;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ThemeStatusSqlMapper {

    @Delete("DELETE FROM theme_status where update_date != #{updateDate}")
    public int deleteNotUpdated(String updateDate);
}
