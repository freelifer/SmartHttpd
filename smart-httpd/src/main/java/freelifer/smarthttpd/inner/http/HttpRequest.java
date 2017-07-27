package freelifer.smarthttpd.inner.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import freelifer.smarthttpd.inner.context.Request;

/**
 * @author kzhu on 2017/7/26.
 */
public class HttpRequest implements Request {

    private Map<String, Object> attribute = new HashMap<>();

    private Map<String, Object> headers = new HashMap<>();

    private String method;

    private String uri;

    private String protocol;

    public HttpRequest(String httpHeader) {
        init(httpHeader);
    }

    private void init(String httpHeader) {
        String[] headers = httpHeader.split("\r\n");
        initMethod(headers[0]);
        initURI(headers[0]);
        initProtocol(headers[0]);
        initRequestHeaders(headers);
    }

    private void initMethod(String str) {
        method = str.substring(0, str.indexOf(" "));
    }


    private void initAttribute(String attr) {
        String[] attrs = attr.split("&");
        for (String string : attrs) {
            String key = string.substring(0, string.indexOf("="));
            String value = string.substring(string.indexOf("=") + 1);
            attribute.put(key, value);
        }
    }

    private void initURI(String str) {
        uri = str.substring(str.indexOf(" ") + 1, str.indexOf(" ", str.indexOf(" ") + 1));
        if (method.toUpperCase().equals("GET")) {
            if (uri.contains("?")) {
                String attr = uri.substring(uri.indexOf("?") + 1, uri.length());
                uri = uri.substring(0, uri.indexOf("?"));
                initAttribute(attr);
            }
        }
    }

    private void initRequestHeaders(String[] strs) {
        for (int i = 1; i < strs.length; i++) {
            String key = strs[i].substring(0, strs[i].indexOf(":"));
            String value = strs[i].substring(strs[i].indexOf(":") + 1);
            headers.put(key, value);
        }
    }

    private void initProtocol(String str) {
        protocol = str.substring(str.lastIndexOf(" ") + 1, str.length());
    }

    @Override
    public Map<String, Object> getAttribute() {
        return attribute;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public Map<String, Object> getHeaders() {
        return headers;
    }

    @Override
    public Set<String> getHeaderNames() {
        return headers.keySet();
    }

    @Override
    public Object getHeader(String key) {
        return headers.get(key);
    }
}
