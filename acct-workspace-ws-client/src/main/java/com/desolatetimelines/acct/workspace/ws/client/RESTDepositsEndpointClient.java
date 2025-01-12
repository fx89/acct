package com.desolatetimelines.acct.workspace.ws.client;

import com.desolatetimelines.acct.common.ws.model.AcctPage;
import com.desolatetimelines.acct.workspace.ws.endpoint.DepositsEndpoint;
import com.desolatetimelines.acct.workspace.ws.model.DepositDetails;
import com.desolatetimelines.acct.workspace.ws.model.DepositModifiableAttributes;
import com.desolatetimelines.acct.workspace.ws.model.DepositProperties;
import com.desolatetimelines.acct.workspace.ws.model.DepositUUIDResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
    contextId = "${WORKSPACE_APPLICATION_NAME}-deposits",
    name = "${WORKSPACE_APPLICATION_NAME}/${WORKSPACE_SERVER_CONTEXT_PATH}/deposits"
)
public interface RESTDepositsEndpointClient extends DepositsEndpoint {

    @Override
    @PutMapping(value = "")
    DepositUUIDResponse createDepositFromSourceAccount(
        @NotNull @RequestParam(value = "workspaceUUID") String workspaceUUID,
        @RequestBody DepositProperties depositProperties
    );

    @Override
    @PostMapping(value = "")
    void updateDepositAttributes(
        @NotNull @RequestParam(name = "workspaceUUID") String workspaceUUID,
        @NotNull @RequestParam(name = "depositUUID") String depositUUID,
        @RequestBody DepositModifiableAttributes modifiableAttributes
    );

    @Override
    @GetMapping(value = "")
    AcctPage<DepositDetails> getSortedPageOfDepositsByWorkspaceUUIDAndOptionalBankUUID(
        @NotNull @RequestParam(name = "workspaceUUID") String workspaceUUID,
        @RequestParam(name = "bankUUID", required = false) String bankUUID,
        @RequestParam(name = "pageNumber") int pageNumber,
        @RequestParam(name = "pageSize") int pageSize
    );

    @Override
    @GetMapping(value = "/toCapitalize")
    AcctPage<DepositDetails> getSortedPageOfDepositsToCapitalize(
        @NotNull @RequestParam(name = "workspaceUUID") String workspaceUUID,
        @RequestParam(name = "pageNumber") int pageNumber,
        @RequestParam(name = "pageSize") int pageSize
    );

}
