package vanilla.stocks.api.server.service.market;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import vanilla.commons.util.string.VanillaStringUtils;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "market_daily")
@IdClass(MarketDailyPK.class)
@Alias("MarketDaily")
public class MarketDaily {

    @Id
    @Column(nullable = false)
    private String date;
    
    @Id
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Float jisu;
    
    @Column(nullable = false, name = "change_value")
    private Float changeValue;
    
    @Column(nullable = false, name = "change_rate")
    private Float changeRate;
    
    @Column(nullable = false, name = "trade_volume")
    private Long tradeVolume;
    
    @Column(nullable = false, name = "trade_price")
    private Long tradePrice;
    
    public MarketDaily() {
    }
    
    public MarketDaily(Map<String, Object> map) {
        this.date = String.valueOf(map.get("date"));
        this.name = String.valueOf(map.get("market"));
        this.jisu = VanillaStringUtils.toFloat(String.valueOf(map.get("jisu")));
        this.changeValue = VanillaStringUtils.toFloat(String.valueOf(map.get("changeValue")));
        this.changeRate = VanillaStringUtils.toFloat(String.valueOf(map.get("changeRate")));
        this.tradeVolume = VanillaStringUtils.toLong(String.valueOf(map.get("tradeVolume")));
        this.tradePrice = VanillaStringUtils.toLong(String.valueOf(map.get("tradePrice")));
    }
}
