package vanilla.stocks.ebestapi.session;

public class ConnectFailException extends Exception{
    public ConnectFailException() {
        super("해당서버에 연결할 수 없습니다.");
    }
}
