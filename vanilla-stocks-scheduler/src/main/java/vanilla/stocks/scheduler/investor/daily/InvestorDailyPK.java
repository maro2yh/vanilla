package vanilla.stocks.scheduler.investor.daily;

import java.io.Serializable;

import lombok.Data;

@Data
public class InvestorDailyPK implements Serializable {

    private static final long serialVersionUID = 1L;
    private String date;
    private String market;
}
