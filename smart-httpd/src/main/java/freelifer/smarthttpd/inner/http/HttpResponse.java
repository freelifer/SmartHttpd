package freelifer.smarthttpd.inner.http;

import java.nio.channels.SelectionKey;

import freelifer.smarthttpd.inner.context.Response;

/**
 * @author kzhu on 2017/7/26.
 */
public class HttpResponse implements Response {

    private SelectionKey key;
    private String contentType = "text/html";
    private int StatuCode = 200;
    private String statuCodeStr = "OK";
    private String htmlFile = "";

    public HttpResponse(SelectionKey key) {
        this.key = key;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public int getStatuCode() {
        return StatuCode;
    }

    @Override
    public SelectionKey getKey() {
        return key;
    }

    @Override
    public String getStatuCodeStr() {
        return statuCodeStr;
    }

    @Override
    public String getHtmlFile() {
        return htmlFile;
    }

    @Override
    public void setHtmlFile(String htmlFile) {
        this.htmlFile = htmlFile;
    }

    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public void setStatuCode(int statuCode) {
        StatuCode = statuCode;
    }

    @Override
    public void setStatuCodeStr(String statuCodeStr) {
        this.statuCodeStr = statuCodeStr;
    }
}
