package com.desolatetimelines.acct.workspace.ws.mapper;

import com.desolatetimelines.acct.workspace.model.AccountDetails;
import com.desolatetimelines.acct.workspace.ws.model.AccountProperties;

/**
 * Provides mapping methods for the {@link AccountDetails} type
 */
public abstract class AccountDetailsMapper {

    public static AccountDetails fromAccountProperties(String accountUUID, AccountProperties accountProperties) {
        return
            AccountDetails.builder()
                .withAccountUUID(accountUUID)
                .withAccountName(accountProperties.accountName())
                .withAccountNumber(accountProperties.accountNumber())
                .withAccountIconUUID(accountProperties.accountIconUUID())
                .withBankUUID(accountProperties.bankUUID())
                .withCurrencyUUID(accountProperties.currencyUUID())
                .build();
    }

}
