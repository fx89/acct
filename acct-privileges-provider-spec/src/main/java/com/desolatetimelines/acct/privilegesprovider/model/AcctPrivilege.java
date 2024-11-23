package com.desolatetimelines.acct.privilegesprovider.model;

import java.util.Objects;

/**
 * Defines a security privilege within the ACCT ecosystem
 *
 * @param privilegeId          A string that uniquely identifies the privilege
 * @param privilegeName        The huma-readable name of the privilege
 * @param privilegeDescription A text that describes the operations that this privilege provides access to
 */
public record AcctPrivilege(
    String privilegeId,
    String privilegeName,
    String privilegeDescription
) {

    public static AcctPrivilegeBuilder builder() {
        return new AcctPrivilegeBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcctPrivilege that = (AcctPrivilege) o;
        return Objects.equals(privilegeId, that.privilegeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privilegeId);
    }

    /**
     * {@code AcctPrivilege} builder static inner class.
     */
    public static final class AcctPrivilegeBuilder {
        private String privilegeId;
        private String privilegeName;
        private String privilegeDescription;

        private AcctPrivilegeBuilder() {
        }

        /**
         * Sets the {@code privilegeId} and returns a reference to this Builder enabling method chaining.
         *
         * @param privilegeId the {@code privilegeId} to set
         * @return a reference to this Builder
         */
        public AcctPrivilegeBuilder withPrivilegeId(String privilegeId) {
            this.privilegeId = privilegeId;
            return this;
        }

        /**
         * Sets the {@code privilegeName} and returns a reference to this Builder enabling method chaining.
         *
         * @param privilegeName the {@code privilegeName} to set
         * @return a reference to this Builder
         */
        public AcctPrivilegeBuilder withPrivilegeName(String privilegeName) {
            this.privilegeName = privilegeName;
            return this;
        }

        /**
         * Sets the {@code privilegeDescription} and returns a reference to this Builder enabling method chaining.
         *
         * @param privilegeDescription the {@code privilegeDescription} to set
         * @return a reference to this Builder
         */
        public AcctPrivilegeBuilder withPrivilegeDescription(String privilegeDescription) {
            this.privilegeDescription = privilegeDescription;
            return this;
        }

        /**
         * Returns a {@code AcctPrivilege} built from the parameters previously set.
         *
         * @return a {@code AcctPrivilege} built with parameters of this {@code AcctPrivilege.Builder}
         */
        public AcctPrivilege build() {
            return new AcctPrivilege(privilegeId, privilegeName, privilegeDescription);
        }
    }
}
