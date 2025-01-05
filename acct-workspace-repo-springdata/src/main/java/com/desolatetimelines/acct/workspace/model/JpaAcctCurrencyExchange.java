package com.desolatetimelines.acct.workspace.model;

import jakarta.persistence.*;

import java.util.Optional;

import static com.desolatetimelines.acct.workspace.util.AcctWorkspaceRepoSpringDataUtils.doWithJpaAcctAccountRecordReturning;
import static com.desolatetimelines.acct.workspace.util.AcctWorkspaceRepoSpringDataUtils.doWithJpaAcctCurrencyExchangeReturning;
import static java.util.function.Function.identity;

@Entity
@Table(name = "\"currency_exchange\"")
public class JpaAcctCurrencyExchange implements AcctCurrencyExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long currencyExchangeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_exchange_source_account_record_id")
    private JpaAcctAccountRecord currencyExchangeSourceAccountRecord;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_exchange_target_account_record_id")
    private JpaAcctAccountRecord currencyExchangeTargetAccountRecord;

    @Column(name = "currency_exchange_rate")
    private Double currencyExchangeRate;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "optional_reverse_currency_exchange_id")
    private JpaAcctCurrencyExchange optionalReverseCurrencyExchange;

    public Long getCurrencyExchangeId() {
        return currencyExchangeId;
    }

    public void setCurrencyExchangeId(Long currencyExchangeId) {
        this.currencyExchangeId = currencyExchangeId;
    }

    @Override
    public AcctAccountRecord getCurrencyExchangeSourceAccountRecord() {
        return currencyExchangeSourceAccountRecord;
    }

    @Override
    public void setCurrencyExchangeSourceAccountRecord(AcctAccountRecord currencyExchangeSourceAccountRecord) {
        this.currencyExchangeSourceAccountRecord =
            doWithJpaAcctAccountRecordReturning(currencyExchangeSourceAccountRecord, identity());
    }

    public void setCurrencyExchangeSourceAccountRecord(JpaAcctAccountRecord currencyExchangeSourceAccountRecord) {
        this.currencyExchangeSourceAccountRecord = currencyExchangeSourceAccountRecord;
    }

    @Override
    public AcctAccountRecord getCurrencyExchangeTargetAccountRecord() {
        return currencyExchangeTargetAccountRecord;
    }

    @Override
    public void setCurrencyExchangeTargetAccountRecord(AcctAccountRecord currencyExchangeTargetAccountRecord) {
        this.currencyExchangeTargetAccountRecord =
            doWithJpaAcctAccountRecordReturning(currencyExchangeTargetAccountRecord, identity());
    }

    public void setCurrencyExchangeTargetAccountRecord(JpaAcctAccountRecord currencyExchangeTargetAccountRecord) {
        this.currencyExchangeTargetAccountRecord = currencyExchangeTargetAccountRecord;
    }

    @Override
    public Double getCurrencyExchangeRate() {
        return currencyExchangeRate;
    }

    @Override
    public void setCurrencyExchangeRate(Double currencyExchangeRate) {
        this.currencyExchangeRate = currencyExchangeRate;
    }

    @Override
    public Double getPurchasePrice() {
        return purchasePrice;
    }

    @Override
    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    @Override
    public Optional<AcctCurrencyExchange> getOptionalReverseCurrencyExchange() {
        return Optional.ofNullable(optionalReverseCurrencyExchange);
    }

    @Override
    public void setOptionalReverseCurrencyExchange(AcctCurrencyExchange optionalReverseCurrencyExchange) {
        this.optionalReverseCurrencyExchange =
            doWithJpaAcctCurrencyExchangeReturning(optionalReverseCurrencyExchange, identity());
    }

    public void setOptionalReverseCurrencyExchange(JpaAcctCurrencyExchange optionalReverseCurrencyExchange) {
        this.optionalReverseCurrencyExchange = optionalReverseCurrencyExchange;
    }
}
