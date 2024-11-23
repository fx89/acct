package com.desolatetimelines.acct.usermanagement.ws.model;

import static java.util.Objects.requireNonNull;

/**
 * Information that identifies a group of users
 *
 * @param groupUUID the unique identifier of the group, a V4 UUID
 * @param groupName the human-readable name of the group
 */
public record AcctGroupInfo(
    String groupUUID,
    String groupName
) {

    /**
     * @return a {@link AcctGroupInfoBuilder builder} for this data type
     */
    public static AcctGroupInfoBuilder builder() {
        return new AcctGroupInfoBuilder();
    }

    /**
     * {@code AcctGroupInfo} builder static inner class.
     */
    public static final class AcctGroupInfoBuilder {
        private String groupUUID;
        private String groupName;

        private AcctGroupInfoBuilder() {
        }

        /**
         * Sets the {@code groupUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupUUID the {@code groupUUID} to set
         * @return a reference to this Builder
         */
        public AcctGroupInfoBuilder withGroupUUID(String groupUUID) {
            this.groupUUID = groupUUID;
            return this;
        }

        /**
         * Sets the {@code groupName} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupName the {@code groupName} to set
         * @return a reference to this Builder
         */
        public AcctGroupInfoBuilder withGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        /**
         * Returns a {@link AcctGroupInfo} built from the parameters previously set.
         */
        public AcctGroupInfo build() {
            requireNonNull(groupUUID, "Group UUID not provided");
            requireNonNull(groupName, "Group name not provided");

            return new AcctGroupInfo(groupUUID, groupName);
        }
    }
}
