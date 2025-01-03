package com.desolatetimelines.acct.workspace.ws.mapper;

import com.desolatetimelines.acct.workspace.model.AccountRecordDetails;
import com.desolatetimelines.acct.workspace.ws.model.AccountRecordProperties;

/**
 * Provides mapping methods for the {@link AccountRecordDetails} type
 */
public abstract class AccountRecordDetailsMapper {

    public static AccountRecordDetails fromAccountRecordProperties(
        Long accountRecordId,
        AccountRecordProperties accountRecordProperties
    ) {
        return
            AccountRecordDetails.builder()
                .withAccountRecordId(accountRecordId)
                .withIncomeOrExpenseItemUUID(accountRecordProperties.incomeOrExpenseItemUUID())
                .withAccountRecordText(accountRecordProperties.accountRecordText())
                .withAccountRecordValue(accountRecordProperties.accountRecordValue())
                .build();
    }

}
