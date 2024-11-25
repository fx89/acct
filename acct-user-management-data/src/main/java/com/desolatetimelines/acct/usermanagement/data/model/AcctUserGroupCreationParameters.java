package com.desolatetimelines.acct.usermanagement.data.model;

/**
 * Container for the parameters used in the creation of a users group
 *
 * @param groupUUID        unique identifier for the group
 * @param groupName        the name of the group
 * @param groupDescription the description of the group
 * @param groupIconUUID    the icon set for the group
 */
public record AcctUserGroupCreationParameters(
    String groupUUID,
    String groupName,
    String groupDescription,
    String groupIconUUID
) {

    public static AcctUserGroupCreationParametersBuilder builder() {
        return new AcctUserGroupCreationParametersBuilder();
    }

    /**
     * {@code AcctUserGroupCreationParameters} builder static inner class.
     */
    public static final class AcctUserGroupCreationParametersBuilder {
        private String groupUUID;
        private String groupName;
        private String groupDescription;
        private String groupIconUUID;

        private AcctUserGroupCreationParametersBuilder() {
        }

        /**
         * Sets the {@code groupUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupUUID the {@code groupUUID} to set
         * @return a reference to this Builder
         */
        public AcctUserGroupCreationParametersBuilder withGroupUUID(String groupUUID) {
            this.groupUUID = groupUUID;
            return this;
        }

        /**
         * Sets the {@code groupName} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupName the {@code groupName} to set
         * @return a reference to this Builder
         */
        public AcctUserGroupCreationParametersBuilder withGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        /**
         * Sets the {@code groupDescription} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupDescription the {@code groupDescription} to set
         * @return a reference to this Builder
         */
        public AcctUserGroupCreationParametersBuilder withGroupDescription(String groupDescription) {
            this.groupDescription = groupDescription;
            return this;
        }

        /**
         * Sets the {@code groupIconUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupIconUUID the {@code groupIconUUID} to set
         * @return a reference to this Builder
         */
        public AcctUserGroupCreationParametersBuilder withGroupIconUUID(String groupIconUUID) {
            this.groupIconUUID = groupIconUUID;
            return this;
        }

        /**
         * Returns a {@code AcctUserGroupCreationParameters} built from the parameters previously set.
         *
         * @return a {@code AcctUserGroupCreationParameters} built with parameters of this {@code AcctUserGroupCreationParameters.Builder}
         */
        public AcctUserGroupCreationParameters build() {
            return new AcctUserGroupCreationParameters(groupUUID, groupName, groupDescription, groupIconUUID);
        }
    }
}
