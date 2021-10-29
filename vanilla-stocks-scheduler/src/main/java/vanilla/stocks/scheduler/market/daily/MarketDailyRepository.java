package vanilla.stocks.scheduler.market.daily;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketDailyRepository extends JpaRepository<MarketDaily, String> {

    public int countByDateAndName(String date, String name);
}
