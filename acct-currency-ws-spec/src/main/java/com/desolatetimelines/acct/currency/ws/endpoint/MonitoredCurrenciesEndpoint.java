package com.desolatetimelines.acct.currency.ws.endpoint;

import com.desolatetimelines.acct.currency.ws.model.MonitoredCurrencyProperties;
import com.desolatetimelines.acct.currency.ws.model.MonitoredCurrencySaveRequest;
import com.desolatetimelines.acct.currency.ws.model.MonitoredCurrencyUUIDResponse;

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

}
