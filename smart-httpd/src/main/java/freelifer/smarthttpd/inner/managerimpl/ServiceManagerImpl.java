package freelifer.smarthttpd.inner.managerimpl;

import java.util.HashMap;
import java.util.Map;

import freelifer.smarthttpd.inner.log.Logger;
import freelifer.smarthttpd.inner.manager.IServiceManager;

/**
 * @author kzhu on 2017/7/26.
 */
public class ServiceManagerImpl implements IServiceManager {

    static final Logger log = Logger.getLogger(true, ServiceManager.class);

    private Map<String, Object> managerMap = new HashMap<>();

    ServiceManagerImpl() {
        managerMap.put(CONFIG_MANAGER, new ConfigManagerImpl());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getSystemService(String serverName) {
        return (T) managerMap.get(serverName);
    }
}
