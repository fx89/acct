package com.desolatetimelines.acct.catalog.model;

import jakarta.persistence.*;

import java.util.Objects;

import static com.desolatetimelines.acct.catalog.util.AcctCatalogRepoSpringDataUtils.doWithJpaAcctIncomeOrExpenseItemSubcategoryReturning;
import static java.util.function.Function.identity;

@Entity
@Table(name = "income_or_expense_item")
public class JpaAcctIncomeOrExpenseItem implements AcctIncomeOrExpenseItem {

    @Id
    @Column(name = "income_or_expense_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incomeExpenseItemId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "income_or_expense_subcategory_id")
    private JpaAcctIncomeOrExpenseItemSubcategory incomeExpenseItemSubcategory;

    @Column(name = "income_or_expense_item_uuid")
    private String incomeExpenseItemUUID;

    @Column(name = "income_or_expense_item_name")
    private String incomeExpenseItemName;

    @Column(name = "income_or_expense_item_description")
    private String incomeExpenseItemDescription;

    @Column(name = "income_or_expense_item_icon_uuid")
    private String incomeExpenseItemIconUUID;

    public Long getIncomeExpenseItemId() {
        return incomeExpenseItemId;
    }

    public void setIncomeExpenseItemId(Long incomeExpenseItemId) {
        this.incomeExpenseItemId = incomeExpenseItemId;
    }

    @Override
    public AcctIncomeOrExpenseItemSubcategory getIncomeOrExpenseItemSubcategory() {
        return incomeExpenseItemSubcategory;
    }

    @Override
    public void setIncomeOrExpenseItemSubcategory(AcctIncomeOrExpenseItemSubcategory incomeOrExpenseItemSubcategory) {
        this.incomeExpenseItemSubcategory =
            doWithJpaAcctIncomeOrExpenseItemSubcategoryReturning(incomeOrExpenseItemSubcategory, identity());
    }

    @Override
    public String getIncomeOrExpenseItemUUID() {
        return incomeExpenseItemUUID;
    }

    @Override
    public void setIncomeOrExpenseItemUUID(String incomeOrExpenseItemUUID) {
        this.incomeExpenseItemUUID = incomeExpenseItemUUID;
    }

    @Override
    public String getIncomeOrExpenseItemName() {
        return incomeExpenseItemName;
    }

    @Override
    public void setIncomeOrExpenseItemName(String incomeOrExpenseItemName) {
        this.incomeExpenseItemName = incomeExpenseItemName;
    }

    @Override
    public String getIncomeOrExpenseItemDescription() {
        return incomeExpenseItemDescription;
    }

    @Override
    public void setIncomeOrExpenseItemDescription(String incomeOrExpenseItemDescription) {
        this.incomeExpenseItemDescription = incomeExpenseItemDescription;
    }

    @Override
    public String getIncomeOrExpenseItemIconUUID() {
        return incomeExpenseItemIconUUID;
    }

    @Override
    public void setIncomeOrExpenseItemIconUUID(String incomeOrExpenseItemIconUUID) {
        this.incomeExpenseItemIconUUID = incomeExpenseItemIconUUID;
    }

    public JpaAcctIncomeOrExpenseItemSubcategory getIncomeExpenseItemSubcategory() {
        return incomeExpenseItemSubcategory;
    }

    public void setIncomeExpenseItemSubcategory(JpaAcctIncomeOrExpenseItemSubcategory incomeExpenseItemSubcategory) {
        this.incomeExpenseItemSubcategory = incomeExpenseItemSubcategory;
    }

    public String getIncomeExpenseItemUUID() {
        return incomeExpenseItemUUID;
    }

    public void setIncomeExpenseItemUUID(String incomeExpenseItemUUID) {
        this.incomeExpenseItemUUID = incomeExpenseItemUUID;
    }

    public String getIncomeExpenseItemName() {
        return incomeExpenseItemName;
    }

    public void setIncomeExpenseItemName(String incomeExpenseItemName) {
        this.incomeExpenseItemName = incomeExpenseItemName;
    }

    public String getIncomeExpenseItemDescription() {
        return incomeExpenseItemDescription;
    }

    public void setIncomeExpenseItemDescription(String incomeExpenseItemDescription) {
        this.incomeExpenseItemDescription = incomeExpenseItemDescription;
    }

    public String getIncomeExpenseItemIconUUID() {
        return incomeExpenseItemIconUUID;
    }

    public void setIncomeExpenseItemIconUUID(String incomeExpenseItemIconUUID) {
        this.incomeExpenseItemIconUUID = incomeExpenseItemIconUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctIncomeOrExpenseItem that = (JpaAcctIncomeOrExpenseItem) o;
        return Objects.equals(incomeExpenseItemUUID, that.incomeExpenseItemUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(incomeExpenseItemUUID);
    }
}
