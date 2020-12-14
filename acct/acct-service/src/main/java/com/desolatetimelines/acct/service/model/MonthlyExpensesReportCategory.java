package com.desolatetimelines.acct.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MonthlyExpensesReportCategory implements Serializable {
	private Long categoryId;

	private Double totalExpenses;

	private List<MonthlyExpensesReportItem> items;

	private MonthlyExpensesReportMonth month;

	public MonthlyExpensesReportCategory(MonthlyExpensesReportMonth month, Long categoryId) {
		this.categoryId = categoryId;
		this.totalExpenses = 0D;
		this.items = new ArrayList<>();
		this.month = month;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Double getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(Double totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public List<MonthlyExpensesReportItem> getItems() {
		return items;
	}

	public void setItems(List<MonthlyExpensesReportItem> items) {
		this.items = items;
	}

	public MonthlyExpensesReportItem getItem(Long itemId) {
		for (MonthlyExpensesReportItem item : items) {
			if (item.getItemId().equals(itemId)) {
				return item;
			}
		}

		return addItem(itemId);
	}

	private MonthlyExpensesReportItem addItem(Long itemId) {
		MonthlyExpensesReportItem item = new MonthlyExpensesReportItem(this, itemId);
		this.items.add(item);
		return item;
	}

	protected void addValue(Double value) {
		totalExpenses += value;
		month.addValue(value);
	}
}
