package vanilla.stocks.api.server.service.investor.daily;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import vanilla.stocks.api.server.config.SqlMapperWhere;

@Mapper
public interface InvestorDailySqlMapper {

    public List<InvestorDaily> findAll(@Param("where") SqlMapperWhere where);
    
    public InvestorDaily findOneLastDate(@Param("where") SqlMapperWhere where);
}
