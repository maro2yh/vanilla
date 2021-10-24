package vanilla.stocks.api.server.db.h2.market;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketDailyRepository extends JpaRepository<MarketDaily, String> {

}
