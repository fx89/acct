package com.desolatetimelines.acct.reporting.dataprovider.service;

import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctSQLiteJDBCConnectionException;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSet;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSetColumn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.LinkedHashSet;
import java.util.UUID;

import static com.desolatetimelines.acct.common.utils.JDBCUtils.resultSetToDataArray;
import static com.desolatetimelines.acct.reporting.dataprovider.utils.ResultSetUtils.getColumnsFromResultSet;

/**
 * Boiler-plating for in-memory data crunching using SQLite with the following
 * characteristics: <ul>
 * <li>Each new session starts with an empty database. The deletion of the
 * database file is handled on closure. Make sure to close the session properly.</li>
 * <li>Runs simple update and select statements.</li>
 * <li>This is meant for internal data crunching only. Do not expose to external
 * users.</li>
 * </ul>
 */
public class AcctSQLiteJDBCConnection implements AutoCloseable {

    /**
     * The name of the database must be unique to each session as each session
     * needs to start with a clean database.
     */
    private final String databaseName = UUID.randomUUID() + ".sqlite.db";

    /**
     * The JDBC connection to the underlying SQLite database
     */
    private final Connection connection;

    public AcctSQLiteJDBCConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
        } catch (SQLException e) {
            throw new AcctSQLiteJDBCConnectionException(
                "Unable to create a session.",
                e
            );
        }
    }

    /**
     * Executes the given SQL statement as an update statement.
     *
     * @param sql the given SQL statement
     * @throws AcctSQLiteJDBCConnectionException in case an {@link SQLException} occurs
     */
    public void executeUpdate(String sql) {
        try (final Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new AcctSQLiteJDBCConnectionException(
                "Unable to execute update.",
                e
            );
        }
    }

    /**
     * Executes the given SQL statement and returns a {@link AcctReportingDataProviderDataSet data set}
     * that contains the query results and some meta-data.
     *
     * @param sql The SQL statement to run.
     */
    public AcctReportingDataProviderDataSet executeQuery(String sql) {
        try (final Statement statement = connection.createStatement()) {
            try (final ResultSet resultSet = statement.executeQuery(sql)) {
                // Get the column names from the result set
                final LinkedHashSet<AcctReportingDataProviderDataSetColumn> columns =
                    getColumnsFromResultSet(resultSet);

                // Get the data from the result set
                final String[][] data =
                    resultSetToDataArray(
                        resultSet,
                        100,
                        new LinkedHashSet<>(columns.stream().map(AcctReportingDataProviderDataSetColumn::name).toList())
                    );

                // Return a new data set object
                return new AcctReportingDataProviderDataSet() {
                    @Override
                    public int recordCount() {
                        return data.length;
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
        } catch (SQLException e) {
            throw new AcctSQLiteJDBCConnectionException(
                "Unable to execute query.",
                e
            );
        }
    }

    /**
     * Closes the connection and deletes the database file.
     */
    @Override
    public void close() {
        // Close the connection
        try {
            connection.close();
        } catch (SQLException e) {
            throw new AcctSQLiteJDBCConnectionException(
                "Unable to close the connection.",
                e
            );
        } finally {
            // Delete the database file
            deleteDatabaseFile();
        }
    }

    private void deleteDatabaseFile() {
        try {
            Files.deleteIfExists(Path.of(databaseName));
        } catch (IOException e) {
            throw new AcctSQLiteJDBCConnectionException(
                "Unable to delete database file " + databaseName + ".",
                e
            );
        }
    }
}
