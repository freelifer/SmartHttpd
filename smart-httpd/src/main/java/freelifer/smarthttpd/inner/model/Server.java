package freelifer.smarthttpd.inner.model;

/**
 * @author kzhu on 2017/7/28.
 */
public final class Server {
//        <connector port="80" minProcessors="1" maxProcessors="1"
//        enableLookups="false" redirectPort="" acceptCount="0"
//        connectionTimeout="5000"/>

    private String name;
    private String port;
    private String minProcessors;
    private String maxProcessors;
    private String enableLookups;
    private String redirectPort;
    private String acceptCount;
    private String connectionTimeout;
    private ApplicationInfo applicationInfo;

    public static Server create() {
        return new Server();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMinProcessors() {
        return minProcessors;
    }

    public void setMinProcessors(String minProcessors) {
        this.minProcessors = minProcessors;
    }

    public String getMaxProcessors() {
        return maxProcessors;
    }

    public void setMaxProcessors(String maxProcessors) {
        this.maxProcessors = maxProcessors;
    }

    public String getEnableLookups() {
        return enableLookups;
    }

    public void setEnableLookups(String enableLookups) {
        this.enableLookups = enableLookups;
    }

    public String getRedirectPort() {
        return redirectPort;
    }

    public void setRedirectPort(String redirectPort) {
        this.redirectPort = redirectPort;
    }

    public String getAcceptCount() {
        return acceptCount;
    }

    public void setAcceptCount(String acceptCount) {
        this.acceptCount = acceptCount;
    }

    public String getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(String connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public ApplicationInfo getApplicationInfo() {
        return applicationInfo;
    }

    public void setApplicationInfo(ApplicationInfo applicationInfo) {
        this.applicationInfo = applicationInfo;
    }

    @Override
    public String toString() {
        return String.format("Server:[\n\tname: %s,\n\tport: %s,\n\tminProcessors: %s,\n\tmaxProcessors: %s," +
                        "\n\tenableLookups: %s,\n\tredirectPort: %s,\n\tacceptCount: %s,\n\tconnectionTimeout: %s," +
                        "\n\tapplicationInfo: %s\n]",
                name,port, minProcessors, maxProcessors, enableLookups, redirectPort, acceptCount, connectionTimeout, applicationInfo);
    }
}