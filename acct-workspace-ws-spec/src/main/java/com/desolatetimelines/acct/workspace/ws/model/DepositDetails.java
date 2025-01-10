package com.desolatetimelines.acct.workspace.ws.model;

import java.time.Instant;

/**
 * Describes the readable properties of a deposit in the ACCT ecosystem
 *
 * @param depositUUID             the unique identifier of the deposit in the ACCT ecosystem
 * @param sourceAccountUUID       the UUID of the account from where the money is transferred to the new deposit
 * @param depositAccountNumber    the account number of the newly created deposit
 * @param currencyUUID            the currency in which the deposit was opened
 * @param bankUUID                the UUID of the bank at which the deposit was opened
 * @param depositValue            the deposited amount
 * @param depositInterestPercent  the interest percentage
 * @param depositStartDate        the date when the deposit was created
 * @param depositProjectedEndDate the date when the deposit is expected to capitalize
 */
public record DepositDetails(
    String depositUUID,
    String sourceAccountUUID,
    String depositAccountNumber,
    String currencyUUID,
    String bankUUID,
    Double depositValue,
    Double depositInterestPercent,
    Instant depositStartDate,
    Instant depositProjectedEndDate
) {
    public static DepositDetailsBuilder builder() {
        return new DepositDetailsBuilder();
    }

    /**
     * {@code DepositDetails} builder static inner class.
     */
    public static final class DepositDetailsBuilder {
        private String depositUUID;
        private String sourceAccountUUID;
        private String depositAccountNumber;
        private String currencyUUID;
        private String bankUUID;
        private Double depositValue;
        private Double depositInterestPercent;
        private Instant depositStartDate;
        private Instant depositProjectedEndDate;

        private DepositDetailsBuilder() {
        }

        /**
         * Sets the {@code depositUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param depositUUID the {@code depositUUID} to set
         * @return a reference to this Builder
         */
        public DepositDetailsBuilder withDepositUUID(String depositUUID) {
            this.depositUUID = depositUUID;
            return this;
        }

        /**
         * Sets the {@code sourceAccountUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param sourceAccountUUID the {@code sourceAccountUUID} to set
         * @return a reference to this Builder
         */
        public DepositDetailsBuilder withSourceAccountUUID(String sourceAccountUUID) {
            this.sourceAccountUUID = sourceAccountUUID;
            return this;
        }

        /**
         * Sets the {@code depositAccountNumber} and returns a reference to this Builder enabling method chaining.
         *
         * @param depositAccountNumber the {@code depositAccountNumber} to set
         * @return a reference to this Builder
         */
        public DepositDetailsBuilder withDepositAccountNumber(String depositAccountNumber) {
            this.depositAccountNumber = depositAccountNumber;
            return this;
        }

        /**
         * Sets the {@code currencyUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param currencyUUID the {@code currencyUUID} to set
         * @return a reference to this Builder
         */
        public DepositDetailsBuilder withCurrencyUUID(String currencyUUID) {
            this.currencyUUID = currencyUUID;
            return this;
        }

        /**
         * Sets the {@code bankUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param bankUUID the {@code bankUUID} to set
         * @return a reference to this Builder
         */
        public DepositDetailsBuilder withBankUUID(String bankUUID) {
            this.bankUUID = bankUUID;
            return this;
        }

        /**
         * Sets the {@code depositValue} and returns a reference to this Builder enabling method chaining.
         *
         * @param depositValue the {@code depositValue} to set
         * @return a reference to this Builder
         */
        public DepositDetailsBuilder withDepositValue(Double depositValue) {
            this.depositValue = depositValue;
            return this;
        }

        /**
         * Sets the {@code depositInterestPercent} and returns a reference to this Builder enabling method chaining.
         *
         * @param depositInterestPercent the {@code depositInterestPercent} to set
         * @return a reference to this Builder
         */
        public DepositDetailsBuilder withDepositInterestPercent(Double depositInterestPercent) {
            this.depositInterestPercent = depositInterestPercent;
            return this;
        }

        /**
         * Sets the {@code depositStartDate} and returns a reference to this Builder enabling method chaining.
         *
         * @param depositStartDate the {@code depositStartDate} to set
         * @return a reference to this Builder
         */
        public DepositDetailsBuilder withDepositStartDate(Instant depositStartDate) {
            this.depositStartDate = depositStartDate;
            return this;
        }

        /**
         * Sets the {@code depositProjectedEndDate} and returns a reference to this Builder enabling method chaining.
         *
         * @param depositProjectedEndDate the {@code depositProjectedEndDate} to set
         * @return a reference to this Builder
         */
        public DepositDetailsBuilder withDepositProjectedEndDate(Instant depositProjectedEndDate) {
            this.depositProjectedEndDate = depositProjectedEndDate;
            return this;
        }

        /**
         * Returns a {@code DepositDetails} built from the parameters previously set.
         *
         * @return a {@code DepositDetails} built with parameters of this {@code DepositDetails.Builder}
         */
        public DepositDetails build() {
            return new DepositDetails(
                depositUUID,
                sourceAccountUUID,
                depositAccountNumber,
                currencyUUID,
                bankUUID,
                depositValue,
                depositInterestPercent,
                depositStartDate,
                depositProjectedEndDate
            );
        }
    }
}
