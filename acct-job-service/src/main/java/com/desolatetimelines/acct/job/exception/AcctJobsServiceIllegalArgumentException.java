package com.desolatetimelines.acct.job.exception;

import com.desolatetimelines.acct.job.service.AcctJobsErrorCodesRegistryService;

import java.util.Map;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.job.service.AcctJobsService jobs service}
 * when there's a problem with an argument presented to a given method.
 */
public class AcctJobsServiceIllegalArgumentException extends AcctJobsServiceException {

    public AcctJobsServiceIllegalArgumentException(
        AcctJobsErrorCodesRegistryService errors,
        String argumentName,
        String argumentValue
    ) {
        super(errors.ILLEGAL_ARGUMENT, Map.of(argumentName, argumentValue));
    }

}
