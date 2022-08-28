package com.desolatetimelines.acct.batch.currencycollector.output.sql;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:acct.sql-inserts.channel.properties")
public class CurrencyExtractorHistoryRecordOutputChannelToSqlConfig {

    @Value( "${file.path}" )
    private String filePath;

    @Value( "${file.namePrefix}" )
    private String fileNamePrefix;

    public String getFilePath() {
        return filePath;
    }

    public String getFileNamePrefix() {
        return fileNamePrefix;
    }

    public CurrencyExtractorHistoryRecordOutputChannelToSqlConfig withFilePath(String filePath) {
    	this.filePath = filePath;
    	return this;
    }

    public CurrencyExtractorHistoryRecordOutputChannelToSqlConfig withFilenamePrefix(String filenamePrefix) {
    	this.fileNamePrefix = filenamePrefix;
    	return this;
    }
}