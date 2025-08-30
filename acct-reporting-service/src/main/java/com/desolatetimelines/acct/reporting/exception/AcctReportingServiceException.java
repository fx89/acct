package com.desolatetimelines.acct.reporting.exception;

import com.desolatetimelines.acct.common.exception.AcctException;

import java.util.Map;

/**
 * Base class for exceptions thrown by the {@link com.desolatetimelines.acct.reporting.service.AcctReportingService}
 */
public class AcctReportingServiceException extends AcctException {

    public AcctReportingServiceException(String errorCode) {
        super(errorCode);
    }

    public AcctReportingServiceException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public AcctReportingServiceException(String errorCode, Map<String, String> parameters) {
        super(errorCode, parameters);
    }

    public AcctReportingServiceException(String errorCode, Map<String, String> parameters, Throwable cause) {
        super(errorCode, parameters, cause);
    }
}
