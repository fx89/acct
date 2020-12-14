package com.desolatetimelines.acct.service;

import static com.desolatetimelines.acct.service.delegate.CommonOperations.checkAccountAvailableAmount;
import static com.desolatetimelines.acct.service.delegate.CommonOperations.resolveOperationIncomeOrExpenseItemId;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.desolatetimelines.acct.service.Exception.AccountServiceBusinessException;
import com.desolatetimelines.acct.service.Exception.AccountServiceException;
import com.desolatetimelines.acct.service.Exception.AccountServiceValidationException;
import com.desolatetimelines.acct.service.currency.CurrencyExtractor;
import com.desolatetimelines.acct.service.currency.CurrencyExtractorHistoryRecord;
import com.desolatetimelines.acct.service.currency.CurrencyType;
import com.desolatetimelines.acct.service.currency.exception.CurrencyExtractorException;
import com.desolatetimelines.acct.service.dao.AccountDataService;
import com.desolatetimelines.acct.service.dao.common.Nameable;
import com.desolatetimelines.acct.service.dao.model.Account;
import com.desolatetimelines.acct.service.dao.model.AccountRecord;
import com.desolatetimelines.acct.service.dao.model.Bank;
import com.desolatetimelines.acct.service.dao.model.CurrencyHistoryRecord;
import com.desolatetimelines.acct.service.dao.model.Deposit;
import com.desolatetimelines.acct.service.dao.model.IncomeOrExpenseItem;
import com.desolatetimelines.acct.service.dao.model.IncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.service.dao.model.InterestHistoryRecord;
import com.desolatetimelines.acct.service.dao.model.MonitoredCurrency;
import com.desolatetimelines.acct.service.delegate.AccountsHelper;
import com.desolatetimelines.acct.service.delegate.DepositsHelper;
import com.desolatetimelines.acct.service.delegate.IncomeOrExpenseItemsHelper;
import com.desolatetimelines.acct.service.model.AccountSummary;

public class AccountService {

	/*
	 * Flag to stop currency history collection if one such job is already running
	 * on the current instance - will avoid nasty stuff such as duplication of
	 * history records
	 */
	private static volatile boolean currencyHistoryCollectionIsRunning = false;

	private AccountDataService dataService;

	private CurrencyExtractor currencyExtractor;

	public AccountDataService getDataService() {
		return dataService;
	}

	public void setDataService(AccountDataService dataService) {
		this.dataService = dataService;
	}

	public CurrencyExtractor getCurrencyExtractor() {
		return currencyExtractor;
	}

	public void setCurrencyExtractor(CurrencyExtractor currencyExtractor) {
		this.currencyExtractor = currencyExtractor;
	}

	public Stream<Account> listAccounts() {
		return dataService.getAllAccounts();
	}

	public Account newAccount(String name) throws AccountServiceValidationException {
		Account account = dataService.newAccountInstance();
		account.setName(name);
		return saveAccount(account);
	}

	public void deleteAccount(Long accountId) {
		dataService.deleteAccount(accountId);
	}

	public Account saveAccount(Account account) throws AccountServiceValidationException {
		validateNameable(account, "account");

		Account acct = dataService.newAccountInstance();
		acct.copyFrom(account);

		return dataService.saveAccount(acct);
	}

	public void transferAmount(Long fromAccountId, Long toAccountId, Double value)
			throws AccountServiceBusinessException {
		List<Account> accounts = dataService.getAllAccounts().collect(Collectors.toList());
		chechAccount(fromAccountId, accounts);
		chechAccount(toAccountId, accounts);

		checkAccountAvailableAmount(this, fromAccountId, value);

		Long opId = resolveOperationIncomeOrExpenseItemId(this, dataService, "Transfer");

		AccountRecord fromRec = dataService.newAccountRecordInstance();
		fromRec.setAccountId(fromAccountId);
		fromRec.setDate(new Date());
		fromRec.setValue(-value);
		fromRec.setIncomeOrExpenseItemId(opId);

		dataService.saveAccountRecord(fromRec);

		AccountRecord toRec = dataService.newAccountRecordInstance();
		toRec.setAccountId(toAccountId);
		toRec.setDate(new Date());
		toRec.setValue(value);
		toRec.setIncomeOrExpenseItemId(opId);

		dataService.saveAccountRecord(toRec);
	}

	private void chechAccount(Long accountId, List<Account> accounts) throws AccountServiceBusinessException {
		Account acct = accounts.stream().filter(ac -> ac.getId().equals(accountId)).findFirst().orElse(null);

		if (acct == null) {
			throw new AccountServiceBusinessException("Account with id [" + accountId + "] not found");
		}
	}

	public Stream<IncomeOrExpenseItemCategory> listIncomeOrExpenseItemCategories() {
		return dataService.getAllIncomeOrExpenseCategories();
	}

	public IncomeOrExpenseItemCategory newIncomeOrExpenseItemCategory(String name)
			throws AccountServiceValidationException {
		IncomeOrExpenseItemCategory category = dataService.newIncomeOrExpenseItemCategoryInstance();
		category.setName(name);
		return saveIncomeOrExpenseItemCategory(category);
	}

	public void deleteIncomeOrExpenseCategory(Long categoryId) {
		dataService.deleteIncomeOrExpenseItemCategory(categoryId);
	}

	public IncomeOrExpenseItemCategory saveIncomeOrExpenseItemCategory(
			IncomeOrExpenseItemCategory incomeOrExpenseItemCategory) throws AccountServiceValidationException {
		validateNameable(incomeOrExpenseItemCategory, "income or expense item category");

		IncomeOrExpenseItemCategory cat = dataService.newIncomeOrExpenseItemCategoryInstance();
		cat.copyFrom(incomeOrExpenseItemCategory);

		return dataService.saveIncomeOrExpenseItemCategory(cat);
	}

	public Stream<IncomeOrExpenseItem> listIncomeOrExpenseItems() {
		return dataService.getIncomeOrExpenseItems().sorted((a, b) -> {
			int result = a.getIncomeOrExpenseItemCategoryId().compareTo(b.getIncomeOrExpenseItemCategoryId());

			if (result == 0) {
				result = a.getName().compareTo(b.getName());
			}

			return result;
		});
	}

	public IncomeOrExpenseItem newIncomeOrExpenseItem(String name, Long incomeOrExpenseItemCategoryId)
			throws AccountServiceValidationException {
		IncomeOrExpenseItem item = dataService.newIncomeOrExpenseItemInstance();
		item.setName(name);
		item.setIncomeOrExpenseItemCategoryId(incomeOrExpenseItemCategoryId);
		return saveIncomeOrExpenseItem(item);
	}

	public void deleteIncomeOrExpenseItem(Long incomeOrExpenseItemId) {
		dataService.deleteIncomeOrExpenseItem(incomeOrExpenseItemId);
	}

	public IncomeOrExpenseItem saveIncomeOrExpenseItem(IncomeOrExpenseItem incomeOrExpenseItem)
			throws AccountServiceValidationException {
		validateIncomeOrExpenseItem(incomeOrExpenseItem);

		IncomeOrExpenseItem item = dataService.newIncomeOrExpenseItemInstance();
		item.copyFrom(incomeOrExpenseItem);
		item.setId(incomeOrExpenseItem.getId());

		IncomeOrExpenseItemsHelper.beforeIncomeOrExpenseItemSave(this, dataService, item);
		return dataService.saveIncomeOrExpenseItem(item);
	}

	public Stream<AccountRecord> listAccountRecords(Long accountId) {
		return dataService.getAccountRecords(accountId).sorted((r1, r2) -> r1.getDate().compareTo(r2.getDate()));
	}

	public AccountRecord newAccountRecord(Long accountId, Date date, Long incomeOrExpenseItemId, Double value)
			throws AccountServiceValidationException {
		AccountRecord rec = dataService.newAccountRecordInstance();

		rec.setAccountId(accountId);
		rec.setDate(date);
		rec.setIncomeOrExpenseItemId(incomeOrExpenseItemId);
		rec.setValue(value);

		return saveAccountRecord(rec);
	}

	public AccountRecord saveAccountRecord(AccountRecord accountRecord) throws AccountServiceValidationException {
		validateAccountRecord(accountRecord);

		AccountRecord rec = dataService.newAccountRecordInstance();
		rec.copyFrom(accountRecord);

		rec = dataService.saveAccountRecord(rec);
		AccountsHelper.afterAccountRecordSaved(this, dataService, rec);
		return rec;
	}

	public void deleteAccountRecord(Long accountRecordId) {
		dataService.deleteAccountRecord(accountRecordId);
	}

	public Stream<Bank> listAllBanks() {
		return dataService.getAllBanks();
	}

	public Bank saveBank(Bank bank) throws AccountServiceValidationException {
		validateNameable(bank, "bank");

		Bank bnk = dataService.newBank();
		bnk.copyFrom(bank);

		return dataService.saveBank(bnk);
	}

	public void deleteBank(Long bankId) {
		dataService.deleteBank(bankId);
	}

	public Bank newBank(String name) throws AccountServiceValidationException {
		Bank bank = dataService.newBank();
		bank.setName(name);
		return saveBank(bank);
	}

	public Stream<Deposit> listAllActiveDeposits() {
		return dataService.getAllDepositsWithFutureEndDate();
	}

	public Deposit saveDeposit(Deposit deposit, Boolean saveOnlyAccountNumber)
			throws AccountServiceValidationException, AccountServiceBusinessException {
		validateDeposit(deposit);

		if (!saveOnlyAccountNumber) {
			DepositsHelper.beforeDepositCreated(this, dataService, deposit);
		}

		Deposit dep = dataService.newDeposit();
		dep.copyFrom(deposit);

		deposit = dataService.saveDeposit(dep);

		if (!saveOnlyAccountNumber) {
			DepositsHelper.afterDepositCreated(this, dataService, deposit);
		}

		return deposit;
	}

	public void deleteDeposit(Long depositId) {
		dataService.deleteDeposit(depositId);
	}

	public Stream<InterestHistoryRecord> listInterestHistoryRecords(Long bankId) {
		return dataService.getInterestHistoryRecords(bankId);
	}

	public AccountSummary getAccountSummary(Long accountId) {
		AccountSummary accountSummary = new AccountSummary();
		accountSummary.setRunningSumIncoming(0d);
		accountSummary.setRunningSumOutgoing(0d);

		Stream<AccountRecord> accountRecords = dataService.getAccountRecords(accountId);

		if (accountRecords != null) {
			accountRecords.forEach(accountRecord -> {
				if (accountRecord.getValue() > 0) {
					accountSummary
							.setRunningSumIncoming(accountSummary.getRunningSumIncoming() + accountRecord.getValue());
				} else {
					accountSummary
							.setRunningSumOutgoing(accountSummary.getRunningSumOutgoing() + accountRecord.getValue());
				}
			});
		}

		return accountSummary;
	}

	public Stream<String> getAllSupportedCurrencyTypes() {
		return Arrays.asList(CurrencyType.values()).stream().map(cur -> cur.getValue());
	}

	public Stream<MonitoredCurrency> getAllMonitoredCurrencies() {
		return dataService.getAllMonitoredCurrencies();
	}

	public MonitoredCurrency monitorCurrency(String currencyName) throws AccountServiceBusinessException {
		CurrencyType currencyType;

		try {
			currencyType = CurrencyType.valueOf(currencyName);
		} catch (IllegalArgumentException exc) {
			throw new AccountServiceBusinessException("The requested currency is not supported", exc);
		}

		MonitoredCurrency currency = dataService.getMonitoredCurrency(currencyName);

		if (currency == null) {
			currency = dataService.newMonitoredCurrency();
		}

		currency.setCurrencyTypeName(currencyType.getValue());

		return dataService.saveMonitoredCurrency(currency);
	}

	public void unmonitorCurrency(Long monitoredCurrencyId) {
		dataService.deleteMonitoredCurrencyRecords(monitoredCurrencyId);
		dataService.deleteMonitoredCurrency(monitoredCurrencyId);
	}

	public void updateCurrenciesFromSource() throws AccountServiceException {
		if (currencyHistoryCollectionIsRunning) {
			return;
		}

		currencyHistoryCollectionIsRunning = true;

		try {
			Stream<MonitoredCurrency> currencies = dataService.getAllMonitoredCurrencies();

			if (currencies == null) {
				throw new AccountServiceBusinessException("There no currencies currently being monitored.");
			}

			currencies.forEach(cur -> {
				CurrencyExtractorHistoryRecord lastExtractedRecord = updateCurrencyHistory(cur);
				updateMonitoredCurrency(cur, lastExtractedRecord);
			});
		} catch (Exception e) {
			currencyHistoryCollectionIsRunning = false;
			throw new AccountServiceBusinessException("Could not extract currency data: " + e.getMessage());
		}

		currencyHistoryCollectionIsRunning = false;
	}

	private void updateMonitoredCurrency(MonitoredCurrency mc, CurrencyExtractorHistoryRecord hr) {
		mc.setLastCollectedDate(hr.getDate());
		mc.setLastCollectedValue(hr.getValue());

		dataService.saveMonitoredCurrency(mc);
	}

	private CurrencyExtractorHistoryRecord updateCurrencyHistory(MonitoredCurrency currency) throws RuntimeException {
		try {
			// Get the currency type to be provided to the currency extractor
			CurrencyType cType = CurrencyType.valueOf(currency.getCurrencyTypeName());

			// Call the currency extractor to get the latest exchange rates history records
			List<CurrencyExtractorHistoryRecord> recs = currencyExtractor.fetchLatestRecords(cType);

			if (recs == null) {
				throw new RuntimeException(new AccountServiceException("The currency extractor has malfunctioned"));
			}

			// Initialize the list of new records to add
			List<CurrencyHistoryRecord> newRecs = new ArrayList<>();

			// Sort the list to be able to:
			// 1) get a minimum date for the database query
			// 2) be able to return the latest record
			recs.sort((d1, d2) -> d1.getDate().compareTo(d2.getDate()));

			// Get the minimum date so we can fetch any records already existing in the
			// databases
			Date minDate = recs.get(0).getDate();

			// Fetch any records already existing in the database, to be updated
			List<CurrencyHistoryRecord> registererdHistory = dataService
					.getCurrencyHistoryRecords(currency.getId(), minDate).collect(Collectors.toList());

			// Prepare the list of new records to be added
			// Continuously update the return value
			recs.forEach(rec -> {
				CurrencyHistoryRecord hstRec = registererdHistory.stream()
						.filter(e -> isSameDay(e.getDate(), rec.getDate())).findFirst().orElse(null);

				if (hstRec == null) {
					hstRec = dataService.newCurrencyHistoryRecord();
					hstRec.setCurrencyId(currency.getId());
					hstRec.setDate(rec.getDate());
					hstRec.setValue(rec.getValue());
					newRecs.add(hstRec);
				}
				;
			});

			// Add the new records to the repository
			dataService.saveAllCurrencyHistoryRecords(newRecs);

			// Return the last record for further processing
			return recs.get(recs.size() - 1);

		} catch (CurrencyExtractorException exc) {
			throw new RuntimeException(new AccountServiceException(exc.getMessage(), exc));
		}
	}

	/**
	 * Should be able to tell if two dates are in the same day even if one's a
	 * timestamp, the other's a date, etc
	 */
	private static boolean isSameDay(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		if (cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)) {
			return false;
		}

		if (cal1.get(Calendar.MONTH) != cal2.get(Calendar.MONTH)) {
			return false;
		}

		if (cal1.get(Calendar.DAY_OF_MONTH) != cal2.get(Calendar.DAY_OF_MONTH)) {
			return false;
		}

		return true;
	}

	public Stream<CurrencyHistoryRecord> getCurrencyHistory(Long monitoredCurrencyId, Date sinceDate) {
		return dataService.getCurrencyHistoryRecords(monitoredCurrencyId, sinceDate)
				.sorted((rec1, rec2) -> rec1.getDate().compareTo(rec2.getDate()));
	}

	private static void validateNameable(Nameable item, String itemTypeName) throws AccountServiceValidationException {
		String itmTypNme = (itemTypeName == null ? "item" : itemTypeName);

		if (item == null) {
			throw new AccountServiceValidationException("Invalid " + itmTypNme);
		}

		if (isBlank(item.getName())) {
			throw new AccountServiceValidationException("Blank " + itmTypNme + " names are not allowed");
		}
	}

	private static void validateDeposit(Deposit deposit) throws AccountServiceValidationException {
		if (deposit == null) {
			throw new AccountServiceValidationException("Invalid deposit");
		}

		if (deposit.getBankId() == null || deposit.getBankId() <= 0) {
			throw new AccountServiceValidationException("Invalid bank");
		}

		if (deposit.getSourceAccountId() == null || deposit.getSourceAccountId() <= 0) {
			throw new AccountServiceValidationException("Invalid source account");
		}

		if (deposit.getStartDate() == null) {
			throw new AccountServiceValidationException("Invalid start date");
		}

		if (deposit.getEndDate() == null) {
			throw new AccountServiceValidationException("Invalid end date");
		}

		if (deposit.getEndDate().compareTo(new Date()) <= 0) {
			throw new AccountServiceValidationException("End date must be in the future");
		}

		if (deposit.getEndDate().compareTo(deposit.getStartDate()) <= 0) {
			throw new AccountServiceValidationException("End date must be after the start date");
		}

		if (deposit.getValue() == null || deposit.getValue() <= 0) {
			throw new AccountServiceValidationException("Invalid value");
		}

		if (deposit.getInterestPercent() == null || deposit.getInterestPercent() <= 0
				|| deposit.getInterestPercent() > 10) {
			throw new AccountServiceValidationException("Invalid interest percentage - must be between 0.01 and 10.00");
		}
	}

	private static void validateIncomeOrExpenseItem(IncomeOrExpenseItem incomeOrExpenseItem)
			throws AccountServiceValidationException {

		validateNameable(incomeOrExpenseItem, "income or expense item");

		if (incomeOrExpenseItem.getIncomeOrExpenseItemCategoryId() == null) {
			throw new AccountServiceValidationException("Income or expense items ");
		}
	}

	private static void validateAccountRecord(AccountRecord accountRecord) throws AccountServiceValidationException {
		if (accountRecord == null) {
			throw new AccountServiceValidationException("Invalid account record");
		}

		if (accountRecord.getAccountId() == null || accountRecord.getAccountId() <= 0) {
			throw new AccountServiceValidationException("Invalid account id");
		}

		if (accountRecord.getDate() == null) {
			throw new AccountServiceValidationException("Invalid date");
		}

		if (accountRecord.getIncomeOrExpenseItemId() == null || accountRecord.getIncomeOrExpenseItemId() <= 0) {
			throw new AccountServiceValidationException("Invalid income or expense item id");
		}
	}
}
