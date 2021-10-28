package vanilla.stocks.api.server.service.market;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import vanilla.stocks.api.server.config.SqlMapperWhere;

@Mapper
public interface MarketDailySqlMapper {

    public int count(@Param("where") SqlMapperWhere where);
    
    public List<MarketDaily> findAll(@Param("where") SqlMapperWhere where);
    
    public MarketDaily findOne(@Param("where") SqlMapperWhere where);
    
    public MarketDaily findOneLastDate(@Param("where") SqlMapperWhere where);
}
