package com.desolatetimelines.acct.job.framework.exception;

/**
 * Exception thrown by the jobs framework when a service tries to start a job that's already running
 */
public class JobAlreadyRunningException extends RuntimeException {

    public JobAlreadyRunningException(String message) {
        super(message);
    }

}
