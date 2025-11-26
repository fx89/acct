package com.desolatetimelines.acct.reporting.ws.client;

import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderId;
import com.desolatetimelines.acct.reporting.ws.endpoint.DataProvidersEndpoint;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${REPORTING_APPLICATION_NAME}-dataProviders",
    name = "${REPORTING_APPLICATION_NAME}/${REPORTING_SERVER_CONTEXT_PATH}/dataProviders"
)
public interface RESTDataProvidersEndpointClient extends DataProvidersEndpoint {

    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    Set<AcctReportingDataProviderId> getDataProviders();

}
