package com.desolatetimelines.acct.service.model;

import java.io.Serializable;

public class MonthlyBalanceReportRecord implements Serializable {
	private int recordYearMonth;

	private Double startingBalance;

	private Double incomingBalance;

	private Double outgoingBalance;

	public int getRecordYearMonth() {
		return recordYearMonth;
	}

	public void setRecordYearMonth(int recordYearMonth) {
		this.recordYearMonth = recordYearMonth;
	}

	public Double getStartingBalance() {
		return startingBalance;
	}

	public void setStartingBalance(Double startingBalance) {
		this.startingBalance = startingBalance;
	}

	public Double getIncomingBalance() {
		return incomingBalance;
	}

	public void setIncomingBalance(Double incomingBalance) {
		this.incomingBalance = incomingBalance;
	}

	public Double getOutgoingBalance() {
		return outgoingBalance;
	}

	public void setOutgoingBalance(Double outgoingBalance) {
		this.outgoingBalance = outgoingBalance;
	}

}
