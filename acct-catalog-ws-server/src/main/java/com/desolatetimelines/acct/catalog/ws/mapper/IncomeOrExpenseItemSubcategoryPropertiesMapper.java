package com.desolatetimelines.acct.catalog.ws.mapper;

import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItemSubcategory;
import com.desolatetimelines.acct.catalog.ws.model.IncomeOrExpenseItemSubcategoryProperties;

import java.util.Collection;

/**
 * Provides mapping methods for the {@link IncomeOrExpenseItemSubcategoryProperties} type
 */
public class IncomeOrExpenseItemSubcategoryPropertiesMapper {

    public static IncomeOrExpenseItemSubcategoryProperties fromAcctIncomeOrExpenseItemSubcategory(
        AcctIncomeOrExpenseItemSubcategory acctIncomeOrExpenseItemSubcategory
    ) {
        return
            IncomeOrExpenseItemSubcategoryProperties.builder()
                .withIncomeOrExpenseItemSubcategoryUUID(acctIncomeOrExpenseItemSubcategory.getIncomeOrExpenseItemSubcategoryUUID())
                .withIncomeOrExpenseItemSubcategoryName(acctIncomeOrExpenseItemSubcategory.getIncomeOrExpenseItemSubcategoryName())
                .withIncomeOrExpenseItemSubcategoryDescription(acctIncomeOrExpenseItemSubcategory.getIncomeOrExpenseItemSubcategoryDescription())
                .withIncomeOrExpenseItemSubcategoryIconUUID(acctIncomeOrExpenseItemSubcategory.getIncomeOrExpenseItemSubcategoryIconUUID())
                .build();
    }

    public static Collection<IncomeOrExpenseItemSubcategoryProperties>
    fromCollectionOfAcctIncomeOrExpenseItemSubcategories(
        Collection<AcctIncomeOrExpenseItemSubcategory> acctIncomeOrExpenseItemSubcategories
    ) {
        return
            acctIncomeOrExpenseItemSubcategories.stream()
                .map(IncomeOrExpenseItemSubcategoryPropertiesMapper::fromAcctIncomeOrExpenseItemSubcategory)
                .toList();
    }

}
