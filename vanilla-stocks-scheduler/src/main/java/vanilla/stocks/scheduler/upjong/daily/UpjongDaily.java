package vanilla.stocks.scheduler.upjong.daily;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import vanilla.commons.util.string.VanillaStringUtils;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "upjong_daily")
@IdClass(UpjongDailyPK.class)
@Alias("UpjongDaily")
public class UpjongDaily {

    @Id
    @Column(nullable = false)
    private String date;
    
    @Id
    @Column(nullable = false)
    private String no;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, name = "change_rate")
    private Float changeRate;
    
    public UpjongDaily() {
    }
    
    public UpjongDaily(String date, Map<String, Object> map) {
        this.date = date;
        this.no = String.valueOf(map.get("no"));
        this.name = String.valueOf(map.get("name"));
        this.changeRate = VanillaStringUtils.toFloat(String.valueOf(map.get("changeRate")));
    }
}
