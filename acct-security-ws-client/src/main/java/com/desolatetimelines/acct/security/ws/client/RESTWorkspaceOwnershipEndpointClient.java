package com.desolatetimelines.acct.security.ws.client;

import com.desolatetimelines.acct.security.ws.endpoint.WorkspaceOwnershipEndpoint;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnedWorkspacesGroup;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;
import com.desolatetimelines.acct.security.ws.endpoint.model.WorkspaceAccessibilityReport;
import com.desolatetimelines.acct.security.ws.endpoint.model.WorkspaceOwner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${SECURITY_APPLICATION_NAME}-workspace-ownership",
    name = "${SECURITY_APPLICATION_NAME}/${SECURITY_SERVER_CONTEXT_PATH}/workspaceOwners"
)
public interface RESTWorkspaceOwnershipEndpointClient extends WorkspaceOwnershipEndpoint {

    @Override
    @GetMapping(value = "/ownedWorkspaces", produces = APPLICATION_JSON_VALUE)
    Collection<String> getWorkspacesOwnedByOwnerOfType(
        @RequestParam("ownerType") OwnerType ownerType,
        @RequestParam("ownerUUID") String ownerUUID
    );

    @Override
    @GetMapping(value = "/userAccessibleWorkspaces", produces = APPLICATION_JSON_VALUE)
    OwnedWorkspacesGroup getUserAccessibleWorkspaces(@RequestParam("userUUID") String userUUID);

    @Override
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    void addWorkspaceOwner(@RequestBody WorkspaceOwner workspaceOwner);

    @Override
    @DeleteMapping(value = "")
    void deleteWorkspaceOwner(
        @RequestParam("ownerType") OwnerType ownerType,
        @RequestParam("ownerUUID") String ownerUUID,
        @RequestParam("workspaceUUID") String workspaceUUID
    );

    @Override
    @GetMapping(value = "/userAccessibleWorkspace")
    WorkspaceAccessibilityReport isUserAccessibleWorkspace(
        @RequestParam("userUUID") String userUUID,
        @RequestParam("workspaceUUID") String workspaceUUID
    );

}
