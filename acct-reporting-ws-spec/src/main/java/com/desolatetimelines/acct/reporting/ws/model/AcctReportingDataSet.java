package com.desolatetimelines.acct.reporting.ws.model;

import java.util.LinkedHashSet;

/**
 * Materialized data set that contains data returned by the ACCT reporting service for
 * both individual data provider instances and combined reports alike.
 *
 * @param recordCount The number of records in the data set.
 * @param columns     The columns exposed by the data set, in the exact order in which they can be
 *                    found in the data array.
 * @param data        The data array that contains the data exposed by the data set.
 */
public record AcctReportingDataSet(
    Integer recordCount,
    LinkedHashSet<AcctReportingDataSetColumn> columns,
    String[][] data
) {
}
