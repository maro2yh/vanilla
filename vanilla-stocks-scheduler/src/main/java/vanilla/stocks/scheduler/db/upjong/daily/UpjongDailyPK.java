package vanilla.stocks.scheduler.db.upjong.daily;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpjongDailyPK implements Serializable {

    private static final long serialVersionUID = 1L;
    private String createdDateTime;
    private String no;
}
