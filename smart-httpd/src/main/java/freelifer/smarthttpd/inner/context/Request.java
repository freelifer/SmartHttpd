package freelifer.smarthttpd.inner.context;

import java.util.Map;
import java.util.Set;

/**
 * @author kzhu on 2017/7/26.
 */
public interface Request {
    String POST = "POST";
    String GET = "GET";

    /**
     * 得到参数
     *
     * @param: @return
     * @return: Map<String,Object>
     * @Autor: Han
     */
    public Map<String, Object> getAttribute();

    /**
     * 得到请求方式
     *
     * @param: @return
     * @return: String
     * @Autor: Han
     */
    public String getMethod();

    /**
     * 得到URI
     *
     * @param: @return
     * @return: String
     * @Autor: Han
     */
    public String getUri();

    /**
     * 版本协议
     *
     * @param: @return
     * @return: String
     * @Autor: Han
     */
    public String getProtocol();

    /**
     * 得到请求头Map
     *
     * @param: @return
     * @return: String
     * @Autor: Han
     */
    public Map<String, Object> getHeaders();

    Set<String> getHeaderNames();

    /**
     * 根据请求头名得到对应的请求头
     *
     * @param: @return
     * @return: String
     * @Autor: Han
     */
    public Object getHeader(String key);
}
