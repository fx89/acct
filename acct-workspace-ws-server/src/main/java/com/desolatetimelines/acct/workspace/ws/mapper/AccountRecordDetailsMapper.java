package com.desolatetimelines.acct.workspace.ws.mapper;

import com.desolatetimelines.acct.workspace.model.AccountRecordDetails;
import com.desolatetimelines.acct.workspace.ws.model.AccountRecordProperties;

import java.time.Instant;
import java.util.Optional;

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
                .withAccountRecordDate(Optional.ofNullable(accountRecordProperties.accountRecordDate()).orElse(Instant.now()))
                .build();
    }


}
