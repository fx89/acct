package com.desolatetimelines.acct.workspace.ws.client;

import com.desolatetimelines.acct.workspace.ws.endpoint.WorkspacesEndpoint;
import com.desolatetimelines.acct.workspace.ws.model.WorkspaceCollectionsResponse;
import com.desolatetimelines.acct.workspace.ws.model.WorkspaceDetails;
import com.desolatetimelines.acct.workspace.ws.model.WorkspaceProperties;
import com.desolatetimelines.acct.workspace.ws.model.WorkspaceUUIDResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${WORKSPACE_APPLICATION_NAME}-workspaces",
    name = "${WORKSPACE_APPLICATION_NAME}/${WORKSPACE_SERVER_CONTEXT_PATH}/workspaces"
)
public interface RESTWorkspacesEndpointClient extends WorkspacesEndpoint {

    @Override
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    WorkspaceUUIDResponse saveWorkspace(
        @RequestParam(name = "workspaceUUID", required = false) String workspaceUUID,
        @RequestBody WorkspaceProperties workspaceProperties
    );

    @Override
    @DeleteMapping(value = "", produces = APPLICATION_JSON_VALUE)
    void deleteWorkspace(@RequestParam(name = "workspaceUUID") String workspaceUUID);

    @Override
    @GetMapping("/currentUser")
    WorkspaceCollectionsResponse getUserAccessibleWorkspaces();

    @Override
    @GetMapping("/user")
    Collection<WorkspaceDetails> getUserWorkspaces(@RequestParam(name = "userUUID") String userUUID);

    @Override
    @GetMapping("/group")
    Collection<WorkspaceDetails> getGroupWorkspaces(@RequestParam(name = "groupUUID") String groupUUID);

}
