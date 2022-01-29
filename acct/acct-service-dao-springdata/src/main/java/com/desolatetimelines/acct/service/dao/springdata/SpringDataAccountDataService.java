package com.desolatetimelines.acct.service.dao.springdata;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;

import com.desolatetimelines.acct.service.dao.AccountDataService;
import com.desolatetimelines.acct.service.dao.model.Account;
import com.desolatetimelines.acct.service.dao.model.AccountRecord;
import com.desolatetimelines.acct.service.dao.model.Bank;
import com.desolatetimelines.acct.service.dao.model.CurrencyHistoryRecord;
import com.desolatetimelines.acct.service.dao.model.Deposit;
import com.desolatetimelines.acct.service.dao.model.IncomeOrExpenseItem;
import com.desolatetimelines.acct.service.dao.model.IncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.service.dao.model.InterestHistoryRecord;
import com.desolatetimelines.acct.service.dao.model.MonitoredCurrency;
import com.desolatetimelines.acct.service.dao.springdata.model.SpringDataAccount;
import com.desolatetimelines.acct.service.dao.springdata.model.SpringDataAccountRecord;
import com.desolatetimelines.acct.service.dao.springdata.model.SpringDataBank;
import com.desolatetimelines.acct.service.dao.springdata.model.SpringDataCurrencyHistoryRecord;
import com.desolatetimelines.acct.service.dao.springdata.model.SpringDataDeposit;
import com.desolatetimelines.acct.service.dao.springdata.model.SpringDataIncomeOrExpenseItem;
import com.desolatetimelines.acct.service.dao.springdata.model.SpringDataIncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.service.dao.springdata.model.SpringDataInterestHistoryRecord;
import com.desolatetimelines.acct.service.dao.springdata.model.SpringDataMonitoredCurrency;
import com.desolatetimelines.acct.service.dao.springdata.repository.SpringDataAccountRecordsRepository;
import com.desolatetimelines.acct.service.dao.springdata.repository.SpringDataAccountsRepository;
import com.desolatetimelines.acct.service.dao.springdata.repository.SpringDataBanksRepository;
import com.desolatetimelines.acct.service.dao.springdata.repository.SpringDataCurrencyHistoryRecordsRepository;
import com.desolatetimelines.acct.service.dao.springdata.repository.SpringDataDepositsRepository;
import com.desolatetimelines.acct.service.dao.springdata.repository.SpringDataIncomeOrExpenseItemCategoriesRepository;
import com.desolatetimelines.acct.service.dao.springdata.repository.SpringDataIncomeOrExpenseItemsRepository;
import com.desolatetimelines.acct.service.dao.springdata.repository.SpringDataInterestHistoryRecordsRepository;
import com.desolatetimelines.acct.service.dao.springdata.repository.SpringDataMonitoredCurrenciesRepository;

import static java.util.function.Function.identity;

@ManagedBean("SpringDataAccountDataService")
public class SpringDataAccountDataService implements AccountDataService {

	@Autowired
	private SpringDataAccountsRepository accountsRepository;

	@Autowired
	private SpringDataIncomeOrExpenseItemCategoriesRepository categoriesRepository;

	@Autowired
	private SpringDataIncomeOrExpenseItemsRepository itemsRepository;

	@Autowired
	private SpringDataAccountRecordsRepository accountRecordsRepository;

	@Autowired
	private SpringDataBanksRepository banksRepository;

	@Autowired
	private SpringDataInterestHistoryRecordsRepository interestHistoryRecordsRepository;

	@Autowired
	private SpringDataDepositsRepository depositsRepository;

	@Autowired
	private SpringDataMonitoredCurrenciesRepository monitoredCurrenciesRepository;

	@Autowired
	private SpringDataCurrencyHistoryRecordsRepository currencyHistoryRecordsRepository;

	@Override
	@Retryable
	public Stream<Account> getAllAccounts() {
		return StreamSupport.stream(accountsRepository.findAll().spliterator(), false).map(identity());
	}

	@Override
	@Retryable
	public Account newAccountInstance() {
		return new SpringDataAccount();
	}

	@Override
	@Retryable
	public Account saveAccount(Account account) {
		return accountsRepository.save((SpringDataAccount) account);
	}

	@Override
	@Retryable
	public void deleteAccount(Long accountId) {
		accountsRepository.deleteById(accountId);
	}

	@Override
	@Retryable
	public Stream<IncomeOrExpenseItemCategory> getAllIncomeOrExpenseCategories() {
		return StreamSupport.stream(categoriesRepository.findAll().spliterator(), false)
				.map(identity());
	}

	@Override
	@Retryable
	public IncomeOrExpenseItemCategory newIncomeOrExpenseItemCategoryInstance() {
		return new SpringDataIncomeOrExpenseItemCategory();
	}

	@Override
	@Retryable
	public IncomeOrExpenseItemCategory saveIncomeOrExpenseItemCategory(
			IncomeOrExpenseItemCategory incomeOrExpenseItemCategory) {
		return categoriesRepository.save((SpringDataIncomeOrExpenseItemCategory) incomeOrExpenseItemCategory);
	}

	@Override
	@Retryable
	public IncomeOrExpenseItemCategory getIncomeOrExpenseItemCategory(Long id) {
		return categoriesRepository.findById(id).orElse(null);
	}

	@Override
	@Retryable
	public void deleteIncomeOrExpenseItemCategory(Long incomeOrExpenseItemCategoryId) {
		categoriesRepository.deleteById(incomeOrExpenseItemCategoryId);
	}

	@Override
	@Retryable
	public Stream<IncomeOrExpenseItem> getIncomeOrExpenseItems() {
		return StreamSupport.stream(itemsRepository.findAll().spliterator(), false).map(identity());
	}

	@Override
	@Retryable
	public IncomeOrExpenseItem getIncomeOrExpenseItem(Long id) {
		return itemsRepository.findById(id).orElse(null);
	}

	@Override
	@Retryable
	public IncomeOrExpenseItem newIncomeOrExpenseItemInstance() {
		return new SpringDataIncomeOrExpenseItem();
	}

	@Override
	@Retryable
	public IncomeOrExpenseItem saveIncomeOrExpenseItem(IncomeOrExpenseItem incomeOrExpenseItem) {
		return itemsRepository.save((SpringDataIncomeOrExpenseItem) incomeOrExpenseItem);
	}

	@Override
	@Retryable
	public void deleteIncomeOrExpenseItem(Long incomeOrExpenseItemId) {
		itemsRepository.deleteById(incomeOrExpenseItemId);
	}

	@Override
	@Retryable
	public Stream<AccountRecord> getAccountRecords(Long accountId) {
		return StreamSupport.stream(accountRecordsRepository.findAllByAccountId(accountId).spliterator(), false)
				.map(identity());
	}

	@Override
	@Retryable
	public Stream<AccountRecord> getAccountRecords(Long accountId, Date sinceDate) {
		return StreamSupport.stream(
				accountRecordsRepository.findAllByAccountIdAndDateGreaterThanEqual(accountId, sinceDate).spliterator(),
				false).map(identity());
	}

	@Override
	@Retryable
	public AccountRecord newAccountRecordInstance() {
		return new SpringDataAccountRecord();
	}

	@Override
	@Retryable
	public AccountRecord saveAccountRecord(AccountRecord accountRecord) {
		return accountRecordsRepository.save((SpringDataAccountRecord) accountRecord);
	}

	@Override
	@Retryable
	public void deleteAccountRecord(Long accountRecordId) {
		accountRecordsRepository.deleteById(accountRecordId);
	}

	@Override
	@Retryable
	public Bank newBank() {
		return new SpringDataBank();
	}

	@Override
	@Retryable
	public Stream<Bank> getAllBanks() {
		return StreamSupport.stream(banksRepository.findAll().spliterator(), false).map(identity());
	}

	@Override
	@Retryable
	public Bank saveBank(Bank bank) {
		return banksRepository.save((SpringDataBank) bank);
	}

	@Override
	@Retryable
	public void deleteBank(Long bankId) {
		banksRepository.deleteById(bankId);
	}

	@Override
	@Retryable
	public InterestHistoryRecord newInterestHistoryRecord() {
		return new SpringDataInterestHistoryRecord();
	}

	@Override
	@Retryable
	public Stream<InterestHistoryRecord> getInterestHistoryRecords(Long bankId) {
		return StreamSupport.stream(interestHistoryRecordsRepository.findAllByBankId(bankId).spliterator(), false)
				.map(identity());
	}

	@Override
	@Retryable
	public InterestHistoryRecord saveInterestHistoryRecord(InterestHistoryRecord interestHistoryRecord) {
		return interestHistoryRecordsRepository.save((SpringDataInterestHistoryRecord) interestHistoryRecord);
	}

	@Override
	@Retryable
	public void deleteInterestHistoryRecord(Long interestHisotryRecordId) {
		interestHistoryRecordsRepository.deleteById(interestHisotryRecordId);
	}

	@Override
	@Retryable
	public Deposit newDeposit() {
		return new SpringDataDeposit();
	}

	@Override
	@Retryable
	public Stream<Deposit> getAllDeposits() {
		return StreamSupport.stream(depositsRepository.findAll().spliterator(), true).map(identity());
	}

	@Override
	@Retryable
	public Stream<Deposit> getAllDepositsWithFutureEndDate() {
		return StreamSupport
				.stream(depositsRepository.findAllByEndDateGreaterThanEqual(new Date()).spliterator(), false)
				.map(identity());
	}

	@Override
	@Retryable
	public Deposit saveDeposit(Deposit deposit) {
		return depositsRepository.save((SpringDataDeposit) deposit);
	}

	@Override
	@Retryable
	public void deleteDeposit(Long depositId) {
		depositsRepository.deleteById(depositId);
	}

	@Override
	@Retryable
	public MonitoredCurrency newMonitoredCurrency() {
		return new SpringDataMonitoredCurrency();
	}

	@Override
	@Retryable
	public MonitoredCurrency getMonitoredCurrency(String currencyName) {
		return monitoredCurrenciesRepository.findOneByCurrencyTypeName(currencyName);
	}

	@Override
	@Retryable
	public Stream<MonitoredCurrency> getAllMonitoredCurrencies() {
		return StreamSupport.stream(monitoredCurrenciesRepository.findAll().spliterator(), true)
				.map(identity());
	}

	@Override
	@Retryable
	public MonitoredCurrency saveMonitoredCurrency(MonitoredCurrency currency) {
		return monitoredCurrenciesRepository.save((SpringDataMonitoredCurrency) currency);
	}

	@Override
	@Retryable
	public void deleteMonitoredCurrency(Long currencyId) {
		monitoredCurrenciesRepository.deleteById(currencyId);
	}

	@Override
	@Retryable
	public CurrencyHistoryRecord newCurrencyHistoryRecord() {
		return new SpringDataCurrencyHistoryRecord();
	}

	@Override
	public void saveAllCurrencyHistoryRecords(List<CurrencyHistoryRecord> records) {
		currencyHistoryRecordsRepository
				.saveAll(records.stream().map(x -> (SpringDataCurrencyHistoryRecord) x).collect(Collectors.toList()));
	}

	@Override
	@Retryable
	public Stream<CurrencyHistoryRecord> getCurrencyHistoryRecords(Long currencyId, Date sinceDate) {
		return StreamSupport
				.stream(currencyHistoryRecordsRepository
						.findAllByCurrencyIdAndDateGreaterThanEqual(currencyId, sinceDate).spliterator(), false)
				.map(identity());
	}

	@Override
	@Retryable
	public CurrencyHistoryRecord saveCurrencyHistoryRecord(CurrencyHistoryRecord rec) {
		return currencyHistoryRecordsRepository.save((SpringDataCurrencyHistoryRecord) rec);
	}

	@Override
	@Retryable
	public void deleteMonitoredCurrencyRecords(Long currencyId) {
		currencyHistoryRecordsRepository.deleteAllByCurrencyId(currencyId);
	}

}
