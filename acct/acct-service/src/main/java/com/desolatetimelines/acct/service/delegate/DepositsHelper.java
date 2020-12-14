package com.desolatetimelines.acct.service.delegate;

import static com.desolatetimelines.acct.service.delegate.CommonOperations.checkAccountAvailableAmount;
import static com.desolatetimelines.acct.service.delegate.CommonOperations.resolveOperationIncomeOrExpenseItemId;

import java.util.Date;

import com.desolatetimelines.acct.service.AccountService;
import com.desolatetimelines.acct.service.Exception.AccountServiceBusinessException;
import com.desolatetimelines.acct.service.dao.AccountDataService;
import com.desolatetimelines.acct.service.dao.model.AccountRecord;
import com.desolatetimelines.acct.service.dao.model.Deposit;
import com.desolatetimelines.acct.service.dao.model.InterestHistoryRecord;

public class DepositsHelper {
	public static void afterDepositCreated(AccountService accountService, AccountDataService dataService,
			Deposit deposit) {
		detectAndRecordChangeInIterestRate(dataService, deposit);
	}

	public static void beforeDepositCreated(AccountService accountService, AccountDataService dataService,
			Deposit deposit) throws AccountServiceBusinessException {
		subtractDepositedValueFromSourceAccount(accountService, dataService, deposit);
	}

	private static void detectAndRecordChangeInIterestRate(AccountDataService dataService, Deposit deposit) {
		Long depositBankId = deposit.getBankId();
		Double interestPercent = deposit.getInterestPercent();

		// Get the latest interest rate for the bank where the deposit was made
		InterestHistoryRecord lastInterest = dataService.getInterestHistoryRecords(depositBankId)
				.reduce((rec1, rec2) -> rec1.getSnapshotDate().compareTo(rec2.getSnapshotDate()) >= 0 ? rec1 : rec2)
				.orElse(null);

		// If the interest rate of the new deposit is greater than the latest interest
		// rate, then register it
		if (lastInterest == null || lastInterest.getValue().equals(interestPercent) == false) {
			InterestHistoryRecord newRec = dataService.newInterestHistoryRecord();
			newRec.setBankId(depositBankId);
			newRec.setSnapshotDate(new Date());
			newRec.setValue(interestPercent);

			dataService.saveInterestHistoryRecord(newRec);
		}
	}

	private static void subtractDepositedValueFromSourceAccount(AccountService accountService,
			AccountDataService dataService, Deposit deposit) throws AccountServiceBusinessException {
		// Check if there are sufficient funds in the source account
		checkAccountAvailableAmount(accountService, deposit.getSourceAccountId(), deposit.getValue());

		// Record the deposit transaction
		AccountRecord accRecord = dataService.newAccountRecordInstance();
		accRecord.setAccountId(deposit.getSourceAccountId());
		accRecord.setDate(deposit.getStartDate());
		accRecord.setValue(-deposit.getValue());
		accRecord.setIncomeOrExpenseItemId(
				resolveOperationIncomeOrExpenseItemId(accountService, dataService, "Deposit"));
		accRecord = dataService.saveAccountRecord(accRecord);
	}
}
