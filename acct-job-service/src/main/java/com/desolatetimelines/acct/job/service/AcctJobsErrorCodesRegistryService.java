package com.desolatetimelines.acct.job.service;

import com.desolatetimelines.acct.common.service.AbstractErrorCodesRegistryService;
import org.springframework.stereotype.Service;

/**
 * Provides error codes for the Jobs service
 */
@Service
public class AcctJobsErrorCodesRegistryService extends AbstractErrorCodesRegistryService {
    public String JOB_ALREADY_RUNNING;

    public String JOB_NOT_FOUND;

    public String ILLEGAL_ARGUMENT;

    protected AcctJobsErrorCodesRegistryService() {
        super(Integer.parseInt(System.getenv("JOBS_REGISTRY_SERVICE_NUMBER")));
    }

    @Override
    protected void initializeServiceSpecificErrorCodes() {
        final String CAT_NAME_SCHEDULING = "Job scheduling";

        final String CAT_NAME_REGISTRATION = "Job registration";

        JOB_ALREADY_RUNNING = resolveErrorCode(
            CAT_NAME_SCHEDULING,
            "Job already running",
            "An error that occurs when an attempt to start a job that's already registered as running occurs"
        );

        ILLEGAL_ARGUMENT = resolveErrorCode(
            CAT_NAME_REGISTRATION,
            "Illegal argument",
            "An illegal argument was supplied when trying to register or unregister a job"
        );

        JOB_NOT_FOUND = resolveErrorCode(
            CAT_NAME_REGISTRATION,
            "Job not found",
            "An attempt to get information about a job was made, but the job does not exist"
        );
    }
}
