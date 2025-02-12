package com.desolatetimelines.acct.catalog.ws.mapper;

import com.desolatetimelines.acct.catalog.model.AcctBank;
import com.desolatetimelines.acct.catalog.ws.model.BankProperties;

import java.util.Collection;

/**
 * Provides mapping methods for the {@link BankProperties} type
 */
public class BankPropertiesMapper {

    public static BankProperties fromAcctBank(AcctBank acctBank) {
        return
            BankProperties.builder()
                .withBankUUID(acctBank.getBankUUID())
                .withBankCode(acctBank.getBankCode())
                .withBankName(acctBank.getBankName())
                .withInternetBankingURL(acctBank.getInternetBankingURL())
                .withBankIconUUID(acctBank.getBankIconUUID())
                .build();
    }

    public static Collection<BankProperties> fromCollectionOfAcctBanks(Collection<AcctBank> acctBanks) {
        return
            acctBanks
                .stream()
                .map(BankPropertiesMapper::fromAcctBank)
                .toList();
    }

}
