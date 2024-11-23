package com.desolatetimelines.acct.usage.data.service;

import com.desolatetimelines.acct.common.rest.service.BackendClientAccessTokenSupplier;
import com.desolatetimelines.acct.common.rest.service.EurekaHostResolver;
import com.desolatetimelines.acct.usage.data.exception.AcctUsedItemsDataServiceException;
import com.desolatetimelines.acct.usage.model.AcctService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Queries ACCT services for used items
 */
@Service
public class AcctUsedItemsDataService {

    /**
     * The REST template is used for running HTTP requests to each service that exports in-use items
     */
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * The access token supplier keeps the access token for the back-end client refreshed
     */
    private final BackendClientAccessTokenSupplier accessTokenSupplier;

    /**
     * The host resolver gets service URLs from the Eureka service
     */
    private final EurekaHostResolver hostResolver;

    public AcctUsedItemsDataService(
        BackendClientAccessTokenSupplier accessTokenSupplier,
        EurekaHostResolver hostResolver
    ) {
        this.accessTokenSupplier = accessTokenSupplier;
        this.hostResolver = hostResolver;
    }

    /**
     * Asks the referenced service to return the UUIDs of all the used items of the given type
     * having the UUID in the given list of item UUIDs
     *
     * @param acctService the referenced service
     * @param objectType  the given type
     * @param itemUUIDs   the given list of item UUIDs
     */
    public Collection<String> getUsedItemUUIDsForService(
        AcctService acctService,
        String objectType,
        Collection<String> itemUUIDs
    ) {
        try {
            // Get the host address from the host resolver
            final String hostAddress = hostResolver.resolveHostAddressByApplicationName(acctService.getServiceName());

            // Compile the service URI
            final URI serviceUri =
                new URI(
                    hostAddress + acctService.getServiceContextPath() + "/itemsInUse?objectType=" + objectType
                );

            // Create the request headers
            final MultiValueMap<String, String> headers = new HttpHeaders();

            // Get the access token from the supplier and put it into the request headers
            final String accessToken = accessTokenSupplier.get();
            headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

            // Set the content type
            headers.set(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);

            // Create the HTTP request
            final RequestEntity<Collection<String>> request =
                new RequestEntity<>(
                    itemUUIDs,
                    headers,
                    HttpMethod.POST,
                    serviceUri
                );

            // Execute the request and get the response
            @SuppressWarnings("rawtypes") final ResponseEntity<Collection> response =
                restTemplate.exchange(request, Collection.class);

            // If the request was not successful, throw an exception
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new IllegalStateException("Request to " + serviceUri + "was unsuccessful");
            }

            // Get the response body
            @SuppressWarnings("unchecked") final Collection<String> responseBody = response.getBody();

            // Return the response body
            return responseBody;

        } catch (URISyntaxException e) {
            throw new AcctUsedItemsDataServiceException(
                "Unable to get used item UUIDs: " + e.getMessage(),
                e
            );
        }
    }

}
