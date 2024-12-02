package com.desolatetimelines.acct.security.ws.endpoint.model;

import java.util.Collection;

/**
 * Groups user-owned workspace UUIDs by the ownership type
 *
 * @param userWorkspaces   a collection of the UUIDs of the workspaces owned directly by the user
 * @param groupWorkspaces  a collection of the UUIDs of the workspaces owned by groups that the user is part of
 * @param publicWorkspaces a collection of the UUIDs of the public workspaces
 */
public record OwnedWorkspacesGroup(
    Collection<String> userWorkspaces,
    Collection<String> groupWorkspaces,
    Collection<String> publicWorkspaces
) {

    public static OwnedWorkspacesGroupBuilder builder() {
        return new OwnedWorkspacesGroupBuilder();
    }

    public static final class OwnedWorkspacesGroupBuilder {
        private Collection<String> userWorkspaces;
        private Collection<String> groupWorkspaces;
        private Collection<String> publicWorkspaces;

        private OwnedWorkspacesGroupBuilder() {
        }

        /**
         * Sets the {@code userWorkspaces} and returns a reference to this Builder enabling method chaining.
         *
         * @param userWorkspaces the {@code userWorkspaces} to set
         * @return a reference to this Builder
         */
        public OwnedWorkspacesGroupBuilder withUserWorkspaces(Collection<String> userWorkspaces) {
            this.userWorkspaces = userWorkspaces;
            return this;
        }

        /**
         * Sets the {@code groupWorkspaces} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupWorkspaces the {@code groupWorkspaces} to set
         * @return a reference to this Builder
         */
        public OwnedWorkspacesGroupBuilder withGroupWorkspaces(Collection<String> groupWorkspaces) {
            this.groupWorkspaces = groupWorkspaces;
            return this;
        }

        /**
         * Sets the {@code publicWorkspaces} and returns a reference to this Builder enabling method chaining.
         *
         * @param publicWorkspaces the {@code publicWorkspaces} to set
         * @return a reference to this Builder
         */
        public OwnedWorkspacesGroupBuilder withPublicWorkspaces(Collection<String> publicWorkspaces) {
            this.publicWorkspaces = publicWorkspaces;
            return this;
        }

        /**
         * Returns a {@code OwnedWorkspacesGroup} built from the parameters previously set.
         *
         * @return a {@code OwnedWorkspacesGroup} built with parameters of this builder
         */
        public OwnedWorkspacesGroup build() {
            return new OwnedWorkspacesGroup(userWorkspaces, groupWorkspaces, publicWorkspaces);
        }
    }
}
