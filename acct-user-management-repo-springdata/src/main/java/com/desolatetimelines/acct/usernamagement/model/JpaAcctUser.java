package com.desolatetimelines.acct.usernamagement.model;

import com.desolatetimelines.acct.usermanagement.model.AcctUser;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "\"user\"")
public class JpaAcctUser implements AcctUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_uuid")
    private String userUUID;

    @Column(name = "user_login_name")
    private String userLoginName;

    @Column(name = "user_encrypted_password")
    private String userEncryptedPassword;

    @Column(name = "user_password_expiry_date")
    private Instant userPasswordExpiryDate;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_icon_uuid")
    private String userIconUUID;

    @Column(name = "default_workspace_uuid")
    private String defaultWorkspaceUUID;

    @Column(name = "soft_deleted")
    private Boolean softDeleted;

    @Column(name = "soft_deleted_date")
    private Instant softDeletedDate;

    public JpaAcctUser() {
    }

    public JpaAcctUser(Long userId, String userUUID, String userLoginName, String userEncryptedPassword, Instant userPasswordExpiryDate, String userName, String userIconUUID, String defaultWorkspaceUUID, Boolean softDeleted, Instant softDeletedDate) {
        this.userId = userId;
        this.userUUID = userUUID;
        this.userLoginName = userLoginName;
        this.userEncryptedPassword = userEncryptedPassword;
        this.userPasswordExpiryDate = userPasswordExpiryDate;
        this.userName = userName;
        this.userIconUUID = userIconUUID;
        this.defaultWorkspaceUUID = defaultWorkspaceUUID;
        this.softDeleted = softDeleted;
        this.softDeletedDate = softDeletedDate;
    }

    private JpaAcctUser(JpaAcctUserBuilder builder) {
        setUserId(builder.userId);
        setUserUUID(builder.userUUID);
        setUserLoginName(builder.userLoginName);
        setUserEncryptedPassword(builder.userEncryptedPassword);
        setUserPasswordExpiryDate(builder.userPasswordExpiryDate);
        setUserName(builder.userName);
        setUserIconUUID(builder.userIconUUID);
        setDefaultWorkspaceUUID(builder.defaultWorkspaceUUID);
        setSoftDeleted(builder.softDeleted);
        setSoftDeletedDate(builder.softDeletedDate);
    }

    public static JpaAcctUserBuilder builder() {
        return new JpaAcctUserBuilder();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String getUserUUID() {
        return userUUID;
    }

    @Override
    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    @Override
    public String getUserLoginName() {
        return userLoginName;
    }

    @Override
    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    @Override
    public String getUserEncryptedPassword() {
        return userEncryptedPassword;
    }

    @Override
    public void setUserEncryptedPassword(String userEncryptedPassword) {
        this.userEncryptedPassword = userEncryptedPassword;
    }

    @Override
    public Instant getUserPasswordExpiryDate() {
        return userPasswordExpiryDate;
    }

    @Override
    public void setUserPasswordExpiryDate(Instant userPasswordExpiryDate) {
        this.userPasswordExpiryDate = userPasswordExpiryDate;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUserIconUUID() {
        return userIconUUID;
    }

    @Override
    public void setUserIconUUID(String userIconUUID) {
        this.userIconUUID = userIconUUID;
    }

    @Override
    public String getDefaultWorkspaceUUID() {
        return defaultWorkspaceUUID;
    }

    @Override
    public void setDefaultWorkspaceUUID(String defaultWorkspaceUUID) {
        this.defaultWorkspaceUUID = defaultWorkspaceUUID;
    }

    @Override
    public Boolean getSoftDeleted() {
        return softDeleted;
    }

    @Override
    public void setSoftDeleted(Boolean softDeleted) {
        this.softDeleted = softDeleted;
    }

    @Override
    public Instant getSoftDeletedDate() {
        return softDeletedDate;
    }

    @Override
    public void setSoftDeletedDate(Instant softDeletedDate) {
        this.softDeletedDate = softDeletedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctUser user = (JpaAcctUser) o;
        return Objects.equals(userId, user.userId) && Objects.equals(userUUID, user.userUUID) && Objects.equals(userLoginName, user.userLoginName) && Objects.equals(userEncryptedPassword, user.userEncryptedPassword) && Objects.equals(userPasswordExpiryDate, user.userPasswordExpiryDate) && Objects.equals(userName, user.userName) && Objects.equals(userIconUUID, user.userIconUUID) && Objects.equals(defaultWorkspaceUUID, user.defaultWorkspaceUUID) && Objects.equals(softDeleted, user.softDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userUUID, userLoginName, userEncryptedPassword, userPasswordExpiryDate, userName, userIconUUID, defaultWorkspaceUUID, softDeleted);
    }


    /**
     * {@code User} builder static inner class.
     */
    public static final class JpaAcctUserBuilder {
        private Long userId;
        private String userUUID;
        private String userLoginName;
        private String userEncryptedPassword;
        private Instant userPasswordExpiryDate;
        private String userName;
        private String userIconUUID;
        private String defaultWorkspaceUUID;
        private Boolean softDeleted;
        private Instant softDeletedDate;

        private JpaAcctUserBuilder() {
        }

        /**
         * Sets the {@code userId} and returns a reference to this Builder enabling method chaining.
         *
         * @param userId the {@code userId} to set
         * @return a reference to this Builder
         */
        public JpaAcctUserBuilder withUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        /**
         * Sets the {@code userUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param userUUID the {@code userUUID} to set
         * @return a reference to this Builder
         */
        public JpaAcctUserBuilder withUserUUID(String userUUID) {
            this.userUUID = userUUID;
            return this;
        }

        /**
         * Sets the {@code userLoginName} and returns a reference to this Builder enabling method chaining.
         *
         * @param userLoginName the {@code userLoginName} to set
         * @return a reference to this Builder
         */
        public JpaAcctUserBuilder withUserLoginName(String userLoginName) {
            this.userLoginName = userLoginName;
            return this;
        }

        /**
         * Sets the {@code userEncryptedPassword} and returns a reference to this Builder enabling method chaining.
         *
         * @param userEncryptedPassword the {@code userEncryptedPassword} to set
         * @return a reference to this Builder
         */
        public JpaAcctUserBuilder withUserEncryptedPassword(String userEncryptedPassword) {
            this.userEncryptedPassword = userEncryptedPassword;
            return this;
        }

        /**
         * Sets the {@code userPasswordExpiryDate} and returns a reference to this Builder enabling method chaining.
         *
         * @param userPasswordExpiryDate the {@code userPasswordExpiryDate} to set
         * @return a reference to this Builder
         */
        public JpaAcctUserBuilder withUserPasswordExpiryDate(Instant userPasswordExpiryDate) {
            this.userPasswordExpiryDate = userPasswordExpiryDate;
            return this;
        }

        /**
         * Sets the {@code userName} and returns a reference to this Builder enabling method chaining.
         *
         * @param userName the {@code userName} to set
         * @return a reference to this Builder
         */
        public JpaAcctUserBuilder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        /**
         * Sets the {@code userIconUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param userIconUUID the {@code userIconUUID} to set
         * @return a reference to this Builder
         */
        public JpaAcctUserBuilder withUserIconUUID(String userIconUUID) {
            this.userIconUUID = userIconUUID;
            return this;
        }

        /**
         * Sets the {@code defaultWorkspaceUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param defaultWorkspaceUUID the {@code defaultWorkspaceUUID} to set
         * @return a reference to this Builder
         */
        public JpaAcctUserBuilder withDefaultWorkspaceUUID(String defaultWorkspaceUUID) {
            this.defaultWorkspaceUUID = defaultWorkspaceUUID;
            return this;
        }

        /**
         * Sets the {@code softDeleted} and returns a reference to this Builder enabling method chaining.
         *
         * @param softDeleted the {@code softDeleted} to set
         * @return a reference to this Builder
         */
        public JpaAcctUserBuilder withSoftDeleted(Boolean softDeleted) {
            this.softDeleted = softDeleted;
            return this;
        }

        /**
         * Sets the {@code softDeletedDate} and returns a reference to this Builder enabling method chaining.
         *
         * @param softDeletedDate the {@code softDeletedDate} to set
         * @return a reference to this Builder
         */
        public JpaAcctUserBuilder withSoftDeletedDate(Instant softDeletedDate) {
            this.softDeletedDate = softDeletedDate;
            return this;
        }

        /**
         * Returns a {@code User} built from the parameters previously set.
         *
         * @return a {@code User} built with parameters of this {@code User.Builder}
         */
        public JpaAcctUser build() {
            return new JpaAcctUser(this);
        }
    }
}
