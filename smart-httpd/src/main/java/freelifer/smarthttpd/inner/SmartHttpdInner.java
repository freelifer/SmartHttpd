package freelifer.smarthttpd.inner;

import java.io.InputStream;

import freelifer.smarthttpd.common.Converter;
import freelifer.smarthttpd.inner.http.HttpServer;
import freelifer.smarthttpd.inner.manager.ConfigManager;
import freelifer.smarthttpd.inner.manager.IServiceManager;
import freelifer.smarthttpd.inner.managerimpl.ServiceManager;
import freelifer.smarthttpd.inner.model.Server;

import static freelifer.smarthttpd.inner.manager.IServiceManager.CONFIG_MANAGER;

/**
 * @author kzhu on 2017/7/28.
 */
public class SmartHttpdInner {

    public static void init(boolean async, String configPath, Converter<String, InputStream> converter) {
        IServiceManager serviceManager = ServiceManager.getInstance();
        initServerConfig(serviceManager, configPath, converter);
        initServer(serviceManager, async);
    }

    private static void initServerConfig(IServiceManager sm, String configPath, Converter<String, InputStream> converter) {
        ConfigManager configManager = sm.getSystemService(CONFIG_MANAGER);
        configManager.setConfigFile(configPath);
        configManager.setConverter(converter);
        configManager.parse();
    }

    private static void initServer(IServiceManager sm, boolean async) {
        ConfigManager configManager = sm.getSystemService(CONFIG_MANAGER);
        Server server = configManager.getServerInfo();

        if (async) {
            new Thread(new HttpServer(false, server)).start();
        } else {
            new HttpServer(false, server).run();
        }
    }


}