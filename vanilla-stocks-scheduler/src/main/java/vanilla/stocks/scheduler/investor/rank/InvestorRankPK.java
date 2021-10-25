package vanilla.stocks.scheduler.investor.rank;

import java.io.Serializable;

import lombok.Data;

@Data
public class InvestorRankPK implements Serializable {

    private static final long serialVersionUID = 1L;
    private String date;
    private String market;
    private String investor;
    private String type;
    private Integer rank;
}
