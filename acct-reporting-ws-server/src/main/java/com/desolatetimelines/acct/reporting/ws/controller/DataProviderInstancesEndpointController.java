package com.desolatetimelines.acct.reporting.ws.controller;

import com.desolatetimelines.acct.reporting.service.AcctReportingService;
import com.desolatetimelines.acct.reporting.ws.endpoint.DataProviderInstancesEndpoint;
import com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceInfo;
import com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceProperties;
import com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceRuntimeParameter;
import com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceUUIDResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.desolatetimelines.acct.reporting.privilegesprovider.model.ReportingPrivilegeIds.DATA_PROVIDER_INSTANCES_READ;
import static com.desolatetimelines.acct.reporting.privilegesprovider.model.ReportingPrivilegeIds.DATA_PROVIDER_INSTANCES_SAVE;
import static com.desolatetimelines.acct.reporting.ws.mapper.DataProviderInstanceDetailsMapper.fromDataProviderInstanceProperties;
import static com.desolatetimelines.acct.reporting.ws.mapper.DataProviderInstanceDetailsMapper.toDataProviderInstanceProperties;
import static com.desolatetimelines.acct.reporting.ws.mapper.DataProviderInstanceInfoMapper.fromSetOfAcctDataProviderInstance;
import static com.desolatetimelines.acct.reporting.ws.mapper.DataProviderInstanceRuntimeParametersMapper.fromSetOfAcctDataProviderInstanceRuntimeParameter;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/dataProviderInstances")
public class DataProviderInstancesEndpointController implements DataProviderInstancesEndpoint {

    private final AcctReportingService reportingService;

    public DataProviderInstancesEndpointController(AcctReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + DATA_PROVIDER_INSTANCES_SAVE + "')")
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public DataProviderInstanceUUIDResponse saveDataProviderInstance(
        @RequestParam(name = "dataProviderInstanceUUID", required = false) String dataProviderInstanceUUID,
        @RequestBody DataProviderInstanceProperties dataProviderInstanceProperties
    ) {
        return
            new DataProviderInstanceUUIDResponse(
                reportingService.saveDataProviderInstance(
                    dataProviderInstanceUUID,
                    fromDataProviderInstanceProperties(dataProviderInstanceProperties)
                )
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + DATA_PROVIDER_INSTANCES_READ + "')")
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public Set<DataProviderInstanceInfo> getDataProviderInstances() {
        return fromSetOfAcctDataProviderInstance(reportingService.getDataProviderInstances());
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + DATA_PROVIDER_INSTANCES_READ + "')")
    @GetMapping(value = "/details", produces = APPLICATION_JSON_VALUE)
    public DataProviderInstanceProperties getDataProviderInstanceDetails(
        @NonNull @RequestParam(name = "dataProviderInstanceUUID") String dataProviderInstanceUUID
    ) {
        return
            toDataProviderInstanceProperties(
                reportingService.getDataProviderInstanceDetails(dataProviderInstanceUUID)
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + DATA_PROVIDER_INSTANCES_READ + "')")
    @GetMapping(value = "/parameters", produces = APPLICATION_JSON_VALUE)
    public Set<DataProviderInstanceRuntimeParameter> getDataProviderInstanceRuntimeParameters(
        @RequestParam(name = "dataProviderInstanceUUID") String dataProviderInstanceUUID
    ) {
        return
            fromSetOfAcctDataProviderInstanceRuntimeParameter(
                reportingService.getDataProviderInstanceRuntimeParameters(dataProviderInstanceUUID)
            );
    }

}
