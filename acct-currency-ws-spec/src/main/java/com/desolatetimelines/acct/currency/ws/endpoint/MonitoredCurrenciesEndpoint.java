package com.desolatetimelines.acct.currency.ws.endpoint;

import com.desolatetimelines.acct.currency.ws.model.*;

import java.util.Collection;

/**
 * Defines operations that are supported by the Currency service for monitored currencies
 */
public interface MonitoredCurrenciesEndpoint {

    /**
     * Creates or updates a monitored currency item. The decision to create or update is based on the presence
     * of the given monitored currency UUID.
     *
     * @param monitoredCurrencyUUID the given monitored currency UUID
     * @param request               the given request
     * @return a container for the UUID of the saved monitored currency
     */
    MonitoredCurrencyUUIDResponse saveMonitoredCurrency(
        String monitoredCurrencyUUID,
        MonitoredCurrencySaveRequest request
    );

    /**
     * Returns a collection of all the monitored currencies
     */
    Collection<MonitoredCurrencyProperties> getMonitoredCurrencies();

    /**
     * Returns a collection containing the details of each available monitored currency
     * exchange records collector
     */
    Collection<MonitoredCurrencyCollector> getAvailableMonitoredCurrencyCollectors();

    /**
     * Returns the monitored currency records for the monitored currency identified by the given monitored currency
     * UUID, sorted by {@link MonitoredCurrencyRecordProperties#monitoredCurrencyRecordDate() record date}
     *
     * @param monitoredCurrencyUUID the given monitored currency UUID
     */
    Collection<MonitoredCurrencyRecordProperties> getMonitoredCurrencyRecords(String monitoredCurrencyUUID);

    /**
     * Deletes the monitored currency with the given monitored currency UUID
     *
     * @param monitoredCurrencyUUID the given monitored currency UUID
     */
    void deleteMonitoredCurrency(String monitoredCurrencyUUID);

    /**
     * Runs the collector for the monitored currency specified by the given monitored currency UUID
     *
     * @param monitoredCurrencyUUID the given monitored currency UUID
     */
    void collectManually(String monitoredCurrencyUUID);

}
