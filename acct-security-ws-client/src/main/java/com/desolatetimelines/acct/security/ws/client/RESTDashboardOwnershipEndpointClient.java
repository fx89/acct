package com.desolatetimelines.acct.security.ws.client;

import com.desolatetimelines.acct.security.ws.endpoint.DashboardOwnershipEndpoint;
import com.desolatetimelines.acct.security.ws.endpoint.model.DashboardAccessibilityReport;
import com.desolatetimelines.acct.security.ws.endpoint.model.DashboardOwner;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnedDashboardsGroup;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${SECURITY_APPLICATION_NAME}-dashboard-ownership",
    name = "${SECURITY_APPLICATION_NAME}/${SECURITY_SERVER_CONTEXT_PATH}/dashboardOwners"
)
public interface RESTDashboardOwnershipEndpointClient extends DashboardOwnershipEndpoint {
    @Override
    @GetMapping(value = "/ownedDashboards", produces = APPLICATION_JSON_VALUE)
    Collection<String> getDashboardsOwnedByOwnerOfType(
        @RequestParam("ownerType") OwnerType ownerType,
        @RequestParam("ownerUUID") String ownerUUID
    );

    @Override
    @GetMapping(value = "/userAccessibleDashboards", produces = APPLICATION_JSON_VALUE)
    OwnedDashboardsGroup getUserAccessibleDashboards(@RequestParam("userUUID") String userUUID);

    @Override
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    void addDashboardOwner(@RequestBody DashboardOwner dashboardOwner);

    @Override
    @DeleteMapping(value = "")
    void deleteDashboardOwner(
        @RequestParam("ownerType") OwnerType ownerType,
        @RequestParam("ownerUUID") String ownerUUID,
        @RequestParam("dashboardUUID") String dashboardUUID
    );

    @Override
    @DeleteMapping(value = "/byDashboardUUID")
    void deleteDashboardOwnersByDashboardUUID(
        @RequestParam("dashboardUUID") String dashboardUUID
    );

    @Override
    @GetMapping(value = "/userAccessibleDashboard")
    DashboardAccessibilityReport isUserAccessibleDashboard(
        @RequestParam("userUUID") String userUUID,
        @RequestParam("dashboardUUID") String dashboardUUID
    );
}
