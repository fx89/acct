package com.desolatetimelines.acct.workspace.model;

import java.util.Collection;

/**
 * Data record which groups workspaces into multiple collections, based on ownership
 * and accessibility to a given user
 *
 * @param userWorkspaces   Workspaces that are directly accessible to the user
 * @param groupWorkspaces  Workspaces that are accessible to the user via a group that the user is part of
 * @param publicWorkspaces Workspaces that are accessible to any user or entity
 */
public record AcctWorkspacesByOwnership(
    Collection<AcctWorkspace> userWorkspaces,
    Collection<AcctWorkspace> groupWorkspaces,
    Collection<AcctWorkspace> publicWorkspaces
) {

    public static WorkspaceDetailsByOwnershipBuilder builder() {
        return new WorkspaceDetailsByOwnershipBuilder();
    }

    /**
     * {@code WorkspaceDetailsByOwnership} builder static inner class.
     */
    public static final class WorkspaceDetailsByOwnershipBuilder {
        private Collection<AcctWorkspace> userWorkspaces;
        private Collection<AcctWorkspace> groupWorkspaces;
        private Collection<AcctWorkspace> publicWorkspaces;

        private WorkspaceDetailsByOwnershipBuilder() {
        }

        /**
         * Sets the {@code userWorkspaces} and returns a reference to this Builder enabling method chaining.
         *
         * @param useWorkspaces the {@code userWorkspaces} to set
         * @return a reference to this Builder
         */
        public WorkspaceDetailsByOwnershipBuilder withUserWorkspaces(Collection<AcctWorkspace> useWorkspaces) {
            this.userWorkspaces = useWorkspaces;
            return this;
        }

        /**
         * Sets the {@code groupWorkspaces} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupWorkspaces the {@code groupWorkspaces} to set
         * @return a reference to this Builder
         */
        public WorkspaceDetailsByOwnershipBuilder withGroupWorkspaces(Collection<AcctWorkspace> groupWorkspaces) {
            this.groupWorkspaces = groupWorkspaces;
            return this;
        }

        /**
         * Sets the {@code publicWorkspaces} and returns a reference to this Builder enabling method chaining.
         *
         * @param publicWorkspaces the {@code publicWorkspaces} to set
         * @return a reference to this Builder
         */
        public WorkspaceDetailsByOwnershipBuilder withPublicWorkspaces(Collection<AcctWorkspace> publicWorkspaces) {
            this.publicWorkspaces = publicWorkspaces;
            return this;
        }

        /**
         * Returns a {@code WorkspaceDetailsByOwnership} built from the parameters previously set.
         *
         * @return a {@code WorkspaceDetailsByOwnership} built with parameters of this {@code WorkspaceDetailsByOwnership.Builder}
         */
        public AcctWorkspacesByOwnership build() {
            return new AcctWorkspacesByOwnership(userWorkspaces, groupWorkspaces, publicWorkspaces);
        }
    }
}
