package com.desolatetimelines.acct.workspace.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

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
    private String recordedUserUUID; // Name slightly modified to avoid issues with the Spring Data repository

    @Column(name = "income_or_expense_item_uuid")
    private String incomeExpenseItemUUID; // Name slightly modified to avoid issues with the Spring Data repository

    @Column(name = "account_record_text")
    private String accountRecordText;

    @Column(name = "account_record_value")
    private Double accountRecordValue;

    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @Column(name = "last_modified_by_user_uuid")
    private String lastModifiedUserUUID; // Name slightly modified to avoid issues with the Spring Data repository

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
        return recordedUserUUID;
    }

    @Override
    public void setRecordedByUserUUID(String recordedByUserUUID) {
        this.recordedUserUUID = recordedByUserUUID;
    }

    public void setAccount(JpaAcctAccount account) {
        this.account = account;
    }

    public String getRecordedUserUUID() {
        return recordedUserUUID;
    }

    public void setRecordedUserUUID(String recordedUserUUID) {
        this.recordedUserUUID = recordedUserUUID;
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
        return lastModifiedUserUUID;
    }

    @Override
    public void setLastModifiedByUserUUID(String lastModifiedByUserUUID) {
        this.lastModifiedUserUUID = lastModifiedByUserUUID;
    }

    public String getLastModifiedUserUUID() {
        return lastModifiedUserUUID;
    }

    public void setLastModifiedUserUUID(String lastModifiedUserUUID) {
        this.lastModifiedUserUUID = lastModifiedUserUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctAccountRecord that = (JpaAcctAccountRecord) o;
        return Objects.equals(accountRecordId, that.accountRecordId) && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountRecordId, account);
    }
}
