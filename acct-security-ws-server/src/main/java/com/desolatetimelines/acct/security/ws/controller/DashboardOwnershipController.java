package com.desolatetimelines.acct.security.ws.controller;

import com.desolatetimelines.acct.security.service.AcctSecurityService;
import com.desolatetimelines.acct.security.ws.endpoint.DashboardOwnershipEndpoint;
import com.desolatetimelines.acct.security.ws.endpoint.model.DashboardAccessibilityReport;
import com.desolatetimelines.acct.security.ws.endpoint.model.DashboardOwner;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnedDashboardsGroup;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.desolatetimelines.acct.security.privilegesprovider.model.SecurityPrivilegeIds.*;
import static com.desolatetimelines.acct.security.ws.mapper.DashboardAccessibilityReportMapper.fromAccessibilityReport;
import static com.desolatetimelines.acct.security.ws.mapper.OwnedDashboardsGroupsMapper.fromAcctDashboardOwnersCollection;
import static com.desolatetimelines.acct.security.ws.mapper.OwnerTypeMapper.toDataLayerOwnerType;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/dashboardOwners")
public class DashboardOwnershipController implements DashboardOwnershipEndpoint {

    private final AcctSecurityService securityService;

    public DashboardOwnershipController(AcctSecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + DASHBOARD_OWNERS_SAVE + "')")
    @GetMapping(value = "/ownedDashboards", produces = APPLICATION_JSON_VALUE)
    public Collection<String> getDashboardsOwnedByOwnerOfType(
        @RequestParam("ownerType") OwnerType ownerType,
        @RequestParam("ownerUUID") String ownerUUID
    ) {
        // If the owner type is ANY then return dashboards owned by the owner of any type
        if (ownerType == OwnerType.ANY) {
            return securityService.getDashboardsOwnedByOwner(ownerUUID);
        }

        // If the owner type is not ANY then return dashboards owned by the owner of the specific type
        return
            securityService.getDashboardsOwnedByOwnerOfType(
                toDataLayerOwnerType(ownerType),
                ownerUUID
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + DASHBOARD_OWNERS_READ + "')")
    @GetMapping(value = "/userAccessibleDashboards", produces = APPLICATION_JSON_VALUE)
    public OwnedDashboardsGroup getUserAccessibleDashboards(@RequestParam("userUUID") String userUUID) {
        return fromAcctDashboardOwnersCollection(securityService.getDashboardsOwnedByUser(userUUID));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + DASHBOARD_OWNERS_SAVE + "')")
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public void addDashboardOwner(@RequestBody DashboardOwner dashboardOwner) {
        securityService.createDashboardOwner(
            toDataLayerOwnerType(dashboardOwner.ownerType()),
            dashboardOwner.ownerUUID(),
            dashboardOwner.dashboardUUID()
        );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + DASHBOARD_OWNERS_DELETE + "')")
    @DeleteMapping(value = "")
    public void deleteDashboardOwner(
        @RequestParam("ownerType") OwnerType ownerType,
        @RequestParam("ownerUUID") String ownerUUID,
        @RequestParam("dashboardUUID") String dashboardUUID
    ) {
        securityService.deleteDashboardOwner(toDataLayerOwnerType(ownerType), ownerUUID, dashboardUUID);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + DASHBOARD_OWNERS_READ + "')")
    @GetMapping(value = "/userAccessibleDashboard")
    public DashboardAccessibilityReport isUserAccessibleDashboard(
        @RequestParam("userUUID") String userUUID,
        @RequestParam("dashboardUUID") String dashboardUUID
    ) {
        return fromAccessibilityReport(securityService.getDashboardOwnedByUser(userUUID, dashboardUUID));
    }
}
