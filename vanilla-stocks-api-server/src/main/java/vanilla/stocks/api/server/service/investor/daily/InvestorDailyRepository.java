package vanilla.stocks.api.server.service.investor.daily;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorDailyRepository extends JpaRepository<InvestorDaily, String> {

    public InvestorDaily findOneByDateAndMarket(String date, String market);
}
