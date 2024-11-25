package com.desolatetimelines.acct.usernamagement.model;

import com.desolatetimelines.acct.usermanagement.model.AcctUser;
import com.desolatetimelines.acct.usermanagement.model.AcctUserGroupMapping;
import com.desolatetimelines.acct.usermanagement.model.AcctUsersGroup;
import jakarta.persistence.*;

import java.util.Objects;

import static com.desolatetimelines.acct.usernamagement.util.AcctUserManagementRepoSpringDataUtils.doWithJpaAcctUser;
import static com.desolatetimelines.acct.usernamagement.util.AcctUserManagementRepoSpringDataUtils.doWithJpaAcctUsersGroup;
import static java.util.function.Function.identity;

@Entity
@Table(name = "\"user_group\"")
public class JpaAcctUserGroupMapping implements AcctUserGroupMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_group_id")
    private Long userGroupId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private JpaAcctUser user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private JpaAcctUsersGroup group;

    public JpaAcctUserGroupMapping() {
    }

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

    @Override
    public AcctUser getUser() {
        return user;
    }

    @Override
    public void setUser(AcctUser acctUser) {
        this.user = doWithJpaAcctUser(acctUser, identity());
    }

    @Override
    public AcctUsersGroup getGroup() {
        return group;
    }

    @Override
    public void setGroup(AcctUsersGroup group) {
        this.group = doWithJpaAcctUsersGroup(group, identity());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctUserGroupMapping that = (JpaAcctUserGroupMapping) o;
        return Objects.equals(userGroupId, that.userGroupId) && Objects.equals(user, that.user) && Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userGroupId, user, group);
    }
}
