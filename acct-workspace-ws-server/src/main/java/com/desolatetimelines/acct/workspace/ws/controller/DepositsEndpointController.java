package com.desolatetimelines.acct.workspace.ws.controller;

import com.desolatetimelines.acct.common.ws.model.AcctUserClaims;
import com.desolatetimelines.acct.workspace.service.AcctWorkspaceService;
import com.desolatetimelines.acct.workspace.ws.endpoint.DepositsEndpoint;
import com.desolatetimelines.acct.workspace.ws.model.DepositModifiableAttributes;
import com.desolatetimelines.acct.workspace.ws.model.DepositProperties;
import com.desolatetimelines.acct.workspace.ws.model.DepositUUIDResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.desolatetimelines.acct.common.ws.util.AcctJwtUtils.extractCurrentUserClaims;
import static com.desolatetimelines.acct.workspace.privilegesprovider.model.WorkspacePrivilegeIds.DEPOSITS_SAVE;

@RestController
@RequestMapping("/deposits")
public class DepositsEndpointController implements DepositsEndpoint {

    private final AcctWorkspaceService workspaceService;

    public DepositsEndpointController(AcctWorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + DEPOSITS_SAVE + "')")
    @PutMapping(value = "")
    public DepositUUIDResponse createDepositFromSourceAccount(
        @NotNull @RequestParam(value = "workspaceUUID") String workspaceUUID,
        @RequestBody DepositProperties depositProperties
    ) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Save, wrap and return
        return
            new DepositUUIDResponse(
                workspaceService.createDeposit(
                    userClaims.userUUID(),
                    workspaceUUID,
                    depositProperties.sourceAccountUUID(),
                    depositProperties.depositAccountNumber(),
                    depositProperties.amount(),
                    depositProperties.projectedEndDate(),
                    depositProperties.interestPct(),
                    userClaims.privilegeNames()
                )
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + DEPOSITS_SAVE + "')")
    @PostMapping(value = "")
    public void updateDepositAttributes(
        @NotNull @RequestParam(name = "workspaceUUID") String workspaceUUID,
        @NotNull @RequestParam(name = "depositUUID") String depositUUID,
        @RequestBody DepositModifiableAttributes modifiableAttributes
    ) {
        // Get the user claims
        final AcctUserClaims userClaims = extractCurrentUserClaims();

        // Save
        workspaceService.updateDepositModifiableProperties(
            userClaims.userUUID(),
            workspaceUUID,
            depositUUID,
            modifiableAttributes.depositAccountNumber(),
            modifiableAttributes.projectedEndDate(),
            userClaims.privilegeNames()
        );
    }

}
