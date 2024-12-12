package com.desolatetimelines.acct.job.repository;

import com.desolatetimelines.acct.job.model.AcctJob;
import com.desolatetimelines.acct.job.model.AcctJobStatus;

import java.util.Optional;

/**
 * Specification for the data repository that retrieves and persists {@link AcctJobStatus job statuses}
 */
public interface AcctJobStatusesRepository {

    /**
     * Retrieves the job status for the referenced {@link AcctJob job}
     * or returns an empty optional
     *
     * @param job the referenced job
     */
    Optional<AcctJobStatus> findFirstByJob(AcctJob job);

    /**
     * Creates a new {@link AcctJobStatus job status}
     *
     * @return a reference to the newly created entity
     */
    AcctJobStatus createNew();

    /**
     * Saves the referenced {@link AcctJobStatus job status}
     *
     * @param jobStatus the referenced job status
     * @return a reference to the saved entity
     */
    AcctJobStatus save(AcctJobStatus jobStatus);

}
