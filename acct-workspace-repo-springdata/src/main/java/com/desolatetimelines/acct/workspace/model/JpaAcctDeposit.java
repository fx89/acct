package com.desolatetimelines.acct.workspace.model;

import jakarta.persistence.*;

import java.time.Instant;

import static com.desolatetimelines.acct.workspace.util.AcctWorkspaceRepoSpringDataUtils.doWithJpaAcctAccountRecordReturning;
import static java.util.function.Function.identity;

@Entity
@Table(name = "\"deposit\"")
@SuppressWarnings("unused")
public class JpaAcctDeposit implements AcctDeposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depositId;

    @Column(name = "deposit_uuid")
    private String depositUUID;

    @Column(name = "deposit_account_number")
    private String depositAccountNumber;

    @Column(name = "currency_uuid")
    private String currencyUUID;

    @Column(name = "bank_uuid")
    private String bankUUID;

    @Column(name = "deposit_value")
    private Double depositValue;

    @Column(name = "deposit_interest_percent")
    private Double depositInterestPercent;

    @Column(name = "deposit_projected_end_date")
    private Instant depositProjectedEndDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deposit_creation_account_record_id")
    private JpaAcctAccountRecord depositCreationAccountRecord;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deposit_return_account_record_id")
    private JpaAcctAccountRecord depositReturnAccountRecord;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deposit_interest_account_record_id")
    private JpaAcctAccountRecord depositInterestAccountRecord;

    public Long getDepositId() {
        return depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    @Override
    public String getDepositUUID() {
        return depositUUID;
    }

    @Override
    public void setDepositUUID(String depositUUID) {
        this.depositUUID = depositUUID;
    }

    @Override
    public String getDepositAccountNumber() {
        return depositAccountNumber;
    }

    @Override
    public void setDepositAccountNumber(String depositAccountNumber) {
        this.depositAccountNumber = depositAccountNumber;
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
    public Double getDepositValue() {
        return depositValue;
    }

    @Override
    public void setDepositValue(Double depositValue) {
        this.depositValue = depositValue;
    }

    @Override
    public Double getDepositInterestPercent() {
        return depositInterestPercent;
    }

    @Override
    public void setDepositInterestPercent(Double depositInterestPercent) {
        this.depositInterestPercent = depositInterestPercent;
    }

    @Override
    public AcctAccountRecord getDepositCreationAccountRecord() {
        return depositCreationAccountRecord;
    }

    @Override
    public void setDepositCreationAccountRecord(AcctAccountRecord depositCreationAccountRecord) {
        this.depositCreationAccountRecord =
            doWithJpaAcctAccountRecordReturning(depositCreationAccountRecord, identity());
    }

    @Override
    public AcctAccountRecord getDepositReturnAccountRecord() {
        return depositReturnAccountRecord;
    }

    @Override
    public void setDepositReturnAccountRecord(AcctAccountRecord depositReturnAccountRecord) {
        this.depositReturnAccountRecord =
            doWithJpaAcctAccountRecordReturning(depositReturnAccountRecord, identity());
    }

    @Override
    public AcctAccountRecord getDepositInterestAccountRecord() {
        return depositInterestAccountRecord;
    }

    @Override
    public void setDepositInterestAccountRecord(AcctAccountRecord depositInterestAccountRecord) {
        this.depositInterestAccountRecord =
            doWithJpaAcctAccountRecordReturning(depositInterestAccountRecord, identity());
    }

    @Override
    public Instant getDepositProjectedEndDate() {
        return depositProjectedEndDate;
    }

    @Override
    public void setDepositProjectedEndDate(Instant depositProjectedEndDate) {
        this.depositProjectedEndDate = depositProjectedEndDate;
    }

    public void setDepositCreationAccountRecord(JpaAcctAccountRecord depositCreationAccountRecord) {
        this.depositCreationAccountRecord = depositCreationAccountRecord;
    }

    public void setDepositReturnAccountRecord(JpaAcctAccountRecord depositReturnAccountRecord) {
        this.depositReturnAccountRecord = depositReturnAccountRecord;
    }

    public void setDepositInterestAccountRecord(JpaAcctAccountRecord depositInterestAccountRecord) {
        this.depositInterestAccountRecord = depositInterestAccountRecord;
    }
}
