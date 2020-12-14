package com.desolatetimelines.acct.service.model;

import java.io.Serializable;

public class MonthlyExpensesReportItem implements Serializable {
	private Long itemId;

	private Double totalExpenses;

	private MonthlyExpensesReportCategory category;

	public MonthlyExpensesReportItem(MonthlyExpensesReportCategory category, Long itemId) {
		this.itemId = itemId;
		this.category = category;
		this.totalExpenses = 0D;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Double getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(Double value) {
		this.totalExpenses = value;
	}

	public void addValue(Double value) {
		this.totalExpenses += value;
		category.addValue(value);
	}
}
