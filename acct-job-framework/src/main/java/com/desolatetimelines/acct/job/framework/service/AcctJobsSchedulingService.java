package com.desolatetimelines.acct.job.framework.service;

import com.desolatetimelines.acct.job.framework.model.AcctJobCron;
import com.desolatetimelines.acct.job.framework.model.AcctJobCronType;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collection;
import java.util.TimeZone;

/**
 * Scans for all {@link Service services} and {@link org.springframework.stereotype.Component components}
 * implementing the {@link AcctJob ACCT job service interface} and registers them with the
 * {@link ThreadPoolTaskScheduler}
 */
@Service
public class AcctJobsSchedulingService {

    private final ThreadPoolTaskScheduler taskScheduler;

    private final Collection<AcctJob> jobs;

    public AcctJobsSchedulingService(Collection<AcctJob> jobs) {
        this.jobs = jobs;

        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(5);
        taskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
    }

    @EventListener(ApplicationReadyEvent.class)
    @SuppressWarnings("unused")
    private void scheduleJobs() {
        // Initialize the task scheduler
        taskScheduler.initialize();

        // For each job...
        jobs.forEach(job -> {
            // Verify the job
            verifyJob(job);

            // Get the cron
            final AcctJobCron cron = job.getCron();

            // Make sure the cron was provided
            if (cron == null) {
                throw new IllegalStateException("Cron not specified for job [" + job.getJobName() + "]");
            }

            // Verify that the cron is set correctly
            verifyCron(cron);

            // If the cron is set for fixed interval then schedule the job at the set interval
            if (AcctJobCronType.FIXED_INTERVAL == cron.cronType()) {
                taskScheduler.scheduleAtFixedRate(job::run, Duration.ofMillis(cron.fixedIntervalMs()));
            }

            // If the cron is set for cron expression then schedule the job with the given cron expression
            if (AcctJobCronType.CRON_EXPRESSION == cron.cronType()) {
                taskScheduler.schedule(job::run, new CronTrigger(cron.cronExpression(), TimeZone.getTimeZone("UTC")));

            }
        });
    }

    /**
     * Throws an {@link IllegalStateException} if anything is wrong with the job configuration
     */
    private static void verifyJob(AcctJob job) {
        if (job.getJobServiceName() == null) {
            throw new IllegalArgumentException("Job service name not specified");
        }

        if (job.getJobName() == null) {
            throw new IllegalArgumentException("Job name not specified");
        }
    }

    /**
     * Throws an {@link IllegalStateException} if anything is wrong with the cron configuration
     */
    private static void verifyCron(AcctJobCron cron) {
        if (cron.cronType() == null) {
            throw new IllegalStateException("Cron type not specified");
        }

        if (AcctJobCronType.FIXED_INTERVAL == cron.cronType()) {
            if (cron.fixedIntervalMs() <= 0) {
                throw new IllegalStateException(
                    "The value of the fixed interval must be greater than zero " +
                        "if the cron type is set to fixed interval"
                );
            }
        }

        if (AcctJobCronType.CRON_EXPRESSION == cron.cronType()) {
            if (cron.cronExpression() == null) {
                throw new IllegalStateException(
                    "The cron expression must be set " +
                        "if the cron type is set to cron expression"
                );
            }
        }
    }

}
