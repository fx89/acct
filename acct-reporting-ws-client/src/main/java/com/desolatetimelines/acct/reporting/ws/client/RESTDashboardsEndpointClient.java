package com.desolatetimelines.acct.reporting.ws.client;

import com.desolatetimelines.acct.reporting.ws.endpoint.DashboardsEndpoint;
import com.desolatetimelines.acct.reporting.ws.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${REPORTING_APPLICATION_NAME}-dashboards",
    name = "${REPORTING_APPLICATION_NAME}/${REPORTING_SERVER_CONTEXT_PATH}/dashboards"
)
public interface RESTDashboardsEndpointClient extends DashboardsEndpoint {

    @Override
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    DashboardUUIDResponse saveDashboard(
        @RequestParam(name = "workspaceUUID") String workspaceUUID,
        @RequestParam(name = "dashboardUUID", required = false) String dashboardUUID,
        @RequestBody DashboardProperties dashboardProperties
    );

    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    UserAccessibleDashboardsContainer getUserAccessibleDashboards(
        @RequestParam(name = "workspaceUUID") String workspaceUUID
    );

    @Override
    @DeleteMapping(value = "")
    void deleteDashboard(
        @RequestParam(name = "workspaceUUID") String workspaceUUID,
        @RequestParam(name = "dashboardUUID") String dashboardUUID
    );

    @Override
    @PutMapping(value = "/reports", produces = APPLICATION_JSON_VALUE)
    void saveDashboardReportWithFilters(
        @RequestParam(name = "workspaceUUID") String workspaceUUID,
        @RequestParam(name = "dashboardUUID") String dashboardUUID,
        @RequestBody DashboardReportProperties dashboardReportProperties
    );

    @Override
    @GetMapping(value = "/reports", produces = APPLICATION_JSON_VALUE)
    Set<DashboardReportExtendedProperties> getDashboardReports(
        @RequestParam(name = "dashboardUUID") String dashboardUUID
    );

    @Override
    @GetMapping(value = "/reports", produces = APPLICATION_JSON_VALUE)
    void deleteDashboardReport(
        @RequestParam(name = "workspaceUUID") String workspaceUUID,
        @RequestParam(name = "dashboardUUID") String dashboardUUID,
        @RequestParam(name = "reportUUID") String reportUUID
    );

}
