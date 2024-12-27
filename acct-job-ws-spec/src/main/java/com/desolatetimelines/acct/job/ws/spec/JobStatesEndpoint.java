package com.desolatetimelines.acct.job.ws.spec;

import com.desolatetimelines.acct.common.ws.model.AcctPage;
import com.desolatetimelines.acct.job.ws.spec.model.JobState;
import com.desolatetimelines.acct.job.ws.spec.model.JobStateHistoryRecord;
import com.desolatetimelines.acct.job.ws.spec.model.JobStateSetting;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UUID;

import java.util.Collection;

/**
 * Specification for the job states endpoint
 */
public interface JobStatesEndpoint {

    /**
     * Returns the {@link JobState current state} of the job with the given UUID
     *
     * @param jobUUID the given UUID
     */
    JobState getJobState(@NotNull @UUID String jobUUID);

    /**
     * Returns a collection of {@link JobState job states} for all the registered jobs
     */
    Collection<JobState> getAllJobStates();

    /**
     * Sets the {@link JobState state} of the job with the given job UUID to a
     * {@link com.desolatetimelines.acct.job.ws.spec.model.JobStatus#RUNNING running}
     * state.
     *
     * @param jobUUID the given job UUID
     */
    void recordJobStarted(@NotNull @UUID String jobUUID);

    /**
     * Sets the {@link JobState state} of the job with the given job UUID
     * according to the provided {@link JobStateSetting settings}
     *
     * @param jobUUID the given job UUID
     * @param setting the provided settings
     */
    void recordJobFinished(@NotNull @UUID String jobUUID, JobStateSetting setting);

    /**
     * Returns a {@link AcctPage page} of {@link JobStateHistoryRecord job state history records}
     * of the requested size and having the requested page number for the job with the given job
     * UUID. The data set is sorted by {@link JobStateHistoryRecord#jobStatusDate() status date}
     * in descending order.
     *
     * @param jobUUID    the given job UUID
     * @param pageNumber the requested page number
     * @param pageSize   the requested size
     */
    AcctPage<JobStateHistoryRecord> getJobStateHistoryRecordsPage(@NotNull @UUID String jobUUID, int pageNumber, int pageSize);

}
