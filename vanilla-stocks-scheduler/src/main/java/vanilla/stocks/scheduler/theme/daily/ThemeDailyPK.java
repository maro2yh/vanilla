package vanilla.stocks.scheduler.theme.daily;

import java.io.Serializable;

import lombok.Data;

@Data
public class ThemeDailyPK implements Serializable {

    private static final long serialVersionUID = 1L;
    private String date;
    private String no;
}
