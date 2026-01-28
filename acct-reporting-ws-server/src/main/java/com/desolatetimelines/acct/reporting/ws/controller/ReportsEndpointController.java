package com.desolatetimelines.acct.reporting.ws.controller;

import com.desolatetimelines.acct.common.ws.model.AcctPage;
import com.desolatetimelines.acct.common.ws.model.AcctUserClaims;
import com.desolatetimelines.acct.reporting.service.AcctReportingService;
import com.desolatetimelines.acct.reporting.ws.endpoint.ReportsEndpoint;
import com.desolatetimelines.acct.reporting.ws.model.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

import static com.desolatetimelines.acct.common.ws.util.AcctJwtUtils.extractCurrentUserClaims;
import static com.desolatetimelines.acct.reporting.privilegesprovider.model.ReportingPrivilegeIds.*;
import static com.desolatetimelines.acct.reporting.ws.mapper.AcctReportingDataSetsMapper.fromAcctReportingDataProviderDataSet;
import static com.desolatetimelines.acct.reporting.ws.mapper.DataProviderInstanceRuntimeParametersMapper.fromSetOfAcctDataProviderInstanceRuntimeParameterSpec;
import static com.desolatetimelines.acct.reporting.ws.mapper.ReportExtendedPropertiesMapper.fromPageOfExtendedReportDetails;
import static com.desolatetimelines.acct.reporting.ws.mapper.ReportPropertiesMapper.toReportDetails;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/reports")
public class ReportsEndpointController implements ReportsEndpoint {

    private final AcctReportingService reportingService;

    public ReportsEndpointController(AcctReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + REPORTS_SAVE + "')")
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public ReportUUIDResponse saveReportForCurrentUser(
        @Nullable @RequestParam(name = "reportUUID", required = false) String reportUUID,
        @NonNull @RequestBody ReportProperties reportProperties
    ) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Create the report for the user and return the UUID
        return
            new ReportUUIDResponse(
                reportingService
                    .saveReport(
                        reportUUID,
                        toReportDetails(reportProperties),
                        userClaims.userUUID(),
                        userClaims.privilegeNames()
                    )
                    .getReportUUID()
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + REPORTS_READ + "')")
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public AcctPage<ReportExtendedProperties> findSortedPageOfUserAccessibleReports(
        @RequestParam(name = "pageNumber") int pageNumber,
        @RequestParam(name = "pageSize") int pageSize
    ) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Fetch the data based on the user claims and map the fetched data to the return type
        return
            fromPageOfExtendedReportDetails(
                reportingService.findSortedPageOfUserAccessibleReports(
                    pageNumber,
                    pageSize,
                    userClaims.userUUID()
                ),
                pageNumber
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + REPORTS_READ + "')")
    @GetMapping(value = "/parameters", produces = APPLICATION_JSON_VALUE)
    public Set<DataProviderInstanceRuntimeParameter> getReportRuntimeParameters(
        @RequestParam(name = "reportUUID") String reportUUID
    ) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Fetch the runtime parameters, do the mapping and return the set
        return
            fromSetOfAcctDataProviderInstanceRuntimeParameterSpec(
                reportingService.getReportRuntimeParameters(reportUUID, userClaims.userUUID())
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + REPORTS_RUN + "')")
    @PostMapping(value = "/data", produces = APPLICATION_JSON_VALUE)
    public AcctReportingDataSet getReportDataWithRuntimeParameters(
        @RequestParam(name = "reportUUID") String reportUUID,
        @RequestBody Set<ReportParameter> parameters
    ) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Run the report for the user, perform all the mappings and return the resulted data set
        return
            fromAcctReportingDataProviderDataSet(
                reportingService.getReportDataWithParameters(
                    reportUUID,
                    parameters.stream()
                        .collect(Collectors.toMap(
                            ReportParameter::parameterName,
                            ReportParameter::parameterValue
                        )),
                    userClaims.userUUID()
                )
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + REPORTS_DELETE + "')")
    @DeleteMapping(value = "")
    public void deleteReport(
        @RequestParam(name = "reportUUID") String reportUUID
    ) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Delete the report for the user
        reportingService.deleteReport(
            reportUUID,
            userClaims.userUUID(),
            userClaims.privilegeNames()
        );
    }

}
