package com.desolatetimelines.acct.job.service;

import com.desolatetimelines.acct.job.data.service.AcctJobsDataService;
import com.desolatetimelines.acct.job.exception.AcctJobsServiceIllegalArgumentException;
import com.desolatetimelines.acct.job.exception.AcctJobsServiceNotFoundException;
import com.desolatetimelines.acct.job.model.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

/**
 * Main class of the services layer of the ACCT jobs registry
 */
@Service
public class AcctJobsService {

    private final AcctJobsDataService dataService;

    public AcctJobsService(AcctJobsDataService dataService) {
        this.dataService = dataService;
    }

    /**
     * Registers a new job
     *
     * @param jobUUID        a V4 UUID that uniquely identifies the job in the ACCT ecosystem
     * @param jobServiceName the name of the service that registered the job
     * @param jobName        the name of the job
     * @param jobDescription a human-readable description of what the job does
     */
    @Transactional
    public void registerJob(String jobUUID, String jobServiceName, String jobName, String jobDescription) {
        // If a job with the given UUID already exists, throw an exception
        dataService.findAcctJobByJobUUID(jobUUID)
            .ifPresent(job -> {
                throw new AcctJobsServiceIllegalArgumentException(
                    "There already is a job registered for the given job UUID"
                );
            });

        // Create a new job entity
        final AcctJob newJob = dataService.createNewAcctJob();

        // Populate the job entity
        newJob.setJobUUID(jobUUID);
        newJob.setJobServiceName(jobServiceName);
        newJob.setJobName(jobName);
        newJob.setJobDescription(jobDescription);

        // Persist the job entity
        dataService.saveJob(newJob);
    }

    /**
     * Returns a set of all the jobs registered with the jobs registry
     */
    public Set<AcctJob> getAllRegisteredJobs() {
        return dataService.findAllAcctJobs();
    }

    /**
     * Returns the {@link AcctJobStatus status} of the job with the given job UUID
     *
     * @param jobUUID the given job UUID
     */
    public AcctJobStatus getJobStatus(String jobUUID) {
        // Get the job or fail
        final AcctJob job =
            dataService.findAcctJobByJobUUID(jobUUID)
                .orElseThrow(() -> new AcctJobsServiceNotFoundException("Job not found"));

        // Get the status or, if no status exists yet, return a new IDLE state
        return
            dataService.getJobStatus(job)
                .orElseGet(() -> {
                    final AcctJobStatus jobStatus = dataService.createNewAcctJobStatus(job);
                    jobStatus.setJobStatus(JobStatus.IDLE);
                    return jobStatus;
                });
    }

    /**
     * Sets the status of the job with the given jo UUID to a RUNNING state
     *
     * @param jobUUID the given job UUID
     */
    @Transactional
    public void recordJobStarted(String jobUUID) {
        // Find the state of the job with the given uuid
        final AcctJobStatus jobStatus = getJobStatus(jobUUID);

        // Get the current date and time
        final Instant currentDateAndTime = Instant.now();

        // Mark the job status as RUNNING
        jobStatus.setJobStatus(JobStatus.RUNNING);

        // Set the last outcome to null
        jobStatus.setLastOutcome(null);

        // If the first start date is not set, it is set at this point
        if (jobStatus.getFirstStartDate() == null) {
            jobStatus.setFirstStartDate(currentDateAndTime);
        }

        // Set the last start date to the value of the current start date
        jobStatus.setLastEndDate(jobStatus.getCurrentStartDate());

        // Set the current start date to the current date and time
        jobStatus.setCurrentStartDate(currentDateAndTime);

        // Save the job status
        dataService.saveJobStatus(jobStatus);

        // Create a new job status history record
        final AcctJobStatusHistoryRecord jobStatusHistoryRecord =
            dataService.createNewAcctJobStatusHistoryRecord(jobStatus.getJob());

        // Set the job status date to the current date and time
        jobStatusHistoryRecord.setJobStatusDate(currentDateAndTime);

        // Set the job status to RUNNING
        jobStatusHistoryRecord.setJobStatus(JobStatus.RUNNING);

        // Save the job status history record
        dataService.saveAcctJobStatusHistoryRecord(jobStatusHistoryRecord);
    }

    /**
     * Sets the status of the job with the given job UUID according to the given outcome an
     * optional error message
     *
     * @param jobUUID      the given job UUID
     * @param jobOutcome   the given outcome
     * @param errorMessage the given error message
     */
    @Transactional
    public void recordJobFinished(String jobUUID, JobOutcome jobOutcome, String errorMessage) {
        // Find the state of the job with the given uuid
        final AcctJobStatus jobStatus = getJobStatus(jobUUID);

        // Get the current date and time
        final Instant currentDateAndTime = Instant.now();

        // Mark the job status sa IDLE
        jobStatus.setJobStatus(JobStatus.IDLE);

        // Set the last start date field to the value of the current start date
        jobStatus.setLastStartDate(jobStatus.getCurrentStartDate());

        // Set the current start date to null
        jobStatus.setCurrentStartDate(null);

        // Sets the last end date field to the current date and time
        jobStatus.setLastEndDate(currentDateAndTime);

        // Set the last outcome to the given outcome
        jobStatus.setLastOutcome(jobOutcome);

        // If the outcome was SUCCESS then set the number of failures since the last successful outcome to 0
        if (jobOutcome == JobOutcome.SUCCESS) {
            jobStatus.setNumberOfFailuresSinceLastSuccessfulOutcome(0);
        }
        // If the outcome was FAILURE then increments the value of number of failures since the last successful outcome
        else {
            jobStatus.setNumberOfFailuresSinceLastSuccessfulOutcome(
                Optional.ofNullable(jobStatus.getNumberOfFailuresSinceLastSuccessfulOutcome()).orElse(0) + 1
            );
        }

        // Save the job status
        dataService.saveJobStatus(jobStatus);

        // Create a new job status history record
        final AcctJobStatusHistoryRecord jobStatusHistoryRecord =
            dataService.createNewAcctJobStatusHistoryRecord(jobStatus.getJob());

        // Set the job status date to the current date and time
        jobStatusHistoryRecord.setJobStatusDate(currentDateAndTime);

        // Set the job status to IDLE
        jobStatusHistoryRecord.setJobStatus(JobStatus.IDLE);

        // Set the job outcome to the given outcome
        jobStatusHistoryRecord.setJobOutcome(jobOutcome);

        // Set the job error message is to the given error message
        jobStatusHistoryRecord.setJobErrorMessage(errorMessage);

        // Save the job status history record
        dataService.saveAcctJobStatusHistoryRecord(jobStatusHistoryRecord);
    }

}
