package com.desolatetimelines.acct.catalog.model;

/**
 * Groups {@link AcctIncomeOrExpenseItemSubcategory subcategories} of
 * {@link AcctIncomeOrExpenseItem income or expense items} of the same
 * type to help reporting
 */
public interface AcctIncomeOrExpenseItemCategory {

    String getIncomeOrExpenseItemCategoryUUID();

    void setIncomeOrExpenseItemCategoryUUID(String incomeOrExpenseItemCategoryUUID);

    String getIncomeOrExpenseItemCategoryName();

    void setIncomeOrExpenseItemCategoryName(String incomeOrExpenseItemCategoryName);

    String getIncomeOrExpenseItemCategoryDescription();

    void setIncomeOrExpenseItemCategoryDescription(String incomeOrExpenseItemCategoryDescription);

    String getIncomeOrExpenseItemCategoryIconUUID();

    void setIncomeOrExpenseItemCategoryIconUUID(String incomeOrExpenseItemCategoryIconUUID);

}
