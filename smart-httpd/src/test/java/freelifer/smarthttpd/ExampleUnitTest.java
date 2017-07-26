package freelifer.smarthttpd;

import org.junit.Test;

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
        Logger log = Logger.getLogger(false, ExampleUnitTest.class);
        log.i("Hi, %s.", "kzhu");
    }
}