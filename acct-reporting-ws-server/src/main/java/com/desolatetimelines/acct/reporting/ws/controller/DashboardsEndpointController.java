package com.desolatetimelines.acct.reporting.ws.controller;

import com.desolatetimelines.acct.reporting.service.AcctReportingService;
import com.desolatetimelines.acct.reporting.ws.endpoint.DashboardsEndpoint;
import com.desolatetimelines.acct.reporting.ws.model.DashboardProperties;
import com.desolatetimelines.acct.reporting.ws.model.DashboardUUIDResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.desolatetimelines.acct.reporting.privilegesprovider.model.ReportingPrivilegeIds.DASHBOARDS_SAVE;
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
    public DashboardUUIDResponse editDashboard(
        @RequestParam(name = "workspaceUUID") String workspaceUUID,
        @RequestParam(name = "dashboardUUID", required = false) String dashboardUUID,
        @RequestBody DashboardProperties dashboardProperties
    ) {
        return null;

    }
}
