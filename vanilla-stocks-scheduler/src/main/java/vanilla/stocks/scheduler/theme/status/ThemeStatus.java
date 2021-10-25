package vanilla.stocks.scheduler.theme.status;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "theme_status")
@Alias("ThemeStatus")
public class ThemeStatus {
    
    @Id
    @Column(nullable = false)
    private String no;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, name = "change_rate")
    private Float changeRate;
    
    @Column(nullable = false, name = "update_date")
    private String updateDate;

    public ThemeStatus() {
    }
    
    public ThemeStatus(Map<String, Object> map, String updateDate) {
        this.no = String.valueOf(map.get("no"));
        this.name = String.valueOf(map.get("name"));
        this.changeRate = VanillaStringUtils.toFloat(String.valueOf(map.get("changeRate")));
        this.updateDate = updateDate;
    }
}
