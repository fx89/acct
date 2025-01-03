package com.desolatetimelines.acct.workspace.ws.controller;

import com.desolatetimelines.acct.common.ws.model.AcctUserClaims;
import com.desolatetimelines.acct.workspace.service.AcctWorkspaceService;
import com.desolatetimelines.acct.workspace.ws.endpoint.AccountRecordsEndpoint;
import com.desolatetimelines.acct.workspace.ws.mapper.AccountRecordDetailsMapper;
import com.desolatetimelines.acct.workspace.ws.model.AccountRecordIdResponse;
import com.desolatetimelines.acct.workspace.ws.model.AccountRecordProperties;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.desolatetimelines.acct.common.ws.util.AcctJwtUtils.extractCurrentUserClaims;
import static com.desolatetimelines.acct.workspace.privilegesprovider.model.WorkspacePrivilegeIds.ACCOUNTS_SAVE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/accountRecords")
public class AccountRecordsEndpointController implements AccountRecordsEndpoint {

    private final AcctWorkspaceService workspaceService;

    public AccountRecordsEndpointController(AcctWorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + ACCOUNTS_SAVE + "')")
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public AccountRecordIdResponse saveAccountRecord(
        @NotNull @RequestParam(name = "workspaceUUID") String workspaceUUID,
        @NotNull @RequestParam(name = "accountUUID") String accountUUID,
        @RequestParam(name = "accountRecordId", required = false) Long accountRecordId,
        @RequestBody AccountRecordProperties accountRecordProperties
    ) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Create or update the account record based on the given parameters and the provisioned user claims
        return
            new AccountRecordIdResponse(
                workspaceService.saveAccountRecord(
                    userClaims.userUUID(),
                    workspaceUUID,
                    accountUUID,
                    AccountRecordDetailsMapper.fromAccountRecordProperties(accountRecordId, accountRecordProperties),
                    userClaims.privilegeNames()
                )
            );
    }

}
