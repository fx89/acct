package com.desolatetimelines.acct.reporting.ws.client;

import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderId;
import com.desolatetimelines.acct.reporting.ws.endpoint.DataProvidersEndpoint;
import com.desolatetimelines.acct.reporting.ws.model.AcctReportingDataSet;
import com.desolatetimelines.acct.reporting.ws.model.DataProviderRunningParameters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Override
    @PostMapping(value = "/dataSet", produces = APPLICATION_JSON_VALUE)
    AcctReportingDataSet getDataProviderDataSet(
        @NonNull @RequestParam(name = "dataProviderUUID") String dataProviderUUID,
        @NonNull @RequestBody DataProviderRunningParameters runningParameters
    );
}
