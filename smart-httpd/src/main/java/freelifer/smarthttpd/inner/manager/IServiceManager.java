package freelifer.smarthttpd.inner.manager;

/**
 * @author kzhu on 2017/7/26.
 */
public interface IServiceManager {

    /**
     * Use with {@link #getSystemService} to retrieve a
     * {@link ConfigManager} for accessing the system's config manager.
     *
     * @see #getSystemService
     * @see ConfigManager
     */
    String CONFIG_MANAGER = "config_manager";

    /**
     * @see #CONFIG_MANAGER
     * @see ConfigManager
     */
    <T> T getSystemService(String serverName);
}
