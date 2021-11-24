package vanilla.stocks.ebestapi.session;

import java.util.UUID;

public class XASessionEventHandler extends IXASessionEvents {

    private Response response;

    public XASessionEventHandler() {
        this.response = new Response(UUID.randomUUID().toString());
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public void login(String szCode, String szMsg) {
        response.putHeader("szCode", szCode);
        response.putHeader("szMsg", szMsg);

        synchronized (this){
            this.notify();
        }
    }

    @Override
    public void logout() {
    }

    @Override
    public void disconnect() {
    }
}
