package com.desolatetimelines.acct.security.ws.controller;

import com.desolatetimelines.acct.security.service.AcctSecurityService;
import com.desolatetimelines.acct.security.ws.endpoint.ReportOwnershipEndpoint;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnedReportsGroup;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;
import com.desolatetimelines.acct.security.ws.endpoint.model.ReportAccessibilityReport;
import com.desolatetimelines.acct.security.ws.endpoint.model.ReportOwner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.desolatetimelines.acct.security.privilegesprovider.model.SecurityPrivilegeIds.*;
import static com.desolatetimelines.acct.security.ws.mapper.OwnedReportsGroupsMapper.fromAcctReportOwnersCollection;
import static com.desolatetimelines.acct.security.ws.mapper.OwnerTypeMapper.toDataLayerOwnerType;
import static com.desolatetimelines.acct.security.ws.mapper.ReportAccessibilityReportMapper.fromAccessibilityReport;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/reportOwners")
public class ReportOwnershipController implements ReportOwnershipEndpoint {

    private final AcctSecurityService securityService;

    public ReportOwnershipController(AcctSecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + REPORT_OWNERS_SAVE + "')")
    @GetMapping(value = "/ownedReports", produces = APPLICATION_JSON_VALUE)
    public Collection<String> getReportsOwnedByOwnerOfType(
        @RequestParam("ownerType") OwnerType ownerType,
        @RequestParam("ownerUUID") String ownerUUID
    ) {
        // If the owner type is ANY then return reports owned by the owner of any type
        if (ownerType == OwnerType.ANY) {
            return securityService.getReportsOwnedByOwner(ownerUUID);
        }

        // If the owner type is not ANY then return reports owned by the owner of the specific type
        return
            securityService.getReportsOwnedByOwnerOfType(
                toDataLayerOwnerType(ownerType),
                ownerUUID
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + REPORT_OWNERS_READ + "')")
    @GetMapping(value = "/userAccessibleReports", produces = APPLICATION_JSON_VALUE)
    public OwnedReportsGroup getUserAccessibleReports(@RequestParam("userUUID") String userUUID) {
        return fromAcctReportOwnersCollection(securityService.getReportsOwnedByUser(userUUID));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + REPORT_OWNERS_SAVE + "')")
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public void addReportOwner(@RequestBody ReportOwner reportOwner) {
        securityService.createReportOwner(
            toDataLayerOwnerType(reportOwner.ownerType()),
            reportOwner.ownerUUID(),
            reportOwner.reportUUID()
        );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + REPORT_OWNERS_DELETE + "')")
    @DeleteMapping(value = "")
    public void deleteReportOwner(
        @RequestParam("ownerType") OwnerType ownerType,
        @RequestParam("ownerUUID") String ownerUUID,
        @RequestParam("reportUUID") String reportUUID
    ) {
        securityService.deleteReportOwner(toDataLayerOwnerType(ownerType), ownerUUID, reportUUID);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + REPORT_OWNERS_READ + "')")
    @GetMapping(value = "/userAccessibleReport")
    public ReportAccessibilityReport isUserAccessibleReport(
        @RequestParam("userUUID") String userUUID,
        @RequestParam("reportUUID") String reportUUID
    ) {
        return fromAccessibilityReport(securityService.getReportOwnedByUser(userUUID, reportUUID));
    }
}
