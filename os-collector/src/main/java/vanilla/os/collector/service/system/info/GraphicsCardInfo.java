package vanilla.os.collector.service.system.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphicsCardInfo {

    private String deviceId;
    private String vendor;
    private String name;
    private String version;
    private long ram;
}
