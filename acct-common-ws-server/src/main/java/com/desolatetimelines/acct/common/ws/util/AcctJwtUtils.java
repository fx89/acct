package com.desolatetimelines.acct.common.ws.util;

import com.desolatetimelines.acct.common.ws.exception.AcctJwtException;
import com.desolatetimelines.acct.common.ws.model.AcctUserClaims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.emptyList;

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
    public static AcctUserClaims extractCurrentUserClaims() {
        final Jwt jwt = extractJwtFromSecurityContextHolder();
        return extractUserClaimsFromJwt(jwt);
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

    @SuppressWarnings("unchecked")
    private static AcctUserClaims extractUserClaimsFromJwt(Jwt jwt) {
        // Get the claims from the jwt or throw na exception
        final Map<String, Object> claims = jwt.getClaims();
        if (claims == null || claims.isEmpty()) {
            throw new AcctJwtException("The provided Jwt does not contain any claims");
        }

        // If this is the back-end client then there are no claims other than the backend scope
        if (((List<String>) Optional.ofNullable(claims.get("scope")).orElse(emptyList())).contains("backend")) {
            return AcctUserClaims.builder().withPrivilegeNames(List.of("backend")).build();
        }


        // Get the userUUID claim
        final String userUUID = extractClaim(claims, "userUUID");

        // Get the scp claim
        final List<String> scp = extractClaim(claims, "scp");

        // Build the user claims object and return a reference
        return
            AcctUserClaims.builder()
                .withUserUUID(userUUID)
                .withPrivilegeNames(scp)
                .build();
    }

    @SuppressWarnings("unchecked")
    private static <T> T extractClaim(Map<String, Object> claims, String claimKey) {
        // Get the claim object from the claims contained inside the Jwt or throw an exception
        final Object claimObject = claims.get(claimKey);
        if (claimObject == null) {
            throw new AcctJwtException("The Jwt token is missing the " + claimKey + " claim");
        }

        // Attempt to cast the claim object to the target type. If the attempt fails, throw an exception.
        try {
            return (T) claimObject;
        } catch (ClassCastException cce) {
            throw new AcctJwtException(
                "The provided " + claimKey + " claim cannot be cast to the target type: " + cce.getMessage(), cce
            );
        }
    }

}
