package vanilla.stocks.scheduler.db.system.error;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vanilla.commons.util.calendar.VanillaCalendarUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "system_error")
@IdClass(SystemErrorPK.class)
@Alias("SystemError")
public class SystemError {

    @Id
    @Column(nullable = false, name = "created_datetime")
    private String createdDateTime;
    
    @Id
    @Column(nullable = false)
    private String system;
    
    @Column(nullable = false)
    private String message;
    
    public SystemError(String message) {
        this.createdDateTime = VanillaCalendarUtils.now();
        this.system = "SCHEDULER";
        this.message = message;
    }
}
