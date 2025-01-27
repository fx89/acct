package com.desolatetimelines.acct.catalog.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "income_or_expense_category")
public class JpaAcctIncomeOrExpenseItemCategory implements AcctIncomeOrExpenseItemCategory {

    @Id
    @Column(name = "income_or_expense_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incomeExpenseCategoryId;

    @Column(name = "income_or_expense_category_uuid")
    private String incomeExpenseItemCategoryUUID;

    @Column(name = "income_or_expense_category_name")
    private String incomeExpenseItemCategoryName;

    @Column(name = "income_or_expense_category_description")
    private String incomeExpenseItemCategoryDescription;

    @Column(name = "income_or_expense_category_icon_uuid")
    private String incomeExpenseItemCategoryIconUUID;

    public Long getIncomeExpenseCategoryId() {
        return incomeExpenseCategoryId;
    }

    public void setIncomeExpenseCategoryId(Long incomeExpenseCategoryId) {
        this.incomeExpenseCategoryId = incomeExpenseCategoryId;
    }

    @Override
    public String getIncomeOrExpenseItemCategoryUUID() {
        return incomeExpenseItemCategoryUUID;
    }

    @Override
    public void setIncomeOrExpenseItemCategoryUUID(String incomeOrExpenseItemCategoryUUID) {
        this.incomeExpenseItemCategoryUUID = incomeOrExpenseItemCategoryUUID;
    }

    @Override
    public String getIncomeOrExpenseItemCategoryName() {
        return incomeExpenseItemCategoryName;
    }

    @Override
    public void setIncomeOrExpenseItemCategoryName(String incomeOrExpenseItemCategoryName) {
        this.incomeExpenseItemCategoryName = incomeOrExpenseItemCategoryName;
    }

    @Override
    public String getIncomeOrExpenseItemCategoryDescription() {
        return incomeExpenseItemCategoryDescription;
    }

    @Override
    public void setIncomeOrExpenseItemCategoryDescription(String incomeOrExpenseItemCategoryDescription) {
        this.incomeExpenseItemCategoryDescription = incomeOrExpenseItemCategoryDescription;
    }

    @Override
    public String getIncomeOrExpenseItemCategoryIconUUID() {
        return incomeExpenseItemCategoryIconUUID;
    }

    @Override
    public void setIncomeOrExpenseItemCategoryIconUUID(String incomeOrExpenseItemCategoryIconUUID) {
        this.incomeExpenseItemCategoryIconUUID = incomeOrExpenseItemCategoryIconUUID;
    }

    public String getIncomeExpenseItemCategoryUUID() {
        return incomeExpenseItemCategoryUUID;
    }

    public void setIncomeExpenseItemCategoryUUID(String incomeExpenseItemCategoryUUID) {
        this.incomeExpenseItemCategoryUUID = incomeExpenseItemCategoryUUID;
    }

    public String getIncomeExpenseItemCategoryName() {
        return incomeExpenseItemCategoryName;
    }

    public void setIncomeExpenseItemCategoryName(String incomeExpenseItemCategoryName) {
        this.incomeExpenseItemCategoryName = incomeExpenseItemCategoryName;
    }

    public String getIncomeExpenseItemCategoryDescription() {
        return incomeExpenseItemCategoryDescription;
    }

    public void setIncomeExpenseItemCategoryDescription(String incomeExpenseItemCategoryDescription) {
        this.incomeExpenseItemCategoryDescription = incomeExpenseItemCategoryDescription;
    }

    public String getIncomeExpenseItemCategoryIconUUID() {
        return incomeExpenseItemCategoryIconUUID;
    }

    public void setIncomeExpenseItemCategoryIconUUID(String incomeExpenseItemCategoryIconUUID) {
        this.incomeExpenseItemCategoryIconUUID = incomeExpenseItemCategoryIconUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctIncomeOrExpenseItemCategory that = (JpaAcctIncomeOrExpenseItemCategory) o;
        return Objects.equals(incomeExpenseItemCategoryUUID, that.incomeExpenseItemCategoryUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(incomeExpenseItemCategoryUUID);
    }
}
