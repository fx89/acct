package com.desolatetimelines.acct.job.util;

import com.desolatetimelines.acct.job.model.*;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Provides utility methods used by components of the Spring Data-based implementation
 * of the ACCT Job repositories
 */
public class AcctJobRepoSpringDataUtils {

    public static JpaAcctJob doWithJpaAcctJob(
        AcctJob acctJob,
        Function<JpaAcctJob, JpaAcctJob> todo
    ) {
        if (acctJob instanceof JpaAcctJob jpaAcctJob) {
            return todo.apply(jpaAcctJob);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctJob.class.getName() +
                " is not of type " + JpaAcctJob.class.getCanonicalName()
        );
    }

    public static void doWithJpaAcctJobWithoutReturning(
        AcctJob acctJob,
        Consumer<JpaAcctJob> todo
    ) {
        doWithJpaAcctJob(acctJob, jpaAcctJob -> {
            todo.accept(jpaAcctJob);
            return jpaAcctJob;
        });
    }

    public static JpaAcctJobStatus doWithJpaAcctJobStatus(
        AcctJobStatus acctJobStatus,
        Function<JpaAcctJobStatus, JpaAcctJobStatus> todo
    ) {
        if (acctJobStatus instanceof JpaAcctJobStatus jpaAcctJobStatus) {
            return todo.apply(jpaAcctJobStatus);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctJobStatus.class.getName() +
                " is not of type " + JpaAcctJobStatus.class.getCanonicalName()
        );
    }

    public static JpaAcctJobStatusHistoryRecord doWithJpaAcctJobStatusHistoryRecord(
        AcctJobStatusHistoryRecord acctJobStatusHistoryRecord,
        Function<JpaAcctJobStatusHistoryRecord, JpaAcctJobStatusHistoryRecord> todo
    ) {
        if (acctJobStatusHistoryRecord instanceof JpaAcctJobStatusHistoryRecord jpaacctJobStatusHistoryRecord) {
            return todo.apply(jpaacctJobStatusHistoryRecord);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctJobStatusHistoryRecord.class.getName() +
                " is not of type " + JpaAcctJobStatusHistoryRecord.class.getCanonicalName()
        );
    }

}
