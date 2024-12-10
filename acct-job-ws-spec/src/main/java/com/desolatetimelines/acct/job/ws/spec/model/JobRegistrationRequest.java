package com.desolatetimelines.acct.job.ws.spec.model;

/**
 * Contains the attributes required for registering a job
 *
 * @param jobServiceName the name of the service that registers the job
 * @param jobName        the name of the job
 * @param jobDescription a human-readable description of what the job does
 */
public record JobRegistrationRequest(
    String jobServiceName,
    String jobName,
    String jobDescription
) {
}
