package com.desolatetimelines.acct.reporting.dataprovider.exception;

import com.desolatetimelines.acct.reporting.dataprovider.service.AcctSQLiteJDBCConnection;

/**
 * Thrown by the {@link AcctSQLiteJDBCConnection} in case SQL exceptions occur.
 */
public class AcctSQLiteJDBCConnectionException extends RuntimeException {
    public AcctSQLiteJDBCConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
