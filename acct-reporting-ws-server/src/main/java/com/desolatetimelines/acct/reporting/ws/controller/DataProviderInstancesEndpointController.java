package com.desolatetimelines.acct.reporting.ws.controller;

import com.desolatetimelines.acct.reporting.service.AcctReportingService;
import com.desolatetimelines.acct.reporting.ws.endpoint.DataProviderInstancesEndpoint;
import com.desolatetimelines.acct.reporting.ws.model.*;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

import static com.desolatetimelines.acct.reporting.privilegesprovider.model.ReportingPrivilegeIds.*;
import static com.desolatetimelines.acct.reporting.ws.mapper.AcctReportingDataSetsMapper.fromAcctReportingDataProviderDataSet;
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

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + REPORT_RUN + "')")
    @PostMapping(value = "/dataSet", produces = APPLICATION_JSON_VALUE)
    public AcctReportingDataSet getDataProviderInstanceDataSet(
        @NonNull @RequestParam(name = "dataProviderInstanceUUID") String dataProviderInstanceUUID,
        @NonNull @RequestBody() Map<String, String> runtimeParameters
    ) {
        return
            fromAcctReportingDataProviderDataSet(
                reportingService.getDataProviderInstanceDataSet(
                    dataProviderInstanceUUID,
                    runtimeParameters
                )
            );
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + DATA_PROVIDER_INSTANCES_DELETE + "')")
    @DeleteMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @Override
    public void deleteDataProviderInstance(
        @NonNull @RequestParam(name = "dataProviderInstanceUUID") String dataProviderInstanceUUID
    ) {
        reportingService.deleteDataProviderInstance(dataProviderInstanceUUID);
    }

}
