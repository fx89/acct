package com.desolatetimelines.acct.reporting.dataprovider.service;

import com.desolatetimelines.acct.catalog.ws.client.RESTCurrenciesEndpointClient;
import com.desolatetimelines.acct.catalog.ws.model.CurrencyProperties;
import com.desolatetimelines.acct.currency.ws.client.RESTMonitoredCurrenciesEndpointClient;
import com.desolatetimelines.acct.currency.ws.model.MonitoredCurrencyProperties;
import com.desolatetimelines.acct.currency.ws.model.MonitoredCurrencyRecordProperties;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingDataProviderInitializationException;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingDataProviderRuntimeException;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSet;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSetColumn;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSetColumnDataType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;

import static com.desolatetimelines.acct.reporting.dataprovider.service.Constants.INSTANCE_PROPERTY_NAME_CURRENCY_CODE;
import static com.desolatetimelines.acct.reporting.dataprovider.service.Constants.INSTANCE_PROPERTY_NAME_NUM_DAYS_AGO;
import static java.util.function.Function.identity;

public class AcctReportingCurrencyHistoryDataProvider implements AcctReportingDataProvider {

    private static final DateTimeFormatter CURRENCY_RECORD_DATE_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final RESTCurrenciesEndpointClient currenciesEndpointClient;

    private final RESTMonitoredCurrenciesEndpointClient monitoredCurrenciesEndpointClient;

    private Long numDaysAgo = 10L;

    private String currencyCode = "EUR";

    public AcctReportingCurrencyHistoryDataProvider(
        RESTCurrenciesEndpointClient currenciesEndpointClient,
        RESTMonitoredCurrenciesEndpointClient monitoredCurrenciesEndpointClient
    ) {
        this.currenciesEndpointClient = currenciesEndpointClient;
        this.monitoredCurrenciesEndpointClient = monitoredCurrenciesEndpointClient;
    }

    @Override
    public void initialize(Map<String, String> dataProviderInstanceProperties) throws AcctReportingDataProviderInitializationException {
        numDaysAgo =
            getProperty(
                dataProviderInstanceProperties,
                INSTANCE_PROPERTY_NAME_NUM_DAYS_AGO,
                Long::parseLong
            );

        currencyCode =
            getProperty(
                dataProviderInstanceProperties,
                INSTANCE_PROPERTY_NAME_CURRENCY_CODE,
                identity()
            );
    }

    private static <T> T getProperty(
        Map<String, String> dataProviderInstanceProperties,
        String propertyName,
        Function<String, T> converter
    ) {
        // Get the string property or fail
        final String propertyValue =
            Optional.ofNullable(
                dataProviderInstanceProperties.get(propertyName)
            ).orElseThrow(() -> new AcctReportingDataProviderInitializationException(
                "Property " + propertyName + "not supplied"
            ));

        // Attempt to convert the string property into the proper type
        try {
            return converter.apply(propertyValue);
        }
        // If the conversion fails, then throw an exception
        catch (Exception e) {
            throw new AcctReportingDataProviderInitializationException(
                "The value supplied for the " + propertyName + " parameter cannot be parsed"
            );
        }
    }

    @Override
    public AcctReportingDataProviderDataSet provideData(Map<String, String> reportParameters) throws AcctReportingDataProviderRuntimeException {
        // Get the currency UUID
        final String currencyUUID =
            currenciesEndpointClient.getCurrencies().stream()
                .filter(c -> Objects.equals(currencyCode, c.currencyCode()))
                .map(CurrencyProperties::currencyUUID)
                .findFirst()
                .orElseThrow(() -> new AcctReportingDataProviderInitializationException(
                    "The supplied currency code, " + currencyCode + " is not supported"
                ));

        // Get the monitored currency UUID based on the currency UUID
        final String monitoredCurrencyUUID =
            monitoredCurrenciesEndpointClient.getMonitoredCurrencies().stream()
                .filter(mc -> Objects.equals(currencyUUID, mc.currencyUUID()))
                .map(MonitoredCurrencyProperties::monitoredCurrencyUUID)
                .findFirst()
                .orElseThrow(() -> new AcctReportingDataProviderInitializationException(
                    "The currency having code=" + currencyCode + " is not monitored"
                ));

        // Set the end date as the current date
        final Date endDate = new Date();

        // Set the start date as the end date minus the given number of days
        final Date startDate =
            Date.from(
                LocalDate
                    .from(endDate.toInstant())
                    .minusDays(numDaysAgo)
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()
            );

        // Get the records
        final Collection<MonitoredCurrencyRecordProperties> monitoredCurrencyRecords =
            monitoredCurrenciesEndpointClient.getMonitoredCurrencyRecordsBetweenDates(
                monitoredCurrencyUUID,
                startDate,
                endDate
            );

        // Build the data set and return a reference
        return new AcctReportingDataProviderDataSet() {

            private final LinkedHashSet<AcctReportingDataProviderDataSetColumn> columnNames =
                new LinkedHashSet<>(List.of(
                    new AcctReportingDataProviderDataSetColumn(
                        "record_date",
                        AcctReportingDataProviderDataSetColumnDataType.TIMESTAMP
                    ),
                    new AcctReportingDataProviderDataSetColumn(
                        "purchase_value",
                        AcctReportingDataProviderDataSetColumnDataType.NUMERIC
                    ),
                    new AcctReportingDataProviderDataSetColumn(
                        "sale_value",
                        AcctReportingDataProviderDataSetColumnDataType.NUMERIC
                    )
                ));

            private final String[][] dataSetRecords = convertRecords(monitoredCurrencyRecords);

            private static String[][] convertRecords(Collection<MonitoredCurrencyRecordProperties> records) {
                // Convert the records to a records list
                final List<String[]> recordsList =
                    records.parallelStream()
                        .map(record -> {
                            final String[] line = new String[3];
                            line[0] = LocalDateTime.from(record.monitoredCurrencyRecordDate()).format(CURRENCY_RECORD_DATE_FORMATTER);
                            line[1] = Double.toString(record.monitoredCurrencyRecordPurchaseValue());
                            line[2] = Double.toString(record.monitoredCurrencyRecordSaleValue());
                            return line;
                        })
                        .toList();

                // Convert the records list to a records array
                final String[][] convertedRecords = new String[recordsList.size()][];
                recordsList.toArray(convertedRecords);

                // Return a reference to the records array
                return convertedRecords;
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
