package com.desolatetimelines.acct.security.util;

import com.desolatetimelines.acct.security.model.AcctGroupPrivilege;
import com.desolatetimelines.acct.security.model.AcctWorkspaceOwner;
import com.desolatetimelines.acct.security.model.JpaAcctGroupPrivilege;
import com.desolatetimelines.acct.security.model.JpaAcctWorkspaceOwner;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Provides utility methods used by components of the Spring Data-based implementation of the ACCT Security repositories
 */
public abstract class AcctSecurityRepoSpringdataUtils {

    public static JpaAcctGroupPrivilege doWithJJpaAcctGroupPrivilege(
        AcctGroupPrivilege acctGroupPrivilege,
        Function<JpaAcctGroupPrivilege, JpaAcctGroupPrivilege> todo
    ) {
        if (acctGroupPrivilege instanceof JpaAcctGroupPrivilege jpaAcctGroupPrivilege) {
            return todo.apply(jpaAcctGroupPrivilege);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctGroupPrivilege.class.getName() +
                " is not of type " + JpaAcctGroupPrivilege.class.getCanonicalName()
        );
    }

    public static JpaAcctWorkspaceOwner doWithJJpaAcctWorkspaceOwnerReturning(
        AcctWorkspaceOwner acctWorkspaceOwner,
        Function<JpaAcctWorkspaceOwner, JpaAcctWorkspaceOwner> todo
    ) {
        if (acctWorkspaceOwner instanceof JpaAcctWorkspaceOwner jpaAcctWorkspaceOwner) {
            return todo.apply(jpaAcctWorkspaceOwner);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctWorkspaceOwner.class.getName() +
                " is not of type " + JpaAcctWorkspaceOwner.class.getCanonicalName()
        );
    }

    public static void doWithJJpaAcctWorkspaceOwner(
        AcctWorkspaceOwner acctWorkspaceOwner,
        Consumer<JpaAcctWorkspaceOwner> todo
    ) {
        doWithJJpaAcctWorkspaceOwnerReturning(acctWorkspaceOwner, in -> {
            todo.accept(in);
            return null;
        });
    }

}
