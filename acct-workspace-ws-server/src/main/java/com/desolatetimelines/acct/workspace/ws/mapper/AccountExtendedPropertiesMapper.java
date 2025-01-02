package com.desolatetimelines.acct.workspace.ws.mapper;

import com.desolatetimelines.acct.workspace.model.AcctAccount;
import com.desolatetimelines.acct.workspace.ws.model.AccountExtendedProperties;

import java.util.Collection;

/**
 * Provides mapping methods for the {@link AccountExtendedProperties} type
 */
public abstract class AccountExtendedPropertiesMapper {

    public static AccountExtendedProperties fromAcctAccount(AcctAccount acctAccount) {
        return
            AccountExtendedProperties.builder()
                .withAccountUUID(acctAccount.getAccountUUID())
                .withAccountName(acctAccount.getAccountName())
                .withAccountNumber(acctAccount.getAccountNumber())
                .withBankUUID(acctAccount.getBankUUID())
                .withCurrencyUUID(acctAccount.getCurrencyUUID())
                .withAccountIconUUID(acctAccount.getAccountIconUUID())
                .build();
    }

    public static Collection<AccountExtendedProperties> fromAcctAccountsCollection(Collection<AcctAccount> acctAccounts) {
        return
            acctAccounts.stream()
                .map(AccountExtendedPropertiesMapper::fromAcctAccount)
                .toList();
    }

}
