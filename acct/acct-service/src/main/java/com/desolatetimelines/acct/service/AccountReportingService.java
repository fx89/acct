package com.desolatetimelines.acct.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.desolatetimelines.acct.service.Exception.AccountServiceException;
import com.desolatetimelines.acct.service.dao.AccountDataService;
import com.desolatetimelines.acct.service.dao.model.AccountRecord;
import com.desolatetimelines.acct.service.dao.model.IncomeOrExpenseItem;
import com.desolatetimelines.acct.service.model.MonthlyBalanceReportRecord;
import com.desolatetimelines.acct.service.model.MonthlyExpensesReport;
import com.desolatetimelines.acct.service.model.MonthlyExpensesReportMonth;

class TempStuff {
	public int prevYYYYMM = 0;
	public int curYYYYMM = 0;

	public Double prevBalance = 0d;
	public Double incomingBalance = 0d;
	public Double outgoingBalance = 0d;
}

class DateValRec {
	public Date date;
	public Double value;

	public DateValRec(Date date, Double value) {
		this.date = date;
		this.value = value;
	}
}

public class AccountReportingService {
	private static final DateFormat DATE_FORMAT_YYYYMM = new SimpleDateFormat("yyyyMM");

	private AccountDataService dataService;

	public AccountDataService getDataService() {
		return dataService;
	}

	public void setDataService(AccountDataService dataService) {
		this.dataService = dataService;
	}

	public Stream<MonthlyBalanceReportRecord> monthlyBalanceReport(Long accountId, int startMonth) {
		Stream<AccountRecord> accRecs = this.dataService.getAccountRecords(accountId);
		return monthlyReport(accRecs.map(rec -> new DateValRec(rec.getDate(), rec.getValue())), startMonth);
	}

	public Stream<MonthlyBalanceReportRecord> monthlyDepositsReport(int startMonth) {
		List<DateValRec> recs = new ArrayList<>(200);

		this.dataService.getAllDeposits().forEach(dep -> {
			recs.add(new DateValRec(dep.getStartDate(), dep.getValue()));
			recs.add(new DateValRec(dep.getEndDate(), -dep.getValue()));
		});

		return monthlyReport(recs.stream(), startMonth);
	}

	public Stream<MonthlyExpensesReport> monthlyExpensesReports(int startMonth) throws ParseException {
		// Initialize the report
		List<MonthlyExpensesReport> reps = new ArrayList<>();

		// Cache the income or expense items
		List<IncomeOrExpenseItem> items = dataService.getIncomeOrExpenseItems().collect(Collectors.toList());

		// Compute the lower date boundary of the report
		Date sinceDate = DATE_FORMAT_YYYYMM.parse(Integer.toString(startMonth));

		// Aggregate the expenses of each account by item and category
		dataService.getAllAccounts().forEach(acct -> {
			MonthlyExpensesReport acctReport = new MonthlyExpensesReport(acct.getId());
			reps.add(acctReport);

			dataService.getAccountRecords(acct.getId(), sinceDate).forEach(rec -> {
				Double recValue = rec.getValue();

				if (recValue < 0) {
					MonthlyExpensesReportMonth month = acctReport.getMonth(computeReportMonth(rec.getDate()));
					Long itemId = rec.getIncomeOrExpenseItemId();
					Long categoryId = getIncomeOrExpenseItemCategoryId(itemId, items);

					month.getCategory(categoryId).getItem(itemId).addValue(-rec.getValue());
				}
			});
		});

		// Sort the report
		reps.forEach(rep -> {
			rep.getMonths().sort((m1, m2) -> m1.getMonthNumber() - m2.getMonthNumber());

			rep.getMonths().forEach(m -> {
				m.getCategories().sort((c1, c2) -> c1.getCategoryId().compareTo(c2.getCategoryId()));

				m.getCategories().forEach(c -> {
					c.getItems().sort((i1, i2) -> i1.getItemId().compareTo(i2.getItemId()));
				});
			});
		});

		// Return the aggregated and sorted report
		return reps.stream();
	}

	private Long getIncomeOrExpenseItemCategoryId(Long itemId, List<IncomeOrExpenseItem> items) {
		IncomeOrExpenseItem item = items.stream().filter(itm -> itm.getId().equals(itemId)).findFirst().orElse(null);

		if (item == null) {
			throw new RuntimeException(new AccountServiceException("Cannot find a category with id [" + itemId + "]"));
		}

		return item.getIncomeOrExpenseItemCategoryId();
	}

	private static Stream<MonthlyBalanceReportRecord> monthlyReport(Stream<DateValRec> records, int startMonth) {

		int curYYYYMM = computeReportMonth(new Date());

		List<MonthlyBalanceReportRecord> ret = new ArrayList<>();

		TempStuff tempStuff = new TempStuff();

		records.sorted((rec1, rec2) -> rec1.date.compareTo(rec2.date)).forEach(rec -> {
			tempStuff.curYYYYMM = computeReportMonth(rec.date);

			if (tempStuff.curYYYYMM < startMonth) {
				tempStuff.prevBalance += rec.value;
			} else {

				if (tempStuff.prevYYYYMM == 0) {
					tempStuff.prevYYYYMM = tempStuff.curYYYYMM;
				}

				if (tempStuff.curYYYYMM != tempStuff.prevYYYYMM) {
					ret.add(newRec(tempStuff));

					tempStuff.prevBalance += (tempStuff.incomingBalance + tempStuff.outgoingBalance);
					tempStuff.incomingBalance = 0d;
					tempStuff.outgoingBalance = 0d;
					tempStuff.prevYYYYMM = tempStuff.curYYYYMM;
				}

				tempStuff.incomingBalance += (rec.value > 0 ? rec.value : 0);
				tempStuff.outgoingBalance += (rec.value < 0 ? rec.value : 0);
			}

		});

		if (tempStuff.prevYYYYMM <= curYYYYMM) {
			ret.add(newRec(tempStuff));
		}

		return fillBlanksMonthlyBalance(ret, startMonth).filter(rec -> rec.getRecordYearMonth() > 0);
	}

	private static MonthlyBalanceReportRecord newRec(TempStuff tempStuff) {
		MonthlyBalanceReportRecord reportRec = new MonthlyBalanceReportRecord();
		reportRec.setRecordYearMonth(tempStuff.prevYYYYMM);
		reportRec.setStartingBalance(tempStuff.prevBalance);
		reportRec.setIncomingBalance(tempStuff.incomingBalance);
		reportRec.setOutgoingBalance(tempStuff.outgoingBalance);

		return reportRec;
	}

	private static Stream<MonthlyBalanceReportRecord> fillBlanksMonthlyBalance(List<MonthlyBalanceReportRecord> input,
			int startMonth) {
		Map<Integer, MonthlyBalanceReportRecord> ret = new HashMap<>();

		// Load up the report records
		input.forEach(rec -> {
			ret.put(rec.getRecordYearMonth(), rec);
		});

		// Compute current month
		int curMonth = computeReportMonth(new Date());

		// Fill the blanks
		Double prevBalance = 0d;
		for (int repMonth = startMonth; repMonth <= curMonth; repMonth = incrementReportMonth(repMonth)) {
			// Look for a record for the given month
			MonthlyBalanceReportRecord rec = ret.get(repMonth);

			// If not found, then add it
			if (rec == null) {
				rec = new MonthlyBalanceReportRecord();
				rec.setRecordYearMonth(repMonth);
				rec.setIncomingBalance(0d);
				rec.setOutgoingBalance(0d);
				rec.setStartingBalance(prevBalance);
				ret.put(repMonth, rec);
			} else {
				// If, however, the record was found, then the starting balance is updated
				prevBalance = rec.getStartingBalance() + rec.getIncomingBalance() + rec.getOutgoingBalance();
			}
		}

		return ret.values().stream()
				.sorted((rec1, rec2) -> rec1.getRecordYearMonth() < rec2.getRecordYearMonth() ? -1 : 1);
	}

	private static int computeReportMonth(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		return Integer.parseInt(dateFormat.format(date));
	}

	private static int incrementReportMonth(int reportMonth) {
		int year = reportMonth / 100;
		int month = reportMonth % 100;

		month++;

		if (month > 12) {
			month = 1;
			year++;
		}

		return (year * 100) + month;
	}

}
