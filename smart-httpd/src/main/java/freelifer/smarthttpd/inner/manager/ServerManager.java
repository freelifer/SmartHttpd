package freelifer.smarthttpd.inner.manager;

import java.io.IOException;
import java.io.InputStream;

import freelifer.smarthttpd.inner.log.Logger;
import freelifer.smarthttpd.inner.util.IOUtils;

/**
 * @author kzhu on 2017/7/26.
 */
public class ServerManager {

    static final Logger log = Logger.getLogger(false, ServerManager.class);

    public static ServerManager getInstance() {
        return Singleton.instance;
    }

    private String configData;

    private ServerManager() {
    }

    private static class Singleton {
        private static final ServerManager instance = new ServerManager();
    }

    public void setConfigInputStream(InputStream is) {
        try {
            configData = IOUtils.getStringFromInputStream(is);
        } catch (IOException e) {
            log.e("ServerManager set Config failure!");
        } finally {
            IOUtils.close(is);
        }
    }

    public Object getServer(String serverName) {
        return null;
    }


}
