package freelifer.smarthttpd.inner.http;

import java.nio.channels.SelectionKey;

import freelifer.smarthttpd.inner.context.Context;
import freelifer.smarthttpd.inner.log.Logger;

/**
 * @author kzhu on 2017/7/26.
 */
public class HttpHandler implements Runnable {

    private SelectionKey key;
    private Context context = new HttpContext();
    private String requestHeader;
    private Handler handler;
    private Logger logger = Logger.getLogger(true, HttpHandler.class);

    public HttpHandler(String requestHeader, SelectionKey key) {
        this.key = key;
        this.requestHeader = requestHeader;
    }

    @Override
    public void run() {
        context.setContext(requestHeader, key);
        String uri = context.getRequest().getUri();
        logger.i("HttpHandler uri %s", uri);
        handler = null;
//        handler = MapHandler.getContextMapInstance().getHandlerMap().get(uri);
        if (handler == null) {
            handler = new NotFoundHandler();
        }
        handler.init(context);
    }

}
