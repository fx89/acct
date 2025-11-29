package com.desolatetimelines.acct.reporting.dataprovider.utils;

import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSetColumn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import static com.desolatetimelines.acct.reporting.dataprovider.mapper.AcctReportingDataProviderDataSetColumnDataTypeMapper.fromJdbcColumnType;

/**
 * Provides utility methods for working with {@link ResultSet JDBC result sets}
 */
public abstract class ResultSetUtils {

    /**
     * Compiles the {@link LinkedHashSet ordered set} of {@link AcctReportingDataProviderDataSetColumn columns}
     * from the referenced {@link ResultSet result set}
     *
     * @param resultSet the referenced result set
     */
    public static LinkedHashSet<AcctReportingDataProviderDataSetColumn> getColumnsFromResultSet(
        ResultSet resultSet
    ) throws SQLException {
        // Create the columns set
        final LinkedHashSet<AcctReportingDataProviderDataSetColumn> columns =
            new LinkedHashSet<>(resultSet.getMetaData().getColumnCount());

        // Populate the columns set from the meta-data in the result set
        for (int c = 1; c <= resultSet.getMetaData().getColumnCount(); c++) {
            columns.add(
                new AcctReportingDataProviderDataSetColumn(
                    resultSet.getMetaData().getColumnLabel(c),
                    fromJdbcColumnType(resultSet.getMetaData().getColumnType(c))
                )
            );
        }

        // Return a reference to the populated columns set
        return columns;
    }

}
