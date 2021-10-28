package vanilla.stocks.api.server.service.market;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarketDailyPK implements Serializable {

    private static final long serialVersionUID = 1L;
    private String date;
    private String name;
}
