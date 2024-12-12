package com.desolatetimelines.acct.job.ws.spec;

import com.desolatetimelines.acct.job.ws.spec.model.JobState;
import com.desolatetimelines.acct.job.ws.spec.model.JobStateSetting;

/**
 * Specification for the job states endpoint
 */
public interface JobStatesEndpoint {

    /**
     * Returns the {@link JobState current state} of the job with the given UUID
     *
     * @param jobUUID the given UUID
     */
    JobState getJobState(String jobUUID);

    /**
     * Sets the {@link JobState state} of the job with the given job UUID to a
     * {@link com.desolatetimelines.acct.job.ws.spec.model.JobStatus#RUNNING running}
     * state.
     *
     * @param jobUUID the given job UUID
     */
    void recordJobStarted(String jobUUID);

    /**
     * Sets the {@link JobState state} of the job with the given job UUID
     * according to the provided {@link JobStateSetting settings}
     *
     * @param jobUUID the given job UUID
     * @param setting the provided settings
     */
    void recordJobFinished(String jobUUID, JobStateSetting setting);

}
