package vanilla.stocks.scheduler.market.status;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "market_status")
@Alias("MarketStatus")
public class MarketStatus {

    @Id
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Float jisu;
    
    @Column(nullable = false, name = "change_price")
    private Float change;
    
    @Column(nullable = false, name = "change_rate")
    private Float changeRate;
    
    public MarketStatus () {
    }
    
    public MarketStatus(String name, Map<String, Object> map) {
        this.name = name;
        this.jisu = VanillaStringUtils.toFloat(String.valueOf(map.get("jisu")));
        this.change = VanillaStringUtils.toFloat(String.valueOf(map.get("change")));
        this.changeRate = VanillaStringUtils.toFloat(String.valueOf(map.get("changeRate")));
    }
}
