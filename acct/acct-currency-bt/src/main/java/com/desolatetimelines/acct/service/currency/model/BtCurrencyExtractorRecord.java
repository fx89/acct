package com.desolatetimelines.acct.service.currency.model;

import com.desolatetimelines.acct.service.currency.CurrencyExtractorHistoryRecord;

import java.util.Date;

public class BtCurrencyExtractorRecord implements CurrencyExtractorHistoryRecord {
    private Date date;

    private Double value;

    public BtCurrencyExtractorRecord(Date date, Double value) {
        this.date = date;
        this.value = value;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }
}
