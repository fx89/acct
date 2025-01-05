package com.desolatetimelines.acct.workspace;

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
        Collection<AcctCurrencyExchange> currencyExchangeRecords
    ) {
        return
            new Page<>(
                acctAccountRecordsPage.data()
                    .stream()
                    .map(acctAccountRecord -> fromAcctAccountRecord(acctAccountRecord, currencyExchangeRecords))
                    .toList(),
                acctAccountRecordsPage.numElements(),
                acctAccountRecordsPage.maxElements()
            );

    }

    public static AccountRecordExtendedDetails fromAcctAccountRecord(
        AcctAccountRecord acctAccountRecord,
        Collection<AcctCurrencyExchange> currencyExchangeRecords
    ) {
        final Optional<AcctCurrencyExchange> optionalCurrencyExchangeRecord =
            extractCurrencyExchange(acctAccountRecord, currencyExchangeRecords);

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
                .withCurrencyExchangeRate(optionalCurrencyExchangeRecord.map(AcctCurrencyExchange::getCurrencyExchangeRate).orElse(null))
                .withPurchasePrice(optionalCurrencyExchangeRecord.map(AcctCurrencyExchange::getPurchasePrice).orElse(null))
                .build();
    }

    private static Optional<AcctCurrencyExchange> extractCurrencyExchange(
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

}
