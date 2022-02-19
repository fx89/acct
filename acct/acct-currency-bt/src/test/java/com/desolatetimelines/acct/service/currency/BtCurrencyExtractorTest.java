package com.desolatetimelines.acct.service.currency;

import com.desolatetimelines.acct.service.currency.exception.CurrencyExtractorException;
import org.junit.Test;

import static com.desolatetimelines.acct.service.currency.CurrencyType.RON;
import static org.junit.Assert.assertEquals;

public class BtCurrencyExtractorTest {

    @Test
    public void test() {
        CurrencyExtractor currencyExtractor = new BtCurrencyExtractor();

        CurrencyType.ALL.stream()
            .filter(cType -> !cType.equals(RON))
            .forEach(cType -> verifyCurrencyRecordsExtraction(currencyExtractor, cType));
    }

    private static void verifyCurrencyRecordsExtraction(
        CurrencyExtractor currencyExtractor,
        CurrencyType currencyType
    ) {
        try {
            currencyExtractor.fetchLatestRecords(currencyType)
                .forEach(rec ->
                        System.out.println(currencyType + ": " + rec.getDate() + " = " + rec.getValue())
                    );
        } catch (CurrencyExtractorException e) {
            e.printStackTrace();
            assertEquals(1, 2);
        }
    }

}
