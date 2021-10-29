package vanilla.stocks.scheduler.investor.daily;

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
@Table(name = "investor_daily")
@IdClass(InvestorDailyPK.class)
@Alias("InvestorDaily")
public class InvestorDaily {

    @Id
    @Column(nullable = false)
    private String date;
    
    @Id
    @Column(nullable = false)
    private String market;
    
    @Column(nullable = false)
    private Long personal;
    
    @Column(nullable = false)
    private Long foreginer;
    
    @Column(nullable = false)
    private Long agency;
    
    @Column(name = "investment_company", nullable = false)
    private Long investmentCompany;  // 금융투자사
    
    @Column(name = "insurance_company", nullable = false)
    private Long insuranceCompany;  // 보험사
    
    @Column(name = "private_equity", nullable = false)
    private Long privateEquity;  // 사모펀드
    
    @Column(nullable = false)
    private Long bank;
    
    @Column(name = "etc_institution", nullable = false)
    private Long etcInstitution;  // 기타 기관
    
    @Column(name = "pension_fund", nullable = false)
    private Long pensionFund;  // 연기금
    
    public InvestorDaily () {
    }
    
    public InvestorDaily (Map<String, Object> map) {
        this.date = String.valueOf(map.get("date"));
        this.market = String.valueOf(map.get("market"));
        this.personal = VanillaStringUtils.toLong(map.get("personal"));
        this.foreginer = VanillaStringUtils.toLong(map.get("foreginer"));
        this.agency = VanillaStringUtils.toLong(map.get("agency"));
        this.investmentCompany = VanillaStringUtils.toLong(map.get("investment_company"));
        this.insuranceCompany = VanillaStringUtils.toLong(map.get("insurance_company"));
        this.privateEquity = VanillaStringUtils.toLong(map.get("private_equity"));
        this.bank = VanillaStringUtils.toLong(map.get("bank"));
        this.etcInstitution = VanillaStringUtils.toLong(map.get("etc_institution"));
        this.pensionFund = VanillaStringUtils.toLong(map.get("pension_fund"));
    }
}
