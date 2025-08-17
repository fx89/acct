package com.desolatetimelines.acct.workspace.ws.model;

import java.time.Instant;

/**
 * Container for all the properties that can be returned by a REST API for a given account record
 *
 * @param accountRecordId         Unique identifier of the account record within the account
 * @param accountRecordDate       The date at which the record was created
 * @param recordedByUserUUID      The UUID of the user who crated the record
 * @param incomeOrExpenseItemUUID The UUID of the income or expense item that the account record relates to
 * @param accountRecordText       The human-readable description of the income or expense
 * @param accountRecordValue      The income or expense value
 * @param lastModifiedDate        The date at which the record was last updated
 * @param lastModifiedByUserUUID  The UUID of the user who made the latest update to the record
 * @param exchangeRate            Optional exchange rate applied when buying the foreign currency, available only if
 *                                the record describes a foreign currency purchase
 * @param purchasePrice           Optional purchase price of foreign currencies, calculated as
 *                                {@code accountRecordValue * exchangeRate}
 * @param sellRate                The optional exchange rate at which the currency was sold
 * @param buyBackRate             The optional exchange rate at which the currency was bought back
 */
public record AccountRecordEnhancedDetails(
    Long accountRecordId,
    Instant accountRecordDate,
    String recordedByUserUUID,
    String incomeOrExpenseItemUUID,
    String accountRecordText,
    Double accountRecordValue,
    Instant lastModifiedDate,
    String lastModifiedByUserUUID,
    Double exchangeRate,
    Double purchasePrice,
    Double sellRate,
    Double buyBackRate
) {
    public static AccountRecordEnhancedDetailsBuilder builder() {
        return new AccountRecordEnhancedDetailsBuilder();
    }

    /**
     * {@code AccountRecordEnhancedDetails} builder static inner class.
     */
    public static final class AccountRecordEnhancedDetailsBuilder {
        private Long accountRecordId;
        private Instant accountRecordDate;
        private String recordedByUserUUID;
        private String incomeOrExpenseItemUUID;
        private String accountRecordText;
        private Double accountRecordValue;
        private Instant lastModifiedDate;
        private String lastModifiedByUserUUID;
        private Double exchangeRate;
        private Double purchasePrice;
        private Double sellRate;
        private Double buyBackRate;

        private AccountRecordEnhancedDetailsBuilder() {
        }

        /**
         * Sets the {@code accountRecordId} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountRecordId the {@code accountRecordId} to set
         * @return a reference to this Builder
         */
        public AccountRecordEnhancedDetailsBuilder withAccountRecordId(Long accountRecordId) {
            this.accountRecordId = accountRecordId;
            return this;
        }

        /**
         * Sets the {@code accountRecordDate} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountRecordDate the {@code accountRecordDate} to set
         * @return a reference to this Builder
         */
        public AccountRecordEnhancedDetailsBuilder withAccountRecordDate(Instant accountRecordDate) {
            this.accountRecordDate = accountRecordDate;
            return this;
        }

        /**
         * Sets the {@code recordedByUserUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param recordedByUserUUID the {@code recordedByUserUUID} to set
         * @return a reference to this Builder
         */
        public AccountRecordEnhancedDetailsBuilder withRecordedByUserUUID(String recordedByUserUUID) {
            this.recordedByUserUUID = recordedByUserUUID;
            return this;
        }

        /**
         * Sets the {@code incomeOrExpenseItemUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param incomeOrExpenseItemUUID the {@code incomeOrExpenseItemUUID} to set
         * @return a reference to this Builder
         */
        public AccountRecordEnhancedDetailsBuilder withIncomeOrExpenseItemUUID(String incomeOrExpenseItemUUID) {
            this.incomeOrExpenseItemUUID = incomeOrExpenseItemUUID;
            return this;
        }

        /**
         * Sets the {@code accountRecordText} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountRecordText the {@code accountRecordText} to set
         * @return a reference to this Builder
         */
        public AccountRecordEnhancedDetailsBuilder withAccountRecordText(String accountRecordText) {
            this.accountRecordText = accountRecordText;
            return this;
        }

        /**
         * Sets the {@code accountRecordValue} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountRecordValue the {@code accountRecordValue} to set
         * @return a reference to this Builder
         */
        public AccountRecordEnhancedDetailsBuilder withAccountRecordValue(Double accountRecordValue) {
            this.accountRecordValue = accountRecordValue;
            return this;
        }

        /**
         * Sets the {@code lastModifiedDate} and returns a reference to this Builder enabling method chaining.
         *
         * @param lastModifiedDate the {@code lastModifiedDate} to set
         * @return a reference to this Builder
         */
        public AccountRecordEnhancedDetailsBuilder withLastModifiedDate(Instant lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
            return this;
        }

        /**
         * Sets the {@code lastModifiedByUserUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param lastModifiedByUserUUID the {@code lastModifiedByUserUUID} to set
         * @return a reference to this Builder
         */
        public AccountRecordEnhancedDetailsBuilder withLastModifiedByUserUUID(String lastModifiedByUserUUID) {
            this.lastModifiedByUserUUID = lastModifiedByUserUUID;
            return this;
        }

        /**
         * Sets the {@code exchangeRate} and returns a reference to this Builder enabling method chaining.
         *
         * @param exchangeRate the {@code exchangeRate} to set
         * @return a reference to this Builder
         */
        public AccountRecordEnhancedDetailsBuilder withExchangeRate(Double exchangeRate) {
            this.exchangeRate = exchangeRate;
            return this;
        }

        /**
         * Sets the {@code purchasePrice} and returns a reference to this Builder enabling method chaining.
         *
         * @param purchasePrice the {@code purchasePrice} to set
         * @return a reference to this Builder
         */
        public AccountRecordEnhancedDetailsBuilder withPurchasePrice(Double purchasePrice) {
            this.purchasePrice = purchasePrice;
            return this;
        }

        /**
         * Sets the {@code sellRate} and returns a reference to this Builder enabling method chaining.
         *
         * @param sellRate the {@code sellRate} to set
         * @return a reference to this Builder
         */
        public AccountRecordEnhancedDetailsBuilder withSellRate(Double sellRate) {
            this.sellRate = sellRate;
            return this;
        }

        /**
         * Sets the {@code buyBackRate} and returns a reference to this Builder enabling method chaining.
         *
         * @param buyBackRate the {@code buyBackRate} to set
         * @return a reference to this Builder
         */
        public AccountRecordEnhancedDetailsBuilder withBuyBackRate(Double buyBackRate) {
            this.buyBackRate = buyBackRate;
            return this;
        }

        /**
         * Returns a {@code AccountRecordEnhancedDetails} built from the parameters previously set.
         *
         * @return a {@code AccountRecordEnhancedDetails} built with parameters of this {@code AccountRecordEnhancedDetails.Builder}
         */
        public AccountRecordEnhancedDetails build() {
            return new AccountRecordEnhancedDetails(
                accountRecordId,
                accountRecordDate,
                recordedByUserUUID,
                incomeOrExpenseItemUUID,
                accountRecordText,
                accountRecordValue,
                lastModifiedDate,
                lastModifiedByUserUUID,
                exchangeRate,
                purchasePrice,
                sellRate,
                buyBackRate
            );
        }
    }
}
