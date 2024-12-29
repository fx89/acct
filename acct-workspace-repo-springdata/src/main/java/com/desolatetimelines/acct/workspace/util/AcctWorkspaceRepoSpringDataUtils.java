package com.desolatetimelines.acct.workspace.util;

import com.desolatetimelines.acct.workspace.model.AcctWorkspace;
import com.desolatetimelines.acct.workspace.model.JpaAcctWorkspace;

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


}
