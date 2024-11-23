package com.desolatetimelines.acct.common.rest.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Collections;
import java.util.function.Supplier;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * Keeps the back-end client authenticated against the given authorization server instance
 * to always be able to supply a valid access token that can be used for back-end operations.<br />
 * <br />
 * This custom implementation is required to get around the issue of the authorization server
 * not being able to authenticate with self because the {@code .well-known} is being accessed
 * at application startup when the HTTP server is not up yet.
 */
@Service
public class BackendClientAccessTokenSupplier implements Supplier<String> {

    // Properties of the request body to be sent to the authentication server for client authentication
    private static final String GRANT_TYPE = "grant_type";
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String SCOPE = "scope";

    // Hardcoded values for some of the aforementioned properties
    private static final String CLIENT_CREDENTIALS = "client_credentials";
    private static final String BACKEND_CLIENT_ID = "backend-client";
    private static final String BACKEND_CLIENT_SCOPE = "openid backend";

    /**
     * A given amount of milliseconds is subtracted from the expiry date of the access token
     * to shift this expiry date into the past, to account for de-synchronization between
     * server clocks and lag on the communications channel
     */
    private static final long EXPIRY_COMPUTATION_ERROR_MARGIN_MILLIS = 1000;

    /**
     * The REST template is used for accessing the token URL
     */
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Defines the HTTP entity containing the headers and body of the request to send to the tokenURL,
     * which can be re-used every time a new access token is needed
     */
    private final HttpEntity<MultiValueMap<String, String>> tokenRequest;

    /**
     * The access token that's currently in use
     */
    private Oauth2AccessTokenData accessTokenData;

    /**
     * The host resolver
     */
    private final EurekaHostResolver eurekaHostResolver;

    private final String authorizationServerContextPath;

    private final String authorizationServerApplicationName;

    public BackendClientAccessTokenSupplier(
        @Value("${AUTHORIZATION_SERVER_CONTEXT_PATH}") String authorizationServerContextPath,
        @Value("${BACKEND_CLIENT_SECRET_CLEAR}") String backendClientSecret,
        @Value("${AUTHORIZATION_SERVER_APPLICATION_NAME}") String authorizationServerApplicationName,
        EurekaHostResolver eurekaHostResolver
    ) {
        this.eurekaHostResolver = eurekaHostResolver;
        this.tokenRequest = createRequest(backendClientSecret);
        this.authorizationServerApplicationName = authorizationServerApplicationName;
        this.authorizationServerContextPath = authorizationServerContextPath;
    }

    /**
     * Creates the HTTP entity that contains the headers and body of the request to the token URL
     */
    private static HttpEntity<MultiValueMap<String, String>> createRequest(String backendClientSecret) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(GRANT_TYPE, CLIENT_CREDENTIALS);
        map.add(CLIENT_ID, BACKEND_CLIENT_ID);
        map.add(CLIENT_SECRET, backendClientSecret);
        map.add(SCOPE, BACKEND_CLIENT_SCOPE);

        return new HttpEntity<>(map, headers);
    }

    /**
     * Returns a valid, non-expired back-end client access token. To keep the access token valid, the
     * expiration date is checked and, if in the past, the token is refreshed before being returned. <br />
     * <br />
     * To keep the state coherent, this method is synchronized.
     */
    @Override
    public synchronized String get() {
        // If the access token is expired then refresh it
        if (accessTokenData == null || accessTokenIsExpired(accessTokenData)) {
            accessTokenData = refreshAccessToken();
        }

        // In any case, return the access token value
        return accessTokenData.accessToken();
    }

    /**
     * Returns true if the value of the {@link Oauth2AccessTokenData#expiresAt() expiresAt}
     * property of the referenced access token data is in the past. An error margin is applied
     * to prevent returning access tokens that are already expired by the time they are used.
     */
    private static boolean accessTokenIsExpired(Oauth2AccessTokenData accessTokenData) {
        return
            accessTokenData.expiresAt()
                .minusMillis(EXPIRY_COMPUTATION_ERROR_MARGIN_MILLIS)
                .isBefore(Instant.now());
    }

    private Oauth2AccessTokenData refreshAccessToken() {
        // Get the token URL from the host resolver
        final String tokenURL =
            eurekaHostResolver.resolveHostAddressByApplicationName(authorizationServerApplicationName) +
                authorizationServerContextPath + "/oauth2/token";

        // Get the new access token
        final Oauth2AccessTokenResponse tokenResponse =
            restTemplate.postForObject(tokenURL, tokenRequest, Oauth2AccessTokenResponse.class);

        // Transform the response into the required data object
        return tokenResponseToTokenData(tokenResponse);
    }

    /**
     * Transforms the referenced token response object into a response data object
     */
    private static Oauth2AccessTokenData tokenResponseToTokenData(Oauth2AccessTokenResponse tokenResponse) {
        return
            Oauth2AccessTokenData.builder()
                // The access token value is copied as is
                .withAccessToken(tokenResponse.getAccessToken())
                // The expiresIn property (expressed in seconds) is turned into expiresAt
                .withExpiresAt(Instant.now().plus(tokenResponse.getExpiresIn(), SECONDS))
                .build();
    }

    /**
     * Defines the required attributes of the response of the token URL
     */
    private static final class Oauth2AccessTokenResponse {
        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("expires_in")
        private Integer expiresIn;

        public String getAccessToken() {
            return accessToken;
        }

        @SuppressWarnings("unused") // used by Jackson
        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public Integer getExpiresIn() {
            return expiresIn;
        }

        @SuppressWarnings("unused") // used by Jackson
        public void setExpiresIn(Integer expiresIn) {
            this.expiresIn = expiresIn;
        }
    }

    /**
     * Properties of the access token
     *
     * @param accessToken the Base64-encoded access token
     * @param expiresAt   the instant when the access token is set to expire
     */
    private record Oauth2AccessTokenData(
        String accessToken,
        Instant expiresAt
    ) {
        public static Oauth2AccessTokenDataBuilder builder() {
            return new Oauth2AccessTokenDataBuilder();
        }

        /**
         * {@code Oauth2AccessTokenData} builder static inner class.
         */
        public static final class Oauth2AccessTokenDataBuilder {
            private String accessToken;
            private Instant expiresAt;

            private Oauth2AccessTokenDataBuilder() {
            }

            /**
             * Sets the {@code accessToken} and returns a reference to this Builder enabling method chaining.
             *
             * @param accessToken the {@code accessToken} to set
             * @return a reference to this Builder
             */
            public Oauth2AccessTokenDataBuilder withAccessToken(String accessToken) {
                this.accessToken = accessToken;
                return this;
            }

            /**
             * Sets the {@code expiresAt} and returns a reference to this Builder enabling method chaining.
             *
             * @param expiresAt the {@code expiresAt} to set
             * @return a reference to this Builder
             */
            public Oauth2AccessTokenDataBuilder withExpiresAt(Instant expiresAt) {
                this.expiresAt = expiresAt;
                return this;
            }

            /**
             * Returns a {@code Oauth2AccessTokenData} built from the parameters previously set.
             *
             * @return a {@code Oauth2AccessTokenData} built with parameters of this {@code Oauth2AccessTokenData.Builder}
             */
            public Oauth2AccessTokenData build() {
                return new Oauth2AccessTokenData(accessToken, expiresAt);
            }
        }
    }
}
