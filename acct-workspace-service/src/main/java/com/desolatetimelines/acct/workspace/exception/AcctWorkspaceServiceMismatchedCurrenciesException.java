package com.desolatetimelines.acct.workspace.exception;

import com.desolatetimelines.acct.common.exception.AcctException;
import com.desolatetimelines.acct.common.exception.NotFoundException;
import com.desolatetimelines.acct.workspace.service.AcctWorkspaceErrorCodesRegistryService;

import java.util.Map;

/**
 * Thrown by the {@link com.desolatetimelines.acct.workspace.service.AcctWorkspaceService}
 * when an illegal operation is attempted on two accounts with different currencies
 */
@NotFoundException
public class AcctWorkspaceServiceMismatchedCurrenciesException extends AcctException {

    /**
     * @param errors        a reference to the error codes registry service defined in the workspace module
     * @param currency1UUID the UUID of one of the currencies
     * @param currency2UUID the UUID of the other currency
     */
    public AcctWorkspaceServiceMismatchedCurrenciesException(
        AcctWorkspaceErrorCodesRegistryService errors,
        String currency1UUID,
        String currency2UUID
    ) {
        super(
            errors.MISMATCHED_CURRENCIES,
            Map.of(
                "currency1UUID", currency1UUID,
                "currency2UUID", currency2UUID
            )
        );
    }
}
