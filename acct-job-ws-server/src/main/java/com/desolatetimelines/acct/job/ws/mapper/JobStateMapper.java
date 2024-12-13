package com.desolatetimelines.acct.job.ws.mapper;

import com.desolatetimelines.acct.job.model.AcctJobStatus;
import com.desolatetimelines.acct.job.ws.spec.model.JobOutcome;
import com.desolatetimelines.acct.job.ws.spec.model.JobState;
import com.desolatetimelines.acct.job.ws.spec.model.JobStatus;

/**
 * Provides mapper methods for the {@link JobState} data type
 */
public abstract class JobStateMapper {

    public static JobState fromAcctJobStatus(AcctJobStatus acctJobStatus) {
        return
            JobState.builder()
                .withJobUUID(acctJobStatus.getJob().getJobUUID())
                .withJobStatus(mapJobStatus(acctJobStatus.getJobStatus()))
                .withCurrentStartDate(acctJobStatus.getCurrentStartDate())
                .withFirstStartDate(acctJobStatus.getFirstStartDate())
                .withLastEndDate(acctJobStatus.getLastEndDate())
                .withLastOutcome(mapJobOutcome(acctJobStatus.getLastOutcome()))
                .withLastStartDate(acctJobStatus.getLastStartDate())
                .withNumberOfFailuresSinceLastSuccessfulOutcome(acctJobStatus.getNumberOfFailuresSinceLastSuccessfulOutcome())
                .build();
    }

    public static JobStatus mapJobStatus(com.desolatetimelines.acct.job.model.JobStatus jobStatus) {
        if (jobStatus == null) {
            return null;
        }

        if (jobStatus == com.desolatetimelines.acct.job.model.JobStatus.IDLE) {
            return JobStatus.IDLE;
        }

        if (jobStatus == com.desolatetimelines.acct.job.model.JobStatus.RUNNING) {
            return JobStatus.RUNNING;
        }

        throw new IllegalArgumentException("Unsupported job status");
    }

    public static JobOutcome mapJobOutcome(com.desolatetimelines.acct.job.model.JobOutcome jobOutcome) {
        if (jobOutcome == null) {
            return null;
        }

        if (jobOutcome == com.desolatetimelines.acct.job.model.JobOutcome.SUCCESS) {
            return JobOutcome.SUCCESS;
        }

        if (jobOutcome == com.desolatetimelines.acct.job.model.JobOutcome.FAILURE) {
            return JobOutcome.FAILURE;
        }

        throw new IllegalArgumentException("Unsupported job outcome");
    }

    public static com.desolatetimelines.acct.job.model.JobOutcome mapJobOutcome(JobOutcome jobOutcome) {
        if (jobOutcome == null) {
            return null;
        }

        if (jobOutcome == JobOutcome.SUCCESS) {
            return com.desolatetimelines.acct.job.model.JobOutcome.SUCCESS;
        }

        if (jobOutcome == JobOutcome.FAILURE) {
            return com.desolatetimelines.acct.job.model.JobOutcome.FAILURE;
        }

        throw new IllegalArgumentException("Unsupported job outcome");
    }

}
