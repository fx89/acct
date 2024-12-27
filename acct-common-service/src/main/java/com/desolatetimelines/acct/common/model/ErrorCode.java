package com.desolatetimelines.acct.common.model;

import com.desolatetimelines.acct.common.service.AbstractErrorCodesRegistryService;

/**
 * Describes an error code contained within the {@link AbstractErrorCodesRegistryService error codes registry}
 * <ul>
 *     <li>errorNumber      = An integer that uniquely identifies the error within its {@link ErrorCategory error category}</li>
 *     <li>errorCode        = A HEX code that uniquely identifies the error within the registry</li>
 *     <li>errorName        = A human-readable name for the error</li>
 *     <li>errorDescription = A human-readable description that explains how the error can be interpreted</li>
 * </ul>
 */
public class ErrorCode {
    private Integer errorNumber;
    private String errorCode;
    private String errorName;
    private String errorDescription;

    private ErrorCode(Integer errorNumber, String errorCode, String errorName, String errorDescription) {
        this.errorNumber = errorNumber;
        this.errorCode = errorCode;
        this.errorName = errorName;
        this.errorDescription = errorDescription;
    }

    public Integer getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(Integer errorNumber) {
        this.errorNumber = errorNumber;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public static ErrorCodeBuilder builder() {
        return new ErrorCodeBuilder();
    }

    /**
     * {@code ErrorCode} builder static inner class.
     */
    public static final class ErrorCodeBuilder {
        private Integer errorNumber;
        private String errorCode;
        private String errorName;
        private String errorDescription;

        private ErrorCodeBuilder() {
        }

        /**
         * Sets the {@code errorNumber} and returns a reference to this Builder enabling method chaining.
         *
         * @param errorNumber the {@code errorNumber} to set
         * @return a reference to this Builder
         */
        public ErrorCodeBuilder withErrorNumber(Integer errorNumber) {
            this.errorNumber = errorNumber;
            return this;
        }

        /**
         * Sets the {@code errorCode} and returns a reference to this Builder enabling method chaining.
         *
         * @param errorCode the {@code errorCode} to set
         * @return a reference to this Builder
         */
        public ErrorCodeBuilder withErrorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        /**
         * Sets the {@code errorName} and returns a reference to this Builder enabling method chaining.
         *
         * @param errorName the {@code errorName} to set
         * @return a reference to this Builder
         */
        public ErrorCodeBuilder withErrorName(String errorName) {
            this.errorName = errorName;
            return this;
        }

        /**
         * Sets the {@code errorDescription} and returns a reference to this Builder enabling method chaining.
         *
         * @param errorDescription the {@code errorDescription} to set
         * @return a reference to this Builder
         */
        public ErrorCodeBuilder withErrorDescription(String errorDescription) {
            this.errorDescription = errorDescription;
            return this;
        }

        /**
         * Returns a {@code ErrorCode} built from the parameters previously set.
         *
         * @return a {@code ErrorCode} built with parameters of this {@code ErrorCode.Builder}
         */
        public ErrorCode build() {
            return new ErrorCode(errorNumber, errorCode, errorName, errorDescription);
        }
    }
}
