package freelifer.smarthttpd;

import org.junit.Test;

import java.io.File;

import freelifer.smarthttpd.inner.http.HttpServer;
import freelifer.smarthttpd.inner.log.Logger;

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
    public void logi() {
        // 加油
        Logger log = Logger.getLogger(true, ExampleUnitTest.class);
        log.i("Hi, %s.", "kzhu");

        new Thread(new HttpServer(false)).start();

        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void config_manager() {
        File file = new File("server.xml");
        System.out.println(file.getAbsolutePath());
        System.out.println("file exists " + file.exists());
    }
}