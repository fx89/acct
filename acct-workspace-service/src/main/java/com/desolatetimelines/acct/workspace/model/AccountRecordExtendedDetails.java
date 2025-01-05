package com.desolatetimelines.acct.workspace.model;

import java.time.Instant;

/**
 * Wraps all the properties that can be retrieved for a given account record
 *
 * @param accountRecordId         The unique identifier of the record within the scope of the account
 * @param accountRecordDate       The date at which the record was created
 * @param recordedByUserUUID      The UUID of the user who created the record
 * @param incomeOrExpenseItemUUID The UUID of the income or expense item that categorizes the record
 * @param accountRecordText       The human-readable description of the specific transaction
 * @param accountRecordValue      The value of the transaction
 * @param lastModifiedDate        The date when the record was last updated
 * @param lastModifiedByUserUUID  The UUID of the user who last updated the record
 * @param currencyExchangeRate    The optional exchange rate applied when purchasing foreign currency
 * @param purchasePrice           The optional foreign exchange purchase price, calculated as
 *                                {@code accountRecordValue * currencyExchangeRate}
 */
public record AccountRecordExtendedDetails(
    Long accountRecordId,
    Instant accountRecordDate,
    String recordedByUserUUID,
    String incomeOrExpenseItemUUID,
    String accountRecordText,
    Double accountRecordValue,
    Instant lastModifiedDate,
    String lastModifiedByUserUUID,
    Double currencyExchangeRate,
    Double purchasePrice
) {
    public static AccountRecordExtendedDetailsBuilder builder() {
        return new AccountRecordExtendedDetailsBuilder();
    }

    /**
     * {@code AccountRecordExtendedDetails} builder static inner class.
     */
    public static final class AccountRecordExtendedDetailsBuilder {
        private Long accountRecordId;
        private Instant accountRecordDate;
        private String recordedByUserUUID;
        private String incomeOrExpenseItemUUID;
        private String accountRecordText;
        private Double accountRecordValue;
        private Instant lastModifiedDate;
        private String lastModifiedByUserUUID;
        private Double currencyExchangeRate;
        private Double purchasePrice;

        private AccountRecordExtendedDetailsBuilder() {
        }

        /**
         * Sets the {@code accountRecordId} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountRecordId the {@code accountRecordId} to set
         * @return a reference to this Builder
         */
        public AccountRecordExtendedDetailsBuilder withAccountRecordId(Long accountRecordId) {
            this.accountRecordId = accountRecordId;
            return this;
        }

        /**
         * Sets the {@code accountRecordDate} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountRecordDate the {@code accountRecordDate} to set
         * @return a reference to this Builder
         */
        public AccountRecordExtendedDetailsBuilder withAccountRecordDate(Instant accountRecordDate) {
            this.accountRecordDate = accountRecordDate;
            return this;
        }

        /**
         * Sets the {@code recordedByUserUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param recordedByUserUUID the {@code recordedByUserUUID} to set
         * @return a reference to this Builder
         */
        public AccountRecordExtendedDetailsBuilder withRecordedByUserUUID(String recordedByUserUUID) {
            this.recordedByUserUUID = recordedByUserUUID;
            return this;
        }

        /**
         * Sets the {@code incomeOrExpenseItemUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param incomeOrExpenseItemUUID the {@code incomeOrExpenseItemUUID} to set
         * @return a reference to this Builder
         */
        public AccountRecordExtendedDetailsBuilder withIncomeOrExpenseItemUUID(String incomeOrExpenseItemUUID) {
            this.incomeOrExpenseItemUUID = incomeOrExpenseItemUUID;
            return this;
        }

        /**
         * Sets the {@code accountRecordText} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountRecordText the {@code accountRecordText} to set
         * @return a reference to this Builder
         */
        public AccountRecordExtendedDetailsBuilder withAccountRecordText(String accountRecordText) {
            this.accountRecordText = accountRecordText;
            return this;
        }

        /**
         * Sets the {@code accountRecordValue} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountRecordValue the {@code accountRecordValue} to set
         * @return a reference to this Builder
         */
        public AccountRecordExtendedDetailsBuilder withAccountRecordValue(Double accountRecordValue) {
            this.accountRecordValue = accountRecordValue;
            return this;
        }

        /**
         * Sets the {@code lastModifiedDate} and returns a reference to this Builder enabling method chaining.
         *
         * @param lastModifiedDate the {@code lastModifiedDate} to set
         * @return a reference to this Builder
         */
        public AccountRecordExtendedDetailsBuilder withLastModifiedDate(Instant lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
            return this;
        }

        /**
         * Sets the {@code lastModifiedByUserUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param lastModifiedByUserUUID the {@code lastModifiedByUserUUID} to set
         * @return a reference to this Builder
         */
        public AccountRecordExtendedDetailsBuilder withLastModifiedByUserUUID(String lastModifiedByUserUUID) {
            this.lastModifiedByUserUUID = lastModifiedByUserUUID;
            return this;
        }

        /**
         * Sets the {@code purchasePrice} and returns a reference to this Builder enabling method chaining.
         *
         * @param purchasePrice the {@code purchasePrice} to set
         * @return a reference to this Builder
         */
        public AccountRecordExtendedDetailsBuilder withPurchasePrice(Double purchasePrice) {
            this.purchasePrice = purchasePrice;
            return this;
        }

        /**
         * Sets the {@code currencyExchangeRate} and returns a reference to this Builder enabling method chaining.
         *
         * @param currencyExchangeRate the {@code currencyExchangeRate} to set
         * @return a reference to this Builder
         */
        public AccountRecordExtendedDetailsBuilder withCurrencyExchangeRate(Double currencyExchangeRate) {
            this.currencyExchangeRate = currencyExchangeRate;
            return this;
        }

        /**
         * Returns a {@code AccountRecordExtendedDetails} built from the parameters previously set.
         *
         * @return a {@code AccountRecordExtendedDetails} built with parameters of this {@code AccountRecordExtendedDetails.Builder}
         */
        public AccountRecordExtendedDetails build() {
            return
                new AccountRecordExtendedDetails(
                    accountRecordId,
                    accountRecordDate,
                    recordedByUserUUID,
                    incomeOrExpenseItemUUID,
                    accountRecordText,
                    accountRecordValue,
                    lastModifiedDate,
                    lastModifiedByUserUUID,
                    currencyExchangeRate,
                    purchasePrice
                );
        }
    }
}
