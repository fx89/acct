package com.desolatetimelines.acct.workspace.ws.controller;

import com.desolatetimelines.acct.common.ws.model.AcctPage;
import com.desolatetimelines.acct.common.ws.model.AcctSortDirection;
import com.desolatetimelines.acct.common.ws.model.AcctUserClaims;
import com.desolatetimelines.acct.workspace.service.AcctWorkspaceService;
import com.desolatetimelines.acct.workspace.ws.endpoint.AccountRecordsEndpoint;
import com.desolatetimelines.acct.workspace.ws.mapper.AccountRecordDetailsMapper;
import com.desolatetimelines.acct.workspace.ws.mapper.AccountRecordEnhancedDetailsMapper;
import com.desolatetimelines.acct.workspace.ws.model.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.desolatetimelines.acct.common.ws.util.AcctJwtUtils.extractCurrentUserClaims;
import static com.desolatetimelines.acct.workspace.privilegesprovider.model.WorkspacePrivilegeIds.*;
import static com.desolatetimelines.acct.workspace.ws.mapper.AcctSortDirectionMapper.toSortDirection;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/accountRecords")
public class AccountRecordsEndpointController implements AccountRecordsEndpoint {

    private final AcctWorkspaceService workspaceService;

    public AccountRecordsEndpointController(AcctWorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + ACCOUNT_RECORDS_SAVE + "')")
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

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + ACCOUNT_RECORDS_READ + "')")
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public AcctPage<AccountRecordEnhancedDetails> findSortedPageOfAccountRecordsByTextPattern(
        @NotNull @RequestParam(value = "workspaceUUID") String workspaceUUID,
        @NotNull @RequestParam(value = "accountUUID") String accountUUID,
        @RequestParam(value = "pattern", required = false) String pattern,
        @NotNull @RequestParam(value = "pageNumber") int pageNumber,
        @NotNull @RequestParam(value = "pageSize") int pageSize,
        @NotNull @RequestParam(value = "sortDirection") AcctSortDirection sortDirection
        ) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Transform and return
        return
            AccountRecordEnhancedDetailsMapper.fromPageOfAccountRecordExtendedDetails(
                workspaceService.findSortedPageOfAccountRecordsByTextPattern(
                    userClaims.userUUID(),
                    workspaceUUID,
                    accountUUID,
                    pattern,
                    pageNumber,
                    pageSize,
                    toSortDirection(sortDirection),
                    userClaims.privilegeNames()
                ),
                pageNumber,
                pageSize
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + ACCOUNT_RECORDS_TRANSFER + "')")
    @PostMapping(value = "/transfer")
    public void transferAmountBetweenAccountsWithSameCurrency(
        @NotNull @RequestParam String workspaceUUID,
        @RequestBody CurrencyTransfer currencyTransfer
    ) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Run the operation
        workspaceService.transferAmountBetweenAccountsWithSameCurrency(
            userClaims.userUUID(),
            workspaceUUID,
            currencyTransfer.sourceAccountUUID(),
            currencyTransfer.targetAccountUUID(),
            currencyTransfer.amount(),
            userClaims.privilegeNames()
        );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + ACCOUNT_RECORDS_TRANSFER + "')")
    @PostMapping(value = "/exchange")
    public void currencyExchange(
        @NotNull @RequestParam(value = "workspaceUUID") String workspaceUUID,
        @RequestBody CurrencyExchange currencyExchange
    ) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Run the operation
        workspaceService.currencyExchange(
            userClaims.userUUID(),
            workspaceUUID,
            currencyExchange.currencyTransfer().sourceAccountUUID(),
            currencyExchange.currencyTransfer().targetAccountUUID(),
            currencyExchange.currencyTransfer().amount(),
            currencyExchange.exchangeRate(),
            currencyExchange.originalAccountRecordId(),
            userClaims.privilegeNames()
        );
    }
}
