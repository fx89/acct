package com.desolatetimelines.acct.service.currency;

import com.desolatetimelines.acct.service.currency.exception.CurrencyExtractorException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BtCurrencyExtractorTest {

    @Test
    public void test() throws CurrencyExtractorException {
        CurrencyExtractor currencyExtractor = new BtCurrencyExtractor();

        currencyExtractor.fetchLatestRecords(CurrencyType.USD).forEach(rec ->
                System.out.println(rec.getDate() + " = " + rec.getValue())
            );

        assertEquals(1, 1);
    }

}
