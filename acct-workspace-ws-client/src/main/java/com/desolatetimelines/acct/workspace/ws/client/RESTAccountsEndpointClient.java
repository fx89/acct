package com.desolatetimelines.acct.workspace.ws.client;

import com.desolatetimelines.acct.workspace.ws.endpoint.AccountsEndpoint;
import com.desolatetimelines.acct.workspace.ws.model.AccountExtendedProperties;
import com.desolatetimelines.acct.workspace.ws.model.AccountProperties;
import com.desolatetimelines.acct.workspace.ws.model.AccountUUIDResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${WORKSPACE_APPLICATION_NAME}-accounts",
    name = "${WORKSPACE_APPLICATION_NAME}/${WORKSPACE_SERVER_CONTEXT_PATH}/accounts"
)
public interface RESTAccountsEndpointClient extends AccountsEndpoint {

    @Override
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    AccountUUIDResponse saveAccount(
        @NotNull @RequestParam(name = "workspaceUUID") String workspaceUUID,
        @RequestParam(name = "accountUUID", required = false) String accountUUID,
        @NotNull @RequestBody AccountProperties accountProperties
    );

    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    Collection<AccountExtendedProperties> getAccountsInWorkspace(
        @NotNull @RequestParam(name = "workspaceUUID") String workspaceUUID
    );

}
