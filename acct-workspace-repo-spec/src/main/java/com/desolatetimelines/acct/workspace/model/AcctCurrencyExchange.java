package com.desolatetimelines.acct.workspace.model;

import java.util.Optional;

public interface AcctCurrencyExchange {

    AcctAccountRecord getCurrencyExchangeSourceAccountRecord();

    void setCurrencyExchangeSourceAccountRecord(AcctAccountRecord currencyExchangeSourceAccountRecord);

    AcctAccountRecord getCurrencyExchangeTargetAccountRecord();

    void setCurrencyExchangeTargetAccountRecord(AcctAccountRecord currencyExchangeTargetAccountRecord);

    Double getCurrencyExchangeRate();

    void setCurrencyExchangeRate(Double currencyExchangeRate);

    Double getPurchasePrice();

    void setPurchasePrice(Double purchasePrice);

    Optional<AcctCurrencyExchange> getOptionalOriginalCurrencyExchange();

    void setOptionalOriginalCurrencyExchange(AcctCurrencyExchange optionalOriginalCurrencyExchange);
}
