package com.desolatetimelines.acct.catalog.ws.mapper;

import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.catalog.ws.model.IncomeOrExpenseItemCategoryProperties;

import java.util.Collection;

/**
 * Provides mapping methods for the {@link IncomeOrExpenseItemCategoryProperties} type
 */
public abstract class IncomeOrExpenseItemCategoryPropertiesMapper {

    public static IncomeOrExpenseItemCategoryProperties fromAcctIncomeOrExpenseItemCategory(
        AcctIncomeOrExpenseItemCategory acctIncomeOrExpenseItemCategory
    ) {
        return
            IncomeOrExpenseItemCategoryProperties.builder()
                .withIncomeOrExpenseItemCategoryUUID(acctIncomeOrExpenseItemCategory.getIncomeOrExpenseItemCategoryUUID())
                .withIncomeOrExpenseItemCategoryName(acctIncomeOrExpenseItemCategory.getIncomeOrExpenseItemCategoryName())
                .withIncomeOrExpenseItemCategoryDescription(acctIncomeOrExpenseItemCategory.getIncomeOrExpenseItemCategoryDescription())
                .withIncomeOrExpenseItemCategoryIconUUID(acctIncomeOrExpenseItemCategory.getIncomeOrExpenseItemCategoryIconUUID())
                .build();
    }

    public static Collection<IncomeOrExpenseItemCategoryProperties> fromCollectionOfAcctIncomeOrExpenseItemCategories(
        Collection<AcctIncomeOrExpenseItemCategory> acctIncomeOrExpenseItemCategories
    ) {
        return
            acctIncomeOrExpenseItemCategories
                .stream()
                .map(IncomeOrExpenseItemCategoryPropertiesMapper::fromAcctIncomeOrExpenseItemCategory)
                .toList();
    }

}
