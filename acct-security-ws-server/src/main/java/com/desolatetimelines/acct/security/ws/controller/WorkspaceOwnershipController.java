package com.desolatetimelines.acct.security.ws.controller;

import com.desolatetimelines.acct.security.service.AcctSecurityService;
import com.desolatetimelines.acct.security.ws.endpoint.WorkspaceOwnershipEndpoint;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnedWorkspacesGroup;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;
import com.desolatetimelines.acct.security.ws.endpoint.model.WorkspaceAccessibilityReport;
import com.desolatetimelines.acct.security.ws.endpoint.model.WorkspaceOwner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.desolatetimelines.acct.security.privilegesprovider.model.SecurityPrivilegeIds.*;
import static com.desolatetimelines.acct.security.ws.mapper.OwnedWorkspacesGroupsMapper.fromAcctWorkspaceOwnersCollection;
import static com.desolatetimelines.acct.security.ws.mapper.OwnerTypeMapper.toDataLayerOwnerType;
import static com.desolatetimelines.acct.security.ws.mapper.WorkspaceAccessibilityReportMapper.fromAccessibilityReport;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/workspaceOwners")
public class WorkspaceOwnershipController implements WorkspaceOwnershipEndpoint {

    private final AcctSecurityService securityService;

    public WorkspaceOwnershipController(AcctSecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + WORKSPACE_OWNERS_READ + "')")
    @GetMapping(value = "/ownedWorkspaces", produces = APPLICATION_JSON_VALUE)
    public Collection<String> getWorkspacesOwnedByOwnerOfType(
        @RequestParam("ownerType") OwnerType ownerType,
        @RequestParam("ownerUUID") String ownerUUID
    ) {
        // If the owner type is ANY then return workspaces owned by the owner of any type
        if (ownerType == OwnerType.ANY) {
            return securityService.getWorkspacesOwnedByOwner(ownerUUID);
        }

        // If the owner type is not ANY then return workspaces owned by the owner of the specific type
        return
            securityService.getWorkspacesOwnedByOwnerOfType(
                toDataLayerOwnerType(ownerType),
                ownerUUID
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + WORKSPACE_OWNERS_READ + "')")
    @GetMapping(value = "/userAccessibleWorkspaces", produces = APPLICATION_JSON_VALUE)
    public OwnedWorkspacesGroup getUserAccessibleWorkspaces(@RequestParam("userUUID") String userUUID) {
        return fromAcctWorkspaceOwnersCollection(securityService.getWorkspacesOwnedByUser(userUUID));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + WORKSPACE_OWNERS_SAVE + "')")
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public void addWorkspaceOwner(@RequestBody WorkspaceOwner workspaceOwner) {
        securityService.createWorkspaceOwner(
            toDataLayerOwnerType(workspaceOwner.ownerType()),
            workspaceOwner.ownerUUID(),
            workspaceOwner.workspaceUUID()
        );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + WORKSPACE_OWNERS_DELETE + "')")
    @DeleteMapping(value = "")
    public void deleteWorkspaceOwner(
        @RequestParam("ownerType") OwnerType ownerType,
        @RequestParam("ownerUUID") String ownerUUID,
        @RequestParam("workspaceUUID") String workspaceUUID
    ) {
        securityService.deleteWorkspaceOwner(toDataLayerOwnerType(ownerType), ownerUUID, workspaceUUID);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + WORKSPACE_OWNERS_READ + "')")
    @GetMapping(value = "/userAccessibleWorkspace")
    public WorkspaceAccessibilityReport isUserAccessibleWorkspace(
        @RequestParam("userUUID") String userUUID,
        @RequestParam("workspaceUUID") String workspaceUUID
    ) {
        return fromAccessibilityReport(securityService.getWorkspaceOwnedByUser(userUUID, workspaceUUID));
    }
}
