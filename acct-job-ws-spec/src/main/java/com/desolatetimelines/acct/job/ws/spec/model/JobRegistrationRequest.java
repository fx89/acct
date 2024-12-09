package com.desolatetimelines.acct.job.ws.spec.model;

/**
 * Contains the attributes required for registering a job
 *
 * @param jobServiceName
 * @param jobName
 * @param jobDescription
 */
public record JobRegistrationRequest(
    String jobServiceName,
    String jobName,
    String jobDescription
) {
}
