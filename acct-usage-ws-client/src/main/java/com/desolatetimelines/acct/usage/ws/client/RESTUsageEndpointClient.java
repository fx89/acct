package com.desolatetimelines.acct.usage.ws.client;

import com.desolatetimelines.acct.usage.ws.endpoint.UsageEndpoint;
import com.desolatetimelines.acct.usage.ws.model.ServiceItemTypesList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
    contextId = "${USAGE_APPLICATION_NAME}-usage",
    name = "${USAGE_APPLICATION_NAME}/${USAGE_SERVER_CONTEXT_PATH}"
)
public interface RESTUsageEndpointClient extends UsageEndpoint {

    @Override
    @RequestMapping(value = "/registerItemTypesForService", method = RequestMethod.PUT)
    void registerItemTypesForService(@RequestBody ServiceItemTypesList serviceItemTypesList);

}
