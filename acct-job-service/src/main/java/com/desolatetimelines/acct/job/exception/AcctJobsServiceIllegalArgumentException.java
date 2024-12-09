package com.desolatetimelines.acct.job.exception;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.job.service.AcctJobsService jobs service}
 * when there's a problem with an argument presented to a given method.
 */
public class AcctJobsServiceIllegalArgumentException extends AcctJobsServiceException {

    public AcctJobsServiceIllegalArgumentException(String message) {
        super(message);
    }

}
