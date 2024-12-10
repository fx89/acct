package com.desolatetimelines.acct.job.ws.mapper;

import com.desolatetimelines.acct.job.model.AcctJob;
import com.desolatetimelines.acct.job.ws.spec.model.JobSummary;

/**
 * provides mapping methods to and from the {@link JobSummary} data type
 */
public abstract class JobSummaryMapper {

    public static JobSummary fromAcctJob(AcctJob acctJob) {
        return
            JobSummary.builder()
                .withJobUUID(acctJob.getJobUUID())
                .withJobServiceName(acctJob.getJobServiceName())
                .withJobName(acctJob.getJobName())
                .withJobDescription(acctJob.getJobDescription())
                .build();
    }

}
