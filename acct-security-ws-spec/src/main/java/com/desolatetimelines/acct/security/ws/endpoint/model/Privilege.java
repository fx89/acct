package com.desolatetimelines.acct.security.ws.endpoint.model;

/**
 * Describes a privilege that can be assigned to a group
 *
 * @param privilegeId          Uniquely identifies the privilege across the ACCT ecosystem
 * @param privilegeName        Human-readable name of the privilege
 * @param privilegeDescription Describes the operations that the privilege provides access to
 */
public record Privilege(
    String privilegeId,
    String privilegeName,
    String privilegeDescription
) {
    public static PrivilegeBuilder builder() {
        return new PrivilegeBuilder();
    }

    /**
     * {@code Privilege} builder static inner class.
     */
    public static final class PrivilegeBuilder {
        private String privilegeId;
        private String privilegeName;
        private String privilegeDescription;

        private PrivilegeBuilder() {
        }

        /**
         * Sets the {@code privilegeId} and returns a reference to this Builder enabling method chaining.
         *
         * @param privilegeId the {@code privilegeId} to set
         * @return a reference to this Builder
         */
        public PrivilegeBuilder withPrivilegeId(String privilegeId) {
            this.privilegeId = privilegeId;
            return this;
        }

        /**
         * Sets the {@code privilegeName} and returns a reference to this Builder enabling method chaining.
         *
         * @param privilegeName the {@code privilegeName} to set
         * @return a reference to this Builder
         */
        public PrivilegeBuilder withPrivilegeName(String privilegeName) {
            this.privilegeName = privilegeName;
            return this;
        }

        /**
         * Sets the {@code privilegeDescription} and returns a reference to this Builder enabling method chaining.
         *
         * @param privilegeDescription the {@code privilegeDescription} to set
         * @return a reference to this Builder
         */
        public PrivilegeBuilder withPrivilegeDescription(String privilegeDescription) {
            this.privilegeDescription = privilegeDescription;
            return this;
        }

        /**
         * Returns a {@code Privilege} built from the parameters previously set.
         *
         * @return a {@code Privilege} built with parameters of this {@code Privilege.Builder}
         */
        public Privilege build() {
            return new Privilege(privilegeId, privilegeName, privilegeDescription);
        }
    }
}
