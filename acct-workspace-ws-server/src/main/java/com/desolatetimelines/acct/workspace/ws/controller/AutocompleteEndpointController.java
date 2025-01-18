package com.desolatetimelines.acct.workspace.ws.controller;

import com.desolatetimelines.acct.common.ws.model.AcctUserClaims;
import com.desolatetimelines.acct.workspace.service.AcctWorkspaceService;
import com.desolatetimelines.acct.workspace.ws.endpoint.AutocompleteEndpoint;
import com.desolatetimelines.acct.workspace.ws.mapper.AutocompleteDataResponseMapper;
import com.desolatetimelines.acct.workspace.ws.model.AutocompleteDataResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static com.desolatetimelines.acct.common.ws.util.AcctJwtUtils.extractCurrentUserClaims;
import static com.desolatetimelines.acct.workspace.privilegesprovider.model.WorkspacePrivilegeIds.AUTOCOMPLETE_GET;

@RestController
@RequestMapping("/autocomplete")
public class AutocompleteEndpointController implements AutocompleteEndpoint {

    private final AcctWorkspaceService workspaceService;

    public AutocompleteEndpointController(AcctWorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }


    @Override
    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + AUTOCOMPLETE_GET + "')")
    public Collection<AutocompleteDataResponse> getAutocompleteData(
        @RequestParam(name = "workspaceUUID") String workspaceUUID,
        @RequestParam(name = "accountUUID") String accountUUID,
        @RequestParam(name = "incomeOrExpenseItemUUID") String incomeOrExpenseItemUUID,
        @RequestParam(name = "textPattern") String textPattern
    ) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Run, map and return
        return
            AutocompleteDataResponseMapper.fromCollectionOfAcctAccountRecordAutocompleteData(
                workspaceService.getAutocompleteData(
                    userClaims.userUUID(),
                    workspaceUUID,
                    accountUUID,
                    incomeOrExpenseItemUUID,
                    textPattern,
                    userClaims.privilegeNames()
                )
            );
    }

}
