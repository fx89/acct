package com.desolatetimelines.acct.usermanagement.exception;

/**
 * Thrown when ACCT resources are not accessible to a given user or group
 */
public class AcctUserManagementIllegalAccessException extends RuntimeException {

    public AcctUserManagementIllegalAccessException(String message) {
        super(message);
    }

}
