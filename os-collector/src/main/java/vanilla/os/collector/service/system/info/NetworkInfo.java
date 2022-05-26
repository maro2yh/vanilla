package vanilla.os.collector.service.system.info;

import lombok.Data;

@Data
public class NetworkInfo {

    private String ip;
    private String dns;
    private String domainName;
    private String ipv4DefaultGateway;
    private String ipv6DefaultGateway;
}
