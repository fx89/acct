package com.desolatetimelines.acct.job.exception;

import com.desolatetimelines.acct.common.exception.ForbiddenException;

import java.util.Map;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.job.service.AcctJobsService jobs service}
 * when asked to record a job as started while the job is already in a RUNNING state.
 */
@ForbiddenException
public class AcctJobsServiceJobAlreadyRunningException extends AcctJobsServiceException {

    public AcctJobsServiceJobAlreadyRunningException(String errorCode, Map<String, String> parameters) {
        super(errorCode, parameters);
    }

    public AcctJobsServiceJobAlreadyRunningException(String errorCode, Map<String, String> parameters, Throwable cause) {
        super(errorCode, parameters, cause);
    }

}
