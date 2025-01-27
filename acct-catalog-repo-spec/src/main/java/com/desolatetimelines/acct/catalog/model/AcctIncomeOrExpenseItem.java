package com.desolatetimelines.acct.catalog.model;

/**
 * Groups expenses of the same type to help reporting
 */
public interface AcctIncomeOrExpenseItem {

    AcctIncomeOrExpenseItemSubcategory getIncomeOrExpenseItemSubcategory();

    void setIncomeOrExpenseItemSubcategory(AcctIncomeOrExpenseItemSubcategory incomeOrExpenseItemSubcategory);

    String getIncomeOrExpenseItemUUID();

    void setIncomeOrExpenseItemUUID(String incomeOrExpenseItemUUID);

    String getIncomeOrExpenseItemName();

    void setIncomeOrExpenseItemName(String incomeOrExpenseItemName);

    String getIncomeOrExpenseItemDescription();

    void setIncomeOrExpenseItemDescription(String incomeOrExpenseItemDescription);

    String getIncomeOrExpenseItemIconUUID();

    void setIncomeOrExpenseItemIconUUID(String incomeOrExpenseItemIconUUID);

}
