package com.desolatetimelines.acct.workspace.model;

/**
 * DTO that can be used for transferring {@link AcctAccount account}-related properties
 * from the presentation layer to the services layer
 *
 * @param accountUUID     The UUID that uniquely identifies the account in the ACCT ecosystem
 * @param accountName     The human-readable name of the account
 * @param accountIconUUID The UUID of the icon that represents the account
 * @param accountNumber   The account number, as it is given by th bank
 * @param currencyUUID    The UUID of the currency that the account uses
 * @param bankUUID        The UUID of the bank where the account is registered
 */
public record AccountDetails(
    String accountUUID,
    String accountName,
    String accountIconUUID,
    String accountNumber,
    String currencyUUID,
    String bankUUID
) {
    public static AccountDetailsBuilder builder() {
        return new AccountDetailsBuilder();
    }

    /**
     * {@code AccountDetails} builder static inner class.
     */
    public static final class AccountDetailsBuilder {
        private String accountUUID;
        private String accountName;
        private String accountIconUUID;
        private String accountNumber;
        private String currencyUUID;
        private String bankUUID;

        private AccountDetailsBuilder() {
        }

        /**
         * Sets the {@code accountUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountUUID the {@code accountUUID} to set
         * @return a reference to this Builder
         */
        public AccountDetailsBuilder withAccountUUID(String accountUUID) {
            this.accountUUID = accountUUID;
            return this;
        }

        /**
         * Sets the {@code accountName} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountName the {@code accountName} to set
         * @return a reference to this Builder
         */
        public AccountDetailsBuilder withAccountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        /**
         * Sets the {@code accountIconUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountIconUUID the {@code accountIconUUID} to set
         * @return a reference to this Builder
         */
        public AccountDetailsBuilder withAccountIconUUID(String accountIconUUID) {
            this.accountIconUUID = accountIconUUID;
            return this;
        }

        /**
         * Sets the {@code accountNumber} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountNumber the {@code accountNumber} to set
         * @return a reference to this Builder
         */
        public AccountDetailsBuilder withAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        /**
         * Sets the {@code currencyUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param currencyUUID the {@code currencyUUID} to set
         * @return a reference to this Builder
         */
        public AccountDetailsBuilder withCurrencyUUID(String currencyUUID) {
            this.currencyUUID = currencyUUID;
            return this;
        }

        /**
         * Sets the {@code bankUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param bankUUID the {@code bankUUID} to set
         * @return a reference to this Builder
         */
        public AccountDetailsBuilder withBankUUID(String bankUUID) {
            this.bankUUID = bankUUID;
            return this;
        }

        /**
         * Returns a {@code AccountDetails} built from the parameters previously set.
         *
         * @return a {@code AccountDetails} built with parameters of this {@code AccountDetails.Builder}
         */
        public AccountDetails build() {
            return
                new AccountDetails(
                    accountUUID,
                    accountName,
                    accountIconUUID,
                    accountNumber,
                    currencyUUID,
                    bankUUID
                );
        }
    }
}
