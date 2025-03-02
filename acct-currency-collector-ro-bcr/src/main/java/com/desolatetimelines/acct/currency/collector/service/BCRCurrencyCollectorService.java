package com.desolatetimelines.acct.currency.collector.service;

import com.desolatetimelines.acct.currency.collector.model.BCRCurrencyCollectionSession;
import com.desolatetimelines.acct.currency.collector.model.CollectedCurrencyExchangeRecord;
import com.desolatetimelines.acct.currency.collector.model.GetExchangeRatesResponse;
import com.desolatetimelines.acct.currency.collector.model.SessionParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class BCRCurrencyCollectorService implements CurrencyCollectorService<BCRCurrencyCollectionSession> {

    /**
     * Supported bank codes
     */
    private static final Collection<String> SUPPORTED_BANK_CODES = List.of("BCR");

    /**
     * Web client for the last available day's currency exchange rates (all vs RON)
     */
    private final WebClient webClient =
        WebClient.builder()
            .baseUrl("https://api.bcr.ro/api/rates/v1/exchangerates")
            .build();

    @Override
    public Collection<String> getSupportedBankCodes() {
        return SUPPORTED_BANK_CODES;
    }

    @Override
    public BCRCurrencyCollectionSession startSession(SessionParameters sessionParameters) {
        return new BCRCurrencyCollectionSession(fetchExchangeRates());
    }

    @Override
    public Collection<CollectedCurrencyExchangeRecord> collectRecords(
        BCRCurrencyCollectionSession session,
        String bankCode,
        String currencyCode
    ) {
        // Identify and return all the records for the currency
        return
            session.exchangeRatesResponse().exchangeRates().exchangeRates().stream()
                .filter(exchangeRate -> Objects.equals(currencyCode, exchangeRate.currency()))
                .map(exchangeRate ->
                    new CollectedCurrencyExchangeRecord(
                        exchangeRate.rateDate().toInstant(),
                        exchangeRate.buyRate(),
                        exchangeRate.sellRate()
                    ))
                .toList();
    }

    @Override
    public void endSession(BCRCurrencyCollectionSession session) {
        // Nothing to do here
    }

    public GetExchangeRatesResponse fetchExchangeRates() {
        // Get the data from the bank
        return webClient.get().retrieve().bodyToMono(GetExchangeRatesResponse.class).block();
    }
}
