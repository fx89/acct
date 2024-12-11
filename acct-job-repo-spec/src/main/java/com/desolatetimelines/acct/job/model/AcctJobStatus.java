package com.desolatetimelines.acct.job.model;

import java.time.Instant;

/**
 * Represents the state of a given {@link AcctJob job}
 */
public interface AcctJobStatus {

    AcctJob getJob();

    void setJob(AcctJob job);

    JobStatus getJobStatus();

    void setJobStatus(JobStatus jobStatus);

    Instant getFirstStartDate();

    void setFirstStartDate(Instant firstStartDate);

    Instant getLastStartDate();

    void setLastStartDate(Instant lastStartDate);

    Instant getLastEndDate();

    void setLastEndDate(Instant lastEndDate);

    JobOutcome getLastOutcome();

    void setLastOutcome(JobOutcome lastOutcome);

    Instant getCurrentStartDate();

    void setCurrentStartDate(Instant currentStartDate);

    Integer getNumberOfFailuresSinceLastSuccessfulOutcome();

    void setNumberOfFailuresSinceLastSuccessfulOutcome(Integer numberOfFailuresSinceLastSuccessfulOutcome);

}
