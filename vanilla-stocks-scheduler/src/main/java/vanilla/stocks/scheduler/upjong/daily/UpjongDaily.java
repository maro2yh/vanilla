package vanilla.stocks.scheduler.upjong.daily;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import vanilla.stocks.scheduler.upjong.status.UpjongStatus;

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
    
    public UpjongDaily(String date, UpjongStatus upjongStatus) {
        this.date = date;
        this.no = upjongStatus.getNo();
        this.name = upjongStatus.getName();
        this.changeRate = upjongStatus.getChangeRate();
    }
}
