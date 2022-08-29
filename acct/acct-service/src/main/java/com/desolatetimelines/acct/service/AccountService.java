package com.desolatetimelines.acct.service;

import com.desolatetimelines.acct.service.Exception.AccountServiceBusinessException;
import com.desolatetimelines.acct.service.Exception.AccountServiceException;
import com.desolatetimelines.acct.service.Exception.AccountServiceValidationException;
import com.desolatetimelines.acct.service.currency.CurrencyExtractor;
import com.desolatetimelines.acct.service.currency.CurrencyExtractorHistoryRecord;
import com.desolatetimelines.acct.service.currency.CurrencyType;
import com.desolatetimelines.acct.service.currency.exception.CurrencyExtractorException;
import com.desolatetimelines.acct.service.dao.AccountDataService;
import com.desolatetimelines.acct.service.dao.common.Nameable;
import com.desolatetimelines.acct.service.dao.model.*;
import com.desolatetimelines.acct.service.delegate.AccountsHelper;
import com.desolatetimelines.acct.service.delegate.DepositsHelper;
import com.desolatetimelines.acct.service.delegate.IncomeOrExpenseItemsHelper;
import com.desolatetimelines.acct.service.model.AccountSummary;
import org.apache.commons.lang3.time.DateUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.desolatetimelines.acct.service.delegate.CommonOperations.checkAccountAvailableAmount;
import static com.desolatetimelines.acct.service.delegate.CommonOperations.resolveOperationIncomeOrExpenseItemId;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class AccountService {

	/*
	 * Flag to stop currency history collection if one such job is already running
	 * on the current instance - will avoid nasty stuff such as duplication of
	 * history records
	 */
	private static volatile boolean currencyHistoryCollectionIsRunning = false;

	private AccountDataService dataService;

	private final Map<String, CurrencyExtractor> currencyExtractorsByBankName = new HashMap<>();

	public AccountDataService getDataService() {
		return dataService;
	}

	public void setDataService(AccountDataService dataService) {
		this.dataService = dataService;
	}

	public void putCurrencyExtractor(String bankName, CurrencyExtractor currencyExtractor) {
		currencyExtractorsByBankName.put(bankName, currencyExtractor);
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
		return
			dataService.getIncomeOrExpenseItems()
				.sorted(Comparator
						.comparingLong(IncomeOrExpenseItem::getIncomeOrExpenseItemCategoryId)
						.thenComparing(Nameable::getName)
					);
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
		return dataService.getAccountRecords(accountId).sorted(Comparator.comparing(AccountRecord::getDate));
	}

	public Stream<AccountRecord> pageAccountRecordsOrderByDate(Long accountId, int pageNumber, int rowsPerPage) {
		return dataService.pageAccountRecordsOrderByDate(accountId, pageNumber, rowsPerPage);
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
		boolean isForeignCurrency
			= listAccounts()
				.filter(acct -> acct.getId().equals(accountRecord.getAccountId()))
				.anyMatch(Account::isForeignCurrencyAccount);

		validateAccountRecord(accountRecord, isForeignCurrency);

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
		return Arrays.stream(CurrencyType.values()).map(CurrencyType::getValue);
	}

	public Stream<MonitoredCurrency> getAllMonitoredCurrencies() {
		return dataService.getAllMonitoredCurrencies();
	}

	public MonitoredCurrency monitorCurrency(String currencyName, Long bankId)
			throws AccountServiceBusinessException
	{
		// Get the currency type
		CurrencyType currencyType;
		try {
			currencyType = CurrencyType.valueOf(currencyName);
		} catch (IllegalArgumentException exc) {
			throw new AccountServiceBusinessException("The requested currency is not supported", exc);
		}

		// Check if the currency is already monitored
		MonitoredCurrency currency
			= dataService.getMonitoredCurrency(currencyName, bankId);

		// If the currency is not already monitored, then start monitoring it
		if (currency == null) {
			currency = dataService.newMonitoredCurrency();
			currency.setCurrencyTypeName(currencyType.getValue());
			currency.setBankId(bankId);

			currency = dataService.saveMonitoredCurrency(currency);
		}

		// Return a reference to the monitored currency
		return currency;
	}

	public void unmonitorCurrency(Long monitoredCurrencyId) {
		dataService.deleteMonitoredCurrencyRecords(monitoredCurrencyId);
		dataService.deleteMonitoredCurrency(monitoredCurrencyId);
	}

	/**
	 * Updates the monitored currencies form the {@link CurrencyExtractor}
	 * defined for the referenced bank. If there is no {@link CurrencyExtractor}
	 * in the application context that can handle the referenced bank, an
	 * exception is thrown. If there is no bank ID provided, then it updates
	 * the monitored currencies for all the banks registered in the system.
	 */
	public void updateCurrenciesFromSource(Long bankId) throws AccountServiceException {
		if (currencyHistoryCollectionIsRunning) {
			return;
		}

		currencyHistoryCollectionIsRunning = true;

		try {
			// Get the monitored currencies grouped by bank ID.
			// Get monitored currencies for all banks, unless
			// the bankId was provided, in which case get only
			// the monitored currencies for the referenced bank.
			Map<Long, List<MonitoredCurrency>> currenciesByBankId
				= dataService.getAllMonitoredCurrencies()
					.filter(currency -> bankId == null || bankId.equals(currency.getBankId()))
					.collect(groupingBy(MonitoredCurrency::getBankId));

			if (currenciesByBankId.isEmpty()) {
				throw new AccountServiceBusinessException("There are no currencies currently being monitored.");
			}

			// Get the bank names grouped by bank ID
			Map<Long, String> bankNamesByBankId
				= dataService.getAllBanks()
					.collect(toMap(Bank::getId, Bank::getName));

			if (bankNamesByBankId.isEmpty()) {
				throw new AccountServiceBusinessException("There are no banks defined.");
			}

			// Gather the currency history from the extractors of each bank
			currenciesByBankId.forEach((dbBankId, currencies) -> {
				// Get the currency extractor
				CurrencyExtractor currencyExtractor
					= getCurrencyExtractorForBankId(dbBankId, bankNamesByBankId);

				// Get the history of each currency
				currencies.forEach(cur -> {
					// Retrieve the currency history from the extractor and register
					// the history that's missing from the local data source, while
					// also returning the last record retrieved for the next step
					CurrencyExtractorHistoryRecord lastExtractedRecord
						= updateCurrencyHistory(cur, currencyExtractor);

					// Update the snapshot in the local data source with information
					// from the last historical record retrieved by the extractor
					updateMonitoredCurrency(cur, lastExtractedRecord);
				});
			});
		} catch (Exception e) {
			currencyHistoryCollectionIsRunning = false;
			throw new AccountServiceBusinessException("Could not extract currency data: " + e.getMessage());
		}

		currencyHistoryCollectionIsRunning = false;
	}

	private CurrencyExtractor getCurrencyExtractorForBankId(
		Long bankId,
		Map<Long, String> bankNamesByBankId
	) {
		// Get the bank name
		String bankName = bankNamesByBankId.get(bankId);
		if (bankName == null) {
			throw new AccountServiceBusinessException("Bank with id = [" + bankId + "] not found");
		}

		// Get currency extractor
		CurrencyExtractor currencyExtractor = currencyExtractorsByBankName.get(bankName);
		if (currencyExtractor == null) {
			throw new AccountServiceBusinessException(
					"There is no currency extractor defined for bank [" + bankName + "]"
			);
		}

		// Return the currency extractor
		return currencyExtractor;
	}

	private void updateMonitoredCurrency(
		MonitoredCurrency monitoredCurrency,
		CurrencyExtractorHistoryRecord lastHistoryRecord
	) {
		monitoredCurrency.setLastCollectedDate(lastHistoryRecord.getDate());
		monitoredCurrency.setLastCollectedValue(lastHistoryRecord.getValue());

		dataService.saveMonitoredCurrency(monitoredCurrency);
	}

	private CurrencyExtractorHistoryRecord updateCurrencyHistory(
		MonitoredCurrency currency,
		CurrencyExtractor currencyExtractor
	) throws RuntimeException {
		try {
			// Get the currency type to be provided to the currency extractor
			CurrencyType cType = CurrencyType.valueOf(currency.getCurrencyTypeName());

			// Call the currency extractor to get the latest exchange rates history records
			List<CurrencyExtractorHistoryRecord> recs = currencyExtractor.fetchLatestRecords(cType);

			if (recs == null) {
				throw new RuntimeException(new AccountServiceException("The currency extractor has malfunctioned"));
			}

			// Truncate the dates of each of the received records to eliminate the time
			// Sort the records by date to allow easy extraction of the earliest and latest record
			recs = recs.stream()
						.peek(rec -> rec.setDate(DateUtils.truncate(rec.getDate(), Calendar.DAY_OF_MONTH)))
						.sorted(Comparator.comparing(CurrencyExtractorHistoryRecord::getDate))
						.collect(Collectors.toList());

			// Remove any pre-existing records since the earliest date until today
			dataService.deleteMonitoredCurrencyRecordsSinceDate(currency.getId(), recs.get(0).getDate());

			// Add the newly fetched records to the repository
			dataService.saveAllCurrencyHistoryRecords(
								recs.stream()
										.map(rec -> newCurrencyHistoryRecord(currency, rec, dataService))
										.collect(Collectors.toList())
							);

			// Return the last record for further processing
			return recs.get(recs.size() - 1);

		} catch (CurrencyExtractorException exc) {
			throw new RuntimeException(new AccountServiceException(exc.getMessage(), exc));
		}
	}

	private static CurrencyHistoryRecord newCurrencyHistoryRecord(
		MonitoredCurrency currency,
		CurrencyExtractorHistoryRecord currencyExtractorRecord,
		AccountDataService dataService
	) {
		CurrencyHistoryRecord newRec = dataService.newCurrencyHistoryRecord();

		newRec.setCurrencyId(currency.getId());
		newRec.setDate(currencyExtractorRecord.getDate());
		newRec.setValue(currencyExtractorRecord.getValue());

		return newRec;
	}

	public Stream<CurrencyHistoryRecord> getCurrencyHistory(Long monitoredCurrencyId, Date sinceDate) {
		return dataService.getCurrencyHistoryRecords(monitoredCurrencyId, sinceDate)
				.sorted(Comparator.comparing(CurrencyHistoryRecord::getDate));
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

	private static void validateAccountRecord(AccountRecord accountRecord, boolean isForeignCurrencyAccount) throws AccountServiceValidationException {
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

		if (isForeignCurrencyAccount && accountRecord.getExchangeRate() == null) {
			throw new AccountServiceValidationException("Exchange rate must be provided");
		}
	}
}
