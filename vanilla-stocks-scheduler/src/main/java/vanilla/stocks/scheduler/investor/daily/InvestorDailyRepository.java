package vanilla.stocks.scheduler.investor.daily;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorDailyRepository extends JpaRepository<InvestorDaily, String> {

    public int countByDateAndMarket(String date, String market);
}
