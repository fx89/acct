package com.desolatetimelines.acct.service.delegate;

import com.desolatetimelines.acct.service.AccountService;
import com.desolatetimelines.acct.service.dao.AccountDataService;
import com.desolatetimelines.acct.service.dao.model.IncomeOrExpenseItem;

public class IncomeOrExpenseItemsHelper {

	public static void beforeIncomeOrExpenseItemSave(AccountService accountService, AccountDataService dataService,
			IncomeOrExpenseItem item) {
		defaultLastUsedValueToZero(item);
	}

	private static void defaultLastUsedValueToZero(IncomeOrExpenseItem item) {
		if (item.getLastUsedValue() == null) {
			item.setLastUsedValue(0d);
		}
	}
}
