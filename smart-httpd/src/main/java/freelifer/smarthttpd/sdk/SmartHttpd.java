package freelifer.smarthttpd.sdk;

import java.io.InputStream;

import freelifer.smarthttpd.common.Converter;
import freelifer.smarthttpd.inner.SmartHttpdInner;

/**
 * @author kzhu on 2017/7/28.
 */
public class SmartHttpd {

    public static void init(boolean async,  String configPath, Converter<String, InputStream> converter) {
        SmartHttpdInner.init(async, configPath, converter);
    }
}
