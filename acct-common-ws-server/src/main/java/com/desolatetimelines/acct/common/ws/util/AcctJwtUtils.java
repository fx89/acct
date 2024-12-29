package com.desolatetimelines.acct.common.ws.util;

import com.desolatetimelines.acct.common.ws.exception.AcctJwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;

/**
 * Provides utility methods for working with JWTs (user access tokens, mostly)
 */
public abstract class AcctJwtUtils {

    /**
     * Extracts the userUUID claim from the access token contained inside the
     * {@link SecurityContext security context}. If the security context does
     * not exist or if it does not contain an access token with a userUUID
     * claim of the proper type, an {@link AcctJwtException exception} is thrown.
     *
     * @return the userUUID claim
     * @throws AcctJwtException in case the userUUID claim cannot be extracted
     *                          for whatever reason
     */
    public static String extractCurrentUserUUID() {
        final Jwt jwt = extractJwtFromSecurityContextHolder();
        return extractUserUUIDClaimFromJwt(jwt);
    }

    private static Jwt extractJwtFromSecurityContextHolder() {
        // Get the security context or throw an exception
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null) {
            throw new AcctJwtException("Missing security context");
        }

        // Get the authentication from the security context or throw an exception
        final Authentication authentication = securityContext.getAuthentication();
        if (authentication == null) {
            throw new AcctJwtException("Missing authentication from security context");
        }

        // Get the credentials from the authentication or throw an exception
        final Object credentials = authentication.getCredentials();
        if (credentials == null) {
            throw new AcctJwtException("Missing credentials from authentication");
        }

        // If the credentials are of type Jwt then cast and return
        if (credentials instanceof Jwt jwt) {
            return jwt;
        }

        // If the credentials are not of type Jwt then throw an exception
        throw new AcctJwtException("Credentials contained in the security context are not of type Jwt");

    }

    private static String extractUserUUIDClaimFromJwt(Jwt jwt) {
        // Get the claims from the jwt or throw na exception
        final Map<String, Object> claims = jwt.getClaims();
        if (claims == null || claims.isEmpty()) {
            throw new AcctJwtException("The provided Jwt does not contain any claims");
        }

        // Get the userUUID claim from the claims contained inside the Jwt or throw an exception
        final Object userUUID = claims.get("userUUID");
        if (userUUID == null) {
            throw new AcctJwtException("The Jwt token is missing the userUUID claim");
        }

        // Attempt to cast the userUUID claim to a string and return it.
        // If the attempt fails, throw an exception.
        try {
            return (String) userUUID;
        } catch (ClassCastException cce) {
            throw new AcctJwtException(
                "The provided userUUID claim cannot be cast to a string: " + cce.getMessage(), cce
            );
        }
    }

}
