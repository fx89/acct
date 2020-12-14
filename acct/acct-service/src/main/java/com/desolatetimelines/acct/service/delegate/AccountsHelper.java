package com.desolatetimelines.acct.service.delegate;

import com.desolatetimelines.acct.service.AccountService;
import com.desolatetimelines.acct.service.dao.AccountDataService;
import com.desolatetimelines.acct.service.dao.model.AccountRecord;
import com.desolatetimelines.acct.service.dao.model.IncomeOrExpenseItem;

public class AccountsHelper {

	public static void afterAccountRecordSaved(AccountService accountService, AccountDataService dataService,
			AccountRecord record) {
		updateIncomeOrExpenseItemLastUsedValue(dataService, record);
	}

	private static void updateIncomeOrExpenseItemLastUsedValue(AccountDataService dataService, AccountRecord record) {
		IncomeOrExpenseItem item = dataService.getIncomeOrExpenseItem(record.getIncomeOrExpenseItemId());
		item.setLastUsedValue(record.getValue());
		dataService.saveIncomeOrExpenseItem(item);
	}
}
