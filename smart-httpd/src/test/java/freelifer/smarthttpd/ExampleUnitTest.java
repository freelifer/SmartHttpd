package freelifer.smarthttpd;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import freelifer.smarthttpd.common.Converter;
import freelifer.smarthttpd.inner.http.HttpServer;
import freelifer.smarthttpd.inner.log.Logger;
import freelifer.smarthttpd.inner.managerimpl.ConfigManagerImpl;
import freelifer.smarthttpd.sdk.SmartHttpd;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void server_start() {
        SmartHttpd.init(false, "server.xml", new Converter<String, InputStream>() {
            @Override
            public InputStream cover(String value) {
                try {
                    return new FileInputStream(value);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }


    @Test
    public void config_manager() {
        ConfigManagerImpl configManager = new ConfigManagerImpl();
        configManager.setConfigFile("server.xml");
        configManager.setConverter(new Converter<String, InputStream>() {
            @Override
            public InputStream cover(String value) {
                try {
                    return new FileInputStream(value);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });

        configManager.parse();
    }
}