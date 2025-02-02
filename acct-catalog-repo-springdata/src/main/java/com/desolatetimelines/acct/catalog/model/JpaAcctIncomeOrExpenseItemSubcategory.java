package com.desolatetimelines.acct.catalog.model;

import jakarta.persistence.*;

import java.util.Objects;

import static com.desolatetimelines.acct.catalog.util.AcctCatalogRepoSpringDataUtils.doWithJpaAcctIncomeOrExpenseItemCategoryReturning;
import static java.util.function.Function.identity;

@Entity
@Table(name = "income_or_expense_subcategory")
public class JpaAcctIncomeOrExpenseItemSubcategory implements AcctIncomeOrExpenseItemSubcategory {

    @Id
    @Column(name = "income_or_expense_subcategory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incomeExpenseSubcategoryId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "income_or_expense_category_id")
    private JpaAcctIncomeOrExpenseItemCategory incomeExpenseItemCategory;

    @Column(name = "income_or_expense_subcategory_uuid")
    private String incomeExpenseItemSubcategoryUUID;

    @Column(name = "income_or_expense_subcategory_name")
    private String incomeExpenseItemSubcategoryName;

    @Column(name = "income_or_expense_subcategory_description")
    private String incomeExpenseItemSubcategoryDescription;

    @Column(name = "income_or_expense_subcategory_icon_uuid")
    private String incomeExpenseItemSubcategoryIconUUID;

    public Long getIncomeExpenseSubcategoryId() {
        return incomeExpenseSubcategoryId;
    }

    public void setIncomeExpenseSubcategoryId(Long incomeExpenseSubcategoryId) {
        this.incomeExpenseSubcategoryId = incomeExpenseSubcategoryId;
    }

    @Override
    public AcctIncomeOrExpenseItemCategory getIncomeExpenseItemCategory() {
        return incomeExpenseItemCategory;
    }

    @Override
    public void setIncomeOrExpenseItemCategory(AcctIncomeOrExpenseItemCategory incomeExpenseItemCategory) {
        this.incomeExpenseItemCategory =
            doWithJpaAcctIncomeOrExpenseItemCategoryReturning(incomeExpenseItemCategory, identity());
    }

    public void setIncomeOrExpenseItemCategory(JpaAcctIncomeOrExpenseItemCategory incomeOrExpenseItemCategory) {
        this.incomeExpenseItemCategory = incomeOrExpenseItemCategory;
    }

    @Override
    public String getIncomeOrExpenseItemSubcategoryUUID() {
        return incomeExpenseItemSubcategoryUUID;
    }

    @Override
    public void setIncomeOrExpenseItemSubcategoryUUID(String incomeExpenseItemSubcategoryUUID) {
        this.incomeExpenseItemSubcategoryUUID = incomeExpenseItemSubcategoryUUID;
    }

    @Override
    public String getIncomeOrExpenseItemSubcategoryName() {
        return incomeExpenseItemSubcategoryName;
    }

    @Override
    public void setIncomeOrExpenseItemSubcategoryName(String incomeExpenseItemSubcategoryName) {
        this.incomeExpenseItemSubcategoryName = incomeExpenseItemSubcategoryName;
    }

    @Override
    public String getIncomeOrExpenseItemSubcategoryDescription() {
        return incomeExpenseItemSubcategoryDescription;
    }

    @Override
    public void setIncomeOrExpenseItemSubcategoryDescription(String incomeExpenseItemSubcategoryDescription) {
        this.incomeExpenseItemSubcategoryDescription = incomeExpenseItemSubcategoryDescription;
    }

    @Override
    public String getIncomeOrExpenseItemSubcategoryIconUUID() {
        return incomeExpenseItemSubcategoryIconUUID;
    }

    @Override
    public void setIncomeOrExpenseItemSubcategoryIconUUID(String incomeExpenseItemSubcategoryIconUUID) {
        this.incomeExpenseItemSubcategoryIconUUID = incomeExpenseItemSubcategoryIconUUID;
    }

    public void setIncomeExpenseItemCategory(JpaAcctIncomeOrExpenseItemCategory incomeExpenseItemCategory) {
        this.incomeExpenseItemCategory = incomeExpenseItemCategory;
    }

    public String getIncomeExpenseItemSubcategoryUUID() {
        return incomeExpenseItemSubcategoryUUID;
    }

    public void setIncomeExpenseItemSubcategoryUUID(String incomeExpenseItemSubcategoryUUID) {
        this.incomeExpenseItemSubcategoryUUID = incomeExpenseItemSubcategoryUUID;
    }

    public String getIncomeExpenseItemSubcategoryName() {
        return incomeExpenseItemSubcategoryName;
    }

    public void setIncomeExpenseItemSubcategoryName(String incomeExpenseItemSubcategoryName) {
        this.incomeExpenseItemSubcategoryName = incomeExpenseItemSubcategoryName;
    }

    public String getIncomeExpenseItemSubcategoryDescription() {
        return incomeExpenseItemSubcategoryDescription;
    }

    public void setIncomeExpenseItemSubcategoryDescription(String incomeExpenseItemSubcategoryDescription) {
        this.incomeExpenseItemSubcategoryDescription = incomeExpenseItemSubcategoryDescription;
    }

    public String getIncomeExpenseItemSubcategoryIconUUID() {
        return incomeExpenseItemSubcategoryIconUUID;
    }

    public void setIncomeExpenseItemSubcategoryIconUUID(String incomeExpenseItemSubcategoryIconUUID) {
        this.incomeExpenseItemSubcategoryIconUUID = incomeExpenseItemSubcategoryIconUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctIncomeOrExpenseItemSubcategory that = (JpaAcctIncomeOrExpenseItemSubcategory) o;
        return Objects.equals(incomeExpenseItemSubcategoryUUID, that.incomeExpenseItemSubcategoryUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(incomeExpenseItemSubcategoryUUID);
    }
}
