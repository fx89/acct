package com.desolatetimelines.acct.reporting.ws.client;

import com.desolatetimelines.acct.reporting.ws.endpoint.DashboardsEndpoint;
import com.desolatetimelines.acct.reporting.ws.model.DashboardProperties;
import com.desolatetimelines.acct.reporting.ws.model.DashboardUUIDResponse;
import com.desolatetimelines.acct.reporting.ws.model.UserAccessibleDashboardsContainer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

}
