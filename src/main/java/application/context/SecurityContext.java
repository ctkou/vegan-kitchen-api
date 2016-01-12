package application.context;

import java.security.Principal;

/**
 * Created by adam on 11/12/15.
 */
public class SecurityContext implements javax.ws.rs.core.SecurityContext {

    private String userName;

    public SecurityContext(String userName) {
        this.userName = userName;
    }

    @Override
    public Principal getUserPrincipal() {
        return new Principal() {
            @Override
            public String getName() {
                return userName;
            }
        };
    }

    @Override
    public boolean isUserInRole(String s) {
        return false; // TODO
    }

    @Override
    public boolean isSecure() {
        return false; // TODO
    }

    @Override
    public String getAuthenticationScheme() {
        return null; // TODO
    }
}
