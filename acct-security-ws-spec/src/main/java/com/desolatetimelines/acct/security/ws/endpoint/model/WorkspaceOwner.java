package com.desolatetimelines.acct.security.ws.endpoint.model;

/**
 * Defines a workspace ownership record
 *
 * @param ownerType     the type of owner
 * @param ownerUUID     the UUID of the owner
 * @param workspaceUUID the UUID of the workspace
 */
public record WorkspaceOwner(
    OwnerType ownerType,
    String ownerUUID,
    String workspaceUUID
) {

    public static WorkspaceOwnerBuilder builder() {
        return new WorkspaceOwnerBuilder();
    }

    /**
     * {@code WorkspaceOwner} builder static inner class.
     */
    public static final class WorkspaceOwnerBuilder {
        private OwnerType ownerType;
        private String ownerUUID;
        private String workspaceUUID;

        private WorkspaceOwnerBuilder() {
        }

        /**
         * Sets the {@code ownerType} and returns a reference to this Builder enabling method chaining.
         *
         * @param ownerType the {@code ownerType} to set
         * @return a reference to this Builder
         */
        public WorkspaceOwnerBuilder withOwnerType(OwnerType ownerType) {
            this.ownerType = ownerType;
            return this;
        }

        /**
         * Sets the {@code ownerUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param ownerUUID the {@code ownerUUID} to set
         * @return a reference to this Builder
         */
        public WorkspaceOwnerBuilder withOwnerUUID(String ownerUUID) {
            this.ownerUUID = ownerUUID;
            return this;
        }

        /**
         * Sets the {@code workspaceUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param workspaceUUID the {@code workspaceUUID} to set
         * @return a reference to this Builder
         */
        public WorkspaceOwnerBuilder withWorkspaceUUID(String workspaceUUID) {
            this.workspaceUUID = workspaceUUID;
            return this;
        }

        /**
         * Returns a {@code WorkspaceOwner} built from the parameters previously set.
         *
         * @return a {@code WorkspaceOwner} built with parameters of this {@code WorkspaceOwner.Builder}
         */
        public WorkspaceOwner build() {
            return new WorkspaceOwner(ownerType, ownerUUID, workspaceUUID);
        }
    }
}
