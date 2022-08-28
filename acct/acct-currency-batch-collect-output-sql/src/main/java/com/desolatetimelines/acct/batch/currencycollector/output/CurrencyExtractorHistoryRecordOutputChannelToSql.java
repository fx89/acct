package com.desolatetimelines.acct.batch.currencycollector.output;

import com.desolatetimelines.acct.batch.currencycollector.output.exception.CurrencyExtractorHistoryRecordOutputChannelException;
import com.desolatetimelines.acct.batch.currencycollector.output.sql.CurrencyExtractorHistoryRecordOutputChannelToSqlConfig;
import com.desolatetimelines.acct.service.currency.CurrencyExtractorHistoryRecord;
import com.desolatetimelines.acct.service.currency.CurrencyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service("CurrencyExtractorHistoryRecordOutputChannelToSql")
public class CurrencyExtractorHistoryRecordOutputChannelToSql implements CurrencyExtractorHistoryRecordOutputChannel {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    private final CurrencyExtractorHistoryRecordOutputChannelToSqlConfig properties;

    public CurrencyExtractorHistoryRecordOutputChannelToSql(
        @Autowired CurrencyExtractorHistoryRecordOutputChannelToSqlConfig properties
    ) {
        this.properties = properties;
    }

    @Override
    public String getName() {
        return "ACCT SQL Inserts";
    }

    @Override
    public void output(String bankName, CurrencyType currencyType, Iterable<CurrencyExtractorHistoryRecord> records)
    throws CurrencyExtractorHistoryRecordOutputChannelException {
        try (FileWriter fileWriter = new FileWriter(getFilePathName(bankName, currencyType))) {
            // Write the delete statements
            fileWriter.write(composeDeleteSql(bankName, currencyType, records) + ";\r\n\r\n");

            // Write the insert statements
            for(CurrencyExtractorHistoryRecord record : records) {
                fileWriter.write(composeInsertSql(bankName, currencyType, record) + ";\r\n");
            }
        }
        catch (IOException exc) {
            throw new CurrencyExtractorHistoryRecordOutputChannelException(exc.getMessage(), exc);
        }
    }

    protected String getFilePathName(String bankName, CurrencyType currencyType) {
        String isoDateStr = DATE_FORMATTER.format(new Date().toInstant().atZone(ZoneId.systemDefault()));

        return
            (
	            properties.getFilePath() + "/" +
                properties.getFileNamePrefix() + "---" +
                bankName + "--" +
                currencyType.name() + "--" +
                isoDateStr +
                ".sql"
            ).replaceAll("\\s", "");
    }

    private String composeDeleteSql(
        String bankName,
        CurrencyType currencyType,
        Iterable<CurrencyExtractorHistoryRecord> records
    ) {
        String datesListStr = "'" +
            StreamSupport.stream(records.spliterator(), false)
                .map(CurrencyExtractorHistoryRecord::getDate)
                .map(date -> DATE_FORMATTER.format(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
                .collect(Collectors.joining("','"))
            + "'";

        return
            "DELETE FROM currency_history_record WHERE " +
                "currency_id = (" +
                        "SELECT MAX(id) FROM monitored_currency " +
                            "WHERE currency_type_name = '" + currencyType.name() + "' " +
                            "AND bank_id = (SELECT MAX(id) FROM bank WHERE name = '" + bankName + "')" +
                    ") " +
                "AND date IN ( " + datesListStr + " )";
    }

    private String composeInsertSql(String bankName, CurrencyType currencyType, CurrencyExtractorHistoryRecord rec) {
        return
            "INSERT INTO currency_history_record(currency_id, date, value) " +
                "VALUES ( " +
                    "(SELECT MAX(id) FROM monitored_currency " +
                        "WHERE currency_type_name = '" + currencyType.name() + "' " +
                        "AND bank_id = (SELECT MAX(id) FROM bank WHERE name = '" + bankName + "'))" +
                    ", '" + DATE_FORMATTER.format(rec.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) + "'" +
                    ", " + rec.getValue().toString() +
                  " )";
    }
}
