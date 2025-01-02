package com.desolatetimelines.acct.workspace.util;

import com.desolatetimelines.acct.workspace.model.AcctAccount;
import com.desolatetimelines.acct.workspace.model.AcctWorkspace;
import com.desolatetimelines.acct.workspace.model.JpaAcctAccount;
import com.desolatetimelines.acct.workspace.model.JpaAcctWorkspace;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Utility methods for working with the ACCT Workspace JPA data model
 */
public abstract class AcctWorkspaceRepoSpringDataUtils {

    public static JpaAcctWorkspace doWithJpaAcctWorkspaceReturning(
        AcctWorkspace acctWorkspace,
        Function<JpaAcctWorkspace, JpaAcctWorkspace> todo
    ) {
        if (acctWorkspace instanceof JpaAcctWorkspace jpaAcctWorkspace) {
            return todo.apply(jpaAcctWorkspace);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctWorkspace.class.getName() +
                " is not of type " + JpaAcctWorkspace.class.getCanonicalName()
        );
    }

    public static void doWithJpaAcctWorkspace(
        AcctWorkspace acctWorkspace,
        Consumer<JpaAcctWorkspace> todo
    ) {
        if (acctWorkspace instanceof JpaAcctWorkspace jpaAcctWorkspace) {
            todo.accept(jpaAcctWorkspace);
            return;
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctWorkspace.class.getName() +
                " is not of type " + JpaAcctWorkspace.class.getCanonicalName()
        );
    }

    public static JpaAcctAccount doWithJpaAcctAccountReturning(
        AcctAccount acctAccount,
        Function<JpaAcctAccount, JpaAcctAccount> todo
    ) {
        if (acctAccount instanceof JpaAcctAccount jpaAcctAccount) {
            return todo.apply(jpaAcctAccount);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctAccount.class.getName() +
                " is not of type " + JpaAcctAccount.class.getCanonicalName()
        );
    }


}
