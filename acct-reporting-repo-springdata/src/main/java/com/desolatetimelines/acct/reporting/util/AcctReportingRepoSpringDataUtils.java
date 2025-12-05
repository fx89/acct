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

}
