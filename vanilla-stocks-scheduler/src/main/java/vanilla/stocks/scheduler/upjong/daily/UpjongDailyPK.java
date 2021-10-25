package vanilla.stocks.scheduler.upjong.daily;

import java.io.Serializable;

import lombok.Data;

@Data
public class UpjongDailyPK implements Serializable {

    private static final long serialVersionUID = 1L;
    private String date;
    private String no;
}
