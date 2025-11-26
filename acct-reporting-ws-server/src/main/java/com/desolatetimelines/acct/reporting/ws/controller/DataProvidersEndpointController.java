package com.desolatetimelines.acct.reporting.ws.controller;

import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderId;
import com.desolatetimelines.acct.reporting.service.AcctReportingService;
import com.desolatetimelines.acct.reporting.ws.endpoint.DataProvidersEndpoint;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static com.desolatetimelines.acct.reporting.privilegesprovider.model.ReportingPrivilegeIds.DATA_PROVIDERS_READ;
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

}
