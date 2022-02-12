package com.desolatetimelines.acct.service.currency;

import com.desolatetimelines.acct.service.currency.exception.CurrencyExtractorException;
import com.desolatetimelines.util.httpclient.exception.HttpClientUtilsException;

import java.util.List;

import static com.desolatetimelines.util.httpclient.HttpClientUtils.getSSLPageContentAsString;

public abstract class UriCurrencyExtractor implements CurrencyExtractor {

    @Override
    public List<CurrencyExtractorHistoryRecord> fetchLatestRecords(CurrencyType currencyType)
            throws CurrencyExtractorException
    {
        try {
            return parsePageContent(
                        getSSLPageContentAsString( mapCurrencyTypeToResourceURI(currencyType) ),
                        currencyType
                    );

        } catch (HttpClientUtilsException exc) {
            throw new CurrencyExtractorException("Unable to extract currency: " + exc.getMessage());
        }
    }

    protected abstract String mapCurrencyTypeToResourceURI(CurrencyType currencyType)
        throws CurrencyExtractorException;

    protected abstract List<CurrencyExtractorHistoryRecord>
        parsePageContent(String pageContent, CurrencyType currencyType)
            throws CurrencyExtractorException;
}
