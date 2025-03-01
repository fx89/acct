package com.desolatetimelines.acct.catalog.ws.client;

import com.desolatetimelines.acct.catalog.ws.endpoint.CurrenciesEndpoint;
import com.desolatetimelines.acct.catalog.ws.model.CurrencyProperties;
import com.desolatetimelines.acct.catalog.ws.model.CurrencySaveRequest;
import com.desolatetimelines.acct.catalog.ws.model.CurrencyUUIDResponse;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCurrenciesEndpoint implements CurrenciesEndpoint {

    private final Map<String, CurrencyProperties> currenciesByUUID = new ConcurrentHashMap<>();

    @Override
    public CurrencyUUIDResponse saveCurrency(String currencyUUID, CurrencySaveRequest request) {
        final String uuid = Optional.ofNullable(currencyUUID).orElseGet(() -> UUID.randomUUID().toString());

        currenciesByUUID.put(
            uuid,
            CurrencyProperties.builder()
                .withCurrencyUUID(uuid)
                .withCurrencyCode(request.currencyCode())
                .withCurrencyName(request.currencyName())
                .withCurrencyIconUUID(request.currencyIconUUID())
                .build()
        );

        return new CurrencyUUIDResponse(uuid);
    }

    @Override
    public Collection<CurrencyProperties> getCurrencies() {
        return currenciesByUUID.values();
    }

    @Override
    public void deleteCurrencies(Collection<String> currencyUUIDs) {
        currencyUUIDs.forEach(currenciesByUUID::remove);
    }

}
