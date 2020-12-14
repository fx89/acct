package com.desolatetimelines.acct.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MonthlyExpensesReportMonth implements Serializable {
	private int monthNumber;

	private Double totalExpenses;

	private List<MonthlyExpensesReportCategory> categories;

	public MonthlyExpensesReportMonth(int monthNumber) {
		this.monthNumber = monthNumber;
		this.totalExpenses = 0D;
		this.categories = new ArrayList<>();
	}

	public int getMonthNumber() {
		return monthNumber;
	}

	public void setMonthNumber(int monthNumber) {
		this.monthNumber = monthNumber;
	}

	public Double getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(Double totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public List<MonthlyExpensesReportCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<MonthlyExpensesReportCategory> categories) {
		this.categories = categories;
	}

	public MonthlyExpensesReportCategory getCategory(Long categoryId) {
		for (MonthlyExpensesReportCategory c : categories) {
			if (c.getCategoryId().equals(categoryId)) {
				return c;
			}
		}

		return addCategory(categoryId);
	}

	private MonthlyExpensesReportCategory addCategory(Long categoryId) {
		MonthlyExpensesReportCategory newCategory = new MonthlyExpensesReportCategory(this, categoryId);
		this.categories.add(newCategory);
		return newCategory;
	}

	protected void addValue(Double value) {
		this.totalExpenses = value;
	}
}
