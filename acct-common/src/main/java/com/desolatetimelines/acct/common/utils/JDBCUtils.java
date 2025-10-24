package com.desolatetimelines.acct.common.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Utilities for working with JDBC
 */
public abstract class JDBCUtils {

    /**
     * Extracts the columns specified in the given {@code columnNames} set from each row retrieved
     * from the referenced {@link ResultSet result set}.
     *
     * @param resultSet         The result set from which the data needs to be extracted.
     * @param expectedRowsCount An approximate number of records that the given SQL statement is
     *                          expected to return. Used as initial size for the data array.
     * @param columnNames       An ordered set of the names of the columns to be extracted from the
     *                          result set. The columns of the returned data array are set in the
     *                          exact same order as provided in this set.
     * @return A bi-dimensional data array containing the data extracted from the given result set.
     * The first dimension of the array is the row. The second dimension of the array is the column.
     * The order of the columns in the data array matches the order in which the columns have been
     * added into the given {@code columnNames} set.
     * @throws SQLException in case anything does not go well.
     */
    public static String[][] resultSetToDataArray(
        ResultSet resultSet,
        int expectedRowsCount,
        LinkedHashSet<String> columnNames
    ) throws SQLException {
        // Initialize the results list
        final ArrayList<String[]> resultsList =
            new ArrayList<>(expectedRowsCount > 0 ? expectedRowsCount : 100);

        // Iterate through each row in the result set
        while (resultSet.next()) {
            // Create a new row array for the values of each column on the current row
            final String[] row = new String[columnNames.size()];

            // Populate the row array
            int columnIndex = 0;
            for (String columnName : columnNames) {
                row[columnIndex] = resultSet.getString(columnName);
                columnIndex++;
            }

            // Add the newly populated row to the results list
            resultsList.add(row);
        }

        // Convert the results list to an array
        final String[][] resultsArray = new String[resultsList.size()][];
        resultsList.toArray(resultsArray);

        // Return a reference to the results list array
        return resultsArray;
    }

}
