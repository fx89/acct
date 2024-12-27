package com.desolatetimelines.acct.job.exception;

import com.desolatetimelines.acct.common.exception.NotFoundException;
import com.desolatetimelines.acct.job.service.AcctJobsErrorCodesRegistryService;

import java.util.Map;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.job.service.AcctJobsService jobs service}
 * when an entity does not exist.
 */
@NotFoundException
public class AcctJobsServiceNotFoundException extends AcctJobsServiceException {

    public AcctJobsServiceNotFoundException(AcctJobsErrorCodesRegistryService errors, Map<String, String> parameters) {
        super(errors.JOB_NOT_FOUND, parameters);
    }

}
