package com.desolatetimelines.acct.service.dao;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.desolatetimelines.acct.service.dao.common.CommonAccountingEntityRepository;
import com.desolatetimelines.acct.service.dao.model.Account;
import com.desolatetimelines.acct.service.dao.model.AccountRecord;
import com.desolatetimelines.acct.service.dao.model.Bank;
import com.desolatetimelines.acct.service.dao.model.CurrencyHistoryRecord;
import com.desolatetimelines.acct.service.dao.model.Deposit;
import com.desolatetimelines.acct.service.dao.model.InMemoryAccount;
import com.desolatetimelines.acct.service.dao.model.InMemoryAccountRecord;
import com.desolatetimelines.acct.service.dao.model.InMemoryBank;
import com.desolatetimelines.acct.service.dao.model.InMemoryCurrencyHistoryRecord;
import com.desolatetimelines.acct.service.dao.model.InMemoryDeposit;
import com.desolatetimelines.acct.service.dao.model.InMemoryIncomeOrExpenseItem;
import com.desolatetimelines.acct.service.dao.model.InMemoryIncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.service.dao.model.InMemoryInterestHistoryRecord;
import com.desolatetimelines.acct.service.dao.model.InMemoryMonitoredCurrency;
import com.desolatetimelines.acct.service.dao.model.IncomeOrExpenseItem;
import com.desolatetimelines.acct.service.dao.model.IncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.service.dao.model.InterestHistoryRecord;
import com.desolatetimelines.acct.service.dao.model.MonitoredCurrency;

public class InMemoryAccountDataServiceImpl implements AccountDataService {

	private CommonAccountingEntityRepository<Account> accountsRepository = new CommonAccountingEntityRepository<>();

	private CommonAccountingEntityRepository<AccountRecord> accountRecordsRepository = new CommonAccountingEntityRepository<>();

	private CommonAccountingEntityRepository<IncomeOrExpenseItem> incomeOrExpenseItemsRepository = new CommonAccountingEntityRepository<>();

	private CommonAccountingEntityRepository<IncomeOrExpenseItemCategory> incomeOrExpenseItemCategoriesRepository = new CommonAccountingEntityRepository<>();

	private CommonAccountingEntityRepository<Bank> banksRepository = new CommonAccountingEntityRepository<>();

	private CommonAccountingEntityRepository<InterestHistoryRecord> interestHistoryRecordsRepository = new CommonAccountingEntityRepository<>();

	private CommonAccountingEntityRepository<Deposit> depositsRepository = new CommonAccountingEntityRepository<>();

	private CommonAccountingEntityRepository<MonitoredCurrency> monitoredCurrenciesRepository = new CommonAccountingEntityRepository<>();

	private CommonAccountingEntityRepository<CurrencyHistoryRecord> currencyHistoryRecordsRepository = new CommonAccountingEntityRepository<>();

	public InMemoryAccountDataServiceImpl() {

	}

	public InMemoryAccountDataServiceImpl withMockData() {
		this.initRepositories();
		return this;
	}

	@Override
	public Stream<Account> getAllAccounts() {
		return accountsRepository.values().stream();
	}

	@Override
	public Account newAccountInstance() {
		return new InMemoryAccount();
	}

	@Override
	public Account saveAccount(Account account) {
		return accountsRepository.save(account);
	}

	@Override
	public void deleteAccount(Long accountId) {
		accountsRepository.remove(accountId);
	}

	@Override
	public Stream<IncomeOrExpenseItemCategory> getAllIncomeOrExpenseCategories() {
		return incomeOrExpenseItemCategoriesRepository.values().stream();
	}

	@Override
	public IncomeOrExpenseItemCategory newIncomeOrExpenseItemCategoryInstance() {
		return new InMemoryIncomeOrExpenseItemCategory();
	}

	@Override
	public IncomeOrExpenseItemCategory saveIncomeOrExpenseItemCategory(
			IncomeOrExpenseItemCategory incomeOrExpenseItemCategory) {
		return incomeOrExpenseItemCategoriesRepository.save(incomeOrExpenseItemCategory);
	}

	@Override
	public IncomeOrExpenseItemCategory getIncomeOrExpenseItemCategory(Long id) {
		return incomeOrExpenseItemCategoriesRepository.get(id);
	}

	@Override
	public void deleteIncomeOrExpenseItemCategory(Long incomeOrExpenseItemCategoryId) {
		incomeOrExpenseItemCategoriesRepository.remove(incomeOrExpenseItemCategoryId);
	}

	@Override
	public Stream<IncomeOrExpenseItem> getIncomeOrExpenseItems() {
		return incomeOrExpenseItemsRepository.values().stream();
	}

	@Override
	public IncomeOrExpenseItem getIncomeOrExpenseItem(Long id) {
		return incomeOrExpenseItemsRepository.values().stream().filter(val -> val.getId().equals(id)).findFirst()
				.orElse(null);
	}

	@Override
	public IncomeOrExpenseItem newIncomeOrExpenseItemInstance() {
		return new InMemoryIncomeOrExpenseItem();
	}

	@Override
	public IncomeOrExpenseItem saveIncomeOrExpenseItem(IncomeOrExpenseItem incomeOrExpenseItem) {
		return incomeOrExpenseItemsRepository.save(incomeOrExpenseItem);
	}

	@Override
	public void deleteIncomeOrExpenseItem(Long incomeOrExpenseItemId) {
		incomeOrExpenseItemsRepository.remove(incomeOrExpenseItemId);
	}

	@Override
	public Stream<AccountRecord> getAccountRecords(Long accountId) {
		return accountRecordsRepository.values().stream().filter(rec -> rec.getAccountId() == accountId);
	}

	@Override
	public Stream<AccountRecord> getAccountRecords(Long accountId, Date sinceDate) {
		return getAccountRecords(accountId).filter(rec -> rec.getDate().compareTo(sinceDate) >= 0);
	}

	@Override
	public AccountRecord newAccountRecordInstance() {
		return new InMemoryAccountRecord();
	}

	@Override
	public AccountRecord saveAccountRecord(AccountRecord accountRecord) {
		return accountRecordsRepository.save(accountRecord);
	}

	@Override
	public void deleteAccountRecord(Long accountRecordId) {
		accountRecordsRepository.remove(accountRecordId);
	}

	@Override
	public Bank newBank() {
		return new InMemoryBank();
	}

	@Override
	public Stream<Bank> getAllBanks() {
		return banksRepository.values().stream();
	}

	@Override
	public Bank saveBank(Bank bank) {
		return banksRepository.save(bank);
	}

	@Override
	public void deleteBank(Long bankId) {
		banksRepository.remove(bankId);
	}

	@Override
	public InterestHistoryRecord newInterestHistoryRecord() {
		return new InMemoryInterestHistoryRecord();
	}

	@Override
	public Stream<InterestHistoryRecord> getInterestHistoryRecords(Long bankId) {
		return interestHistoryRecordsRepository.values().stream();
	}

	@Override
	public InterestHistoryRecord saveInterestHistoryRecord(InterestHistoryRecord interestHistoryRecord) {
		return interestHistoryRecordsRepository.save(interestHistoryRecord);
	}

	@Override
	public void deleteInterestHistoryRecord(Long interestHisotryRecordId) {
		interestHistoryRecordsRepository.remove(interestHisotryRecordId);
	}

	@Override
	public Deposit newDeposit() {
		return new InMemoryDeposit();
	}

	@Override
	public Stream<Deposit> getAllDeposits() {
		return depositsRepository.values().stream();
	}

	@Override
	public Stream<Deposit> getAllDepositsWithFutureEndDate() {
		Date today = new Date();

		Stream<Deposit> ret = depositsRepository.values().stream()
				.filter(dep -> dep.getEndDate().compareTo(today) >= 0);

		if (ret == null) {
			ret = new ArrayList<Deposit>().stream();
		}

		return ret;
	}

	@Override
	public Deposit saveDeposit(Deposit deposit) {
		return depositsRepository.save(deposit);
	}

	@Override
	public void deleteDeposit(Long depositId) {
		depositsRepository.remove(depositId);
	}

	@Override
	public MonitoredCurrency newMonitoredCurrency() {
		return new InMemoryMonitoredCurrency();
	}

	@Override
	public MonitoredCurrency getMonitoredCurrency(String currencyName) {
		return monitoredCurrenciesRepository.values().stream()
				.filter(val -> val.getCurrencyTypeName().equals(currencyName)).findFirst().orElse(null);
	}

	@Override
	public Stream<MonitoredCurrency> getAllMonitoredCurrencies() {
		return monitoredCurrenciesRepository.values().stream();
	}

	@Override
	public MonitoredCurrency saveMonitoredCurrency(MonitoredCurrency currency) {
		return monitoredCurrenciesRepository.save(currency);
	}

	@Override
	public void deleteMonitoredCurrency(Long currencyId) {
		monitoredCurrenciesRepository.remove(currencyId);
	}

	@Override
	public CurrencyHistoryRecord newCurrencyHistoryRecord() {
		return new InMemoryCurrencyHistoryRecord();
	}

	@Override
	public Stream<CurrencyHistoryRecord> getCurrencyHistoryRecords(Long currencyId, Date sinceDate) {
		return currencyHistoryRecordsRepository.values().stream()
				.filter(rec -> rec.getCurrencyId().equals(currencyId) && rec.getDate().compareTo(sinceDate) > 0);
	}

	@Override
	public CurrencyHistoryRecord saveCurrencyHistoryRecord(CurrencyHistoryRecord rec) {
		return currencyHistoryRecordsRepository.save(rec);
	}

	@Override
	public void saveAllCurrencyHistoryRecords(List<CurrencyHistoryRecord> records) {
		records.forEach(rec -> {
			saveCurrencyHistoryRecord(rec);
		});
	}

	@Override
	public void deleteMonitoredCurrencyRecords(Long currencyId) {
		List<Long> markedForDelete = new ArrayList<>(300);

		currencyHistoryRecordsRepository.values().forEach(rec -> {
			if (rec.getCurrencyId().equals(currencyId)) {
				markedForDelete.add(rec.getId());
			}
		});

		markedForDelete.forEach(id -> {
			currencyHistoryRecordsRepository.remove(id);
		});
	}

	private void initRepositories() {
		Account accIncome = newAccountInstance();
		accIncome.setName("Mock income account");
		accIncome = saveAccount(accIncome);

		Account accExpenses = newAccountInstance();
		accExpenses.setName("Mock expenses account");
		accExpenses = saveAccount(accExpenses);

		IncomeOrExpenseItemCategory catFixedIncome = newIncomeOrExpenseItemCategoryInstance();
		catFixedIncome.setName("Fixed income");
		catFixedIncome = saveIncomeOrExpenseItemCategory(catFixedIncome);

		IncomeOrExpenseItemCategory catUtilities = newIncomeOrExpenseItemCategoryInstance();
		catUtilities.setName("Utilities");
		catUtilities = saveIncomeOrExpenseItemCategory(catUtilities);

		IncomeOrExpenseItemCategory catMiscExpenses = newIncomeOrExpenseItemCategoryInstance();
		catMiscExpenses.setName("Misc expenses");
		catMiscExpenses = saveIncomeOrExpenseItemCategory(catMiscExpenses);

		IncomeOrExpenseItem itmMonthlyWage = newIncomeOrExpenseItemInstance();
		itmMonthlyWage.setIncomeOrExpenseItemCategoryId(catFixedIncome.getId());
		itmMonthlyWage.setName("Monthly wage");
		itmMonthlyWage = saveIncomeOrExpenseItem(itmMonthlyWage);

		IncomeOrExpenseItem itmLeasingIncome = newIncomeOrExpenseItemInstance();
		itmLeasingIncome.setIncomeOrExpenseItemCategoryId(catUtilities.getId());
		itmLeasingIncome.setName("Leasing income");
		itmLeasingIncome = saveIncomeOrExpenseItem(itmLeasingIncome);

		IncomeOrExpenseItem itmElectricityBill = newIncomeOrExpenseItemInstance();
		itmElectricityBill.setIncomeOrExpenseItemCategoryId(catUtilities.getId());
		itmElectricityBill.setName("Electricity bill");
		itmElectricityBill = saveIncomeOrExpenseItem(itmElectricityBill);

		IncomeOrExpenseItem itmTelecomBill = newIncomeOrExpenseItemInstance();
		itmTelecomBill.setIncomeOrExpenseItemCategoryId(catUtilities.getId());
		itmTelecomBill.setName("Telecom bill");
		itmTelecomBill = saveIncomeOrExpenseItem(itmTelecomBill);

		IncomeOrExpenseItem itmComputerParts = newIncomeOrExpenseItemInstance();
		itmComputerParts.setIncomeOrExpenseItemCategoryId(catMiscExpenses.getId());
		itmComputerParts.setName("Computer parts");
		itmComputerParts = saveIncomeOrExpenseItem(itmComputerParts);

		IncomeOrExpenseItem itmFurniture = newIncomeOrExpenseItemInstance();
		itmFurniture.setIncomeOrExpenseItemCategoryId(catMiscExpenses.getId());
		itmFurniture.setName("Furniture");
		itmFurniture = saveIncomeOrExpenseItem(itmFurniture);

		AccountRecord rec = newAccountRecordInstance();
		rec.setAccountId(accIncome.getId());
		rec.setDate(new Date());
		rec.setIncomeOrExpenseItemId(itmMonthlyWage.getId());
		rec.setValue(13d);

		rec = newAccountRecordInstance();
		rec.setAccountId(accIncome.getId());
		rec.setDate(new Date());
		rec.setIncomeOrExpenseItemId(itmLeasingIncome.getId());
		rec.setValue(16d);
		saveAccountRecord(rec);

		rec = newAccountRecordInstance();
		rec.setAccountId(accExpenses.getId());
		rec.setDate(new Date());
		rec.setIncomeOrExpenseItemId(itmElectricityBill.getId());
		rec.setValue(1d);
		saveAccountRecord(rec);

		rec = newAccountRecordInstance();
		rec.setAccountId(accExpenses.getId());
		rec.setDate(new Date());
		rec.setIncomeOrExpenseItemId(itmTelecomBill.getId());
		rec.setValue(2d);

		rec = newAccountRecordInstance();
		rec.setAccountId(accExpenses.getId());
		rec.setDate(new Date());
		rec.setIncomeOrExpenseItemId(itmComputerParts.getId());
		rec.setValue(10d);
		saveAccountRecord(rec);

		rec = newAccountRecordInstance();
		rec.setAccountId(accExpenses.getId());
		rec.setDate(new Date());
		rec.setIncomeOrExpenseItemId(itmFurniture.getId());
		rec.setValue(-4d);
		saveAccountRecord(rec);

		// Test data for reporting
		rec = newAccountRecordInstance();
		rec.setAccountId(accIncome.getId());
		rec.setDate(Date.from(LocalDate.of(2018, Month.JANUARY, 3).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		rec.setIncomeOrExpenseItemId(itmMonthlyWage.getId());
		rec.setValue(17d);
		saveAccountRecord(rec);

		rec = newAccountRecordInstance();
		rec.setAccountId(accIncome.getId());
		rec.setDate(Date.from(LocalDate.of(2019, Month.FEBRUARY, 2).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		rec.setIncomeOrExpenseItemId(itmMonthlyWage.getId());
		rec.setValue(16d);
		saveAccountRecord(rec);

		rec = newAccountRecordInstance();
		rec.setAccountId(accIncome.getId());
		rec.setDate(Date.from(LocalDate.of(2019, Month.FEBRUARY, 2).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		rec.setIncomeOrExpenseItemId(itmMonthlyWage.getId());
		rec.setValue(-15d);
		saveAccountRecord(rec);

		rec = newAccountRecordInstance();
		rec.setAccountId(accIncome.getId());
		rec.setDate(Date.from(LocalDate.of(2019, Month.MARCH, 3).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		rec.setIncomeOrExpenseItemId(itmMonthlyWage.getId());
		rec.setValue(16d);
		saveAccountRecord(rec);

		rec = newAccountRecordInstance();
		rec.setAccountId(accIncome.getId());
		rec.setDate(Date.from(LocalDate.of(2019, Month.MARCH, 3).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		rec.setIncomeOrExpenseItemId(itmMonthlyWage.getId());
		rec.setValue(-15d);
		saveAccountRecord(rec);

		rec = newAccountRecordInstance();
		rec.setAccountId(accIncome.getId());
		rec.setDate(Date.from(LocalDate.of(2019, Month.APRIL, 4).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		rec.setIncomeOrExpenseItemId(itmMonthlyWage.getId());
		rec.setValue(16d);
		saveAccountRecord(rec);

		rec = newAccountRecordInstance();
		rec.setAccountId(accIncome.getId());
		rec.setDate(Date.from(LocalDate.of(2019, Month.APRIL, 4).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		rec.setIncomeOrExpenseItemId(itmMonthlyWage.getId());
		rec.setValue(-15d);
		saveAccountRecord(rec);

		rec = newAccountRecordInstance();
		rec.setAccountId(accIncome.getId());
		rec.setDate(Date.from(LocalDate.of(2019, Month.MAY, 5).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		rec.setIncomeOrExpenseItemId(itmMonthlyWage.getId());
		rec.setValue(16d);
		saveAccountRecord(rec);

		rec = newAccountRecordInstance();
		rec.setAccountId(accIncome.getId());
		rec.setDate(Date.from(LocalDate.of(2019, Month.MAY, 5).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		rec.setIncomeOrExpenseItemId(itmMonthlyWage.getId());
		rec.setValue(-15d);
		saveAccountRecord(rec);

		rec = newAccountRecordInstance();
		rec.setAccountId(accIncome.getId());
		rec.setDate(Date.from(LocalDate.of(2019, Month.JUNE, 6).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		rec.setIncomeOrExpenseItemId(itmMonthlyWage.getId());
		rec.setValue(16d);
		saveAccountRecord(rec);

		rec = newAccountRecordInstance();
		rec.setAccountId(accIncome.getId());
		rec.setDate(Date.from(LocalDate.of(2019, Month.JUNE, 6).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		rec.setIncomeOrExpenseItemId(itmMonthlyWage.getId());
		rec.setValue(-15d);
		saveAccountRecord(rec);

		rec = newAccountRecordInstance();
		rec.setAccountId(accIncome.getId());
		rec.setDate(Date.from(LocalDate.of(2019, Month.JULY, 7).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		rec.setIncomeOrExpenseItemId(itmMonthlyWage.getId());
		rec.setValue(16d);
		saveAccountRecord(rec);

		rec = newAccountRecordInstance();
		rec.setAccountId(accIncome.getId());
		rec.setDate(Date.from(LocalDate.of(2019, Month.JULY, 7).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		rec.setIncomeOrExpenseItemId(itmMonthlyWage.getId());
		rec.setValue(-15d);
		saveAccountRecord(rec);
	}

}
