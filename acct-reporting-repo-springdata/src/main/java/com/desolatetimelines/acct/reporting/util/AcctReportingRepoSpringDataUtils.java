package com.desolatetimelines.acct.reporting.util;

import com.desolatetimelines.acct.reporting.model.*;

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

    public static JpaAcctDataProviderInstance doWithJpaAcctDataProviderInstanceReturning(
        AcctDataProviderInstance acctDataProviderInstance,
        Function<JpaAcctDataProviderInstance, JpaAcctDataProviderInstance> todo
    ) {
        if (acctDataProviderInstance instanceof JpaAcctDataProviderInstance jpaAcctDataProviderInstance) {
            return todo.apply(jpaAcctDataProviderInstance);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctDataProviderInstance.class.getName() +
                " is not of type " + JpaAcctDataProviderInstance.class.getCanonicalName()
        );
    }

    public static void doWithJpaAcctDataProviderInstance(
        AcctDataProviderInstance acctDataProviderInstance,
        Consumer<JpaAcctDataProviderInstance> todo
    ) {
        if (acctDataProviderInstance instanceof JpaAcctDataProviderInstance jpaAcctDataProviderInstance) {
            todo.accept(jpaAcctDataProviderInstance);
            return;
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctDashboard.class.getName() +
                " is not of type " + JpaAcctDashboard.class.getCanonicalName()
        );
    }

    public static JpaAcctDataProviderInstanceRuntimeParameter doWithAcctDataProviderInstanceRuntimeParameterReturning(
        AcctDataProviderInstanceRuntimeParameter acctDataProviderInstanceRuntimeParameter,
        Function<JpaAcctDataProviderInstanceRuntimeParameter, JpaAcctDataProviderInstanceRuntimeParameter> todo
    ) {
        if (acctDataProviderInstanceRuntimeParameter instanceof JpaAcctDataProviderInstanceRuntimeParameter jpaAcctDataProviderInstanceRuntimeParameter) {
            return todo.apply(jpaAcctDataProviderInstanceRuntimeParameter);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctDataProviderInstanceRuntimeParameter.class.getName() +
                " is not of type " + JpaAcctDataProviderInstanceRuntimeParameter.class.getCanonicalName()
        );
    }

    public static void doWithJpaAcctDataProviderInstanceRuntimeParameter(
        AcctDataProviderInstanceRuntimeParameter acctDataProviderInstanceRuntimeParameter,
        Consumer<JpaAcctDataProviderInstanceRuntimeParameter> todo
    ) {
        if (acctDataProviderInstanceRuntimeParameter instanceof JpaAcctDataProviderInstanceRuntimeParameter jpaAcctDataProviderInstanceRuntimeParameter) {
            todo.accept(jpaAcctDataProviderInstanceRuntimeParameter);
            return;
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctDataProviderInstanceRuntimeParameter.class.getName() +
                " is not of type " + JpaAcctDataProviderInstanceRuntimeParameter.class.getCanonicalName()
        );
    }

    public static JpaAcctDataProviderInstanceProperty doWithJpaAcctDataProviderInstancePropertyReturning(
        AcctDataProviderInstanceProperty acctDataProviderInstanceProperty,
        Function<JpaAcctDataProviderInstanceProperty, JpaAcctDataProviderInstanceProperty> todo
    ) {
        if (acctDataProviderInstanceProperty instanceof JpaAcctDataProviderInstanceProperty jpaAcctDataProviderInstanceProperty) {
            return todo.apply(jpaAcctDataProviderInstanceProperty);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctDataProviderInstanceProperty.class.getName() +
                " is not of type " + JpaAcctDataProviderInstanceProperty.class.getCanonicalName()
        );
    }

    public static void doWithJpaAcctReport(
        AcctReport acctReport,
        Consumer<JpaAcctReport> todo
    ) {
        if (acctReport instanceof JpaAcctReport jpaAcctReport) {
            todo.accept(jpaAcctReport);
            return;
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctReport.class.getName() +
                " is not of type " + JpaAcctReport.class.getCanonicalName()
        );
    }

    public static JpaAcctReport doWithJpaAcctReportReturning(
        AcctReport acctReport,
        Function<JpaAcctReport, JpaAcctReport> todo
    ) {
        if (acctReport instanceof JpaAcctReport jpaAcctReport) {
            return todo.apply(jpaAcctReport);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctReport.class.getName() +
                " is not of type " + JpaAcctReport.class.getCanonicalName()
        );
    }

    public static JpaAcctReportDataProviderInstance doWithJpaAcctReportDataProviderInstanceReturning(
        AcctReportDataProviderInstance acctReportDataProviderInstance,
        Function<JpaAcctReportDataProviderInstance, JpaAcctReportDataProviderInstance> todo
    ) {
        if (acctReportDataProviderInstance instanceof JpaAcctReportDataProviderInstance jpaAcctReportDataProviderInstance) {
            return todo.apply(jpaAcctReportDataProviderInstance);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctReportDataProviderInstance.class.getName() +
                " is not of type " + JpaAcctReportDataProviderInstance.class.getCanonicalName()
        );
    }

    public static void doWithJpaAcctReportDataProviderInstance(
        AcctReportDataProviderInstance acctReportDataProviderInstance,
        Consumer<JpaAcctReportDataProviderInstance> todo
    ) {
        if (acctReportDataProviderInstance instanceof JpaAcctReportDataProviderInstance jpaAcctReportDataProviderInstance) {
            todo.accept(jpaAcctReportDataProviderInstance);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctReportDataProviderInstance.class.getName() +
                " is not of type " + JpaAcctReportDataProviderInstance.class.getCanonicalName()
        );
    }

    public static JpaAcctReportSeries doWithJpaAcctReportSeriesReturning(
        AcctReportSeries acctReportSeries,
        Function<JpaAcctReportSeries, JpaAcctReportSeries> todo
    ) {
        if (acctReportSeries instanceof JpaAcctReportSeries jpaAcctReportSeries) {
            return todo.apply(jpaAcctReportSeries);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctReportSeries.class.getName() +
                " is not of type " + JpaAcctReportSeries.class.getCanonicalName()
        );
    }

    public static void doWithJpaAcctReportSeries(
        AcctReportSeries acctReportSeries,
        Consumer<JpaAcctReportSeries> todo
    ) {
        if (acctReportSeries instanceof JpaAcctReportSeries jpaAcctReportSeries) {
            todo.accept(jpaAcctReportSeries);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctReportSeries.class.getName() +
                " is not of type " + JpaAcctReportSeries.class.getCanonicalName()
        );
    }

}
