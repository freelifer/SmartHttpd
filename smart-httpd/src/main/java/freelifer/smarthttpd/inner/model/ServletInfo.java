package freelifer.smarthttpd.inner.model;

/**
 * @author kzhu on 2017/7/28.
 */
public class ServletInfo {

    private String url;
    private String servletClass;

    public static ServletInfo create() {
        return new ServletInfo();
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServletClass() {
        return servletClass;
    }

    public void setServletClass(String servletClass) {
        this.servletClass = servletClass;
    }

    @Override
    public String toString() {
        return String.format("\n\t\t\tServletInfo:[url: %s,servletClass: %s]",
                url, servletClass);
    }
}
