package com.desolatetimelines.acct.common.exception;

import java.util.Map;

/**
 * Base class for all ACCT exception. Contains the following ACCT-specific identification items: <ul>
 * <li>{@link AcctException#getErrorCode() error code} - unique identifier for the type of error</li>
 * <li>{@link AcctException#getParameters()}  parameters} - unique identifier for the type of error</li>
 * </ul>
 * Can be decorated with one or more of the following annotations: <ul>
 * <li>{@link BadParameterException}</li>
 * <li>{@link ForbiddenException}</li>
 * <li>{@link NotFoundException}</li>
 * </ul>
 */
public class AcctException extends RuntimeException {

    private final String errorCode;

    private final Map<String, String> parameters;

    public AcctException(String errorCode) {
        super();
        this.errorCode = errorCode;
        this.parameters = null;
    }

    public AcctException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.parameters = null;
    }

    public AcctException(String errorCode, Map<String, String> parameters) {
        super();
        this.errorCode = errorCode;
        this.parameters = parameters;
    }

    public AcctException(String errorCode, Map<String, String> parameters, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.parameters = parameters;
    }

    /**
     * This code is unique across the ACCT ecosystem and represents a specific error message
     * that can be translated into a human-readable error message in any given language
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * This is a key/value map that contains the dynamic elements of the human-readable error
     * message that results from the translation of a given {@link AcctException#getErrorCode() error code}
     */
    public Map<String, String> getParameters() {
        return parameters;
    }

    /**
     * Tells if the {@link Class} of this exception is annotated with
     * {@link BadParameterException}
     */
    public boolean isBadParameterException() {
        return this.getClass().isAnnotationPresent(BadParameterException.class);
    }

    /**
     * Tells if the {@link Class} of this exception is annotated with
     * {@link ForbiddenException}
     */
    public boolean isForbiddenException() {
        return this.getClass().isAnnotationPresent(ForbiddenException.class);
    }

    /**
     * Tells if the {@link Class} of this exception is annotated with
     * {@link NotFoundException}
     */
    public boolean isNotFoundException() {
        return this.getClass().isAnnotationPresent(NotFoundException.class);
    }
}
