package com.desolatetimelines.acct.job.service;

import com.desolatetimelines.acct.job.data.service.AcctJobsDataService;
import com.desolatetimelines.acct.job.exception.AcctJobsServiceIllegalArgumentException;
import com.desolatetimelines.acct.job.model.AcctJob;
import org.springframework.stereotype.Service;

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

}
