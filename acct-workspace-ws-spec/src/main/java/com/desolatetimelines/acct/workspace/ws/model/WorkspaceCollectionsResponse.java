package com.desolatetimelines.acct.workspace.ws.model;

import java.util.Collection;

/**
 * A response object that contains the details of all workspaces accessible to a given owner,
 * grouped into 3 categories: user workspaces, group workspaces and public workspaces.
 *
 * @param userWorkspaces   A collection of workspaces directly-accessible to the owner
 * @param groupWorkspace   A collection of workspaces owned by a group that the owner is part of
 * @param publicWorkspaces A collection of workspaces that are publicly accessible
 */
public record WorkspaceCollectionsResponse(

    Collection<WorkspaceDetails> userWorkspaces,

    Collection<WorkspaceDetails> groupWorkspace,

    Collection<WorkspaceDetails> publicWorkspaces

) {

    public static WorkspaceCollectionsResponseBuilder builder() {
        return new WorkspaceCollectionsResponseBuilder();
    }

    /**
     * {@code WorkspaceCollectionsResponse} builder static inner class.
     */
    public static final class WorkspaceCollectionsResponseBuilder {
        private Collection<WorkspaceDetails> userWorkspaces;
        private Collection<WorkspaceDetails> groupWorkspace;
        private Collection<WorkspaceDetails> publicWorkspaces;

        private WorkspaceCollectionsResponseBuilder() {
        }

        /**
         * Sets the {@code userWorkspaces} and returns a reference to this Builder enabling method chaining.
         *
         * @param userWorkspaces the {@code userWorkspaces} to set
         * @return a reference to this Builder
         */
        public WorkspaceCollectionsResponseBuilder withUserWorkspaces(Collection<WorkspaceDetails> userWorkspaces) {
            this.userWorkspaces = userWorkspaces;
            return this;
        }

        /**
         * Sets the {@code groupWorkspace} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupWorkspace the {@code groupWorkspace} to set
         * @return a reference to this Builder
         */
        public WorkspaceCollectionsResponseBuilder withGroupWorkspace(Collection<WorkspaceDetails> groupWorkspace) {
            this.groupWorkspace = groupWorkspace;
            return this;
        }

        /**
         * Sets the {@code publicWorkspaces} and returns a reference to this Builder enabling method chaining.
         *
         * @param publicWorkspaces the {@code publicWorkspaces} to set
         * @return a reference to this Builder
         */
        public WorkspaceCollectionsResponseBuilder withPublicWorkspaces(Collection<WorkspaceDetails> publicWorkspaces) {
            this.publicWorkspaces = publicWorkspaces;
            return this;
        }

        /**
         * Returns a {@code WorkspaceCollectionsResponse} built from the parameters previously set.
         *
         * @return a {@code WorkspaceCollectionsResponse} built with parameters of this {@code WorkspaceCollectionsResponse.Builder}
         */
        public WorkspaceCollectionsResponse build() {
            return new WorkspaceCollectionsResponse(userWorkspaces, groupWorkspace, publicWorkspaces);
        }
    }
}
