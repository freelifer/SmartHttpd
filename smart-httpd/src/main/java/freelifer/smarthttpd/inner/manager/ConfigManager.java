package freelifer.smarthttpd.inner.manager;

import java.io.InputStream;

import freelifer.smarthttpd.common.Converter;
import freelifer.smarthttpd.inner.model.Server;

/**
 * @author kzhu on 2017/7/27.
 */
public interface ConfigManager {

    void setConfigFile(String filePath);
    void setConverter(Converter<String, InputStream> converter);
    void parse();

    Server getServerInfo();
}
