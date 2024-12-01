package com.desolatetimelines.acct.security.ws.controller;

import com.desolatetimelines.acct.security.service.AcctSecurityService;
import com.desolatetimelines.acct.security.ws.endpoint.WorkspaceOwnershipEndpoint;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnedWorkspacesGroup;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static com.desolatetimelines.acct.security.privilegesprovider.model.SecurityPrivilegeIds.WORKSPACE_OWNERS_READ;
import static com.desolatetimelines.acct.security.ws.mapper.OwnedWorkspacesGroupsMapper.fromAcctWorkspaceOwnersCollection;
import static com.desolatetimelines.acct.security.ws.mapper.OwnerTypeMapper.toDataLayerOwnerType;
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
}
