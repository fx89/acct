package com.desolatetimelines.acct.usage.util;

import com.desolatetimelines.acct.usage.model.AcctService;
import com.desolatetimelines.acct.usage.model.AcctUsedItemType;
import com.desolatetimelines.acct.usage.model.JpaAcctService;
import com.desolatetimelines.acct.usage.model.JpaAcctUsedItemType;

import java.util.function.Function;

/**
 * Provides utility methods used by components of the Spring Data-based implementation of the ACCT Usage repositories
 */
public abstract class AcctUsageRepoSpringdataUtils {

    public static JpaAcctService doWithJpaAcctService(
        AcctService acctService,
        Function<JpaAcctService, JpaAcctService> todo
    ) {
        if (acctService instanceof JpaAcctService jpaAcctService) {
            return todo.apply(jpaAcctService);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctService.class.getName() +
                " is not of type " + JpaAcctService.class.getCanonicalName()
        );
    }

    public static JpaAcctUsedItemType doWithJpaAcctUsedItemType(
        AcctUsedItemType acctUsedItemType,
        Function<JpaAcctUsedItemType, JpaAcctUsedItemType> todo
    ) {
        if (acctUsedItemType instanceof JpaAcctUsedItemType jpaAcctUsedItemType) {
            return todo.apply(jpaAcctUsedItemType);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctUsedItemType.class.getName() +
                " is not of type " + JpaAcctUsedItemType.class.getCanonicalName()
        );
    }

}
