package freelifer.smarthttpd.inner.http;

import freelifer.smarthttpd.inner.context.Context;
import freelifer.smarthttpd.inner.context.Response;
import freelifer.smarthttpd.inner.log.Logger;

/**
 * @author kzhu on 2017/7/27.
 */
public class NotFoundHandler extends AbstractHandler {

    private Logger logger = Logger.getLogger(true, NotFoundHandler.class);
    private Response response;

    @Override
    public void doGet(Context context) {
        logger.i("404 Handler");
        response = context.getResponse();

        response.setStatuCode(404);
        response.setStatuCodeStr("Not Found");
        response.setHtmlFile("404.html");
    }
}