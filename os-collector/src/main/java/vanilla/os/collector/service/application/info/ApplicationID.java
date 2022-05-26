package vanilla.os.collector.service.application.info;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "ApplicationID")
@Table(name = "application_id")
@Alias("ApplicationID")
public class ApplicationID {

    @Id
    @Column(name = "id", columnDefinition = "varchar", length = 50, nullable = false)
    private String id;
    
    @Column(name = "create_timestamp", columnDefinition = "bigint", nullable = false)
    private Long createTimestamp;
}
