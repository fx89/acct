package com.desolatetimelines.acct.workspace.ws.controller;

import com.desolatetimelines.acct.common.ws.model.AcctUserClaims;
import com.desolatetimelines.acct.workspace.service.AcctWorkspaceService;
import com.desolatetimelines.acct.workspace.ws.endpoint.AccountsEndpoint;
import com.desolatetimelines.acct.workspace.ws.mapper.AccountDetailsMapper;
import com.desolatetimelines.acct.workspace.ws.model.AccountProperties;
import com.desolatetimelines.acct.workspace.ws.model.AccountUUIDResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.desolatetimelines.acct.common.ws.util.AcctJwtUtils.extractCurrentUserClaims;
import static com.desolatetimelines.acct.workspace.privilegesprovider.model.WorkspacePrivilegeIds.ACCOUNT_SAVE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/accounts")
public class AccountsEndpointController implements AccountsEndpoint {

    private final AcctWorkspaceService workspaceService;

    public AccountsEndpointController(AcctWorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + ACCOUNT_SAVE + "')")
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public AccountUUIDResponse saveAccount(
        @NotNull @RequestParam(name = "workspaceUUID") String workspaceUUID,
        @RequestParam(name = "accountUUID", required = false) String accountUUID,
        @NotNull @RequestBody AccountProperties accountProperties
    ) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Run, map and return
        return
            new AccountUUIDResponse(
                workspaceService.saveAccount(
                    userClaims.userUUID(),
                    workspaceUUID,
                    AccountDetailsMapper.fromAccountProperties(accountUUID, accountProperties),
                    userClaims.privilegeNames()
                )
            );
    }
}
