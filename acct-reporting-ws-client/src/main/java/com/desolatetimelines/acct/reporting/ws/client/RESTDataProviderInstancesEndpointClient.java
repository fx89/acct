package com.desolatetimelines.acct.reporting.ws.client;

import com.desolatetimelines.acct.reporting.ws.endpoint.DataProviderInstancesEndpoint;
import com.desolatetimelines.acct.reporting.ws.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
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
    @GetMapping(value = "/details", produces = APPLICATION_JSON_VALUE)
    DataProviderInstanceProperties getDataProviderInstanceDetails(
        @NonNull @RequestParam(name = "dataProviderInstanceUUID") String dataProviderInstanceUUID
    );

    @Override
    @GetMapping(value = "/parameters", produces = APPLICATION_JSON_VALUE)
    Set<DataProviderInstanceRuntimeParameter> getDataProviderInstanceRuntimeParameters(
        @RequestParam(name = "dataProviderInstanceUUID") String dataProviderInstanceUUID
    );

    @Override
    @PostMapping(value = "/dataSet", produces = APPLICATION_JSON_VALUE)
    AcctReportingDataSet getDataProviderInstanceDataSet(
        @NonNull @RequestParam(name = "dataProviderInstanceUUID") String dataProviderInstanceUUID,
        @NonNull @RequestBody() Map<String, String> runtimeParameters
    );

    @Override
    @DeleteMapping(value = "", produces = APPLICATION_JSON_VALUE)
    void deleteDataProviderInstance(
        @NonNull @RequestParam(name = "dataProviderInstanceUUID") String dataProviderInstanceUUID
    );

}
