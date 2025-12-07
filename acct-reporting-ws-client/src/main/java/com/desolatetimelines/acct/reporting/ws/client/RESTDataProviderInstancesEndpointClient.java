package com.desolatetimelines.acct.reporting.ws.client;

import com.desolatetimelines.acct.reporting.ws.endpoint.DataProviderInstancesEndpoint;
import com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceInfo;
import com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceProperties;
import com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceRuntimeParameter;
import com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceUUIDResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${REPORTING_APPLICATION_NAME}-dashboards",
    name = "${REPORTING_APPLICATION_NAME}/${REPORTING_SERVER_CONTEXT_PATH}/dataProviderInstances"
)
public interface RESTDataProviderInstancesEndpointClient extends DataProviderInstancesEndpoint {

    @Override
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    DataProviderInstanceUUIDResponse saveDataProviderInstance(
        @RequestParam(name = "dataProviderInstanceUUID", required = false) String dataProviderInstanceUUID,
        @RequestBody DataProviderInstanceProperties dataProviderInstanceProperties
    );

    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    Set<DataProviderInstanceInfo> getDataProviderInstances();

    @Override
    @GetMapping(value = "/parameters", produces = APPLICATION_JSON_VALUE)
    Set<DataProviderInstanceRuntimeParameter> getDataProviderInstanceRuntimeParameters(
        @RequestParam(name = "dataProviderInstanceUUID") String dataProviderInstanceUUID
    );

}
