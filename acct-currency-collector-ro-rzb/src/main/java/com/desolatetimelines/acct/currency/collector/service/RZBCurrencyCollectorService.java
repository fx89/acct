package com.desolatetimelines.acct.currency.collector.service;

import com.desolatetimelines.acct.currency.collector.exception.AcctCurrencyCollectorServiceException;
import com.desolatetimelines.acct.currency.collector.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static java.time.ZoneOffset.UTC;

@Service
public class RZBCurrencyCollectorService implements CurrencyCollectorService<RZBCurrencyCollectionSession> {

    /**
     * Currency code for the RON currency
     */
    private static final String RON = "RON";

    /**
     * Date format for the date in the currency exchange rates URL
     */
    private final SimpleDateFormat yyyyMmDdDateFormat = new SimpleDateFormat("yyyyMMdd");

    /**
     * Date format for the {@link CurrenciesListItem#validityDate() validity date}
     */
    private final DateTimeFormatter validityDateFormat = DateTimeFormatter.ofPattern("MMM d, yyyy, h:mm:ss a");

    @Override
    public Collection<String> getSupportedBankCodes() {
        return List.of("RZB");
    }

    @Override
    public RZBCurrencyCollectionSession startSession(SessionParameters sessionParameters) {
        return new RZBCurrencyCollectionSession(collectRates());
    }

    @Override
    public Collection<CollectedCurrencyExchangeRecord> collectRecords(
        RZBCurrencyCollectionSession session,
        String bankCode,
        String currencyCode
    ) {
        // Identify the currency
        final CurrenciesListItem currenciesListItem =
            session.ratesListResponse().rates().getFirst().currencyList().stream()
                .filter(i ->
                    i.currencyPair().left().code().equals(currencyCode) &&
                        i.currencyPair().right().code().equals(RON)
                )
                .findFirst()
                // If not found, then crash
                .orElseThrow(() ->
                    new AcctCurrencyCollectorServiceException("Currency " + currencyCode + "/" + RON + " not found")
                );

        // Parse the date, extract the buy rate and sell rate and pack it all up
        return
            List.of(
                new CollectedCurrencyExchangeRecord(
                    parseValidityDate(currenciesListItem.validityDate()),
                    currenciesListItem.buyRate().value(),
                    currenciesListItem.sellRate().value()
                )
            );
    }

    @Override
    public void endSession(RZBCurrencyCollectionSession session) {
        // Nothing to do here
    }

    /**
     * Loads the rates from RZB using the web client
     */
    private RZBRatesListResponse collectRates() {
        return
            WebClient.builder()
                .baseUrl(
                    "https://www.raiffeisen.ro/ro.exchangerates" +
                        "." + yyyyMmDdDateFormat.format(new Date()) +
                        ".BASE" +
                        ".EUR-USD-GBP-CAD-HUF-MDL-CHF-SEK-JPY-DKK-RUB-TRY-CZK-PLN-EGP.RON" +
                        ".json"
                )
                .build()
                .get()
                .retrieve()
                .bodyToMono(RZBRatesListResponse.class).block();
    }

    private Instant parseValidityDate(String strValidityDate) {
        return
            LocalDateTime
                .parse(
                    strValidityDate.replace('\u202F', ' '),
                    validityDateFormat
                )
                .atZone(UTC)
                .toInstant();
    }
}
