package com.desolatetimelines.acct.job.exception;

/**
 * Generic exception thrown by the {@link com.desolatetimelines.acct.job.service.AcctJobsService jobs service}
 */
public class AcctJobsServiceException extends RuntimeException {

    public AcctJobsServiceException(String message) {
        super(message);
    }

}
