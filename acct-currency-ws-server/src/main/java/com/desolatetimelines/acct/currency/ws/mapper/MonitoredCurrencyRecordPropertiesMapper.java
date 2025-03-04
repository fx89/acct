package com.desolatetimelines.acct.currency.ws.mapper;

import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrencyRecord;
import com.desolatetimelines.acct.currency.ws.model.MonitoredCurrencyRecordProperties;

import java.util.Collection;

/**
 * Provides mapping methods for the {@link MonitoredCurrencyRecordProperties} type
 */
public abstract class MonitoredCurrencyRecordPropertiesMapper {

    public static MonitoredCurrencyRecordProperties fromAcctMonitoredCurrencyRecord(
        AcctMonitoredCurrencyRecord acctMonitoredCurrencyRecord
    ) {
        return
            MonitoredCurrencyRecordProperties.builder()
                .withMonitoredCurrencyRecordDate(acctMonitoredCurrencyRecord.getMonitoredCurrencyRecordDate())
                .withMonitoredCurrencyRecordPurchaseValue(acctMonitoredCurrencyRecord.getMonitoredCurrencyRecordPurchaseValue())
                .withMonitoredCurrencyRecordSaleValue(acctMonitoredCurrencyRecord.getMonitoredCurrencyRecordSaleValue())
                .build();
    }

    public static Collection<MonitoredCurrencyRecordProperties> fromCollectionOfAcctMonitoredCurrencyRecord(
        Collection<AcctMonitoredCurrencyRecord> acctMonitoredCurrencyRecord
    ) {
        return
            acctMonitoredCurrencyRecord.stream()
                .map(MonitoredCurrencyRecordPropertiesMapper::fromAcctMonitoredCurrencyRecord)
                .toList();
    }

}
