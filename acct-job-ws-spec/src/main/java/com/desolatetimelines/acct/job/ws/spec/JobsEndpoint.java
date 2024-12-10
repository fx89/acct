package com.desolatetimelines.acct.job.ws.spec;

import com.desolatetimelines.acct.job.ws.spec.model.JobRegistrationRequest;
import com.desolatetimelines.acct.job.ws.spec.model.JobSummary;

import java.util.Collection;

/**
 * Specification for the jobs endpoint
 */
public interface JobsEndpoint {

    /**
     * Registers a job for the given job UUID with the parameters contained
     * by the given job registration request
     *
     * @param jobUUID the given job UUID
     * @param request the given job registration request
     */
    void registerJob(String jobUUID, JobRegistrationRequest request);

    /**
     * Returns a list of all jobs registered in the jobs registry
     */
    Collection<JobSummary> getAllRegisteredJobs();

}
