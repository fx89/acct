package com.desolatetimelines.acct.common.model;

/**
 * Provides common UUIDs for referencing various common default entities cross-module
 */
public abstract class CommonUUIDs {

    /**
     * Special UUID for transfer between two accounts
     */
    public static final String INCOME_OR_EXPENSE_ITEM_UUID_FOR_TRANSFER = "9eacf1d5-5631-45e6-a7a3-cfc522054307";

    /**
     * Special UUID for currency exchange between two accounts
     */
    public static final String INCOME_OR_EXPENSE_ITEM_UUID_FOR_CURRENCY_EXCHANGE = "ea8d5742-3751-4596-8255-edc7623a59e9";

    /**
     * Special UUID for transfer deposit-related operations
     */
    public static final String INCOME_OR_EXPENSE_ITEM_UUID_FOR_DEPOSIT = "c8c2cef2-0781-44a0-bdd6-77697e73e8e3";

    /**
     * Special UUID for transfer deposit interest operations
     */
    public static final String INCOME_OR_EXPENSE_ITEM_UUID_FOR_DEPOSIT_INTEREST = "f703e387-8fba-4529-bdcb-ed66561758e9";

}
