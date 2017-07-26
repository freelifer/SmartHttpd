package freelifer.smarthttpd.inner.http;

import java.nio.channels.SelectionKey;

import freelifer.smarthttpd.inner.context.Context;
import freelifer.smarthttpd.inner.context.Request;
import freelifer.smarthttpd.inner.context.Response;

/**
 * @author kzhu on 2017/7/26.
 */
public class HttpContext extends Context {

    private Request request;
    private Response response;

    @Override
    public void setContext(String requestHeader, SelectionKey key) {

        //初始化request
        request = new HttpRequest(requestHeader);
        //初始化response
        response = new HttpResponse(key);
        setRequest();
        setResponse();
    }

    private void setRequest() {
        super.request = this.request;
    }

    private void setResponse() {
        super.response = this.response;
    }
}
