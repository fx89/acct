package com.desolatetimelines.acct.reporting.dataprovider.service;

import com.desolatetimelines.acct.reporting.dataprovider.annotation.Immutable;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingDataProviderInitializationException;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingDataProviderRuntimeException;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSet;

import java.util.Map;

/**
 * The data provider is used in the first step of the ACCT report compilation process. Each report references
 * one or more data provider instances, each of which provides a data set that is then used in the report.
 * There can be multiple instances of the same data provider, differentiated by the different sets of data
 * provider instance properties that are used when initializing the data provider instances. Once a data
 * provider instance is initialized, its data extraction method may be called multiple times with different
 * sets of report parameters.
 */
public interface AcctReportingDataProvider {

    /**
     * Called when the data provider instance is initialized. Allows the data provider instance to set the
     * properties that influence the runtime behavior of the data provider instance.
     *
     * @param dataProviderInstanceProperties A key / value map that contains the runtime properties of the data
     *                                       provider instance.
     * @throws AcctReportingDataProviderInitializationException in case anything wrong happens while initializing
     *                                                          the data provider instance, including bad
     *                                                          parameter values
     */
    void initialize(Map<String, String> dataProviderInstanceProperties)
        throws AcctReportingDataProviderInitializationException;

    /**
     * Compiles the report data based on the {@link AcctReportingDataProvider#initialize(Map) pre-set}
     * instance properties and the provided report parameters.
     *
     * @param reportParameters A key / value map that contains the provided report parameters. These are
     *                         runtime parameters that can differ from one call of this method to another.
     *                         The report parameters map is the same for all data providers that are used
     *                         by the report. This map is immutable, which means that the data provider
     *                         is not allowed to change its contents in any way.
     * @return The {@link AcctReportingDataProviderDataSet data set} resulted from the process of compiling
     * the report data
     * @throws AcctReportingDataProviderRuntimeException when any sort of exception occurs during the
     *                                                   report data compilation process
     */
    AcctReportingDataProviderDataSet provideData(@Immutable Map<String, String> reportParameters)
        throws AcctReportingDataProviderRuntimeException;

}
