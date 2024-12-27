package com.desolatetimelines.acct.job.framework.service;

import com.desolatetimelines.acct.job.framework.model.AcctJobCron;
import com.desolatetimelines.acct.job.ws.spec.model.JobSummary;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Defines the properties and lifecycle of a background job running within the ACCT ecosystem.<br />
 * <br />
 * All implementing classes must have the {@link org.springframework.stereotype.Service Service}
 * annotation.
 */
public abstract class AcctJob {

    private final JobSummary jobSummary;

    private final long maxDelayMs;

    @Autowired
    private AcctJobsExecutionService jobsExecutionService;

    /**
     * This is the name of the service to which the job belongs (i.e. user-management, security, workspace, etc.)
     */
    protected abstract String getJobServiceName();

    /**
     * This is a V4 UUID that uniquely identifies the job across the ACCT ecosystem
     */
    protected abstract String getJobUUID();

    /**
     * This is a human-readable name for the job
     */
    protected abstract String getJobName();

    /**
     * This is a somewhat detailed description of what the job does
     */
    protected abstract String getJobDescription();

    /**
     * This is an object that specifies how a job is triggered.<br />
     * Note that the <b>UTC</b> timezone is used for cron expressions.<br />
     * <br />
     * See also {@link AcctJobCron}
     */
    protected abstract AcctJobCron getCron();

    /**
     * This is the maximum amount of time, expressed in milliseconds, the job will wait since it is triggered
     * and until it actually starts. The job will choose a random wait time between 0ms and this number. With
     * this approach, multiple instances of the job, running on multiple instances of their
     * {@link AcctJob#getJobServiceName() containing service}, have equal chances to start first and block the
     * other instances, thus assuring proper workload distribution without the need of a management system.
     */
    protected abstract long getMaxDelayMs();

    protected abstract void internalJobLogicRunnable();

    public AcctJob() {
        jobSummary =
            JobSummary.builder()
                .withJobServiceName(getJobServiceName())
                .withJobUUID(getJobUUID())
                .withJobName(getJobName())
                .withJobDescription(getJobDescription())
                .build();

        maxDelayMs = getMaxDelayMs();
    }

    @PostConstruct
    @SuppressWarnings("unused")
    private void resolveJobRegistration() {
        jobsExecutionService.resolveJobRegistration(jobSummary);
    }

    public void run() {
        jobsExecutionService.runJob(jobSummary, this::internalJobLogicRunnable, maxDelayMs);
    }

}
