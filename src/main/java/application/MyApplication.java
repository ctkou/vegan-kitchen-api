package application;

import manager.MemcachedManager;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by wendywang on 2015-11-07.
 */
@ApplicationPath("/")
public class MyApplication extends ResourceConfig {

    private static final String PROPERTIES_FILE = "config.properties";

    public static Properties properties = new Properties();
    public static MemcachedManager memcachedManager;

    private Properties loadConfiguration() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return properties;
    }

    public MyApplication() throws IOException {
        packages(true, "rest.api", "application");
        loadConfiguration();
        memcachedManager = new MemcachedManager();
    }

}
