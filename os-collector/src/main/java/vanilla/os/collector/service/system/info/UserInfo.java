package vanilla.os.collector.service.system.info;

import lombok.Data;

@Data
public class UserInfo {

    private String hostname;
    private String name;
    private String dir;
    private String home;
    private String country;
    private String language;
    private String timezone;
}
