package com.desolatetimelines.acct.batch.currencycollector.output;

import com.desolatetimelines.acct.batch.currencycollector.output.exception.CurrencyExtractorHistoryRecordOutputChannelException;
import com.desolatetimelines.acct.service.currency.CurrencyExtractorHistoryRecord;
import com.desolatetimelines.acct.service.currency.CurrencyType;

public interface CurrencyExtractorHistoryRecordOutputChannel {

    String getName();

    void output(
        String bankName,
        CurrencyType currencyType,
        Iterable<CurrencyExtractorHistoryRecord> records
    )
    throws CurrencyExtractorHistoryRecordOutputChannelException;

}
