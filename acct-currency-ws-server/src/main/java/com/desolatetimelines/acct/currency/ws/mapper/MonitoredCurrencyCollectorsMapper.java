package com.desolatetimelines.acct.currency.ws.mapper;

import com.desolatetimelines.acct.currency.collector.service.CurrencyCollectorService;
import com.desolatetimelines.acct.currency.ws.model.MonitoredCurrencyCollector;

import java.util.Collection;
import java.util.Map;

/**
 * Provides mapping methods for the {@link MonitoredCurrencyCollector} type
 */
public abstract class MonitoredCurrencyCollectorsMapper {

    public static MonitoredCurrencyCollector fromEntryOfStringAndCurrencyCollectorService(
        Map.Entry<String, CurrencyCollectorService<?>> entry
    ) {
        return
            new MonitoredCurrencyCollector(
                entry.getKey(),
                entry.getValue().getSupportedBankCodes()
            );
    }

    public static Collection<MonitoredCurrencyCollector> fromMapOfStringAndCurrencyCollectorService(
        Map<String, CurrencyCollectorService<?>> map
    ) {
        return
            map.entrySet().stream()
                .map(MonitoredCurrencyCollectorsMapper::fromEntryOfStringAndCurrencyCollectorService)
                .toList();
    }

}
