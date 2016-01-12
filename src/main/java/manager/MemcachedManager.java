package manager;

import application.MyApplication;
import exception.SessionNotFoundException;
import net.spy.memcached.MemcachedClient;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by adam on 25/12/15.
 */
public class MemcachedManager {

    private static int TIME_OUT_IN_SECOND = 60 * 30;
    private MemcachedClient memcachedClient;

    public MemcachedManager() throws IOException {
        String memcachedHost = MyApplication.properties.getProperty("memcached_host");
        int memcachedPort = Integer.parseInt(MyApplication.properties.getProperty("memcached_port"));
        memcachedClient = new MemcachedClient(new InetSocketAddress(memcachedHost, memcachedPort));
    }

    public String getUserNameByAuthorizationToken(String authorization) throws SessionNotFoundException {
        try {
            return (String) memcachedClient.get(authorization);
        }
        catch (Exception exception) {
            throw new SessionNotFoundException("Session not found");
        }
    }

    public void storeAuthorizationToken(String userName, String authorizationToken) {
        memcachedClient.set(authorizationToken, TIME_OUT_IN_SECOND, userName);
    }

    public void removeAuthorizationToken(String authorization) {
        memcachedClient.delete(authorization);
    }
}
