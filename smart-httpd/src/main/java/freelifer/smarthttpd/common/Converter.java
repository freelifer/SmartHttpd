package freelifer.smarthttpd.common;

/**
 * @author kzhu on 2017/7/28.
 */
public interface Converter<V, K> {

    K cover(V value);
}
