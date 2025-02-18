package com.desolatetimelines.acct.currency.model;

import java.time.Instant;

public interface AcctMonitoredCurrency {

    String getMonitoredCurrencyUUID();

    void setMonitoredCurrencyUUID(String monitoredCurrencyUUID);

    String getBankUUID();

    void setBankUUID(String bankUUID);

    String getCurrencyUUID();

    void setCurrencyUUID(String currencyUUID);

    String getQuotedCurrencyUUID();

    void setQuotedCurrencyUUID(String quotedCurrencyUUID);

    String getCollectorName();

    void setCollectorName(String collectorName);

    String getScheduledTimeHHMM();

    void setScheduledTimeHHMM(String scheduledTimeHHMM);

    Instant getLastCollectionDate();

    void setLastCollectionDate(Instant lastCollectionDate);

    String getCollectionErrorMessage();

    void setCollectionErrorMessage(String collectionErrorMessage);

    Instant getLastMonitoredCurrencyRecordDate();

    void setLastMonitoredCurrencyRecordDate(Instant lastMonitoredCurrencyRecordDate);

    Double getLastMonitoredCurrencyRecordPurchaseValue();

    void setLastMonitoredCurrencyRecordPurchaseValue(Double lastMonitoredCurrencyPurchaseValue);

    Double getLastMonitoredCurrencyRecordSaleValue();

    void setLastMonitoredCurrencyRecordSaleValue(Double lastMonitoredCurrencySaleValue);

}
