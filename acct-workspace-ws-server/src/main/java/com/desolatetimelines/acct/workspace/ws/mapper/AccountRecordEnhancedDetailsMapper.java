package com.desolatetimelines.acct.workspace.ws.mapper;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.common.ws.model.AcctPage;
import com.desolatetimelines.acct.common.ws.model.AcctPageInfo;
import com.desolatetimelines.acct.workspace.model.AccountRecordExtendedDetails;
import com.desolatetimelines.acct.workspace.ws.model.AccountRecordEnhancedDetails;

/**
 * Provides mapping methods for the {@link AccountRecordEnhancedDetails} type
 */
public abstract class AccountRecordEnhancedDetailsMapper {

    public static AccountRecordEnhancedDetails fromAccountRecordExtendedDetails(
        AccountRecordExtendedDetails accountRecordExtendedDetails
    ) {
        return
            AccountRecordEnhancedDetails.builder()
                .withAccountRecordId(accountRecordExtendedDetails.accountRecordId())
                .withIncomeOrExpenseItemUUID(accountRecordExtendedDetails.incomeOrExpenseItemUUID())
                .withAccountRecordText(accountRecordExtendedDetails.accountRecordText())
                .withAccountRecordValue(accountRecordExtendedDetails.accountRecordValue())
                .withAccountRecordDate(accountRecordExtendedDetails.accountRecordDate())
                .withRecordedByUserUUID(accountRecordExtendedDetails.recordedByUserUUID())
                .withLastModifiedDate(accountRecordExtendedDetails.lastModifiedDate())
                .withLastModifiedByUserUUID(accountRecordExtendedDetails.lastModifiedByUserUUID())
                .withExchangeRate(accountRecordExtendedDetails.currencyExchangeRate())
                .withPurchasePrice(accountRecordExtendedDetails.purchasePrice())
                .build();
    }

    public static AcctPage<AccountRecordEnhancedDetails> fromPageOfAccountRecordExtendedDetails(
        Page<AccountRecordExtendedDetails> page,
        int pageNumber,
        int pageSize
    ) {
        return
            new AcctPage<>(
                page.data().stream().map(AccountRecordEnhancedDetailsMapper::fromAccountRecordExtendedDetails).toList(),
                new AcctPageInfo(
                    page.numElements(),
                    page.maxElements(),
                    (int) (Math.ceil((double) page.maxElements() / (double) pageSize)),
                    pageNumber
                )
            );
    }

}
