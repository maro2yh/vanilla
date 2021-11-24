package vanilla.stocks.ebestapi.session;

import lombok.Data;

@Data
public class ConnectInfo {

    private String serverIp;
    private int serverPort;
    private int connectTimeOut;
    private int requestReadTimeOut;
    private String id;
    private String password;
    private String cPassword;
}
