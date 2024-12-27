package com.desolatetimelines.acct.job.framework.model;

/**
 * Specifies the kind of scheduling a {@link com.desolatetimelines.acct.job.framework.service.AcctJob background job}
 * runs on.<li>
 *     <ul><b>FIXED_INTERVAL</b> - Job runs at a fixed interval, which is specified in milliseconds</ul>
 *     <ul><b>CRON_EXPRESSION</b> - Job runs according to a cron expression</ul>
 * </li>
 */
public enum AcctJobCronType {

    /**
     * Job runs at a fixed interval, which is specified in milliseconds
     */
    FIXED_INTERVAL,

    /**
     * Job runs according to a cron expression
     */
    CRON_EXPRESSION

}
