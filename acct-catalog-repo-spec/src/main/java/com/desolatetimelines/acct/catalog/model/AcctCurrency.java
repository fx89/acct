package com.desolatetimelines.acct.catalog.model;

/**
 * Uniquely identifies a currency in the ACCT catalog
 */
public interface AcctCurrency {

    String getCurrencyUUID();

    void setCurrencyUUID(String currencyUUID);

    String getCurrencyCode();

    void setCurrencyCode(String currencyCode);

    String getCurrencyName();

    void setCurrencyName(String currencyName);

    String getCurrencyIconUUID();

    void setCurrencyIconUUID(String currencyIconUUID);

}
