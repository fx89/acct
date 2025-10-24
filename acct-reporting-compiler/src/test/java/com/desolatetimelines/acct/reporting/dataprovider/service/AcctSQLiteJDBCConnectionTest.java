package com.desolatetimelines.acct.reporting.dataprovider.service;

import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctSQLiteJDBCConnectionException;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSet;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSetColumnDataType.NUMERIC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AcctSQLiteJDBCConnectionTest {

    @Test
    void testAcctSQLiteJDBCConnection_worksAsDesigned() {
        try (final AcctSQLiteJDBCConnection connection = new AcctSQLiteJDBCConnection()) {

            // Create a table
            connection.executeUpdate("""
                    CREATE TABLE tbl1 (
                        col1 char(200),
                        col2 char(200),
                        col3 char(200),
                        col4 real
                    )
                """);

            // Add data to the first table
            connection.executeUpdate("""
                    INSERT INTO tbl1(col1, col2, col3, col4)
                    VALUES
                        ('A1', 'B1', 'C1', 1),
                        ('A2', 'B2', 'C2', 12.34)
                """);

            // Create a second table
            connection.executeUpdate("""
                    CREATE TABLE tbl2 (
                        col1 char(200),
                        col2 char(200),
                        col5 char(200)
                    )
                """);

            // Add data to the second table
            connection.executeUpdate("""
                    INSERT INTO tbl2(col1, col2, col5)
                    VALUES
                        ('A1', 'D1', 'E1'),
                        ('A2', 'D2', 'E2')
                """);

            // Join the two tables
            final AcctReportingDataProviderDataSet result =
                connection.executeQuery("""
                        SELECT
                            tbl1.col1 "tbl1.col1",
                            tbl1.col2 tbl1_col2,
                            tbl1.col3 tbl1_col3,
                            tbl1.col4 tbl1_col4,
                            tbl2.col1 tbl2_col1,
                            tbl2.col2 tbl2_col2,
                            tbl2.col5 tbl2_col5
                        FROM
                            tbl1,
                            tbl2
                        WHERE
                            tbl1.col1 = tbl2.col1
                    """
                );

            // Check that the result set size is the expected one
            assertEquals(2, result.recordCount());
            assertEquals(7, result.columns().size());
            assertEquals(7, result.data()[0].length);

            // Check the column names and order
            assertEquals("tbl1.col1", result.columns().getFirst().name());
            assertEquals("tbl2_col5", result.columns().getLast().name());

            // Check that the data type for the column declared with the "real" SQLite data type
            // is translated to NUMERIC in ACCT terms
            assertEquals(
                NUMERIC,
                result.columns().stream()
                    .filter(c -> Objects.equals("tbl1_col4", c.name()))
                    .findFirst()
                    .orElseThrow()
                    .dataType()
            );
        }
    }

    @Test
    void testAcctSQLiteJDBCConnection_ThrowsExceptionOnInvalidQuery() {
        try (final AcctSQLiteJDBCConnection connection = new AcctSQLiteJDBCConnection()) {
            assertThrows(
                AcctSQLiteJDBCConnectionException.class,
                () -> connection.executeQuery("bad query")
            );

            assertThrows(
                AcctSQLiteJDBCConnectionException.class,
                () -> connection.executeUpdate("another bad query")
            );
        }
    }
}
