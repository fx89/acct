package com.desolatetimelines.acct.job.model;

import java.time.Instant;

/**
 * Represents a state history record of a given {@link AcctJob job}
 */
public interface AcctJobStatusHistoryRecord {

    AcctJob getJob();

    void setJob(AcctJob job);

    Instant getJobStatusDate();

    void setJobStatusDate(Instant jobStatusDate);

    JobStatus getJobStatus();

    void setJobStatus(JobStatus jobStatus);

    JobOutcome getJobOutcome();

    void setJobOutcome(JobOutcome jobOutcome);

    String getJobErrorMessage();

    void setJobErrorMessage(String jobErrorMessage);

}
