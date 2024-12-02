package com.desolatetimelines.acct.security.util;

import com.desolatetimelines.acct.security.model.*;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Provides utility methods used by components of the Spring Data-based implementation of the ACCT Security repositories
 */
public abstract class AcctSecurityRepoSpringdataUtils {

    public static JpaAcctGroupPrivilege doWithJJpaAcctGroupPrivilege(
        AcctGroupPrivilege acctGroupPrivilege,
        Function<JpaAcctGroupPrivilege, JpaAcctGroupPrivilege> todo
    ) {
        if (acctGroupPrivilege instanceof JpaAcctGroupPrivilege jpaAcctGroupPrivilege) {
            return todo.apply(jpaAcctGroupPrivilege);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctGroupPrivilege.class.getName() +
                " is not of type " + JpaAcctGroupPrivilege.class.getCanonicalName()
        );
    }

    public static JpaAcctWorkspaceOwner doWithJpaAcctWorkspaceOwnerReturning(
        AcctWorkspaceOwner acctWorkspaceOwner,
        Function<JpaAcctWorkspaceOwner, JpaAcctWorkspaceOwner> todo
    ) {
        if (acctWorkspaceOwner instanceof JpaAcctWorkspaceOwner jpaAcctWorkspaceOwner) {
            return todo.apply(jpaAcctWorkspaceOwner);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctWorkspaceOwner.class.getName() +
                " is not of type " + JpaAcctWorkspaceOwner.class.getCanonicalName()
        );
    }

    public static void doWithJpaAcctWorkspaceOwner(
        AcctWorkspaceOwner acctWorkspaceOwner,
        Consumer<JpaAcctWorkspaceOwner> todo
    ) {
        doWithJpaAcctWorkspaceOwnerReturning(acctWorkspaceOwner, in -> {
            todo.accept(in);
            return null;
        });
    }

    public static JpaAcctDashboardOwner doWithJpaAcctDashboardOwnerReturning(
        AcctDashboardOwner acctDashboardOwner,
        Function<JpaAcctDashboardOwner, JpaAcctDashboardOwner> todo
    ) {
        if (acctDashboardOwner instanceof JpaAcctDashboardOwner jpaAcctDashboardOwner) {
            return todo.apply(jpaAcctDashboardOwner);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctDashboardOwner.class.getName() +
                " is not of type " + JpaAcctDashboardOwner.class.getCanonicalName()
        );
    }

    public static void doWithJpaAcctDashboardOwner(
        AcctDashboardOwner acctDashboardOwner,
        Consumer<JpaAcctDashboardOwner> todo
    ) {
        doWithJpaAcctDashboardOwnerReturning(acctDashboardOwner, in -> {
            todo.accept(in);
            return null;
        });
    }

    public static JpaAcctReportOwner doWithJpaAcctReportOwnerReturning(
        AcctReportOwner acctReportOwner,
        Function<JpaAcctReportOwner, JpaAcctReportOwner> todo
    ) {
        if (acctReportOwner instanceof JpaAcctReportOwner jpaAcctReportOwner) {
            return todo.apply(jpaAcctReportOwner);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctReportOwner.class.getName() +
                " is not of type " + JpaAcctReportOwner.class.getCanonicalName()
        );
    }

    public static void doWithJpaAcctReportOwner(
        AcctReportOwner acctReportOwner,
        Consumer<JpaAcctReportOwner> todo
    ) {
        doWithJpaAcctReportOwnerReturning(acctReportOwner, in -> {
            todo.accept(in);
            return null;
        });
    }

}
