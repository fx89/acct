package com.desolatetimelines.acct.workspace.ws.client;

import com.desolatetimelines.acct.workspace.ws.endpoint.AccountRecordsEndpoint;
import com.desolatetimelines.acct.workspace.ws.model.AccountRecordIdResponse;
import com.desolatetimelines.acct.workspace.ws.model.AccountRecordProperties;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${WORKSPACE_APPLICATION_NAME}-account-records",
    name = "${WORKSPACE_APPLICATION_NAME}/${WORKSPACE_SERVER_CONTEXT_PATH}/accounts-records"
)
public interface RESTAccountRecordsEndpointClient extends AccountRecordsEndpoint {

    @Override
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    AccountRecordIdResponse saveAccountRecord(
        @NotNull @RequestParam(name = "workspaceUUID") String workspaceUUID,
        @NotNull @RequestParam(name = "accountUUID") String accountUUID,
        @RequestParam(name = "accountRecordId", required = false) Long accountRecordId,
        @RequestBody AccountRecordProperties accountRecordProperties
    );

}
