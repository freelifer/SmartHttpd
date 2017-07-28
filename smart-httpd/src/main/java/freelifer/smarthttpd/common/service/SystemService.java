package freelifer.smarthttpd.common.service;

import freelifer.smarthttpd.inner.manager.IServiceManager;
import freelifer.smarthttpd.inner.managerimpl.ServiceManager;

/**
 * @author kzhu on 2017/7/28.
 */
public class SystemService {

    static {
        try {
            Class<?> cls = Class.forName("freelifer.smarthttpd.inner.managerimpl.ServiceManagerImpl");
            Object target = cls.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String[] serviceName = new String[]{IServiceManager.CONFIG_MANAGER};
    }

    public static void init() {
    }
}
