package com.desolatetimelines.acct.workspace.ws.client;

import com.desolatetimelines.acct.workspace.ws.endpoint.AutocompleteEndpoint;
import com.desolatetimelines.acct.workspace.ws.model.AutocompleteDataResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@FeignClient(
    contextId = "${WORKSPACE_APPLICATION_NAME}-autocomplete",
    name = "${WORKSPACE_APPLICATION_NAME}/${WORKSPACE_SERVER_CONTEXT_PATH}/autocomplete"
)
public interface RESTAutocompleteEndpointClient extends AutocompleteEndpoint {

    @Override
    @GetMapping("")
    Collection<AutocompleteDataResponse> getAutocompleteData(
        @RequestParam(name = "workspaceUUID") String workspaceUUID,
        @RequestParam(name = "accountUUID") String accountUUID,
        @RequestParam(name = "incomeOrExpenseItemUUID") String incomeOrExpenseItemUUID,
        @RequestParam(name = "textPattern") String textPattern
    );

}
