package com.desolatetimelines.acct.catalog.ws.mapper;

import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItem;
import com.desolatetimelines.acct.catalog.ws.model.IncomeOrExpenseItemProperties;

import java.util.Collection;

/**
 * Provides mapping methods for the {@link IncomeOrExpenseItemProperties} type
 */
public class IncomeOrExpenseItemPropertiesMapper {

    public static IncomeOrExpenseItemProperties fromAcctIncomeOrExpenseItem(
        AcctIncomeOrExpenseItem acctIncomeOrExpenseItem
    ) {
        return
            IncomeOrExpenseItemProperties.builder()
                .withIncomeOrExpenseItemUUID(acctIncomeOrExpenseItem.getIncomeOrExpenseItemUUID())
                .withIncomeOrExpenseItemName(acctIncomeOrExpenseItem.getIncomeOrExpenseItemName())
                .withIncomeOrExpenseItemDescription(acctIncomeOrExpenseItem.getIncomeOrExpenseItemDescription())
                .withIncomeOrExpenseItemIconUUID(acctIncomeOrExpenseItem.getIncomeOrExpenseItemIconUUID())
                .build();
    }

    public static Collection<IncomeOrExpenseItemProperties> fromCollectionOfAcctIncomeOrExpenseItems(
        Collection<AcctIncomeOrExpenseItem> incomeOrExpenseItems
    ) {
        return
            incomeOrExpenseItems.stream()
                .map(IncomeOrExpenseItemPropertiesMapper::fromAcctIncomeOrExpenseItem)
                .toList();
    }

}
