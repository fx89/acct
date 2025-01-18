package com.desolatetimelines.acct.workspace.model;

import jakarta.persistence.*;

import static com.desolatetimelines.acct.workspace.util.AcctWorkspaceRepoSpringDataUtils.doWithJpaAcctAccountReturning;
import static java.util.function.Function.identity;

@Entity
@Table(name = "\"account_record_autocomplete_data\"")
public class JpaAcctAccountRecordAutocompleteData implements AcctAccountRecordAutocompleteData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountRecordAutocompleteDataId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private JpaAcctAccount account;

    @Column(name = "income_or_expense_item_uuid")
    private String incomeExpenseItemUUID; // Name slightly altered to avoid confusing Spring Data with the "Or"

    @Column(name = "account_record_text")
    private String accountRecordText;

    @Column(name = "last_used_account_record_value")
    private Double lastUsedAccountRecordValue;

    public Long getAccountRecordAutocompleteDataId() {
        return accountRecordAutocompleteDataId;
    }

    public void setAccountRecordAutocompleteDataId(Long accountRecordAutocompleteDataId) {
        this.accountRecordAutocompleteDataId = accountRecordAutocompleteDataId;
    }

    @Override
    public AcctAccount getAccount() {
        return account;
    }

    @Override
    public void setAccount(AcctAccount account) {
        this.account = doWithJpaAcctAccountReturning(account, identity());
    }

    public void setAccount(JpaAcctAccount account) {
        this.account = account;
    }

    @Override
    public String getIncomeOrExpenseItemUUID() {
        return incomeExpenseItemUUID;
    }

    @Override
    public void setIncomeOrExpenseItemUUID(String incomeOrExpenseItemUUID) {
        this.incomeExpenseItemUUID = incomeOrExpenseItemUUID;
    }

    public String getIncomeExpenseItemUUID() {
        return incomeExpenseItemUUID;
    }

    public void setIncomeExpenseItemUUID(String incomeExpenseItemUUID) {
        this.incomeExpenseItemUUID = incomeExpenseItemUUID;
    }

    @Override
    public String getAccountRecordText() {
        return accountRecordText;
    }

    @Override
    public void setAccountRecordText(String accountRecordText) {
        this.accountRecordText = accountRecordText;
    }

    @Override
    public Double getLastUsedAccountRecordValue() {
        return lastUsedAccountRecordValue;
    }

    @Override
    public void setLastUsedAccountRecordValue(Double lastUsedAccountRecordValue) {
        this.lastUsedAccountRecordValue = lastUsedAccountRecordValue;
    }
}
