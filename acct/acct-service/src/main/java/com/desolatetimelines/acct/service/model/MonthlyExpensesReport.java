package com.desolatetimelines.acct.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MonthlyExpensesReport implements Serializable {
	private Long accountId;

	private List<MonthlyExpensesReportMonth> months;

	public MonthlyExpensesReport(Long accountId) {
		this.accountId = accountId;
		this.months = new ArrayList<>();
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public List<MonthlyExpensesReportMonth> getMonths() {
		return months;
	}

	public void setMonths(List<MonthlyExpensesReportMonth> months) {
		this.months = months;
	}

	public MonthlyExpensesReportMonth getMonth(int monthNumber) {
		for (MonthlyExpensesReportMonth m : months) {
			if (m.getMonthNumber() == monthNumber) {
				return m;
			}
		}

		return addMonth(monthNumber);
	}

	private MonthlyExpensesReportMonth addMonth(int monthNumber) {
		MonthlyExpensesReportMonth newMonth = new MonthlyExpensesReportMonth(monthNumber);
		months.add(newMonth);
		return newMonth;
	}
}
