package com.desolatetimelines.acct.authorization.data.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * Represents the {@link UserDetails user details} required for the ACCT authentication process
 */
public class AcctUser implements UserDetails {

    private final String username;

    private final String password;

    private final Set<AcctGrantedAuthority> grantedAuthorities;

    private final String userUUID;

    private final String userHumanReadableName;

    private final String userIconUUID;

    private final String defaultWorkspaceUUID;

    private AcctUser(
        String username,
        String password,
        Set<AcctGrantedAuthority> grantedAuthorities,
        String userUUID,
        String userHumanReadableName,
        String userIconUUID,
        String defaultWorkspaceUUID
    ) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.userUUID = userUUID;
        this.userHumanReadableName = userHumanReadableName;
        this.userIconUUID = userIconUUID;
        this.defaultWorkspaceUUID = defaultWorkspaceUUID;
    }

    public static AcctUserBuilder builder() {
        return new AcctUserBuilder();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public String getUserHumanReadableName() {
        return userHumanReadableName;
    }

    public String getUserIconUUID() {
        return userIconUUID;
    }

    public String getDefaultWorkspaceUUID() {
        return defaultWorkspaceUUID;
    }

    /**
     * {@code AcctUser} builder static inner class.
     */
    public static final class AcctUserBuilder {
        private String username;
        private String password;
        private final Set<AcctGrantedAuthority> grantedAuthorities = new HashSet<>();

        private String userUUID;
        private String userHumanReadableName;
        private String userIconUUID;
        private String defaultWorkspaceUUID;

        private AcctUserBuilder() {
        }

        /**
         * Sets the {@code username} and returns a reference to this Builder enabling method chaining.
         *
         * @param username the {@code username} to set
         * @return a reference to this Builder
         */
        public AcctUserBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        /**
         * Sets the {@code password} and returns a reference to this Builder enabling method chaining.
         *
         * @param password the {@code password} to set
         * @return a reference to this Builder
         */
        public AcctUserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * Adds a granted authority and returns a reference to this Builder enabling method chaining.
         *
         * @param grantedAuthority the {@code grantedAuthority} to set
         * @return a reference to this Builder
         */
        @SuppressWarnings("UnusedReturnValue")
        public AcctUserBuilder withGrantedAuthority(String grantedAuthority) {
            requireNonNull(grantedAuthority, "Granted authority not provided");
            this.grantedAuthorities.add(new AcctGrantedAuthority(grantedAuthority));
            return this;
        }

        /**
         * Adds the given set of granted authorities and returns a reference to this Builder enabling method chaining.
         *
         * @param grantedAuthorities the {@code grantedAuthorities} to set
         * @return a reference to this Builder
         */
        public AcctUserBuilder withGrantedAuthorities(Set<String> grantedAuthorities) {
            requireNonNull(grantedAuthorities, "Granted authorities not provided");
            grantedAuthorities.forEach(this::withGrantedAuthority);
            return this;
        }

        /**
         * Sets the {@code userUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param userUUID the {@code userUUID} to set
         * @return a reference to this Builder
         */
        public AcctUserBuilder withUserUUID(String userUUID) {
            this.userUUID = userUUID;
            return this;
        }

        /**
         * Sets the {@code userHumanReadableName} and returns a reference to this Builder enabling method chaining.
         *
         * @param userHumanReadableName the {@code userHumanReadableName} to set
         * @return a reference to this Builder
         */
        public AcctUserBuilder withUserHumanReadableName(String userHumanReadableName) {
            this.userHumanReadableName = userHumanReadableName;
            return this;
        }

        /**
         * Sets the {@code userIconUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param userIconUUID the {@code userIconUUID} to set
         * @return a reference to this Builder
         */
        public AcctUserBuilder withUserIconUUID(String userIconUUID) {
            this.userIconUUID = userIconUUID;
            return this;
        }

        /**
         * Sets the {@code defaultWorkspaceUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param defaultWorkspaceUUID the {@code defaultWorkspaceUUID} to set
         * @return a reference to this Builder
         */
        public AcctUserBuilder withDefaultWorkspaceUUID(String defaultWorkspaceUUID) {
            this.defaultWorkspaceUUID = defaultWorkspaceUUID;
            return this;
        }

        /**
         * Returns a {@code AcctUser} built from the parameters previously set.
         *
         * @return a {@code AcctUser} built with parameters of this {@code AcctUser.Builder}
         */
        public AcctUser build() {
            requireNonNull(username, "Username not provided");
            requireNonNull(password, "Password not provided");
            requireNonNull(userUUID, "User UUID not provided");
            requireNonNull(userHumanReadableName, "Human-readable name not provided");

            return
                new AcctUser(
                    username,
                    password,
                    grantedAuthorities,
                    userUUID,
                    userHumanReadableName,
                    userIconUUID,
                    defaultWorkspaceUUID
                );
        }
    }
}
