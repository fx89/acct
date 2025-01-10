package com.desolatetimelines.acct.workspace.ws.mapper;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.common.ws.model.AcctPage;
import com.desolatetimelines.acct.common.ws.model.AcctPageInfo;
import com.desolatetimelines.acct.workspace.model.AcctDeposit;
import com.desolatetimelines.acct.workspace.ws.model.DepositDetails;

/**
 * Provides mapping methods for the {@link DepositDetails} type
 */
public abstract class DepositDetailsMapper {

    public static DepositDetails fromAcctDeposit(AcctDeposit acctDeposit) {
        return
            DepositDetails.builder()
                .withDepositUUID(acctDeposit.getDepositUUID())
                .withSourceAccountUUID(acctDeposit.getDepositCreationAccountRecord().getAccount().getAccountUUID())
                .withBankUUID(acctDeposit.getBankUUID())
                .withDepositAccountNumber(acctDeposit.getDepositAccountNumber())
                .withCurrencyUUID(acctDeposit.getCurrencyUUID())
                .withDepositValue(acctDeposit.getDepositValue())
                .withDepositStartDate(acctDeposit.getDepositCreationAccountRecord().getAccountRecordDate())
                .withDepositProjectedEndDate(acctDeposit.getDepositProjectedEndDate())
                .withDepositInterestPercent(acctDeposit.getDepositInterestPercent())
                .build();
    }

    public static AcctPage<DepositDetails> fromPageOfAcctDeposit(Page<AcctDeposit> page, int pageNumber, int pageSize) {
        return
            new AcctPage<>(
                page.data().stream().map(DepositDetailsMapper::fromAcctDeposit).toList(),
                new AcctPageInfo(
                    page.numElements(),
                    page.maxElements(),
                    (int) page.maxElements() / pageSize,
                    pageNumber
                )
            );
    }

}
