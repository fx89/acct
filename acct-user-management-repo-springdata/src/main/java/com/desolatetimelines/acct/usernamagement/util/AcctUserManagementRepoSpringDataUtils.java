package com.desolatetimelines.acct.usernamagement.util;

import com.desolatetimelines.acct.usermanagement.model.AcctUser;
import com.desolatetimelines.acct.usermanagement.model.AcctUserGroupMapping;
import com.desolatetimelines.acct.usermanagement.model.AcctUsersGroup;
import com.desolatetimelines.acct.usernamagement.model.JpaAcctUser;
import com.desolatetimelines.acct.usernamagement.model.JpaAcctUserGroupMapping;
import com.desolatetimelines.acct.usernamagement.model.JpaAcctUsersGroup;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Provides utility methods used by components of the Spring Data-based implementation
 * of the ACCT User Management repositories
 */
public abstract class AcctUserManagementRepoSpringDataUtils {

    public static JpaAcctUser doWithJpaAcctUser(
        AcctUser acctService,
        Function<JpaAcctUser, JpaAcctUser> todo
    ) {
        if (acctService instanceof JpaAcctUser jpaAcctUser) {
            return todo.apply(jpaAcctUser);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctUser.class.getName() +
                " is not of type " + JpaAcctUser.class.getCanonicalName()
        );
    }

    public static void doWithJpaAcctUsersGroupWithoutReturning(
        AcctUsersGroup acctUsersGroup,
        Consumer<JpaAcctUsersGroup> todo
    ) {
        doWithJpaAcctUsersGroup(acctUsersGroup, jpaAcctUsersGroup -> {
            todo.accept(jpaAcctUsersGroup);
            return jpaAcctUsersGroup;
        });
    }

    public static JpaAcctUsersGroup doWithJpaAcctUsersGroup(
        AcctUsersGroup acctUsersGroup,
        Function<JpaAcctUsersGroup, JpaAcctUsersGroup> todo
    ) {
        if (acctUsersGroup instanceof JpaAcctUsersGroup jpaAcctUsersGroup) {
            return todo.apply(jpaAcctUsersGroup);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctUsersGroup.class.getName() +
                " is not of type " + JpaAcctUsersGroup.class.getCanonicalName()
        );
    }

    public static JpaAcctUserGroupMapping doWithJpaAcctUserGroupMapping(
        AcctUserGroupMapping acctUserGroupMapping,
        Function<JpaAcctUserGroupMapping, JpaAcctUserGroupMapping> todo
    ) {
        if (acctUserGroupMapping instanceof JpaAcctUserGroupMapping jpaAcctUserGroupMapping) {
            return todo.apply(jpaAcctUserGroupMapping);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctUserGroupMapping.class.getName() +
                " is not of type " + JpaAcctUserGroupMapping.class.getCanonicalName()
        );
    }

}

