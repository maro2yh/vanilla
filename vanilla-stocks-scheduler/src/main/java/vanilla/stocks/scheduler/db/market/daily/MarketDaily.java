package vanilla.stocks.scheduler.db.market.daily;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
    
    @Column(nullable = false, name = "change_price")
    private Float change;
    
    @Column(nullable = false, name = "change_rate")
    private Float changeRate;
    
    @Column(nullable = false)
    private Long volume;
    
    @Column(nullable = false)
    private Long amount;
}