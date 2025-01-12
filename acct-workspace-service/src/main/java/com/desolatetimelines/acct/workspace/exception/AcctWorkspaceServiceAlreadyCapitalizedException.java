package com.desolatetimelines.acct.workspace.exception;

import com.desolatetimelines.acct.common.exception.AcctException;
import com.desolatetimelines.acct.common.exception.NotFoundException;
import com.desolatetimelines.acct.workspace.service.AcctWorkspaceErrorCodesRegistryService;

import java.util.Map;

/**
 * Thrown by the {@link com.desolatetimelines.acct.workspace.service.AcctWorkspaceService}
 * when an attempt is made to capitalize an already-capitalized deposit
 */
@NotFoundException
public class AcctWorkspaceServiceAlreadyCapitalizedException extends AcctException {

    /**
     * @param errors      a reference to the error codes registry service defined in the workspace module
     * @param depositUUID the UUID of the affected deposit
     */
    public AcctWorkspaceServiceAlreadyCapitalizedException(
        AcctWorkspaceErrorCodesRegistryService errors,
        String depositUUID
    ) {
        super(
            errors.ALREADY_CAPITALIZED,
            Map.of("accountUUID", depositUUID)
        );
    }
}
