package com.desolatetimelines.acct.workspace.mapper;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.workspace.model.AccountRecordExtendedDetails;
import com.desolatetimelines.acct.workspace.model.AcctAccountRecord;
import com.desolatetimelines.acct.workspace.model.AcctCurrencyExchange;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

/**
 * Provides mapper methods for the {@link AccountRecordExtendedDetails} type
 */
public abstract class AccountRecordExtendedDetailsMapper {

    public static Page<AccountRecordExtendedDetails> fromPageOfAcctAccountRecords(
        Page<AcctAccountRecord> acctAccountRecordsPage,
        Collection<AcctCurrencyExchange> currencyExchangeRecordsWithSourceAccountRecords,
        Collection<AcctCurrencyExchange> currencyExchangeRecordsWithTargetAccountRecords,
        Collection<AcctCurrencyExchange> buyBackExchanges
    ) {
        return
            new Page<>(
                acctAccountRecordsPage.data()
                    .stream()
                    .map(acctAccountRecord ->
                        fromAcctAccountRecord(
                            acctAccountRecord,
                            currencyExchangeRecordsWithSourceAccountRecords,
                            currencyExchangeRecordsWithTargetAccountRecords,
                            buyBackExchanges
                        )
                    )
                    .toList(),
                acctAccountRecordsPage.numElements(),
                acctAccountRecordsPage.maxElements()
            );

    }

    public static AccountRecordExtendedDetails fromAcctAccountRecord(
        AcctAccountRecord acctAccountRecord,
        Collection<AcctCurrencyExchange> currencyExchangeRecordsWithSourceAccountRecords,
        Collection<AcctCurrencyExchange> currencyExchangeRecordsWithTargetAccountRecords,
        Collection<AcctCurrencyExchange> buyBackExchanges
    ) {
        final Optional<AcctCurrencyExchange> optionalCurrencyExchangeRecordWithSourceAccountRecord =
            extractSourceCurrencyExchange(acctAccountRecord, currencyExchangeRecordsWithSourceAccountRecords);

        final Optional<AcctCurrencyExchange> optionalCurrencyExchangeRecordWithTargetAccountRecord =
            extractTargetCurrencyExchange(acctAccountRecord, currencyExchangeRecordsWithTargetAccountRecords);

        final Optional<AcctCurrencyExchange> optionalBuyBackExchange =
            optionalCurrencyExchangeRecordWithTargetAccountRecord.flatMap(
                exchangeTargetingRecord ->
                    extractBuyBackExchangeRecord(exchangeTargetingRecord, buyBackExchanges)
            );

        return
            AccountRecordExtendedDetails.builder()
                .withAccountRecordId(acctAccountRecord.getAccountRecordId())
                .withAccountRecordDate(acctAccountRecord.getAccountRecordDate())
                .withIncomeOrExpenseItemUUID(acctAccountRecord.getIncomeOrExpenseItemUUID())
                .withAccountRecordText(acctAccountRecord.getAccountRecordText())
                .withAccountRecordValue(acctAccountRecord.getAccountRecordValue())
                .withRecordedByUserUUID(acctAccountRecord.getRecordedByUserUUID())
                .withLastModifiedDate(acctAccountRecord.getLastModifiedDate())
                .withLastModifiedByUserUUID(acctAccountRecord.getLastModifiedByUserUUID())
                .withCurrencySellRate(optionalCurrencyExchangeRecordWithSourceAccountRecord.map(AcctCurrencyExchange::getCurrencyExchangeRate).orElse(null))
                .withBuyBackRate(optionalBuyBackExchange.map(AcctCurrencyExchange::getCurrencyExchangeRate).orElse(null))
                .withCurrencyExchangeRate(optionalCurrencyExchangeRecordWithTargetAccountRecord.map(AcctCurrencyExchange::getCurrencyExchangeRate).orElse(null))
                .withPurchasePrice(optionalCurrencyExchangeRecordWithTargetAccountRecord.map(AcctCurrencyExchange::getPurchasePrice).orElse(null))
                .build();
    }

    private static Optional<AcctCurrencyExchange> extractSourceCurrencyExchange(
        AcctAccountRecord acctAccountRecord,
        Collection<AcctCurrencyExchange> currencyExchangeRecords
    ) {
        return
            currencyExchangeRecords.stream()
                .filter(currencyExchange ->
                    Objects.equals(currencyExchange.getCurrencyExchangeSourceAccountRecord(), acctAccountRecord)
                )
                .findFirst();
    }

    private static Optional<AcctCurrencyExchange> extractTargetCurrencyExchange(
        AcctAccountRecord acctAccountRecord,
        Collection<AcctCurrencyExchange> currencyExchangeRecords
    ) {
        return
            currencyExchangeRecords.stream()
                .filter(currencyExchange ->
                    Objects.equals(currencyExchange.getCurrencyExchangeTargetAccountRecord(), acctAccountRecord)
                )
                .findFirst();
    }

    private static Optional<AcctCurrencyExchange> extractBuyBackExchangeRecord(
        AcctCurrencyExchange acctCurrencyExchange,
        Collection<AcctCurrencyExchange> buyBackExchanges
    ) {
        return
            buyBackExchanges.stream()
                .filter(buyBackExchange ->
                    Objects.equals(
                        buyBackExchange.getOptionalOriginalCurrencyExchange().orElse(null),
                        acctCurrencyExchange
                    )
                )
                .findFirst();
    }

}
