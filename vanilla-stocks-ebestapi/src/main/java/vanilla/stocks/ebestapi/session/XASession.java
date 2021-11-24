package vanilla.stocks.ebestapi.session;

import com4j.COM4J;
import com4j.EventCookie;

public class XASession extends Thread {

    private ConnectInfo connectInfo;

    private IXASession ixaSession;
    private EventCookie eventCookie;

    public XASession(ConnectInfo connectInfo) {
        super("XASession");
        this.connectInfo = connectInfo;
    }

    /**
     * 연결 확인
     * 
     * @return 연결 여부
     */
    public boolean isConnected() {
        if (ixaSession == null)
            return false;
        return ixaSession.isConnected();
    }

    /**
     * 연결
     * 
     * @param connectInfo 대상 서버 정보
     * @return 연결 여부
     */
    private boolean connect() {
        try {
            this.ixaSession.connectTimeOut(connectInfo.getConnectTimeOut());
            return this.ixaSession.connectServer(connectInfo.getServerIp(), connectInfo.getServerPort());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 로그인 요청
     * 
     * @return 응답
     * @throws InterruptedException    wait
     * @throws RequestTimeOutException 타임 아웃
     * @throws ConnectFailException    연결 실패
     */
    public synchronized Response login() throws InterruptedException, RequestTimeOutException, ConnectFailException {

        final XASessionEventHandler xaSessionEventHandler = new XASessionEventHandler();
        this.ixaSession = XASessionFactory.createXASession();
        this.eventCookie = this.ixaSession.advise(IXASessionEvents.class, xaSessionEventHandler);

        if (connect()) {
            this.ixaSession.login(connectInfo.getId(), connectInfo.getPassword(), connectInfo.getCPassword(), XA_SERVER_TYPE.XA_REAL_SERVER.comEnumValue(), false);

            synchronized (xaSessionEventHandler) {
                long startTime = System.currentTimeMillis();
                xaSessionEventHandler.wait(connectInfo.getRequestReadTimeOut());
                long elapsedTime = System.currentTimeMillis() - startTime;

                if (elapsedTime >= connectInfo.getRequestReadTimeOut())
                    throw new RequestTimeOutException(connectInfo.getRequestReadTimeOut());
            }
            return xaSessionEventHandler.getResponse();
        } else {
            throw new ConnectFailException();
        }
    }

    /**
     * session close
     */
    public synchronized void close() {
        try {
            if (this.ixaSession != null)
                this.ixaSession.disconnectServer();
        } catch (Exception e) {
        }

        try {
            if (this.eventCookie != null)
                this.eventCookie.close();
        } catch (Exception e) {
        }

        COM4J.cleanUp();
    }
}
