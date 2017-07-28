package freelifer.smarthttpd.inner.managerimpl;

import java.util.HashMap;
import java.util.Map;

import freelifer.smarthttpd.inner.log.Logger;
import freelifer.smarthttpd.inner.manager.IServiceManager;

/**
 * @author kzhu on 2017/7/26.
 */
public class ServiceManagerImpl implements IServiceManager {

    private Map<String, Object> managerMap;

    public ServiceManagerImpl(Map<String, Object> managerMap) {
        this.managerMap = managerMap;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getSystemService(String serverName) {
        return (T) managerMap.get(serverName);
    }
}
