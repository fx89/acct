package com.desolatetimelines.acct.reporting.ws.controller;

import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderId;
import com.desolatetimelines.acct.reporting.service.AcctReportingService;
import com.desolatetimelines.acct.reporting.ws.endpoint.DataProvidersEndpoint;
import com.desolatetimelines.acct.reporting.ws.model.AcctReportingDataSet;
import com.desolatetimelines.acct.reporting.ws.model.DataProviderRunningParameters;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.desolatetimelines.acct.reporting.privilegesprovider.model.ReportingPrivilegeIds.DATA_PROVIDERS_READ;
import static com.desolatetimelines.acct.reporting.ws.mapper.AcctReportingDataSetsMapper.fromAcctReportingDataProviderDataSet;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/dataProviders")
public class DataProvidersEndpointController implements DataProvidersEndpoint {

    private final AcctReportingService reportingService;

    public DataProvidersEndpointController(AcctReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + DATA_PROVIDERS_READ + "')")
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public Set<AcctReportingDataProviderId> getDataProviders() {
        return reportingService.getDataProviders();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + DATA_PROVIDERS_READ + "')")
    @PostMapping(value = "/dataSet", produces = APPLICATION_JSON_VALUE)
    public AcctReportingDataSet getDataProviderDataSet(
        @NonNull @RequestParam(name = "dataProviderUUID") String dataProviderUUID,
        @NonNull @RequestBody DataProviderRunningParameters runningParameters
    ) {
        return
            fromAcctReportingDataProviderDataSet(
                reportingService.getDataProviderDataSet(
                    dataProviderUUID,
                    runningParameters.instanceProperties(),
                    runningParameters.runtimeParameters()
                )
            );
    }


}
