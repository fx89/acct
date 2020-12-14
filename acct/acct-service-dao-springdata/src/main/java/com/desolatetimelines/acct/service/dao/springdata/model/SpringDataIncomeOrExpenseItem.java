package com.desolatetimelines.acct.service.dao.springdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.desolatetimelines.acct.service.dao.model.IncomeOrExpenseItem;

@Entity
@Table(name = "income_or_expense_item")
public class SpringDataIncomeOrExpenseItem implements IncomeOrExpenseItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "category_id")
	private Long incomeOrExpenseItemCategoryId;

	@Column(name = "last_used_value")
	private Double lastUsedValue;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Long getIncomeOrExpenseItemCategoryId() {
		return incomeOrExpenseItemCategoryId;
	}

	@Override
	public void setIncomeOrExpenseItemCategoryId(Long incomeOrExpenseItemCategoryId) {
		this.incomeOrExpenseItemCategoryId = incomeOrExpenseItemCategoryId;
	}

	@Override
	public Double getLastUsedValue() {
		return lastUsedValue;
	}

	@Override
	public void setLastUsedValue(Double lastUsedValue) {
		this.lastUsedValue = lastUsedValue;
	}
}
