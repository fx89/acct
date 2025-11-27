package com.desolatetimelines.acct.currency.ws.mapper;

import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrencyRecord;
import com.desolatetimelines.acct.currency.model.MonitoredCurrencyRecord;
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

    public static Collection<MonitoredCurrencyRecordProperties> fromCollectionOfAcctMonitoredCurrencyRecords(
        Collection<AcctMonitoredCurrencyRecord> acctMonitoredCurrencyRecord
    ) {
        return
            acctMonitoredCurrencyRecord.stream()
                .map(MonitoredCurrencyRecordPropertiesMapper::fromAcctMonitoredCurrencyRecord)
                .toList();
    }

    public static MonitoredCurrencyRecord toMonitoredCurrencyRecord(MonitoredCurrencyRecordProperties properties) {
        return
            MonitoredCurrencyRecord.builder()
                .withMonitoredCurrencyRecordDate(properties.monitoredCurrencyRecordDate())
                .withMonitoredCurrencyRecordPurchaseValue(properties.monitoredCurrencyRecordPurchaseValue())
                .withMonitoredCurrencyRecordSaleValue(properties.monitoredCurrencyRecordSaleValue())
                .build();
    }

    public static Collection<MonitoredCurrencyRecord> toCollectionOfMonitoredCurrencyRecord(
        Collection<MonitoredCurrencyRecordProperties> propertiesCollection
    ) {
        return
            propertiesCollection.stream()
                .map(MonitoredCurrencyRecordPropertiesMapper::toMonitoredCurrencyRecord)
                .toList();
    }

}
