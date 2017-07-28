package freelifer.smarthttpd.inner.model;

import java.util.List;

/**
 * @author kzhu on 2017/7/28.
 */
public final class ApplicationInfo {
//        <connector port="80" minProcessors="1" maxProcessors="1"
//        enableLookups="false" redirectPort="" acceptCount="0"
//        connectionTimeout="5000"/>

    private String name;
    private List<ServletInfo> servletInfos;

    public static ApplicationInfo create() {
        return new ApplicationInfo();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServletInfo> getServletInfos() {
        return servletInfos;
    }

    public void setServletInfos(List<ServletInfo> servletInfos) {
        this.servletInfos = servletInfos;
    }

    @Override
    public String toString() {
        return String.format("\n\t\tApplicationInfo:[name: %s, servletInfos: %s\n\t\t]",
                name, servletInfos);
    }
}