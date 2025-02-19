package com.desolatetimelines.acct.currency.ws.mapper;

import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.ws.model.MonitoredCurrencyProperties;

import java.util.Collection;

/**
 * Provides mapper methods for the {@link MonitoredCurrencyProperties} type
 */
public class MonitoredCurrencyPropertiesMapper {

    public static MonitoredCurrencyProperties fromAcctMonitoredCurrency(AcctMonitoredCurrency acctMonitoredCurrency) {
        return
            MonitoredCurrencyProperties.builder()
                .withMonitoredCurrencyUUID(acctMonitoredCurrency.getMonitoredCurrencyUUID())
                .withBankUUID(acctMonitoredCurrency.getBankUUID())
                .withCurrencyUUID(acctMonitoredCurrency.getCurrencyUUID())
                .withQuotedCurrencyUUID(acctMonitoredCurrency.getQuotedCurrencyUUID())
                .withCollectorName(acctMonitoredCurrency.getCollectorName())
                .withScheduledTimeHhMm(acctMonitoredCurrency.getScheduledTimeHHMM())
                .withLastMonitoredCurrencyRecordDate(acctMonitoredCurrency.getLastMonitoredCurrencyRecordDate())
                .withLastMonitoredCurrencyRecordPurchaseValue(acctMonitoredCurrency.getLastMonitoredCurrencyRecordPurchaseValue())
                .withLastMonitoredCurrencyRecordSaleValue(acctMonitoredCurrency.getLastMonitoredCurrencyRecordSaleValue())
                .build();
    }

    public static Collection<MonitoredCurrencyProperties> fromCollectionOfAcctMonitoredCurrencies(
        Collection<AcctMonitoredCurrency> acctMonitoredCurrencies
    ) {
        return
            acctMonitoredCurrencies.stream()
                .map(MonitoredCurrencyPropertiesMapper::fromAcctMonitoredCurrency)
                .toList();
    }

}
