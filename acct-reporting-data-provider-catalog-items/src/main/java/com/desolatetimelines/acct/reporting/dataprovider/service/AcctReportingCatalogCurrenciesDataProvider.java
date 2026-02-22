package com.desolatetimelines.acct.reporting.dataprovider.service;

import com.desolatetimelines.acct.catalog.ws.client.RESTCurrenciesEndpointClient;
import com.desolatetimelines.acct.catalog.ws.model.CurrencyProperties;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingDataProviderInitializationException;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingDataProviderRuntimeException;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSet;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSetColumn;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSetColumnDataType;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class AcctReportingCatalogCurrenciesDataProvider implements AcctReportingDataProvider {

    private final RESTCurrenciesEndpointClient currenciesEndpointClient;

    public AcctReportingCatalogCurrenciesDataProvider(
        RESTCurrenciesEndpointClient currenciesEndpointClient
    ) {
        this.currenciesEndpointClient = currenciesEndpointClient;
    }

    @Override
    public void initialize(Map<String, String> dataProviderInstanceProperties) throws AcctReportingDataProviderInitializationException {

    }

    @Override
    public AcctReportingDataProviderDataSet provideData(Map<String, String> reportParameters) throws AcctReportingDataProviderRuntimeException {
        // Get the currencies from the currencies endpoint using the currencies endpoint client
        final Collection<CurrencyProperties> currencies = currenciesEndpointClient.getCurrencies();

        // Build the data set and return a reference
        return new AcctReportingDataProviderDataSet() {

            private final LinkedHashSet<AcctReportingDataProviderDataSetColumn> columnNames =
                new LinkedHashSet<>(List.of(
                    new AcctReportingDataProviderDataSetColumn(
                        "currency_uuid",
                        AcctReportingDataProviderDataSetColumnDataType.STRING
                    ),
                    new AcctReportingDataProviderDataSetColumn(
                        "currency_code",
                        AcctReportingDataProviderDataSetColumnDataType.STRING
                    ),
                    new AcctReportingDataProviderDataSetColumn(
                        "currency_name",
                        AcctReportingDataProviderDataSetColumnDataType.STRING
                    )
                ));

            private final String[][] dataSetRecords = createRecords();

            private String[][] createRecords() {
                // Create the records array
                final String[][] records = new String[currencies.size()][columnNames.size()];

                // Populate the records array with the relevant properties of the fetched currencies
                int currentIndex = 0;
                for (CurrencyProperties currencyProperties : currencies) {
                    records[currentIndex][0] = currencyProperties.currencyUUID();
                    records[currentIndex][1] = currencyProperties.currencyCode();
                    records[currentIndex][2] = currencyProperties.currencyName();

                    // Increment the current index
                    currentIndex++;
                }

                // Return a reference to the records array
                return records;
            }

            @Override
            public int recordCount() {
                return dataSetRecords.length;
            }

            @Override
            public LinkedHashSet<AcctReportingDataProviderDataSetColumn> columns() {
                return columnNames;
            }

            @Override
            public String[][] data() {
                return dataSetRecords;
            }
        };
    }

}
