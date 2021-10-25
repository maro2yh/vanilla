package vanilla.stocks.scheduler.system.error;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemErrorPK implements Serializable {

    private static final long serialVersionUID = 1L;
    private String createdDateTime;
    private String system;
}
