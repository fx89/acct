package com.desolatetimelines.acct.catalog.util;

import com.desolatetimelines.acct.catalog.model.*;

import java.util.function.Function;

/**
 * Provides utility methods used by components of the Spring Data-based implementation
 * of the ACCT Catalog repositories
 */
public abstract class AcctCatalogRepoSpringDataUtils {

    public static JpaAcctIconCategory doWithJpaAcctIconCategoryReturning(
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

    public static JpaAcctIcon doWithJpaAcctIconReturning(
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

    public static JpaAcctIncomeOrExpenseItemCategory doWithJpaAcctIncomeOrExpenseItemCategoryReturning(
        AcctIncomeOrExpenseItemCategory acctIncomeOrExpenseItemCategory,
        Function<JpaAcctIncomeOrExpenseItemCategory, JpaAcctIncomeOrExpenseItemCategory> todo
    ) {
        if (acctIncomeOrExpenseItemCategory instanceof JpaAcctIncomeOrExpenseItemCategory jpaIncomeOrExpenseItemCategory) {
            return todo.apply(jpaIncomeOrExpenseItemCategory);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctIncomeOrExpenseItemCategory.class.getName() +
                " is not of type " + JpaAcctIncomeOrExpenseItemCategory.class.getCanonicalName()
        );
    }

    public static JpaAcctIncomeOrExpenseItemSubcategory doWithJpaAcctIncomeOrExpenseItemSubcategoryReturning(
        AcctIncomeOrExpenseItemSubcategory acctIncomeOrExpenseItemSubcategory,
        Function<JpaAcctIncomeOrExpenseItemSubcategory, JpaAcctIncomeOrExpenseItemSubcategory> todo
    ) {
        if (acctIncomeOrExpenseItemSubcategory instanceof JpaAcctIncomeOrExpenseItemSubcategory jpaIncomeOrExpenseItemSubcategory) {
            return todo.apply(jpaIncomeOrExpenseItemSubcategory);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctIncomeOrExpenseItemSubcategory.class.getName() +
                " is not of type " + JpaAcctIncomeOrExpenseItemSubcategory.class.getCanonicalName()
        );
    }

    public static JpaAcctIncomeOrExpenseItem doWithJpaAcctIncomeOrExpenseItemReturning(
        AcctIncomeOrExpenseItem acctIncomeOrExpenseItem,
        Function<JpaAcctIncomeOrExpenseItem, JpaAcctIncomeOrExpenseItem> todo
    ) {
        if (acctIncomeOrExpenseItem instanceof JpaAcctIncomeOrExpenseItem jpaIncomeOrExpenseItem) {
            return todo.apply(jpaIncomeOrExpenseItem);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctIncomeOrExpenseItem.class.getName() +
                " is not of type " + JpaAcctIncomeOrExpenseItem.class.getCanonicalName()
        );
    }

    public static JpaAcctBank doWithJpaAcctBankReturning(
        AcctBank acctBank,
        Function<JpaAcctBank, JpaAcctBank> todo
    ) {
        if (acctBank instanceof JpaAcctBank jpaAcctBank) {
            return todo.apply(jpaAcctBank);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctBank.class.getName() +
                " is not of type " + JpaAcctBank.class.getCanonicalName()
        );
    }

}

