package vanilla.os.collector.service.application.info;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Application")
@Table(name = "application")
@IdClass(ApplicationInfoPK.class)
@Alias("Application")
public class ApplicationInfo {

    @Id
    @Column(name = "pid", columnDefinition = "int", nullable = false)
    private Integer pid;
    
    @Id
    @Column(name = "start_timestamp", columnDefinition = "bigint", nullable = false)
    private Long startTimestamp;
    
    @Column(name = "name", columnDefinition = "varchar", length = 50, nullable = false)
    private String name;
    
    @Column(name = "version", columnDefinition = "varchar", length = 10, nullable = false)
    private String version;
    
    @Column(name = "local_address", columnDefinition = "varchar", length = 25, nullable = false)
    private String localAddress;
    
    @Column(name = "home", columnDefinition = "varchar", length = 500, nullable = false)
    private String home;
    
    @Column(name = "log_path", columnDefinition = "varchar", length = 500, nullable = false)
    private String logPath;
    
    @Column(name = "log_filename", columnDefinition = "varchar", length = 50, nullable = false)
    private String logFilename;
    
    @Column(name = "log_level", columnDefinition = "varchar", length = 10, nullable = false)
    private String logLevel;
    
    @Column(name = "log_max_history", columnDefinition = "varchar", length = 10, nullable = false)
    private String logMaxHistory;
    
    @Column(name = "log_max_size", columnDefinition = "varchar", length = 10, nullable = false)
    private String logMaxSize;
    
    @Column(name = "log_mode", columnDefinition = "varchar", length = 10, nullable = false)
    private String logMode;
    
    @Column(name = "java_version", columnDefinition = "varchar", length = 30, nullable = true)
    private String javaVersion;
}
