package vanilla.os.collector.service.system.info;

import lombok.Data;

@Data
public class NetworkInterfaceInfo {

    private int index;
    private String name;
    private String displayName;
    private String alias;
    private int type;
    private int ndisPhysicalMediumType;
    private long collisions;
    private String status;
    private String ipv4Address;
    private String ipv6Address;
    private String macAddress;
    private String subnetMask;
    private long timestamp;
}
