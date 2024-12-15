package com.desolatetimelines.acct.job.data.service;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.job.model.AcctJob;
import com.desolatetimelines.acct.job.model.AcctJobStatus;
import com.desolatetimelines.acct.job.model.AcctJobStatusHistoryRecord;
import com.desolatetimelines.acct.job.repository.AcctJobStatusHistoryRecordsRepository;
import com.desolatetimelines.acct.job.repository.AcctJobStatusesRepository;
import com.desolatetimelines.acct.job.repository.AcctJobsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Facade for the data layer of the jobs registry service, allowing for the
 * loading and persisting of jobs registry data objects
 */
@Service
public class AcctJobsDataService {

    private final AcctJobsRepository jobsRepository;

    private final AcctJobStatusesRepository jobStatusesRepository;

    private final AcctJobStatusHistoryRecordsRepository jobStatusHistoryRecordsRepository;

    public AcctJobsDataService(
        AcctJobsRepository jobsRepository,
        AcctJobStatusesRepository jobStatusesRepository,
        AcctJobStatusHistoryRecordsRepository jobStatusHistoryRecordsRepository
    ) {
        this.jobsRepository = jobsRepository;
        this.jobStatusesRepository = jobStatusesRepository;
        this.jobStatusHistoryRecordsRepository = jobStatusHistoryRecordsRepository;
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

    /**
     * Returns a set of all {@link AcctJob jobs} in the database
     */
    public Set<AcctJob> findAllAcctJobs() {
        return jobsRepository.findAll();
    }

    /**
     * Creates a new {@link AcctJobStatus job status} for the referenced {@link AcctJob job}
     *
     * @param job the referenced job
     * @return a reference to the newly created job status
     */
    public AcctJobStatus createNewAcctJobStatus(AcctJob job) {
        final AcctJobStatus jobStatus = jobStatusesRepository.createNew();
        jobStatus.setJob(job);
        return jobStatus;
    }

    /**
     * Returns the {@link AcctJobStatus status} of the referenced {@link AcctJob job}.
     * If the job does not a status then an empty optional is returned.
     *
     * @param job the referenced job
     */
    public Optional<AcctJobStatus> getJobStatus(AcctJob job) {
        return jobStatusesRepository.findFirstByJob(job);
    }

    /**
     * Saves the referenced {@link AcctJobStatus job status}
     *
     * @param jobStatus the referenced job status
     * @return a referenced to the saved entity
     */
    public AcctJobStatus saveJobStatus(AcctJobStatus jobStatus) {
        return jobStatusesRepository.save(jobStatus);
    }

    /**
     * Creates a new {@link AcctJobStatusHistoryRecord job status history record}
     * for the referenced {@link AcctJob job}
     *
     * @param job the referenced job
     * @return a reference to the created record
     */
    public AcctJobStatusHistoryRecord createNewAcctJobStatusHistoryRecord(AcctJob job) {
        final AcctJobStatusHistoryRecord historyRecord = jobStatusHistoryRecordsRepository.createNew();
        historyRecord.setJob(job);
        return historyRecord;
    }

    /**
     * Saves the referenced {@link AcctJobStatusHistoryRecord job status history record}
     *
     * @param acctJobStatusHistoryRecord the referenced job status history record
     * @return a reference to the saved entity
     */
    public AcctJobStatusHistoryRecord saveAcctJobStatusHistoryRecord(
        AcctJobStatusHistoryRecord acctJobStatusHistoryRecord
    ) {
        return jobStatusHistoryRecordsRepository.save(acctJobStatusHistoryRecord);
    }

    /**
     * Returns a {@link Page page} of {@link AcctJobStatusHistoryRecord job status history records}
     * for the {@link AcctJob job} with the given job UUID. The data set is sorted in descending order
     * by {@link AcctJobStatusHistoryRecord#getJobStatusDate() job status date}. The given page number
     * controls the number of the page. The given page size controls the size of the page.
     *
     * @param jobUUID    the given job UUID
     * @param pageNumber the given page number
     * @param pageSize   the given page size
     */
    public Page<AcctJobStatusHistoryRecord> getJobStateHistoryRecordsPage(
        String jobUUID,
        int pageNumber,
        int pageSize
    ) {
        return jobStatusHistoryRecordsRepository.getJobStateHistoryRecordsPage(jobUUID, pageNumber, pageSize);
    }

}
