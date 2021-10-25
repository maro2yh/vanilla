package vanilla.stocks.scheduler.investor.rank;

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
@Table(name = "investor_rank")
@IdClass(InvestorRankPK.class)
@Alias("InvestorRank")
public class InvestorRank {

    @Id
    @Column(nullable = false)
    private String date;

    @Id
    @Column(nullable = false)
    private String market;

    @Id
    @Column(nullable = false)
    private String investor;

    @Id
    @Column(nullable = false)
    private String type;

    @Id
    @Column(nullable = false)
    private Integer rank;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Long trade;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Long volume;

    public InvestorRank() {
    }
    
    public InvestorRank(String market, String investor, String type, Map<String, Object> map) {
        this.market = market;
        this.investor = investor;
        this.type = type;
        this.date = String.valueOf(map.get("date"));
        this.rank = VanillaStringUtils.toInteger(String.valueOf(map.get("rank")));
        this.name = String.valueOf(map.get("name"));
        this.code = String.valueOf(map.get("code"));
        this.trade = VanillaStringUtils.toLong(String.valueOf(map.get("trade")));
        this.amount = VanillaStringUtils.toLong(String.valueOf(map.get("amount")));
        this.volume = VanillaStringUtils.toLong(String.valueOf(map.get("volume")));
    }

    public static InvestorRank createForeignerBuyData(String market, Map<String, Object> map) {
        return new InvestorRank(market, "FOREIGNER", "BUY", map);
    }

    public static InvestorRank createForeignerSellData(String market, Map<String, Object> map) {
        return new InvestorRank(market, "FOREIGNER", "SELL", map);
    }

    public static InvestorRank createAgencyBuyData(String market, Map<String, Object> map) {
        return new InvestorRank(market, "AGENCY", "BUY", map);
    }
    
    public static InvestorRank createAgencySellData(String market, Map<String, Object> map) {
        return new InvestorRank(market, "AGENCY", "SELL", map);
    }
}
