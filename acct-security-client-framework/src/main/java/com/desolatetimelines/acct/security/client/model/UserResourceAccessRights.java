package com.desolatetimelines.acct.security.client.model;

/**
 * Convenience type that holds the user's access rights to a given resource type.
 *
 * @param ownResources   Set to true if the user has access to resources owned by the user
 * @param groupResources Set to true if the user has access to resources owned by groups that the user is part of
 * @param anyResources   Set to true if the user has access to resources owned by any user or group
 */
public record UserResourceAccessRights(
    boolean ownResources,
    boolean groupResources,
    boolean anyResources
) {

    public static UserResourceAccessRightsBuilder builder() {
        return new UserResourceAccessRightsBuilder();
    }

    /**
     * {@code UserResourceAccess} builder static inner class.
     */
    public static final class UserResourceAccessRightsBuilder {
        private boolean ownResources;
        private boolean groupResources;
        private boolean anyResources;

        private UserResourceAccessRightsBuilder() {
        }

        /**
         * Sets the {@code ownResources} and returns a reference to this Builder enabling method chaining.
         *
         * @param ownResources the {@code ownResources} to set
         * @return a reference to this Builder
         */
        public UserResourceAccessRightsBuilder withOwnResources(boolean ownResources) {
            this.ownResources = ownResources;
            return this;
        }

        /**
         * Sets the {@code groupResources} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupResources the {@code groupResources} to set
         * @return a reference to this Builder
         */
        public UserResourceAccessRightsBuilder withGroupResources(boolean groupResources) {
            this.groupResources = groupResources;
            return this;
        }

        /**
         * Sets the {@code anyResources} and returns a reference to this Builder enabling method chaining.
         *
         * @param anyResources the {@code anyResources} to set
         * @return a reference to this Builder
         */
        public UserResourceAccessRightsBuilder withAnyResources(boolean anyResources) {
            this.anyResources = anyResources;
            return this;
        }

        /**
         * Returns a {@code UserResourceAccess} built from the parameters previously set.
         *
         * @return a {@code UserResourceAccess} built with parameters of this {@code UserResourceAccess.Builder}
         */
        public UserResourceAccessRights build() {
            return new UserResourceAccessRights(ownResources, groupResources, anyResources);
        }
    }
}
