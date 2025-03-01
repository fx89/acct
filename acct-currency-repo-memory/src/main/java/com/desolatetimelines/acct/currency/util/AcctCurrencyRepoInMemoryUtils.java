package com.desolatetimelines.acct.currency.util;

import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.model.InMemoryAcctMonitoredCurrency;

import java.util.function.Function;

/**
 * Provides utility methods used by components of the in-memory implementation
 * of the ACCT Currency repositories
 */
public abstract class AcctCurrencyRepoInMemoryUtils {

    public static InMemoryAcctMonitoredCurrency doWithInMemoryAcctMonitoredCurrencyReturning(
        AcctMonitoredCurrency acctMonitoredCurrency,
        Function<InMemoryAcctMonitoredCurrency, InMemoryAcctMonitoredCurrency> todo
    ) {
        if (acctMonitoredCurrency instanceof InMemoryAcctMonitoredCurrency inMemoryAcctMonitoredCurrency) {
            return todo.apply(inMemoryAcctMonitoredCurrency);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctMonitoredCurrency.class.getName() +
                " is not of type " + InMemoryAcctMonitoredCurrency.class.getCanonicalName()
        );
    }

}

