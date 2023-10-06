package academy.learnprograrmming.rest;

import academy.learnprogramming.service.SecurityUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Key;


@Authz
@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {
    @Inject
    private SecurityUtil securityUtil;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authString = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authString == null || authString.isEmpty() || !authString.startsWith("Bearer")) {
            throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED).build());
        }
        String token = authString.substring("Bearer ".length()).trim();
        try {
            Key key = securityUtil.getSecurityKey();
            Jws<Claims> claimJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);

        }catch (Exception e) {
            throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}

