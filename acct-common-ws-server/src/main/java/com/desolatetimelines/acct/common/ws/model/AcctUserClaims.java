package com.desolatetimelines.acct.common.ws.model;

import java.util.Collection;

public record AcctUserClaims(
    String userUUID,
    Collection<String> privilegeNames
) {
    public static Builder builder() {
        return new Builder();
    }

    /**
     * {@code AcctUserClaims} builder static inner class.
     */
    public static final class Builder {
        private String userUUID;
        private Collection<String> privilegeNames;

        private Builder() {
        }

        /**
         * Sets the {@code userUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param userUUID the {@code userUUID} to set
         * @return a reference to this Builder
         */
        public Builder withUserUUID(String userUUID) {
            this.userUUID = userUUID;
            return this;
        }

        /**
         * Sets the {@code privilegeNames} and returns a reference to this Builder enabling method chaining.
         *
         * @param privilegeNames the {@code privilegeNames} to set
         * @return a reference to this Builder
         */
        public Builder withPrivilegeNames(Collection<String> privilegeNames) {
            this.privilegeNames = privilegeNames;
            return this;
        }

        /**
         * Returns a {@code AcctUserClaims} built from the parameters previously set.
         *
         * @return a {@code AcctUserClaims} built with parameters of this {@code AcctUserClaims.Builder}
         */
        public AcctUserClaims build() {
            return new AcctUserClaims(userUUID, privilegeNames);
        }
    }
}
