package com.desolatetimelines.acct.authorization.service;

import com.desolatetimelines.acct.authorization.exception.CustomLoginServiceException;
import com.desolatetimelines.acct.common.utils.HttpClientUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import static com.desolatetimelines.acct.common.utils.HttpClientUtils.*;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

/**
 * Handles custom login operations
 */
@Service
public class CustomLoginService {

    private static final String RANDOM_UUID = UUID.randomUUID().toString();

    /**
     * This is Spring's authorization server's base path, which is compiled from the available
     * application properties as follows:<ul>
     * <li>The protocol prefix is "https" if the "server.ssl.key-alias" property is set or "http" otherwise</li>
     * <li>The host is always localhost</li>
     * <li>The context path is taken from the "server.servlet.context-path" variable, which is expected to be present</li>
     * </ul>
     */
    private final String authorizationServerBasePath;

    public CustomLoginService(
        @Value("${server.servlet.context-path}") String authorizationServerContextPath,
        Environment environment
    ) {
        this.authorizationServerBasePath =
            (RANDOM_UUID.equals(environment.getProperty("server.ssl.key-alias", RANDOM_UUID)) ? "http" : "https") +
                "://localhost" +
                (RANDOM_UUID.equals(environment.getProperty("server.port", RANDOM_UUID)) ? "" : (":" + environment.getProperty("server.port", RANDOM_UUID))) +
                authorizationServerContextPath;
    }

    /**
     * Authenticates the user with the given username and the given password. Then authorizes the user
     * to get the authorization code. Once the authorization code is extracted, it is used together with
     * the client id and client secret to request an access token for the user. Throws an exception if
     * any problem occurs within the aforementioned process.
     *
     * @param clientId     the client ID of the client that uses the service to log in and authorize the user
     * @param clientSecret the secret of the client that uses the service to log in and authorize the user
     * @param username     the given username
     * @param password     the given password
     * @return the requested access token
     */
    public String loginAndAuthorize(
        String clientId,
        String clientSecret,
        String username,
        String password
    ) {
        try {
            // Create the HTTP client and cookie manager and run the requests using this client
            try (final HttpClientUtils.HttpClientWithCookieManager client = createHttpClientWithCookieManager()) {
                // Log in
                postFormUrlEncoded(
                    client,
                    authorizationServerBasePath + "/login",
                    Map.of(
                        "username", username,
                        "password", password
                    )
                );

                // Authorize
                final HttpResponseWithCookies<String> authorizeResponse =
                    getRequest(
                        client,
                        authorizationServerBasePath + "/oauth2/authorize",
                        Map.of(
                            "response_type", "code",
                            "client_id", clientId
                        )
                    );

                // If the response code is not 302, then throw an exception
                if (authorizeResponse.httpResponse().statusCode() != 302) {
                    throw new CustomLoginServiceException("Unable to log in. Bad credentials.");
                }

                // If the response code is 302, then parse the location header to get the authorization code

                // Get the location header or throw an exception
                final String location =
                    authorizeResponse.httpResponse().headers()
                        .firstValue("Location")
                        .orElseThrow(() -> new CustomLoginServiceException(
                            "Unable to authorize. Location header is missing."
                        ));

                // Split by "="
                final String[] parts = location.split("=");

                // If there's no second part, then throw an exception
                if (parts.length < 2) {
                    throw new CustomLoginServiceException("Unable to authorize. Authorization code is missing.");
                }

                // Extract the authorization code
                final String authorizationCode = parts[1];

                // Get the user access token
                final HttpResponseWithCookies<String> accessTokenResponse =
                    postFormUrlEncoded(
                        client,
                        authorizationServerBasePath + "/oauth2/token",
                        Map.of(
                            "grant_type", "authorization_code",
                            "code", authorizationCode,
                            "client_id", clientId,
                            "client_secret", clientSecret
                        )
                    );

                // If the response code is not 200, then throw an exception
                if (accessTokenResponse.httpResponse().statusCode() != 200) {
                    throw new CustomLoginServiceException("Unable to get access token. Bad credentials.");
                }

                // Parse the access token response body to allow extracting the access token
                final AccessTokenResponse accessTokenResponseBody =
                    new ObjectMapper()
                        .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
                        .readValue(
                            accessTokenResponse.httpResponse().body(),
                            AccessTokenResponse.class
                        );

                // Extract and return the access token
                return accessTokenResponseBody.accessToken();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Unable to log in: " + e.getMessage(), e);
        }
    }

    private record AccessTokenResponse(
        @JsonProperty(value = "access_token")
        String accessToken
    ) {
        private AccessTokenResponse(String accessToken) {
            this.accessToken = accessToken;
        }
    }

}