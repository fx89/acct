package com.desolatetimelines.acct.job.ws.spec.model;

/**
 * Contains properties used to set the current state of a given job
 *
 * @param jobOutcome   the outcome of the last run of the job (SUCCESS / FAILURE)
 * @param errorMessage optional error message in case of failure
 */
public record JobStateSetting(
    JobOutcome jobOutcome,
    String errorMessage
) {
}
