package com.desolatetimelines.acct.usermanagement.ws.model;

/**
 * Identifies and provides the details of a users group
 *
 * @param groupUUID        the unique identifier of the group, a V4 UUID
 * @param groupName        the human-readable name of the group
 * @param groupDescription a text that states the purpose of the group
 * @param groupIconUUID    the UUID of the icon chosen for the group
 */
public record AcctGroupDetails(
    String groupUUID,
    String groupName,
    String groupDescription,
    String groupIconUUID
) {

    public static AcctGroupDetailsBuilder builder() {
        return new AcctGroupDetailsBuilder();
    }

    /**
     * {@code AcctGroupDetails} builder static inner class.
     */
    public static final class AcctGroupDetailsBuilder {
        private String groupUUID;
        private String groupName;
        private String groupDescription;
        private String groupIconUUID;

        private AcctGroupDetailsBuilder() {
        }

        /**
         * Sets the {@code groupUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupUUID the {@code groupUUID} to set
         * @return a reference to this Builder
         */
        public AcctGroupDetailsBuilder withGroupUUID(String groupUUID) {
            this.groupUUID = groupUUID;
            return this;
        }

        /**
         * Sets the {@code groupName} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupName the {@code groupName} to set
         * @return a reference to this Builder
         */
        public AcctGroupDetailsBuilder withGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        /**
         * Sets the {@code groupDescription} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupDescription the {@code groupDescription} to set
         * @return a reference to this Builder
         */
        public AcctGroupDetailsBuilder withGroupDescription(String groupDescription) {
            this.groupDescription = groupDescription;
            return this;
        }

        /**
         * Sets the {@code groupIconUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupIconUUID the {@code groupIconUUID} to set
         * @return a reference to this Builder
         */
        public AcctGroupDetailsBuilder withGroupIconUUID(String groupIconUUID) {
            this.groupIconUUID = groupIconUUID;
            return this;
        }

        /**
         * Returns a {@code AcctGroupDetails} built from the parameters previously set.
         *
         * @return a {@code AcctGroupDetails} built with parameters of this {@code AcctGroupDetails.Builder}
         */
        public AcctGroupDetails build() {
            return new AcctGroupDetails(groupUUID, groupName, groupDescription, groupIconUUID);
        }
    }
}
