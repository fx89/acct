package com.desolatetimelines.acct.job.exception;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.job.service.AcctJobsService jobs service}
 * when an entity does not exist.
 */
public class AcctJobsServiceNotFoundException extends AcctJobsServiceException {

    public AcctJobsServiceNotFoundException(String message) {
        super(message);
    }

}
