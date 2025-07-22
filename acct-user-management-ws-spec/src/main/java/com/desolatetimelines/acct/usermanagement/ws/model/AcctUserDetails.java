package com.desolatetimelines.acct.usermanagement.ws.model;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * Details of a given user, including a set of groups that the user is part of
 *
 * @param userUUID              the unique identifier of the user, a V4 UUID
 * @param userLoginName         the login ID of the user
 * @param userEncryptedPassword the encrypted password of the user
 * @param userName              the human-readable name of the user
 * @param defaultWorkspaceUUID  the workspace where the user lands upon login
 * @param userIconUUID          the unique identifier of the user's icon, a V4 UUID
 * @param userGroups            a set of {@link AcctGroupInfo group info records} for the groups that the user is part of
 */
public record AcctUserDetails(
    String userUUID,
    String userLoginName,
    String userEncryptedPassword,
    String userName,
    String defaultWorkspaceUUID,
    String userIconUUID,
    Set<AcctGroupInfo> userGroups
) {
    public static AcctUserDetailsBuilder builder() {
        return new AcctUserDetailsBuilder();
    }

    /**
     * {@code GetCurrentUserResponse} builder static inner class.
     */
    public static final class AcctUserDetailsBuilder {
        private String userUUID;
        private String userLoginName;
        private String userEncryptedPassword;
        private String userName;
        private String defaultWorkspaceUUID;
        private String userIconUUID;
        private final Set<AcctGroupInfo> userGroups = new HashSet<>();

        private AcctUserDetailsBuilder() {
        }

        public AcctUserDetailsBuilder withTemplate(AcctUserDetails template) {
            // Update the properties
            this
                .withUserUUID(template.userUUID())
                .withUserLoginName(template.userLoginName())
                .withDefaultWorkspaceUUID(template.defaultWorkspaceUUID())
                .withUserName(template.userName())
                .withUserIconUUID(template.userIconUUID())
                .withUserEncryptedPassword(template.userEncryptedPassword());

            // Add the groups (if any)
            if (template.userGroups() != null) {
                template.userGroups().forEach(this::withUserGroup);
            }

            // Return a reference
            return this;
        }

        /**
         * Sets the {@code userUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param userUUID the {@code userUUID} to set
         * @return a reference to this Builder
         */
        public AcctUserDetailsBuilder withUserUUID(String userUUID) {
            this.userUUID = userUUID;
            return this;
        }

        /**
         * Sets the {@code userLoginName} and returns a reference to this Builder enabling method chaining.
         *
         * @param userLoginName the {@code userLoginName} to set
         * @return a reference to this Builder
         */
        public AcctUserDetailsBuilder withUserLoginName(String userLoginName) {
            this.userLoginName = userLoginName;
            return this;
        }

        /**
         * Sets the {@code userEncryptedPassword} and returns a reference to this Builder enabling method chaining.
         *
         * @param userEncryptedPassword the {@code userEncryptedPassword} to set
         * @return a reference to this Builder
         */
        public AcctUserDetailsBuilder withUserEncryptedPassword(String userEncryptedPassword) {
            this.userEncryptedPassword = userEncryptedPassword;
            return this;
        }

        /**
         * Sets the {@code userName} and returns a reference to this Builder enabling method chaining.
         *
         * @param userName the {@code userName} to set
         * @return a reference to this Builder
         */
        public AcctUserDetailsBuilder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        /**
         * Sets the {@code defaultWorkspaceUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param defaultWorkspaceUUID the {@code defaultWorkspaceUUID} to set
         * @return a reference to this Builder
         */
        public AcctUserDetailsBuilder withDefaultWorkspaceUUID(String defaultWorkspaceUUID) {
            this.defaultWorkspaceUUID = defaultWorkspaceUUID;
            return this;
        }

        /**
         * Sets the {@code userIconUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param userIconUUID the {@code userIconUUID} to set
         * @return a reference to this Builder
         */
        public AcctUserDetailsBuilder withUserIconUUID(String userIconUUID) {
            this.userIconUUID = userIconUUID;
            return this;
        }

        /**
         * Sets the {@code userGroups} and returns a reference to this Builder enabling method chaining.
         *
         * @param userGroup the {@code userGroups} to set
         * @return a reference to this Builder
         */
        public AcctUserDetailsBuilder withUserGroup(AcctGroupInfo userGroup) {
            requireNonNull(userGroup, "Null user group reference provided");
            this.userGroups.add(userGroup);
            return this;
        }

        /**
         * Returns a {@code GetCurrentUserResponse} built from the parameters previously set.
         */
        public AcctUserDetails build() {
            requireNonNull(userUUID, "User UUID not provided");
            requireNonNull(userLoginName, "User login name not provided");
            requireNonNull(userName, "User name not provided");

            return
                new AcctUserDetails(
                    userUUID,
                    userLoginName,
                    userEncryptedPassword,
                    userName,
                    defaultWorkspaceUUID,
                    userIconUUID,
                    userGroups
                );
        }
    }
}
