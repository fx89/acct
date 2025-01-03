package com.desolatetimelines.acct.workspace.model;

import jakarta.persistence.*;

import java.time.Instant;

import static com.desolatetimelines.acct.workspace.util.AcctWorkspaceRepoSpringDataUtils.doWithJpaAcctAccountReturning;
import static java.util.function.Function.identity;

@Entity
@Table(name = "\"account_record\"")
public class JpaAcctAccountRecord implements AcctAccountRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountRecordId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private JpaAcctAccount account;

    @Column(name = "account_record_date")
    private Instant accountRecordDate;

    @Column(name = "recorded_by_user_uuid")
    private String recordedByUserUUID;

    @Column(name = "income_or_expense_item_uuid")
    private String incomeOrExpenseItemUUID;

    @Column(name = "account_record_text")
    private String accountRecordText;

    @Column(name = "account_record_value")
    private Double accountRecordValue;

    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @Column(name = "last_modified_by_user_uuid")
    private String lastModifiedByUserUUID;

    @Override
    public Long getAccountRecordId() {
        return accountRecordId;
    }

    @Override
    public void setAccountRecordId(Long accountRecordId) {
        this.accountRecordId = accountRecordId;
    }

    @Override
    public AcctAccount getAccount() {
        return account;
    }

    @Override
    public void setAccount(AcctAccount account) {
        this.account = doWithJpaAcctAccountReturning(account, identity());
    }

    @Override
    public Instant getAccountRecordDate() {
        return accountRecordDate;
    }

    @Override
    public void setAccountRecordDate(Instant accountRecordDate) {
        this.accountRecordDate = accountRecordDate;
    }

    @Override
    public String getRecordedByUserUUID() {
        return recordedByUserUUID;
    }

    @Override
    public void setRecordedByUserUUID(String recordedByUserUUID) {
        this.recordedByUserUUID = recordedByUserUUID;
    }

    @Override
    public String getIncomeOrExpenseItemUUID() {
        return incomeOrExpenseItemUUID;
    }

    @Override
    public void setIncomeOrExpenseItemUUID(String incomeOrExpenseItemUUID) {
        this.incomeOrExpenseItemUUID = incomeOrExpenseItemUUID;
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
    public Double getAccountRecordValue() {
        return accountRecordValue;
    }

    @Override
    public void setAccountRecordValue(Double accountRecordValue) {
        this.accountRecordValue = accountRecordValue;
    }

    @Override
    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String getLastModifiedByUserUUID() {
        return lastModifiedByUserUUID;
    }

    @Override
    public void setLastModifiedByUserUUID(String lastModifiedByUserUUID) {
        this.lastModifiedByUserUUID = lastModifiedByUserUUID;
    }
}
