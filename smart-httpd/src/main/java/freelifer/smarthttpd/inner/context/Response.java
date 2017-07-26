package freelifer.smarthttpd.inner.context;

import java.nio.channels.SelectionKey;

/**
 * @author kzhu on 2017/7/26.
 */
public interface Response {
    public static final String SERVER_NAME = "android-server";
//    public static final String SERVER_NAME = XMLUtil.getRootElement("server.xml").element("serverName").getText();

    public String getContentType();

    public int getStatuCode();

    public String getStatuCodeStr();

    public String getHtmlFile();

    public void setHtmlFile(String htmlFile);

    public SelectionKey getKey();

    public void setContentType(String contentType);

    public void setStatuCode(int statuCode);

    public void setStatuCodeStr(String statuCodeStr);
}
