package com.desolatetimelines.acct.workspace.exception;

import com.desolatetimelines.acct.common.exception.AcctException;
import com.desolatetimelines.acct.common.exception.NotFoundException;
import com.desolatetimelines.acct.workspace.service.AcctWorkspaceErrorCodesRegistryService;

import java.util.Map;

/**
 * Thrown by the {@link com.desolatetimelines.acct.workspace.service.AcctWorkspaceService}
 * when an operation is attempted on two accounts that are expected to have different currencies
 * and yet have the same currency
 */
@NotFoundException
public class AcctWorkspaceServiceSameCurrencyException extends AcctException {

    /**
     * @param errors       a reference to the error codes registry service defined in the workspace module
     * @param accountUUID1 the UUID of one of the accounts
     * @param accountUUID2 the UUID of the other account
     */
    public AcctWorkspaceServiceSameCurrencyException(
        AcctWorkspaceErrorCodesRegistryService errors,
        String accountUUID1,
        String accountUUID2
    ) {
        super(
            errors.SAME_CURRENCY,
            Map.of(
                "accountUUID1", accountUUID1,
                "accountUUID2", accountUUID2
            )
        );
    }
}
