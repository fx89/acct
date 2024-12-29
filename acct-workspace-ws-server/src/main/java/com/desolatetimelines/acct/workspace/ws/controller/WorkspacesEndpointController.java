package com.desolatetimelines.acct.workspace.ws.controller;

import com.desolatetimelines.acct.workspace.service.AcctWorkspaceService;
import com.desolatetimelines.acct.workspace.ws.endpoint.WorkspacesEndpoint;
import com.desolatetimelines.acct.workspace.ws.mapper.WorkspacePropertiesMapper;
import com.desolatetimelines.acct.workspace.ws.mapper.WorkspaceUUIDResponseMapper;
import com.desolatetimelines.acct.workspace.ws.model.WorkspaceProperties;
import com.desolatetimelines.acct.workspace.ws.model.WorkspaceUUIDResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.desolatetimelines.acct.common.ws.util.AcctJwtUtils.extractCurrentUserUUID;
import static com.desolatetimelines.acct.workspace.privilegesprovider.model.WorkspacePrivilegeIds.WORKSPACES_SAVE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/workspaces")
public class WorkspacesEndpointController implements WorkspacesEndpoint {

    private final AcctWorkspaceService workspaceService;

    public WorkspacesEndpointController(AcctWorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + WORKSPACES_SAVE + "')")
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public WorkspaceUUIDResponse saveWorkspace(
        @RequestParam(name = "workspaceUUID", required = false) String workspaceUUID,
        @RequestBody WorkspaceProperties workspaceProperties
    ) {
        return
            WorkspaceUUIDResponseMapper.fromAcctWorkspace(
                workspaceService.saveWorkspace(
                    extractCurrentUserUUID(),
                    WorkspacePropertiesMapper.toWorkspaceDetails(workspaceUUID, workspaceProperties)
                )
            );
    }

}
