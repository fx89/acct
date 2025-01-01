package com.desolatetimelines.acct.workspace.ws.controller;

import com.desolatetimelines.acct.common.ws.model.AcctUserClaims;
import com.desolatetimelines.acct.workspace.service.AcctWorkspaceService;
import com.desolatetimelines.acct.workspace.ws.endpoint.WorkspacesEndpoint;
import com.desolatetimelines.acct.workspace.ws.mapper.WorkspaceCollectionsResponseMapper;
import com.desolatetimelines.acct.workspace.ws.mapper.WorkspaceDetailsMapper;
import com.desolatetimelines.acct.workspace.ws.mapper.WorkspacePropertiesMapper;
import com.desolatetimelines.acct.workspace.ws.mapper.WorkspaceUUIDResponseMapper;
import com.desolatetimelines.acct.workspace.ws.model.WorkspaceCollectionsResponse;
import com.desolatetimelines.acct.workspace.ws.model.WorkspaceDetails;
import com.desolatetimelines.acct.workspace.ws.model.WorkspaceProperties;
import com.desolatetimelines.acct.workspace.ws.model.WorkspaceUUIDResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.desolatetimelines.acct.common.ws.util.AcctJwtUtils.extractCurrentUserClaims;
import static com.desolatetimelines.acct.workspace.privilegesprovider.model.WorkspacePrivilegeIds.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/workspaces")
public class WorkspacesEndpointController implements WorkspacesEndpoint {

    private final AcctWorkspaceService workspaceService;

    public WorkspacesEndpointController(AcctWorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + WORKSPACES_SAVE_OWN + "')")
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public WorkspaceUUIDResponse saveWorkspace(
        @RequestParam(name = "workspaceUUID", required = false) String workspaceUUID,
        @RequestBody WorkspaceProperties workspaceProperties
    ) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Run the save operation
        return
            WorkspaceUUIDResponseMapper.fromAcctWorkspace(
                workspaceService.saveWorkspace(
                    userClaims.userUUID(),
                    WorkspacePropertiesMapper.toWorkspaceDetails(workspaceUUID, workspaceProperties),
                    userClaims.privilegeNames()
                )
            );
    }

    @Override
    @PreAuthorize(
        "hasAnyAuthority(" +
            "'SCOPE_backend', " +
            "'SCOPE_" + WORKSPACES_DELETE_OWN + "', " +
            "'SCOPE_" + WORKSPACES_DELETE_GROUP + "', " +
            "'SCOPE_" + WORKSPACES_DELETE_ANY + "'" +
            ")"
    )
    @DeleteMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public void deleteWorkspace(@RequestParam(name = "workspaceUUID") String workspaceUUID) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Run the delete operation for the userUUID, privilege names and workspaceUUID
        workspaceService.deleteWorkspace(userClaims.userUUID(), userClaims.privilegeNames(), workspaceUUID);
    }

    @Override
    @PreAuthorize(
        "hasAnyAuthority(" +
            "'SCOPE_backend', " +
            "'SCOPE_" + WORKSPACES_READ_OWN + "', " +
            "'SCOPE_" + WORKSPACES_READ_GROUP + "', " +
            "'SCOPE_" + WORKSPACES_READ_ANY + "'" +
            ")"
    )
    @GetMapping("/currentUser")
    public WorkspaceCollectionsResponse getUserAccessibleWorkspaces() {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Retrieve, map and return
        return
            WorkspaceCollectionsResponseMapper.fromAcctWorkspacesByOwnership(
                workspaceService.retrieveUserAccessibleWorkspaces(userClaims.userUUID(), userClaims.privilegeNames())
            );
    }

    @Override
    @PreAuthorize(
        "hasAnyAuthority(" +
            "'SCOPE_backend', " +
            "'SCOPE_" + WORKSPACES_READ_ANY + "', " +
            ")"
    )
    @GetMapping("/user")
    public Collection<WorkspaceDetails> getUserWorkspaces(@RequestParam(name = "userUUID") String userUUID) {
        return
            WorkspaceDetailsMapper.fromAcctWorkspacesCollection(
                workspaceService.retrieveWorkspacesOwnedByUser(userUUID)
            );
    }


}
