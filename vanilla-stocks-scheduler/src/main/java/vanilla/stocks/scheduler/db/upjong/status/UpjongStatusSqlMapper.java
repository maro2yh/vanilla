package vanilla.stocks.scheduler.db.upjong.status;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UpjongStatusSqlMapper {

    @Delete("DELETE FROM upjong_status where update_date != updateDate")
    public int deleteNotUpdated(String updateDate);
}
