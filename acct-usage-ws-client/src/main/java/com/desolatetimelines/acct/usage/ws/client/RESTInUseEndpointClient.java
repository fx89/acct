package com.desolatetimelines.acct.usage.ws.client;

import com.desolatetimelines.acct.usage.ws.endpoint.InUseEndpoint;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${USAGE_APPLICATION_NAME}-in-use",
    name = "${USAGE_APPLICATION_NAME}/${USAGE_SERVER_CONTEXT_PATH}"
)
public interface RESTInUseEndpointClient extends InUseEndpoint {

    @Override
    @PostMapping(value = "/itemsInUse", consumes = APPLICATION_JSON_VALUE)
    Collection<String> getItemsInUseOfType(
        @RequestParam(name = "objectType") String objectType,
        @RequestBody Collection<String> itemUUIDsList
    );

}
