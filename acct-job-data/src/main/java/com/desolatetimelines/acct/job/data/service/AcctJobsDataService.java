package com.desolatetimelines.acct.job.data.service;

import com.desolatetimelines.acct.job.model.AcctJob;
import com.desolatetimelines.acct.job.repository.AcctJobsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Facade for the data layer of the jobs registry service, allowing for the
 * loading and persisting of jobs registry data objects
 */
@Service
public class AcctJobsDataService {

    private final AcctJobsRepository jobsRepository;

    public AcctJobsDataService(AcctJobsRepository jobsRepository) {
        this.jobsRepository = jobsRepository;
    }

    /**
     * Returns a reference to the {@link AcctJob job} with the given job UUID
     * or an empty optional if not found
     *
     * @param jobUUID the given job UUID
     */
    public Optional<AcctJob> findAcctJobByJobUUID(String jobUUID) {
        return jobsRepository.findFirstByJobUUID(jobUUID);
    }

    /**
     * Creates a new {@link AcctJob job}
     *
     * @return a reference to the newly created job
     */
    public AcctJob createNewAcctJob() {
        return jobsRepository.createNew();
    }

    /**
     * Persists the referenced {@link AcctJob job}
     *
     * @param job the referenced job
     * @return a referenced to the persisted entity
     */
    public AcctJob saveJob(AcctJob job) {
        return jobsRepository.save(job);
    }

}
