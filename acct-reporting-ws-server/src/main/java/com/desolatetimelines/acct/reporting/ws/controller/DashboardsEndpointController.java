package com.desolatetimelines.acct.reporting.ws.controller;

import com.desolatetimelines.acct.common.ws.model.AcctUserClaims;
import com.desolatetimelines.acct.reporting.service.AcctReportingService;
import com.desolatetimelines.acct.reporting.ws.endpoint.DashboardsEndpoint;
import com.desolatetimelines.acct.reporting.ws.model.DashboardProperties;
import com.desolatetimelines.acct.reporting.ws.model.DashboardReportProperties;
import com.desolatetimelines.acct.reporting.ws.model.DashboardUUIDResponse;
import com.desolatetimelines.acct.reporting.ws.model.UserAccessibleDashboardsContainer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.desolatetimelines.acct.common.ws.util.AcctJwtUtils.extractCurrentUserClaims;
import static com.desolatetimelines.acct.reporting.privilegesprovider.model.ReportingPrivilegeIds.*;
import static com.desolatetimelines.acct.reporting.ws.mapper.DashboardPropertiesMapper.toDashboardDetails;
import static com.desolatetimelines.acct.reporting.ws.mapper.DashboardReportDetailsMapper.fromDashboardReportProperties;
import static com.desolatetimelines.acct.reporting.ws.mapper.UserAccessibleDashboardsContainerMapper.fromServicesLayerDashboardsContainer;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/dashboards")
public class DashboardsEndpointController implements DashboardsEndpoint {

    private final AcctReportingService reportingService;

    public DashboardsEndpointController(AcctReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + DASHBOARDS_SAVE + "')")
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public DashboardUUIDResponse saveDashboard(
        @RequestParam(name = "workspaceUUID") String workspaceUUID,
        @RequestParam(name = "dashboardUUID", required = false) String dashboardUUID,
        @RequestBody DashboardProperties dashboardProperties
    ) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        return
            new DashboardUUIDResponse(
                reportingService.saveDashboard(
                    workspaceUUID,
                    dashboardUUID,
                    toDashboardDetails(dashboardProperties),
                    userClaims.userUUID()
                ).getDashboardUUID()
            );

    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + DASHBOARDS_READ + "')")
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public UserAccessibleDashboardsContainer getUserAccessibleDashboards(
        @RequestParam(name = "workspaceUUID") String workspaceUUID
    ) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Retrieve the dashboard groups for the user, map them to the proper data type and return a reference
        return
            fromServicesLayerDashboardsContainer(
                reportingService.readUserAccessibleDashboards(workspaceUUID, userClaims.userUUID())
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + DASHBOARDS_DELETE + "', 'SCOPE_" + DASHBOARDS_DELETE_GROUP + "')")
    @DeleteMapping(value = "")
    public void deleteDashboard(
        @RequestParam(name = "workspaceUUID") String workspaceUUID,
        @RequestParam(name = "dashboardUUID") String dashboardUUID
    ) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Delete the dashboard, if accessible
        reportingService.deleteDashboard(
            workspaceUUID,
            dashboardUUID,
            userClaims.userUUID(),
            userClaims.privilegeNames()
        );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + DASHBOARDS_SAVE + "', '" + DASHBOARDS_SAVE_GROUP + "')")
    @PutMapping(value = "/reports", produces = APPLICATION_JSON_VALUE)
    public void saveDashboardReportWithFilters(
        @RequestParam(name = "workspaceUUID") String workspaceUUID,
        @RequestParam(name = "dashboardUUID") String dashboardUUID,
        @RequestBody DashboardReportProperties dashboardReportProperties
    ) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Save the dashboard report together with the specified filters
        reportingService.saveDashboardReportWithFilters(
            workspaceUUID,
            dashboardUUID,
            fromDashboardReportProperties(dashboardReportProperties),
            userClaims.userUUID(),
            userClaims.privilegeNames()
        );
    }

}
