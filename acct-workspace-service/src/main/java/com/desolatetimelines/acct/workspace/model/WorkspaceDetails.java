package com.desolatetimelines.acct.workspace.model;

/**
 * DTO containing workspace properties for transfer between the presentation layer and services layer
 *
 * @param workspaceUUID        The unique identifier of the workspace across the ACCT ecosystem
 * @param workspaceName        The human-readable name of the workspace
 * @param workspaceDescription The human-readable description of the workspace
 * @param workspaceIconUUID    The UUID of the icon used when displaying the workspace
 * @param defaultCurrencyUUID  The UUID of the workspace' default currency
 */
public record WorkspaceDetails(
    String workspaceUUID,
    String workspaceName,
    String workspaceDescription,
    String workspaceIconUUID,
    String defaultCurrencyUUID
) {

    public static WorkspaceDetailsBuilder builder() {
        return new WorkspaceDetailsBuilder();
    }

    /**
     * {@code WorkspaceDetails} builder static inner class.
     */
    public static final class WorkspaceDetailsBuilder {
        private String workspaceUUID;
        private String workspaceName;
        private String workspaceDescription;
        private String workspaceIconUUID;
        private String defaultCurrencyUUID;

        private WorkspaceDetailsBuilder() {
        }

        /**
         * Sets the {@code workspaceUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param workspaceUUID the {@code workspaceUUID} to set
         * @return a reference to this Builder
         */
        public WorkspaceDetailsBuilder withWorkspaceUUID(String workspaceUUID) {
            this.workspaceUUID = workspaceUUID;
            return this;
        }

        /**
         * Sets the {@code workspaceName} and returns a reference to this Builder enabling method chaining.
         *
         * @param workspaceName the {@code workspaceName} to set
         * @return a reference to this Builder
         */
        public WorkspaceDetailsBuilder withWorkspaceName(String workspaceName) {
            this.workspaceName = workspaceName;
            return this;
        }

        /**
         * Sets the {@code workspaceDescription} and returns a reference to this Builder enabling method chaining.
         *
         * @param workspaceDescription the {@code workspaceDescription} to set
         * @return a reference to this Builder
         */
        public WorkspaceDetailsBuilder withWorkspaceDescription(String workspaceDescription) {
            this.workspaceDescription = workspaceDescription;
            return this;
        }

        /**
         * Sets the {@code workspaceIconUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param workspaceIconUUID the {@code workspaceIconUUID} to set
         * @return a reference to this Builder
         */
        public WorkspaceDetailsBuilder withWorkspaceIconUUID(String workspaceIconUUID) {
            this.workspaceIconUUID = workspaceIconUUID;
            return this;
        }

        /**
         * Sets the {@code defaultCurrencyUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param defaultCurrencyUUID the {@code defaultCurrencyUUID} to set
         * @return a reference to this Builder
         */
        public WorkspaceDetailsBuilder withDefaultCurrencyUUID(String defaultCurrencyUUID) {
            this.defaultCurrencyUUID = defaultCurrencyUUID;
            return this;
        }

        /**
         * Returns a {@code WorkspaceDetails} built from the parameters previously set.
         *
         * @return a {@code WorkspaceDetails} built with parameters of this {@code WorkspaceDetails.Builder}
         */
        public WorkspaceDetails build() {
            return
                new WorkspaceDetails(
                    workspaceUUID,
                    workspaceName,
                    workspaceDescription,
                    workspaceIconUUID,
                    defaultCurrencyUUID
                );
        }
    }
}
