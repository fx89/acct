package com.desolatetimelines.acct.batch.currencycollector;

import com.desolatetimelines.acct.batch.currencycollector.output.CurrencyExtractorHistoryRecordOutputChannel;
import com.desolatetimelines.acct.batch.currencycollector.output.exception.CurrencyExtractorHistoryRecordOutputChannelException;
import com.desolatetimelines.acct.service.currency.CurrencyExtractor;
import com.desolatetimelines.acct.service.currency.CurrencyExtractorHistoryRecord;
import com.desolatetimelines.acct.service.currency.CurrencyType;
import com.desolatetimelines.acct.service.currency.exception.CurrencyExtractorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CurrencyBatchCollectMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyBatchCollectMain.class);

    @Autowired
    private CurrencyExtractor[] currencyExtractors;

    @Autowired
    private CurrencyExtractorHistoryRecordOutputChannel[] outputChannels;

    @PostConstruct
    private void init() {
        LOGGER.info("");
        LOGGER.info("");
        LOGGER.info("");

        LOGGER.info("Currency batch collection working with {} currency extractor(s) and {} output channel(s)",
                currencyExtractors.length,
                outputChannels.length
            );
    }

    public void run() {
        outputRecords(extractRecords());
    }

    /**
     * For each bank currency extractor and for each currency, extract the available list of history records
     */
    private Map<String, Map<CurrencyType, Iterable<CurrencyExtractorHistoryRecord>>> extractRecords() {
        // Initialize the result object (bank name > currency type > history records list)
        Map<String, Map<CurrencyType, Iterable<CurrencyExtractorHistoryRecord>>> ret
            = new HashMap<>();

        for(CurrencyExtractor currencyExtractor : currencyExtractors) {
            // Get and log the bank name
            String bankName = currencyExtractor.getBankName();
            LOGGER.info("Running currency extractor for {}", bankName);

            // Create the entry for the bank name
            Map<CurrencyType, Iterable<CurrencyExtractorHistoryRecord>> currTypeRecs = new HashMap<>();
            ret.put(bankName, currTypeRecs);

            // Extract the records for each currency type using the currency extractor for the bank.
            // Try to move on in case an error occurs, to extract as much data as possible.
            for(CurrencyType currencyType : CurrencyType.ALL) {
                try {
                    Iterable<CurrencyExtractorHistoryRecord> recs = currencyExtractor.fetchLatestRecords(currencyType);
                    currTypeRecs.put(currencyType, recs);
                }
                catch (CurrencyExtractorException exc) {
                    LOGGER.error("There was an error running the currency extractor for {} / {}: {}",
                            bankName,
                            currencyType.name(),
                            exc.getMessage()
                    );
                }
            }
        }

        return ret;
    }

    /**
     * For each bank and currency type provided, submit the currency history records to each available
     * {@link CurrencyExtractorHistoryRecordOutputChannel CurrencyExtractorHistoryRecordOutputChannel}
     */
    private void outputRecords(Map<String, Map<CurrencyType, Iterable<CurrencyExtractorHistoryRecord>>> recsPerBankAndCurrType) {
        recsPerBankAndCurrType.forEach((bankName, currTypeRecs) -> {
            currTypeRecs.forEach((currType, recs) -> {
                for(CurrencyExtractorHistoryRecordOutputChannel outputChannel : outputChannels) {
                    // Get the output channel name
                    String outputChannelName = outputChannel.getName();

                    // Try to write the data through the output channel.
                    // Try to go on in case of exceptions.
                    try {
                        LOGGER.info("Writing to output channel {} for {} / {}",
                                outputChannelName,
                                bankName,
                                currType.name()
                            );
                        outputChannel.output(bankName, currType, recs);
                    }
                    catch (CurrencyExtractorHistoryRecordOutputChannelException exc) {
                        LOGGER.error("There was an error on the output channel {} for {} / {}: {}",
                                outputChannelName,
                                bankName,
                                currType.name(),
                                exc.getMessage()
                            );
                    }
                }
            });
        });
    }
}
