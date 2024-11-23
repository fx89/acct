package com.desolatetimelines.acct.common.rest.service;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused") // Used by Spring
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer";

    /**
     * Supplies access tokens for authorizing the back-end client with the resource servers
     */
    private final BackendClientAccessTokenSupplier backendClientAccessTokenSupplier;

    public FeignClientInterceptor(BackendClientAccessTokenSupplier backendClientAccessTokenSupplier) {
        this.backendClientAccessTokenSupplier = backendClientAccessTokenSupplier;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        final String accessToken = backendClientAccessTokenSupplier.get();
        requestTemplate.header(AUTHORIZATION, String.format("%s %s", BEARER, accessToken));
    }
}
