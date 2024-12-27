package com.desolatetimelines.acct.job.exception;

import com.desolatetimelines.acct.common.exception.AcctException;

import java.util.Map;

/**
 * Generic exception thrown by the {@link com.desolatetimelines.acct.job.service.AcctJobsService jobs service}
 */
public class AcctJobsServiceException extends AcctException {

    public AcctJobsServiceException(String errorCode, Map<String, String> parameters) {
        super(errorCode, parameters);
    }

    public AcctJobsServiceException(String errorCode, Map<String, String> parameters, Throwable cause) {
        super(errorCode, parameters, cause);
    }

}
