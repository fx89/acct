package com.desolatetimelines.acct.common.model;

import com.desolatetimelines.acct.common.service.AbstractErrorCodesRegistryService;

import java.util.List;

/**
 * Describes a category of error codes contained by the
 * {@link AbstractErrorCodesRegistryService error codes registry}
 *
 * @param errorCategoryNumber An integer that uniquely identifies the error category within its
 *                            {@link ErrorThrowingServiceDescription service}
 * @param errorCategoryName   A human-readable name that uniquely identifies the error category within the registry
 * @param errorCodes          A list of {@link ErrorCode error codes} that are registered under this category
 */
public record ErrorCategory(
    Integer errorCategoryNumber,
    String errorCategoryName,
    List<ErrorCode> errorCodes
) {
    public static ErrorCategoryBuilder builder() {
        return new ErrorCategoryBuilder();
    }

    /**
     * {@code ErrorCategory} builder static inner class.
     */
    public static final class ErrorCategoryBuilder {
        private Integer errorCategoryNumber;
        private String errorCategoryName;
        private List<ErrorCode> errorCodes;

        private ErrorCategoryBuilder() {
        }

        /**
         * Sets the {@code errorCategoryNumber} and returns a reference to this Builder enabling method chaining.
         *
         * @param errorCategoryNumber the {@code errorCategoryNumber} to set
         * @return a reference to this Builder
         */
        public ErrorCategoryBuilder withErrorCategoryNumber(Integer errorCategoryNumber) {
            this.errorCategoryNumber = errorCategoryNumber;
            return this;
        }

        /**
         * Sets the {@code errorCategoryName} and returns a reference to this Builder enabling method chaining.
         *
         * @param errorCategoryName the {@code errorCategoryName} to set
         * @return a reference to this Builder
         */
        public ErrorCategoryBuilder withErrorCategoryName(String errorCategoryName) {
            this.errorCategoryName = errorCategoryName;
            return this;
        }

        /**
         * Sets the {@code errorCodes} and returns a reference to this Builder enabling method chaining.
         *
         * @param errorCodes the {@code errorCodes} to set
         * @return a reference to this Builder
         */
        public ErrorCategoryBuilder withErrorCodes(List<ErrorCode> errorCodes) {
            this.errorCodes = errorCodes;
            return this;
        }

        /**
         * Returns a {@code ErrorCategory} built from the parameters previously set.
         *
         * @return a {@code ErrorCategory} built with parameters of this {@code ErrorCategory.Builder}
         */
        public ErrorCategory build() {
            return new ErrorCategory(errorCategoryNumber, errorCategoryName, errorCodes);
        }
    }
}
