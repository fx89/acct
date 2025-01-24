package com.desolatetimelines.acct.catalog.ws.client;

import com.desolatetimelines.acct.catalog.ws.endpoint.IconsEndpoint;
import com.desolatetimelines.acct.catalog.ws.model.IconCreateRequest;
import com.desolatetimelines.acct.catalog.ws.model.IconUUIDResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${CATALOG_APPLICATION_NAME}-dashboard-ownership",
    name = "${CATALOG_APPLICATION_NAME}/${CATALOG_SERVER_CONTEXT_PATH}/icons"
)
public interface RESTIconsEndpointClient extends IconsEndpoint {

    @Override
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    IconUUIDResponse createIcon(@RequestBody IconCreateRequest request);

}
