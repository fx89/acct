package com.desolatetimelines.acct.workspace.model;

import java.time.Instant;

/**
 * Groups the {@link AcctAccountRecord account record} properties that can be
 * modified directly by users.
 *
 * @param accountRecordId         An ID that uniquely identifies the account record within the account
 * @param incomeOrExpenseItemUUID The unique identifier of the income or expense item within the ACCT ecosystem
 * @param accountRecordText       The human-readable description of the income or expense record
 * @param accountRecordValue      The amount that's been received or expedited
 */
public record AccountRecordDetails(
    Long accountRecordId,
    String incomeOrExpenseItemUUID,
    String accountRecordText,
    Double accountRecordValue,
    Instant accountRecordDate
) {

    public static Builder builder() {
        return new Builder();
    }

    /**
     * {@code AccountRecordDetails} builder static inner class.
     */
    public static final class Builder {
        private Long accountRecordId;
        private String incomeOrExpenseItemUUID;
        private String accountRecordText;
        private Double accountRecordValue;
        private Instant accountRecordDate = Instant.now();

        private Builder() {
        }

        /**
         * Sets the {@code accountRecordId} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountRecordId the {@code accountRecordId} to set
         * @return a reference to this Builder
         */
        public Builder withAccountRecordId(Long accountRecordId) {
            this.accountRecordId = accountRecordId;
            return this;
        }

        /**
         * Sets the {@code incomeOrExpenseItemUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param incomeOrExpenseItemUUID the {@code incomeOrExpenseItemUUID} to set
         * @return a reference to this Builder
         */
        public Builder withIncomeOrExpenseItemUUID(String incomeOrExpenseItemUUID) {
            this.incomeOrExpenseItemUUID = incomeOrExpenseItemUUID;
            return this;
        }

        /**
         * Sets the {@code accountRecordText} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountRecordText the {@code accountRecordText} to set
         * @return a reference to this Builder
         */
        public Builder withAccountRecordText(String accountRecordText) {
            this.accountRecordText = accountRecordText;
            return this;
        }

        /**
         * Sets the {@code accountRecordValue} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountRecordValue the {@code accountRecordValue} to set
         * @return a reference to this Builder
         */
        public Builder withAccountRecordValue(Double accountRecordValue) {
            this.accountRecordValue = accountRecordValue;
            return this;
        }

        /**
         * Sets the {@code accountRecordDate} and returns a reference to this Builder enabling method chaining.
         *
         * @param accountRecordDate the {@code accountRecordDate} to set
         * @return a reference to this Builder
         */
        public Builder withAccountRecordDate(Instant accountRecordDate) {
            this.accountRecordDate = accountRecordDate;
            return this;
        }

        /**
         * Returns a {@code AccountRecordDetails} built from the parameters previously set.
         *
         * @return a {@code AccountRecordDetails} built with parameters of this {@code AccountRecordDetails.Builder}
         */
        public AccountRecordDetails build() {
            return new AccountRecordDetails(
                accountRecordId,
                incomeOrExpenseItemUUID,
                accountRecordText,
                accountRecordValue,
                accountRecordDate
            );
        }
    }
}
