package com.desolatetimelines.acct.catalog.ws.mapper;

import com.desolatetimelines.acct.catalog.model.AcctCurrency;
import com.desolatetimelines.acct.catalog.ws.model.CurrencyProperties;

import java.util.Collection;

/**
 * Provides mapper for the {@link CurrencyProperties} type
 */
public class CurrencyPropertiesMapper {

    public static CurrencyProperties fromAcctCurrency(AcctCurrency acctCurrency) {
        return
            CurrencyProperties.builder()
                .withCurrencyUUID(acctCurrency.getCurrencyUUID())
                .withCurrencyCode(acctCurrency.getCurrencyCode())
                .withCurrencyName(acctCurrency.getCurrencyName())
                .withCurrencyIconUUID(acctCurrency.getCurrencyIconUUID())
                .build();
    }

    public static Collection<CurrencyProperties> fromCollectionOfAcctCurrencies(Collection<AcctCurrency> acctCurrencies) {
        return
            acctCurrencies.stream()
                .map(CurrencyPropertiesMapper::fromAcctCurrency)
                .toList();
    }

}
