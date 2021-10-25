package vanilla.stocks.scheduler.upjong.status;

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
@Table(name = "upjong_status")
@Alias("UpjongStatus")
public class UpjongStatus {

    @Id
    @Column(nullable = false)
    private String no;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, name = "change_rate")
    private Float changeRate;
    
    @Column(nullable = false, name = "update_date")
    private String updateDate;
    
    public UpjongStatus() {
    }
    
    public UpjongStatus(Map<String, Object> map, String updateDate) {
        this.no = String.valueOf(map.get("no"));
        this.name = String.valueOf(map.get("name"));
        this.changeRate = VanillaStringUtils.toFloat(String.valueOf(map.get("changeRate")));
        this.updateDate = updateDate;
    }
}
