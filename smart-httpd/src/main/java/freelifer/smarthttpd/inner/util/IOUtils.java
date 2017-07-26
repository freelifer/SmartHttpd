package freelifer.smarthttpd.inner.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author kzhu on 2017/7/26.
 */
public class IOUtils {


    public static String getStringFromInputStream(InputStream is) throws IOException {
        StringBuilder out = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = is.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
