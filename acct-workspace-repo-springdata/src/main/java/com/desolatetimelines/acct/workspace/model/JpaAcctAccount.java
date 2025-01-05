package com.desolatetimelines.acct.workspace.model;

import jakarta.persistence.*;

import java.util.Objects;

import static com.desolatetimelines.acct.workspace.util.AcctWorkspaceRepoSpringDataUtils.doWithJpaAcctWorkspaceReturning;
import static java.util.function.Function.identity;

@Entity
@Table(name = "\"account\"")
public class JpaAcctAccount implements AcctAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workspace_id")
    private JpaAcctWorkspace workspace;

    @Column(name = "account_uuid")
    private String accountUUID;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_icon_uuid")
    private String accountIconUUID;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "currency_uuid")
    private String currencyUUID;

    @Column(name = "bank_uuid")
    private String bankUUID;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String getAccountUUID() {
        return accountUUID;
    }

    @Override
    public void setAccountUUID(String accountUUID) {
        this.accountUUID = accountUUID;
    }

    @Override
    public AcctWorkspace getWorkspace() {
        return workspace;
    }

    @Override
    public void setWorkspace(AcctWorkspace workspace) {
        this.workspace = doWithJpaAcctWorkspaceReturning(workspace, identity());
    }

    public void setWorkspace(JpaAcctWorkspace workspace) {
        this.workspace = workspace;
    }

    @Override
    public String getAccountName() {
        return accountName;
    }

    @Override
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public String getAccountIconUUID() {
        return accountIconUUID;
    }

    @Override
    public void setAccountIconUUID(String accountIconUUID) {
        this.accountIconUUID = accountIconUUID;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String getCurrencyUUID() {
        return currencyUUID;
    }

    @Override
    public void setCurrencyUUID(String currencyUUID) {
        this.currencyUUID = currencyUUID;
    }

    @Override
    public String getBankUUID() {
        return bankUUID;
    }

    @Override
    public void setBankUUID(String bankUUID) {
        this.bankUUID = bankUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctAccount that = (JpaAcctAccount) o;
        return Objects.equals(workspace, that.workspace) && Objects.equals(accountUUID, that.accountUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workspace, accountUUID);
    }
}
