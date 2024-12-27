package com.desolatetimelines.acct.job.framework.model;

/**
 * Specifies how a {@link com.desolatetimelines.acct.job.framework.service.AcctJob background job} is schedules
 *
 * @param cronType        The kind of scheduling (either fixed interval or cron expression)
 * @param fixedIntervalMs The value of the fixed interval in milliseconds, if the cronType is set to fixed interval
 * @param cronExpression  The value of the cron expression, if the cronType is set to cron expression
 */
public record AcctJobCron(
    AcctJobCronType cronType,
    long fixedIntervalMs,
    String cronExpression
) {
    /**
     * Creates a {@link AcctJobCron} that schedules a
     * {@link com.desolatetimelines.acct.job.framework.service.AcctJob background job}
     * at a fixed interval
     *
     * @param fixedIntervalMs the fixed interval, expressed in milliseconds
     */
    public static AcctJobCron acctJobCronWithFixedIntervalMs(long fixedIntervalMs) {
        return new AcctJobCron(AcctJobCronType.FIXED_INTERVAL, fixedIntervalMs, null);
    }

    /**
     * Creates a {@link AcctJobCron} that schedules a
     * {@link com.desolatetimelines.acct.job.framework.service.AcctJob background job}
     * according to the given cron expression
     *
     * @param cronExpression the given cron expression
     */
    public static AcctJobCron acctJobCronWithCronExpression(String cronExpression) {
        return new AcctJobCron(AcctJobCronType.CRON_EXPRESSION, 0, cronExpression);
    }
}
