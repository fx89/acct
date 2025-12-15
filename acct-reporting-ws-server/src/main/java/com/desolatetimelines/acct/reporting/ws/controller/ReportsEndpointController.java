package com.desolatetimelines.acct.reporting.ws.controller;

import com.desolatetimelines.acct.common.ws.model.AcctUserClaims;
import com.desolatetimelines.acct.reporting.service.AcctReportingService;
import com.desolatetimelines.acct.reporting.ws.endpoint.ReportsEndpoint;
import com.desolatetimelines.acct.reporting.ws.model.ReportProperties;
import com.desolatetimelines.acct.reporting.ws.model.ReportUUIDResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.desolatetimelines.acct.common.ws.util.AcctJwtUtils.extractCurrentUserClaims;
import static com.desolatetimelines.acct.reporting.privilegesprovider.model.ReportingPrivilegeIds.REPORT_SAVE;
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
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + REPORT_SAVE + "')")
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
                        userClaims.userUUID()
                    )
                    .getReportUUID()
            );
    }
}
