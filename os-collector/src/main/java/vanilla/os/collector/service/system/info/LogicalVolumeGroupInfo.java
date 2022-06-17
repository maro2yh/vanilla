package vanilla.os.collector.service.system.info;

import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogicalVolumeGroupInfo {

    private String groupName;
    private Map<String, Set<String>> logicalVolumes;
    private Set<String> physicalVolumes;
}
