package vanilla.stocks.scheduler.investor.rank;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import vanilla.stocks.html.naver.InvestorRankPage;
import vanilla.stocks.scheduler.system.error.SystemError;
import vanilla.stocks.scheduler.system.error.SystemErrorRepository;

@Slf4j
@Component
public class InvestorRankTask {
    
    @Autowired
    private InvestorRankRepository investorRankRepository;
    
    @Autowired
    private SystemErrorRepository systemErrorRepository;
    
    @Scheduled(cron = "0 30 8 * * *")
    public void execute() {
        log.debug("Start the 'InvestorRank' scheduler.");
        
        List<Map<String, Object>> kospiForeignerBuyList = null;
        List<Map<String, Object>> kospiForeignerSellList = null;
        List<Map<String, Object>> kospiAgencyBuyList = null;
        List<Map<String, Object>> kospiAgencySellList = null;
        List<Map<String, Object>> kosdaqForeignerBuyList = null;
        List<Map<String, Object>> kosdaqForeignerSellList = null;
        List<Map<String, Object>> kosdaqAgencyBuyList = null;
        List<Map<String, Object>> kosdaqAgencySellList = null;
        
        try {
            kospiForeignerBuyList = new InvestorRankPage().getList("kospi", "foreigner", "buy");
            kospiForeignerSellList = new InvestorRankPage().getList("kospi", "foreigner", "sell");
            kospiAgencyBuyList = new InvestorRankPage().getList("kospi", "agency", "buy");
            kospiAgencySellList = new InvestorRankPage().getList("kospi", "agency", "sell");
            kosdaqForeignerBuyList = new InvestorRankPage().getList("kospi", "foreigner", "buy");
            kosdaqForeignerSellList = new InvestorRankPage().getList("kospi", "foreigner", "sell");
            kosdaqAgencyBuyList = new InvestorRankPage().getList("kospi", "agency", "buy");
            kosdaqAgencySellList = new InvestorRankPage().getList("kospi", "agency", "sell");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            systemErrorRepository.save(new SystemError(e.getMessage()));
            return;
        }
        
        for (Map<String, Object> map : kospiForeignerBuyList) {
            investorRankRepository.save(InvestorRank.createForeignerBuyData("kospi", map));
        }
        
        for (Map<String, Object> map : kospiForeignerSellList) {
            investorRankRepository.save(InvestorRank.createForeignerSellData("kospi", map));
        }
        
        for (Map<String, Object> map : kosdaqForeignerBuyList) {
            investorRankRepository.save(InvestorRank.createForeignerBuyData("kosdaq", map));
        }
        
        for (Map<String, Object> map : kosdaqForeignerSellList) {
            investorRankRepository.save(InvestorRank.createForeignerSellData("kosdaq", map));
        }
        
        for (Map<String, Object> map : kospiAgencyBuyList) {
            investorRankRepository.save(InvestorRank.createAgencyBuyData("kospi", map));
        }
        
        for (Map<String, Object> map : kospiAgencySellList) {
            investorRankRepository.save(InvestorRank.createAgencySellData("kospi", map));
        }
        
        for (Map<String, Object> map : kosdaqAgencyBuyList) {
            investorRankRepository.save(InvestorRank.createAgencyBuyData("kosdaq", map));
        }
        
        for (Map<String, Object> map : kosdaqAgencySellList) {
            investorRankRepository.save(InvestorRank.createAgencySellData("kosdaq", map));
        }
        
        log.debug("Completed the 'InvestorRank' scheduler.");
    }
}
