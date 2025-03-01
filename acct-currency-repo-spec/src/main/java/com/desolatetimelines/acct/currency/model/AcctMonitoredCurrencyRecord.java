package com.desolatetimelines.acct.currency.model;

import java.time.Instant;

public interface AcctMonitoredCurrencyRecord {

    AcctMonitoredCurrency getMonitoredCurrency();

    void setMonitoredCurrency(AcctMonitoredCurrency monitoredCurrency);

    Instant getMonitoredCurrencyRecordDate();

    void setMonitoredCurrencyRecordDate(Instant monitoredCurrencyRecordDate);

    Double getMonitoredCurrencyRecordPurchaseValue();

    void setMonitoredCurrencyRecordPurchaseValue(Double monitoredCurrencyRecordPurchaseValue);

    Double getMonitoredCurrencyRecordSaleValue();

    void setMonitoredCurrencyRecordSaleValue(Double monitoredCurrencySaleValue);

}
