package com.desolatetimelines.acct.job.ws.spec;

import com.desolatetimelines.acct.job.ws.spec.model.JobState;

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

}
