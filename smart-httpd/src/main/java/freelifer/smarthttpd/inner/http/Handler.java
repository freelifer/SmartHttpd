package freelifer.smarthttpd.inner.http;

import freelifer.smarthttpd.inner.context.Context;

/**
 * @author kzhu on 2017/7/26.
 */
public interface Handler {

    void init(Context context);

    void service(Context context);

    void doGet(Context context);

    void doPost(Context context);

    void destory(Context context);
}
