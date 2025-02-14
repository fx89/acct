package com.desolatetimelines.acct.catalog.ws.endpoint;

import com.desolatetimelines.acct.catalog.ws.model.CurrencyProperties;
import com.desolatetimelines.acct.catalog.ws.model.CurrencySaveRequest;
import com.desolatetimelines.acct.catalog.ws.model.CurrencyUUIDResponse;

import java.util.Collection;

/**
 * Defines operations that are supported by the Catalog service for currencies
 */
public interface CurrenciesEndpoint {

    /**
     * Creates a new currency or updates an existing currency with the properties in the given request.
     * The decision to create or update is based on the existence of the given currency UUID.
     *
     * @param currencyUUID the given currency UUID
     * @param request      the given request
     * @return a container for the UUID of the created or updated currency
     */
    CurrencyUUIDResponse saveCurrency(String currencyUUID, CurrencySaveRequest request);

    /**
     * Returns a collection of all the currencies registered in the catalog
     */
    Collection<CurrencyProperties> getCurrencies();

    /**
     * Deletes the currencies identified by the UUIDs in the given list of currency UUIDs.<br />
     * <br />
     * If any of the referenced currencies cannot be found, an exception is thrown.<br />
     * <br />
     * If any of the referenced currencies is in use, an exception is thrown.
     *
     * @param currencyUUIDs the given list of currency UUIDs
     */
    void deleteCurrencies(Collection<String> currencyUUIDs);

}
