package vanilla.os.collector.service.system.info;

import lombok.Data;

@Data
public class OSInfo {

    private String name;
    private String version;
    private Integer bit;
    private String buildNumber;
    private String codeName;
    private String arch;
    private String famaily;
    private String manufacturer;
    private Long bootTime;
    private Long upTime;
}
