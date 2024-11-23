package com.desolatetimelines.acct.security.util;

import com.desolatetimelines.acct.security.model.AcctGroupPrivilege;
import com.desolatetimelines.acct.security.model.JpaAcctGroupPrivilege;

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


}
