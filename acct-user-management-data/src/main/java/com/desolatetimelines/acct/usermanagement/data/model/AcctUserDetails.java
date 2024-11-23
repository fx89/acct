package com.desolatetimelines.acct.usermanagement.data.model;

import com.desolatetimelines.acct.usermanagement.model.AcctUser;
import com.desolatetimelines.acct.usermanagement.model.AcctUsersGroup;

import java.util.Set;

/**
 * Provides details about a given user account in the ACCT ecosystem
 *
 * @param userAccount the details of the user account
 * @param userGroups  the details of the groups that the user account is attached to
 */
public record AcctUserDetails(
    AcctUser userAccount,
    Set<AcctUsersGroup> userGroups
) {

    public static AcctUserDetailsBuilder builder() {
        return new AcctUserDetailsBuilder();
    }

    /**
     * {@code AcctUserDetails} builder static inner class.
     */
    public static final class AcctUserDetailsBuilder {
        private AcctUser userAccount;
        private Set<AcctUsersGroup> userGroups;

        private AcctUserDetailsBuilder() {
        }

        /**
         * Sets the {@code userAccount} and returns a reference to this Builder enabling method chaining.
         *
         * @param userAccount the {@code userAccount} to set
         * @return a reference to this Builder
         */
        public AcctUserDetailsBuilder withUserAccount(AcctUser userAccount) {
            this.userAccount = userAccount;
            return this;
        }

        /**
         * Sets the {@code userGroups} and returns a reference to this Builder enabling method chaining.
         *
         * @param userGroups the {@code userGroups} to set
         * @return a reference to this Builder
         */
        public AcctUserDetailsBuilder withUserGroups(Set<AcctUsersGroup> userGroups) {
            this.userGroups = userGroups;
            return this;
        }

        /**
         * Returns a {@code AcctUserDetails} built from the parameters previously set.
         *
         * @return a {@code AcctUserDetails} built with parameters of this {@code AcctUserDetails.Builder}
         */
        public AcctUserDetails build() {
            return new AcctUserDetails(userAccount, userGroups);
        }
    }
}
