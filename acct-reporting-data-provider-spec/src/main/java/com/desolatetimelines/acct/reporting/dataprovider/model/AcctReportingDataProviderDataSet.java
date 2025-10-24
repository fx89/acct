package com.desolatetimelines.acct.reporting.dataprovider.model;

import com.desolatetimelines.acct.reporting.dataprovider.service.AcctReportingDataProvider;

import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Contains the data returned by the {@link AcctReportingDataProvider data provider's}
 * {@link AcctReportingDataProvider#provideData(Map) provideData} method, along with
 * meta-data that describes the data set, such as the number of records in the
 * data set and the number and names of the columns exposed by the data set.<br />
 * <br />
 * This is a materialized data set. As opposed to a streaming data set, all the data
 * is buffered at the time the data set is compiled and is available until the data
 * set is garbage collected.
 */
public interface AcctReportingDataProviderDataSet {

    /**
     * Returns the number of records contained by the data set
     */
    int recordCount();

    /**
     * Returns a set of the names of all columns in the data set, in the order in which they
     * are found within the {@link AcctReportingDataProviderDataSet#data() data array} of
     * the data set. Enforcing of the ordering is important and ensures that the data set is
     * read correctly.
     */
    LinkedHashSet<AcctReportingDataProviderDataSetColumn> columns();

    /**
     * Returns a reference to the data array contained inside the data set. The first
     * coordinate of the array represents the row number. The second coordinate is the
     * column number. The column number is consistent with the index of the column in
     * the {@link AcctReportingDataProviderDataSet#columns() columns set}.
     */
    String[][] data();

    /**
     * Returns a new inline implementation of the {@link AcctReportingDataProviderDataSet} interface,
     * which contains no data at all.
     */
    static AcctReportingDataProviderDataSet emptyDataSet() {
        return new AcctReportingDataProviderDataSet() {

            private final LinkedHashSet<AcctReportingDataProviderDataSetColumn> columns =
                new LinkedHashSet<>();

            private final String[][] data = new String[0][];

            @Override
            public int recordCount() {
                return 0;
            }

            @Override
            public LinkedHashSet<AcctReportingDataProviderDataSetColumn> columns() {
                return columns;
            }

            @Override
            public String[][] data() {
                return data;
            }
        };
    }
}
