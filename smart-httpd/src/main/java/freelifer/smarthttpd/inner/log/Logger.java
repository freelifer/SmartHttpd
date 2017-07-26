package freelifer.smarthttpd.inner.log;

import java.util.Date;

/**
 * @author kzhu on 2017/7/26.
 */
public class Logger {

    private final String tag;
    private final boolean debug;

    private static final String FORMAT = "%s / %s/%s ";

    public static Logger getLogger(boolean debug, Class<?> cls) {
        return new Logger(debug, cls.getSimpleName());
    }

    private Logger(boolean debug, String tag) {
        this.debug = debug;
        this.tag = tag;
    }

    // 07-26 19:40:23.425 3196-3577/? D/WifiHW: wpa_ctrl_request reply=RSSI=-57
    public void i(String format, Object... args) {
        if (debug) {
            return;
        }
        String msg = String.format(FORMAT + format, new Date(), "I", tag, args);
        System.out.println(msg);
    }

    public void e(String format, Object... args) {
        String msg = String.format(FORMAT + format, new Date(), "E", tag, args);
        System.out.println(msg);
    }

}
