package com.desolatetimelines.acct.currency.util;

import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.model.JpaAcctMonitoredCurrency;

import java.util.function.Function;

/**
 * Provides utility methods used by components of the Spring Data-based implementation
 * of the ACCT Currency repositories
 */
public abstract class AcctCurrencyRepoSpringDataUtils {

    public static JpaAcctMonitoredCurrency doWithJpaAcctMonitoredCurrencyReturning(
        AcctMonitoredCurrency acctMonitoredCurrency,
        Function<JpaAcctMonitoredCurrency, JpaAcctMonitoredCurrency> todo
    ) {
        if (acctMonitoredCurrency instanceof JpaAcctMonitoredCurrency jpaAcctMonitoredCurrency) {
            return todo.apply(jpaAcctMonitoredCurrency);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctMonitoredCurrency.class.getName() +
                " is not of type " + JpaAcctMonitoredCurrency.class.getCanonicalName()
        );
    }

}

