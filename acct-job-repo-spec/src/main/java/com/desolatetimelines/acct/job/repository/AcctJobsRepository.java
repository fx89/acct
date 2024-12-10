package com.desolatetimelines.acct.job.repository;

import com.desolatetimelines.acct.job.model.AcctJob;

import java.util.Optional;
import java.util.Set;

/**
 * Specification for the data repository that retrieves and persists {@link AcctJob jobs}
 */
public interface AcctJobsRepository {

    /**
     * Returns the job with the given job UUID or an empty optional if not found
     *
     * @param jobUUID the given job UUID
     */
    Optional<AcctJob> findFirstByJobUUID(String jobUUID);

    /**
     * Creates a new {@link AcctJob job}
     *
     * @return a reference to the newly created job
     */
    AcctJob createNew();

    /**
     * Persists the referenced {@link AcctJob job}
     *
     * @param job the referenced job
     * @return a referenced to the persisted entity
     */
    AcctJob save(AcctJob job);

    Set<AcctJob> findAll();

}
