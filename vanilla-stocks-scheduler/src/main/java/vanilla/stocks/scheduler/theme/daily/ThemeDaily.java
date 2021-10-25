package vanilla.stocks.scheduler.theme.daily;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import vanilla.stocks.scheduler.theme.status.ThemeStatus;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "theme_daily")
@IdClass(ThemeDailyPK.class)
@Alias("ThemeDaily")
public class ThemeDaily {

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
    
    public ThemeDaily() {
    }
    
    public ThemeDaily(String date, ThemeStatus themeStatus) {
        this.date = date;
        this.no = themeStatus.getNo();
        this.name = themeStatus.getName();
        this.changeRate = themeStatus.getChangeRate();
    }
}
