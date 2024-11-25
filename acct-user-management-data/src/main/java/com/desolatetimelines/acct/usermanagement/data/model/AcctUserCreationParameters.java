package com.desolatetimelines.acct.usermanagement.data.model;

import static java.util.Objects.requireNonNull;

/**
 * Holds parameters for user creation
 *
 * @param userUUID             a new UUID for the user
 * @param userLoginName        the login ID of the user
 * @param userName             the human-readable name of the user
 * @param userIconUUID         the UUID of the icon chosen for the user
 * @param defaultWorkspaceUUID the UUID of the workspace on which the user lands after logging in
 */
public record AcctUserCreationParameters(

    String userUUID,
    String userLoginName,
    String userEncryptedPassword,
    String userName,
    String userIconUUID,
    String defaultWorkspaceUUID

) {

    public static UserCreationParametersBuilder builder() {
        return new UserCreationParametersBuilder();
    }

    /**
     * {@code UserCreationParameters} builder static inner class.
     */
    public static final class UserCreationParametersBuilder {
        private String userLoginName;
        private String userName;
        private String userEncryptedPassword;
        private String userIconUUID;
        private String defaultWorkspaceUUID;
        private String userUUID;

        private UserCreationParametersBuilder() {
        }

        /**
         * Sets the {@code userLoginName} and returns a reference to this Builder enabling method chaining.
         *
         * @param userLoginName the {@code userLoginName} to set
         * @return a reference to this Builder
         */
        public UserCreationParametersBuilder withUserLoginName(String userLoginName) {
            this.userLoginName = userLoginName;
            return this;
        }

        /**
         * Sets the {@code userName} and returns a reference to this Builder enabling method chaining.
         *
         * @param userName the {@code userName} to set
         * @return a reference to this Builder
         */
        public UserCreationParametersBuilder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        /**
         * Sets the {@code userIconUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param userIconUUID the {@code userIconUUID} to set
         * @return a reference to this Builder
         */
        public UserCreationParametersBuilder withUserIconUUID(String userIconUUID) {
            this.userIconUUID = userIconUUID;
            return this;
        }

        /**
         * Sets the {@code defaultWorkspaceUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param defaultWorkspaceUUID the {@code defaultWorkspaceUUID} to set
         * @return a reference to this Builder
         */
        public UserCreationParametersBuilder withDefaultWorkspaceUUID(String defaultWorkspaceUUID) {
            this.defaultWorkspaceUUID = defaultWorkspaceUUID;
            return this;
        }

        /**
         * Sets the {@code userUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param userUUID the {@code userUUID} to set
         * @return a reference to this Builder
         */
        public UserCreationParametersBuilder withUserUUID(String userUUID) {
            this.userUUID = userUUID;
            return this;
        }

        /**
         * Sets the {@code userEncryptedPassword} and returns a reference to this Builder enabling method chaining.
         *
         * @param userEncryptedPassword the {@code userEncryptedPassword} to set
         * @return a reference to this Builder
         */
        public UserCreationParametersBuilder withUserEncryptedPassword(String userEncryptedPassword) {
            this.userEncryptedPassword = userEncryptedPassword;
            return this;
        }

        /**
         * Returns a {@code UserCreationParameters} built from the parameters previously set.
         *
         * @return a {@code UserCreationParameters} built with parameters of this {@code UserCreationParameters.Builder}
         */
        public AcctUserCreationParameters build() {
            requireNonNull(userUUID, "User UUID not provided");
            requireNonNull(userLoginName, "User login name not provided");
            requireNonNull(userEncryptedPassword, "Encrypted password not provided");
            requireNonNull(userName, "User name not provided");
            requireNonNull(userIconUUID, "User's icon UUID not provided");
            requireNonNull(defaultWorkspaceUUID, "User's default workspace UUID' not provided");

            return
                new AcctUserCreationParameters(
                    userUUID,
                    userLoginName,
                    userEncryptedPassword,
                    userName,
                    userIconUUID,
                    defaultWorkspaceUUID
                );
        }
    }
}
