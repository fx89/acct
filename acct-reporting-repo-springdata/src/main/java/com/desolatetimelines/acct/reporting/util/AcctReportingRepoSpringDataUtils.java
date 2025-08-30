package com.desolatetimelines.acct.reporting.util;

import com.desolatetimelines.acct.reporting.model.AcctDashboard;
import com.desolatetimelines.acct.reporting.model.JpaAcctDashboard;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Utility methods for working with the ACCT Reporting JPA data model
 */
public abstract class AcctReportingRepoSpringDataUtils {

    public static JpaAcctDashboard doWithJpaAcctDashboardReturning(
        AcctDashboard acctDashboard,
        Function<JpaAcctDashboard, JpaAcctDashboard> todo
    ) {
        if (acctDashboard instanceof JpaAcctDashboard jpaAcctDashboard) {
            return todo.apply(jpaAcctDashboard);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctDashboard.class.getName() +
                " is not of type " + JpaAcctDashboard.class.getCanonicalName()
        );
    }

    public static void doWithJpaAcctDashboard(
        AcctDashboard acctDashboard,
        Consumer<JpaAcctDashboard> todo
    ) {
        if (acctDashboard instanceof JpaAcctDashboard jpaAcctDashboard) {
            todo.accept(jpaAcctDashboard);
            return;
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctDashboard.class.getName() +
                " is not of type " + JpaAcctDashboard.class.getCanonicalName()
        );
    }

}
