package com.desolatetimelines.acct.workspace.exception;

import com.desolatetimelines.acct.common.exception.AcctException;
import com.desolatetimelines.acct.common.exception.NotFoundException;
import com.desolatetimelines.acct.workspace.service.AcctWorkspaceErrorCodesRegistryService;

import java.util.Map;

/**
 * Thrown by the {@link com.desolatetimelines.acct.workspace.service.AcctWorkspaceService}
 * when an account does not have sufficient funds for the attempted operation
 */
@NotFoundException
public class AcctWorkspaceServiceInsufficientFundsException extends AcctException {

    /**
     * @param errors      a reference to the error codes registry service defined in the workspace module
     * @param accountUUID the UUID of the affected account
     */
    public AcctWorkspaceServiceInsufficientFundsException(
        AcctWorkspaceErrorCodesRegistryService errors,
        String accountUUID
    ) {
        super(
            errors.INSUFFICIENT_FUNDS,
            Map.of("accountUUID", accountUUID)
        );
    }
}
