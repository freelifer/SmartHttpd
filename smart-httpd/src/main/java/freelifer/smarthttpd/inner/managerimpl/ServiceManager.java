package freelifer.smarthttpd.inner.managerimpl;

import java.util.HashMap;
import java.util.Map;

import freelifer.smarthttpd.inner.log.Logger;
import freelifer.smarthttpd.inner.manager.IServiceManager;

/**
 * @author kzhu on 2017/7/26.
 */
public class ServiceManager {
    final static Map<String, Object> managerMap = new HashMap<>();
    static {
        managerMap.put(IServiceManager.CONFIG_MANAGER, new ConfigManagerImpl());
    }

    public static IServiceManager getInstance() {
        return Singleton.instance;
    }

    private static class Singleton {
        private static final IServiceManager instance = new ServiceManagerImpl(managerMap);
    }
}
