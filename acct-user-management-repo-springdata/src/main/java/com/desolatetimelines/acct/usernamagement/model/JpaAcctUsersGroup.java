package com.desolatetimelines.acct.usernamagement.model;

import com.desolatetimelines.acct.usermanagement.model.AcctUsersGroup;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "\"group\"")
public class JpaAcctUsersGroup implements AcctUsersGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Column(name = "group_uuid")
    private String groupUUID;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_description")
    private String groupDescription;

    @Column(name = "group_icon_uuid")
    private String groupIconUUID;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_group",
        joinColumns = @JoinColumn(name = "group_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<JpaAcctUser> users;

    public JpaAcctUsersGroup() {
    }

    public JpaAcctUsersGroup(Long groupId, String groupUUID, String groupName, String groupDescription, String groupIconUUID, Set<JpaAcctUser> users) {
        this.groupId = groupId;
        this.groupUUID = groupUUID;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.groupIconUUID = groupIconUUID;
        this.users = users;
    }

    private JpaAcctUsersGroup(JpaAcctUsersGroupBuilder builder) {
        setGroupId(builder.groupId);
        setGroupUUID(builder.groupUUID);
        setGroupName(builder.groupName);
        setGroupDescription(builder.groupDescription);
        setGroupIconUUID(builder.groupIconUUID);
        setUsers(builder.users);
    }

    public static JpaAcctUsersGroupBuilder builder() {
        return new JpaAcctUsersGroupBuilder();
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String getGroupUUID() {
        return groupUUID;
    }

    @Override
    public void setGroupUUID(String groupUUID) {
        this.groupUUID = groupUUID;
    }

    @Override
    public String getGroupName() {
        return groupName;
    }

    @Override
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String getGroupDescription() {
        return groupDescription;
    }

    @Override
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    @Override
    public String getGroupIconUUID() {
        return groupIconUUID;
    }

    @Override
    public void setGroupIconUUID(String groupIconUUID) {
        this.groupIconUUID = groupIconUUID;
    }

    public Set<JpaAcctUser> getUsers() {
        return users;
    }

    public void setUsers(Set<JpaAcctUser> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctUsersGroup group = (JpaAcctUsersGroup) o;
        return Objects.equals(groupId, group.groupId) && Objects.equals(groupUUID, group.groupUUID) && Objects.equals(groupName, group.groupName) && Objects.equals(groupDescription, group.groupDescription) && Objects.equals(groupIconUUID, group.groupIconUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupUUID, groupName, groupDescription, groupIconUUID);
    }


    /**
     * {@code Group} builder static inner class.
     */
    public static final class JpaAcctUsersGroupBuilder {
        private Long groupId;
        private String groupUUID;
        private String groupName;
        private String groupDescription;
        private String groupIconUUID;
        private Set<JpaAcctUser> users;

        private JpaAcctUsersGroupBuilder() {
        }

        /**
         * Sets the {@code groupId} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupId the {@code groupId} to set
         * @return a reference to this Builder
         */
        public JpaAcctUsersGroupBuilder withGroupId(Long groupId) {
            this.groupId = groupId;
            return this;
        }

        /**
         * Sets the {@code groupUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupUUID the {@code groupUUID} to set
         * @return a reference to this Builder
         */
        public JpaAcctUsersGroupBuilder withGroupUUID(String groupUUID) {
            this.groupUUID = groupUUID;
            return this;
        }

        /**
         * Sets the {@code groupName} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupName the {@code groupName} to set
         * @return a reference to this Builder
         */
        public JpaAcctUsersGroupBuilder withGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        /**
         * Sets the {@code groupDescription} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupDescription the {@code groupDescription} to set
         * @return a reference to this Builder
         */
        public JpaAcctUsersGroupBuilder withGroupDescription(String groupDescription) {
            this.groupDescription = groupDescription;
            return this;
        }

        /**
         * Sets the {@code groupIconUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupIconUUID the {@code groupIconUUID} to set
         * @return a reference to this Builder
         */
        public JpaAcctUsersGroupBuilder withGroupIconUUID(String groupIconUUID) {
            this.groupIconUUID = groupIconUUID;
            return this;
        }

        /**
         * Sets the {@code users} and returns a reference to this Builder enabling method chaining.
         *
         * @param users the {@code users} to set
         * @return a reference to this Builder
         */
        public JpaAcctUsersGroupBuilder withUsers(Set<JpaAcctUser> users) {
            this.users = users;
            return this;
        }

        /**
         * Returns a {@code Group} built from the parameters previously set.
         *
         * @return a {@code Group} built with parameters of this {@code Group.Builder}
         */
        public JpaAcctUsersGroup build() {
            return new JpaAcctUsersGroup(this);
        }
    }
}
