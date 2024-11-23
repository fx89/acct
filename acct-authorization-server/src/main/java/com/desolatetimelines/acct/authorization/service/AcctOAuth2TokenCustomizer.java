package com.desolatetimelines.acct.authorization.service;

import com.desolatetimelines.acct.authorization.data.model.AcctUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@SuppressWarnings("unused")
public class AcctOAuth2TokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {
    @Override
    public void customize(JwtEncodingContext context) {
        // Client tokens do not need additional claims
        if (context.getPrincipal() instanceof OAuth2ClientAuthenticationToken clientToken) {
            return;
        }

        // Extract the user details from the encoding context
        final UserDetails userDetails = extractUserDetails(context);

        // Get the claims builder
        final JwtClaimsSet.Builder claimsBuilder = context.getClaims();

        // Add the user's granted authorities
        claimsBuilder
            .claim(
                "authorities",
                userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet())
            );

        // Add other custom claims
        addCustomClaims(userDetails, claimsBuilder);
    }

    private void addCustomClaims(UserDetails userDetails, JwtClaimsSet.Builder claimsBuilder) {
        if (userDetails instanceof AcctUser acctUser) {
            claimsBuilder.claim("humanReadableName", acctUser.getUserHumanReadableName());
            claimsBuilder.claim("userUUID", acctUser.getUserUUID());

            if (acctUser.getUserIconUUID() != null) {
                claimsBuilder.claim("iconUUID", acctUser.getUserIconUUID());
            }

            if (acctUser.getDefaultWorkspaceUUID() != null) {
                claimsBuilder.claim("defaultWorkspaceUUID", acctUser.getDefaultWorkspaceUUID());
            }
        }
    }

    /**
     * Extracts the {@link UserDetails user details} from the referenced
     * {@link JwtEncodingContext encoding context}, as long as the type
     * of principal contained inside the context is known.
     */
    private static UserDetails extractUserDetails(JwtEncodingContext context) {
        if (context.getPrincipal() instanceof OAuth2ClientAuthenticationToken) {
            return (UserDetails) context.getPrincipal().getDetails();
        }

        if (context.getPrincipal() instanceof AbstractAuthenticationToken) {
            return (UserDetails) context.getPrincipal().getPrincipal();
        }

        throw new IllegalStateException("Unexpected token type");
    }
}
