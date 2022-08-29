package com.desolatetimelines.acct.service.dao;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.desolatetimelines.acct.service.dao.model.Account;
import com.desolatetimelines.acct.service.dao.model.AccountRecord;
import com.desolatetimelines.acct.service.dao.model.Bank;
import com.desolatetimelines.acct.service.dao.model.CurrencyHistoryRecord;
import com.desolatetimelines.acct.service.dao.model.Deposit;
import com.desolatetimelines.acct.service.dao.model.IncomeOrExpenseItem;
import com.desolatetimelines.acct.service.dao.model.IncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.service.dao.model.InterestHistoryRecord;
import com.desolatetimelines.acct.service.dao.model.MonitoredCurrency;

public interface AccountDataService {
	Stream<Account> getAllAccounts();

	Account newAccountInstance();

	Account saveAccount(Account account);

	void deleteAccount(Long accountId);

	Stream<IncomeOrExpenseItemCategory> getAllIncomeOrExpenseCategories();

	IncomeOrExpenseItemCategory newIncomeOrExpenseItemCategoryInstance();

	IncomeOrExpenseItemCategory saveIncomeOrExpenseItemCategory(
			IncomeOrExpenseItemCategory incomeOrExpenseItemCategory);

	IncomeOrExpenseItemCategory getIncomeOrExpenseItemCategory(Long id);

	void deleteIncomeOrExpenseItemCategory(Long incomeOrExpenseItemCategoryId);

	Stream<IncomeOrExpenseItem> getIncomeOrExpenseItems();

	IncomeOrExpenseItem getIncomeOrExpenseItem(Long id);

	IncomeOrExpenseItem newIncomeOrExpenseItemInstance();

	IncomeOrExpenseItem saveIncomeOrExpenseItem(IncomeOrExpenseItem incomeOrExpenseItem);

	void deleteIncomeOrExpenseItem(Long incomeOrExpenseItemId);

	Stream<AccountRecord> getAccountRecords(Long accountId);

	Stream<AccountRecord> getAccountRecords(Long accountId, Date sinceDate);

	Stream<AccountRecord> pageAccountRecordsOrderByDate(Long accountId, int pageNumber, int rowsPerPage);

	AccountRecord newAccountRecordInstance();

	AccountRecord saveAccountRecord(AccountRecord accountRecord);

	void deleteAccountRecord(Long accountRecordId);

	Bank newBank();

	Stream<Bank> getAllBanks();

	Bank saveBank(Bank bank);

	void deleteBank(Long bankId);

	InterestHistoryRecord newInterestHistoryRecord();

	Stream<InterestHistoryRecord> getInterestHistoryRecords(Long bankId);

	InterestHistoryRecord saveInterestHistoryRecord(InterestHistoryRecord interestHistoryRecord);

	void deleteInterestHistoryRecord(Long interestHisotryRecordId);

	Deposit newDeposit();

	Stream<Deposit> getAllDeposits();

	Stream<Deposit> getAllDepositsWithFutureEndDate();

	Deposit saveDeposit(Deposit deposit);

	void deleteDeposit(Long depositId);

	MonitoredCurrency newMonitoredCurrency();

	Stream<? extends MonitoredCurrency> getMonitoredCurrencies(String currencyName);

	MonitoredCurrency getMonitoredCurrency(String currencyName, Long bankId);

	Stream<MonitoredCurrency> getAllMonitoredCurrencies();

	MonitoredCurrency saveMonitoredCurrency(MonitoredCurrency currency);

	void deleteMonitoredCurrency(Long currencyId);

	CurrencyHistoryRecord newCurrencyHistoryRecord();

	Stream<CurrencyHistoryRecord> getCurrencyHistoryRecords(Long currencyId, Date sinceDate);

	CurrencyHistoryRecord saveCurrencyHistoryRecord(CurrencyHistoryRecord rec);

	void saveAllCurrencyHistoryRecords(List<CurrencyHistoryRecord> records);

	void deleteMonitoredCurrencyRecords(Long currencyId);

	void deleteMonitoredCurrencyRecordsSinceDate(Long currencyId, Date sinceDate);

}
