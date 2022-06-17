package vanilla.os.collector.service.application.info;

import java.io.Serializable;

import lombok.Data;

@Data
public class ApplicationInfoPK implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer pid;
    private Long startTimestamp;
}
