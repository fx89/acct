package com.desolatetimelines.acct.job.util;

import com.desolatetimelines.acct.job.model.AcctJob;
import com.desolatetimelines.acct.job.model.JpaAcctJob;

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

}
