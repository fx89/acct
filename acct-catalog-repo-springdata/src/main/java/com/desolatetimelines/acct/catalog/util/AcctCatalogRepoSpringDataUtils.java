package com.desolatetimelines.acct.catalog.util;

import com.desolatetimelines.acct.catalog.model.AcctIcon;
import com.desolatetimelines.acct.catalog.model.AcctIconCategory;
import com.desolatetimelines.acct.catalog.model.JpaAcctIcon;
import com.desolatetimelines.acct.catalog.model.JpaAcctIconCategory;

import java.util.function.Function;

/**
 * Provides utility methods used by components of the Spring Data-based implementation
 * of the ACCT Catalog repositories
 */
public abstract class AcctCatalogRepoSpringDataUtils {

    public static JpaAcctIconCategory doWithJpaAcctIconCategory(
        AcctIconCategory acctIconCategory,
        Function<JpaAcctIconCategory, JpaAcctIconCategory> todo
    ) {
        if (acctIconCategory instanceof JpaAcctIconCategory jpaAcctIconCategory) {
            return todo.apply(jpaAcctIconCategory);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctIconCategory.class.getName() +
                " is not of type " + JpaAcctIconCategory.class.getCanonicalName()
        );
    }

    public static JpaAcctIcon doWithJpaAcctIcon(
        AcctIcon acctIcon,
        Function<JpaAcctIcon, JpaAcctIcon> todo
    ) {
        if (acctIcon instanceof JpaAcctIcon jpaAcctIcon) {
            return todo.apply(jpaAcctIcon);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctIcon.class.getName() +
                " is not of type " + JpaAcctIcon.class.getCanonicalName()
        );
    }

}

