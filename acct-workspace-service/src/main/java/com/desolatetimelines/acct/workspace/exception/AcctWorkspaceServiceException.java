package com.desolatetimelines.acct.workspace.exception;

import com.desolatetimelines.acct.common.exception.AcctException;

import java.util.Map;

/**
 * Base class for exceptions thrown by the {@link com.desolatetimelines.acct.workspace.service.AcctWorkspaceService}
 */
public class AcctWorkspaceServiceException extends AcctException {

    public AcctWorkspaceServiceException(String errorCode) {
        super(errorCode);
    }

    public AcctWorkspaceServiceException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public AcctWorkspaceServiceException(String errorCode, Map<String, String> parameters) {
        super(errorCode, parameters);
    }

    public AcctWorkspaceServiceException(String errorCode, Map<String, String> parameters, Throwable cause) {
        super(errorCode, parameters, cause);
    }
}
