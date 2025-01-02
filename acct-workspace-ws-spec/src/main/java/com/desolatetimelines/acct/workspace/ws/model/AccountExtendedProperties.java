package com.desolatetimelines.acct.workspace.ws.model;

/**
 * Describes the account properties returned by REST APIs
 */
public record AccountExtendedProperties(
    String accountUUID,
    String accountName,
    String accountIconUUID,
    String accountNumber,
    String currencyUUID,
    String bankUUID
) {
    public static AccountExtendedPropertiesBuilder builder() {
        return new AccountExtendedPropertiesBuilder();
    }

    /**
     * {@code AccountExtendedProperties} builder static inner class.
     */
    public static final class AccountExtendedPropertiesBuilder {
        private String accountUUID;
        private String accountName;
        private String accountIconUUID;
        private String accountNumber;
        private String currencyUUID;
        private String bankUUID;

        private AccountExtendedPropertiesBuilder() {
        }

        /**
         * Sets the {@code accountUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountUUID the {@code accountUUID} to set
         * @return a reference to this Builder
         */
        public AccountExtendedPropertiesBuilder withAccountUUID(String accountUUID) {
            this.accountUUID = accountUUID;
            return this;
        }

        /**
         * Sets the {@code accountName} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountName the {@code accountName} to set
         * @return a reference to this Builder
         */
        public AccountExtendedPropertiesBuilder withAccountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        /**
         * Sets the {@code accountIconUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountIconUUID the {@code accountIconUUID} to set
         * @return a reference to this Builder
         */
        public AccountExtendedPropertiesBuilder withAccountIconUUID(String accountIconUUID) {
            this.accountIconUUID = accountIconUUID;
            return this;
        }

        /**
         * Sets the {@code accountNumber} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountNumber the {@code accountNumber} to set
         * @return a reference to this Builder
         */
        public AccountExtendedPropertiesBuilder withAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        /**
         * Sets the {@code currencyUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param currencyUUID the {@code currencyUUID} to set
         * @return a reference to this Builder
         */
        public AccountExtendedPropertiesBuilder withCurrencyUUID(String currencyUUID) {
            this.currencyUUID = currencyUUID;
            return this;
        }

        /**
         * Sets the {@code bankUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param bankUUID the {@code bankUUID} to set
         * @return a reference to this Builder
         */
        public AccountExtendedPropertiesBuilder withBankUUID(String bankUUID) {
            this.bankUUID = bankUUID;
            return this;
        }

        /**
         * Returns a {@code AccountExtendedProperties} built from the parameters previously set.
         *
         * @return a {@code AccountExtendedProperties} built with parameters of this {@code AccountExtendedProperties.Builder}
         */
        public AccountExtendedProperties build() {
            return
                new AccountExtendedProperties(
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
