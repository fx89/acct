package com.desolatetimelines.acct.service.delegate;

import com.desolatetimelines.acct.service.AccountService;
import com.desolatetimelines.acct.service.Exception.AccountServiceBusinessException;
import com.desolatetimelines.acct.service.dao.AccountDataService;
import com.desolatetimelines.acct.service.dao.model.IncomeOrExpenseItem;
import com.desolatetimelines.acct.service.dao.model.IncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.service.model.AccountSummary;

public class CommonOperations {
	public static void checkAccountAvailableAmount(AccountService accountService, Long accountId, Double requiredValue)
			throws AccountServiceBusinessException {
		// Get the source account summary
		AccountSummary sourceAccountSummary = accountService.getAccountSummary(accountId);

		// Get the available amount from the source account summary
		Double sourceAccountAvailableAmount = sourceAccountSummary.getRunningSumIncoming()
				+ sourceAccountSummary.getRunningSumOutgoing();

		// Check if there are sufficient funds in the source account based on the
		// available amount from the source account summary
		if (sourceAccountAvailableAmount < requiredValue) {
			throw new AccountServiceBusinessException("Insufficient funds in the source account");
		}
	}

	public static Long resolveOperationIncomeOrExpenseItemId(AccountService accountService,
			AccountDataService dataService, String incomeOrExpenseItemName) {
		// Find the category
		// TODO: Replace it with findOneByName, or leave it like it is if there will not
		// be too many categories - looking to minimize work on data service
		// implementations
		IncomeOrExpenseItemCategory operationsCategory = accountService.listIncomeOrExpenseItemCategories()
				.filter(cat -> cat.getName().equals("Operations")).findFirst().orElse(null);

		// If the category was not found, create it
		if (operationsCategory == null) {
			operationsCategory = dataService.newIncomeOrExpenseItemCategoryInstance();
			operationsCategory.setName("Operations");
			operationsCategory = dataService.saveIncomeOrExpenseItemCategory(operationsCategory);
		}

		// Find the deposit item
		// TODO: Replace with findOneByCategoryIdAndName - or maybe not - looking to
		// minimize work on data service implementations, if possible
		final IncomeOrExpenseItemCategory op = operationsCategory;
		IncomeOrExpenseItem depositItem = accountService.listIncomeOrExpenseItems()
				.filter(item -> item.getIncomeOrExpenseItemCategoryId() == op.getId()
						&& item.getName().contentEquals(incomeOrExpenseItemName))
				.findFirst().orElse(null);

		// If the deposit item was not found, create it
		if (depositItem == null) {
			depositItem = dataService.newIncomeOrExpenseItemInstance();
			depositItem.setIncomeOrExpenseItemCategoryId(operationsCategory.getId());
			depositItem.setName(incomeOrExpenseItemName);
			depositItem = dataService.saveIncomeOrExpenseItem(depositItem);
		}

		// Return the result;
		return depositItem.getId();
	}
}
