package com.desolatetimelines.acct.reporting.dataprovider.service;

import com.desolatetimelines.acct.catalog.ws.client.RESTBanksEndpointClient;
import com.desolatetimelines.acct.catalog.ws.model.BankProperties;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingDataProviderInitializationException;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingDataProviderRuntimeException;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSet;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSetColumn;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSetColumnDataType;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class AcctReportingCatalogBanksDataProvider implements AcctReportingDataProvider {

    private final RESTBanksEndpointClient banksEndpointClient;

    public AcctReportingCatalogBanksDataProvider(
        RESTBanksEndpointClient banksEndpointClient
    ) {
        this.banksEndpointClient = banksEndpointClient;
    }

    @Override
    public void initialize(Map<String, String> dataProviderInstanceProperties) throws AcctReportingDataProviderInitializationException {

    }

    @Override
    public AcctReportingDataProviderDataSet provideData(Map<String, String> reportParameters) throws AcctReportingDataProviderRuntimeException {
        // Get the banks from the banks endpoint using the banks endpoint client
        final Collection<BankProperties> banks = banksEndpointClient.getBanks();

        // Build the data set and return a reference
        return new AcctReportingDataProviderDataSet() {

            private final LinkedHashSet<AcctReportingDataProviderDataSetColumn> columnNames =
                new LinkedHashSet<>(List.of(
                    new AcctReportingDataProviderDataSetColumn(
                        "bank_uuid",
                        AcctReportingDataProviderDataSetColumnDataType.STRING
                    ),
                    new AcctReportingDataProviderDataSetColumn(
                        "bank_code",
                        AcctReportingDataProviderDataSetColumnDataType.STRING
                    ),
                    new AcctReportingDataProviderDataSetColumn(
                        "bank_name",
                        AcctReportingDataProviderDataSetColumnDataType.STRING
                    )
                ));

            private final String[][] dataSetRecords = createRecords();

            private String[][] createRecords() {
                // Create the records array
                final String[][] records = new String[banks.size()][columnNames.size()];

                // Populate the records array with the relevant properties of the fetched banks
                int currentIndex = 0;
                for (BankProperties bankProperties : banks) {
                    records[currentIndex][0] = bankProperties.bankUUID();
                    records[currentIndex][1] = bankProperties.bankCode();
                    records[currentIndex][2] = bankProperties.bankName();

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
