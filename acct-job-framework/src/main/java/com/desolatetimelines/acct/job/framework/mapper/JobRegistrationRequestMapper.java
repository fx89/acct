package com.desolatetimelines.acct.job.framework.mapper;

import com.desolatetimelines.acct.job.ws.spec.model.JobRegistrationRequest;
import com.desolatetimelines.acct.job.ws.spec.model.JobSummary;

/**
 * Provides mapping methods for the {@link JobRegistrationRequest} type
 */
public abstract class JobRegistrationRequestMapper {

    public static JobRegistrationRequest fromJobSummary(JobSummary jobSummary) {
        return
            JobRegistrationRequest.builder()
                .withJobServiceName(jobSummary.jobServiceName())
                .withJobName(jobSummary.jobName())
                .withJobDescription(jobSummary.jobDescription())
                .build();
    }

}
