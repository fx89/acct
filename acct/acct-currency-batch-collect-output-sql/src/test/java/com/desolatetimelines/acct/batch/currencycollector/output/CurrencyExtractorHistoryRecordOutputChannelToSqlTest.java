package com.desolatetimelines.acct.batch.currencycollector.output;

import static org.junit.Assert.assertEquals;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.Test;

import com.desolatetimelines.acct.batch.currencycollector.output.sql.CurrencyExtractorHistoryRecordOutputChannelToSqlConfig;
import com.desolatetimelines.acct.service.currency.CurrencyType;

public class CurrencyExtractorHistoryRecordOutputChannelToSqlTest {

    @Test
    public void testFilenameGeneration() {
        CurrencyExtractorHistoryRecordOutputChannelToSqlConfig config
        	= new CurrencyExtractorHistoryRecordOutputChannelToSqlConfig()
        		.withFilenamePrefix("PREFIX")
        		.withFilePath("PATH");

        CurrencyExtractorHistoryRecordOutputChannelToSql extractor
            = new CurrencyExtractorHistoryRecordOutputChannelToSql(config);

        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
        String isoDateStr = DATE_FORMATTER.format(new Date().toInstant().atZone(ZoneId.systemDefault()));

        assertEquals(
        		"PATH/PREFIX---BancaTransilvania--CHF--" + isoDateStr + ".sql",
        		extractor.getFilePathName("Banca Transilvania", CurrencyType.CHF)
    		);
    }

}
