package application.filter;

import application.MyApplication;
import application.context.SecurityContext;
import org.apache.http.HttpStatus;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by adam on 10/12/15.
 */
@Provider
@Authentication
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    /**
     *
     * @param containerRequestContext
     * @throws IOException
     */
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        try {
            String authorization = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
            String userName = MyApplication.memcachedManager.getUserNameByAuthorizationToken(authorization);
            containerRequestContext.setSecurityContext(new SecurityContext(userName));
        } catch (Exception exception) {
            containerRequestContext.abortWith(Response.status(HttpStatus.SC_FORBIDDEN).build());
        }
    }
}
