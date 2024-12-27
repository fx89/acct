package com.desolatetimelines.acct.job.framework.service;

import com.desolatetimelines.acct.job.framework.mapper.JobRegistrationRequestMapper;
import com.desolatetimelines.acct.job.ws.client.RESTJobStatesEndpointClient;
import com.desolatetimelines.acct.job.ws.client.RESTJobsEndpointClient;
import com.desolatetimelines.acct.job.ws.spec.model.*;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 * Provides common functionality for all background jobs in the ACCT ecosystem.<br />
 * <br />
 * Assures the following: <ul>
 * <li>Uniform registration and management in the Job Registry</li>
 * <li>Synchronization between multiple instances of the same job</li>
 * <li></li>
 * </ul>
 */
@Service
public class AcctJobsExecutionService {

    // TODO: Make it possible to inject more than just one service of type AbstractErrorCodesRegistryService
    //       so that this code can be taken from the source of truth rather than risk forgetting to change it
    //       if the source of truth changes it
    private static final String ERR_CODE_JOB_ALREADY_RUNNING = "0x0500A0000";

    private final RESTJobsEndpointClient jobsEndpointClient;

    private final RESTJobStatesEndpointClient jobStatesEndpointClient;

    public AcctJobsExecutionService(
        RESTJobsEndpointClient jobsEndpointClient,
        RESTJobStatesEndpointClient jobStatesEndpointClient
    ) {
        this.jobsEndpointClient = jobsEndpointClient;
        this.jobStatesEndpointClient = jobStatesEndpointClient;
    }


    public void runJob(JobSummary jobSummary, Runnable jobLogic, long maxDelayMs) {
        // Make sure the job summary is set up correctly
        requireNonNull(jobSummary, "Job summary not provided");
        requireNonNull(jobSummary.jobUUID(), "Job UUID not provided within the job summary");

        // Make sure the runnable is set
        requireNonNull(jobLogic, "Job logic runnable not provided");

        // Delay the thread for a random number of milliseconds to ensure distribution across nodes
        // (jobs starting with random delays each time on each node result in random nodes taking the lead)
        delayThread(maxDelayMs);

        // Get the job state
        final JobState jobState = jobStatesEndpointClient.getJobState(jobSummary.jobUUID());

        // If another instance of the job is already running then exit
        if (jobState != null && JobStatus.RUNNING == jobState.jobStatus()) {
            return;
        }

        // Set the status to running
        try {
            jobStatesEndpointClient.recordJobStarted(jobSummary.jobUUID());
        }
        // If a forbidden error is returned and the error code is the one for "job already running" then exit
        catch (FeignException.Forbidden forbiddenException) {
            final ByteBuffer responseBodyByteBuffer = forbiddenException.responseBody().orElse(null);

            if (responseBodyByteBuffer != null) {
                if (new String(responseBodyByteBuffer.array()).contains(ERR_CODE_JOB_ALREADY_RUNNING)) {
                    return;
                }
            }

        }

        // Try to run the job logic and record the successful ending of the job
        try {
            jobLogic.run();

            jobStatesEndpointClient.recordJobFinished(
                jobSummary.jobUUID(),
                JobStateSetting.builder()
                    .withJobOutcome(JobOutcome.SUCCESS)
                    .build()
            );
        }
        // If there was any error running the job then record the unsuccessful ending of the job
        catch (Throwable exception) {
            jobStatesEndpointClient.recordJobFinished(
                jobSummary.jobUUID(),
                JobStateSetting.builder()
                    .withJobOutcome(JobOutcome.FAILURE)
                    .withErrorMessage(exception.getMessage())
                    .build()
            );
        }

    }

    private void delayThread(long maxDelayMs) {
        try {
            Thread.sleep((long) (Math.random() * maxDelayMs));
        } catch (InterruptedException interruptedException) {
            throw new RuntimeException(
                "Thread sleep was interrupted: " + interruptedException.getMessage(),
                interruptedException
            );
        }
    }

    public void resolveJobRegistration(JobSummary jobSummary) {
        // Make sure the job summary is set up correctly
        requireNonNull(jobSummary, "Job summary not provided");
        requireNonNull(jobSummary.jobUUID(), "Job UUID not provided within the job summary");

        // Attempt to find the job registration
        final Optional<JobSummary> existingJobSummary =
            jobsEndpointClient.getAllRegisteredJobs()
                .stream()
                .filter(job -> Objects.equals(job.jobUUID(), jobSummary.jobUUID()))
                .findFirst();

        // If not found, register the job
        if (existingJobSummary.isEmpty()) {
            jobsEndpointClient.registerJob(
                jobSummary.jobUUID(),
                JobRegistrationRequestMapper.fromJobSummary(jobSummary)
            );
        }
        // If found, update the job
        else {
            jobsEndpointClient.updateJob(
                jobSummary.jobUUID(),
                JobRegistrationRequestMapper.fromJobSummary(jobSummary)
            );
        }
    }

}
