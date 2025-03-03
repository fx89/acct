package com.desolatetimelines.acct.currency.privilegesprovider.model;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;

/**
 * Defines the privileges required by the Currency service
 */
public enum CurrencyPrivilege {
    MONITORED_CURRENCIES_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(CurrencyPrivilegeIds.MONITORED_CURRENCIES_SAVE)
            .withPrivilegeName("Save a monitored currency")
            .withPrivilegeDescription("Allows persisting monitored currencies")
            .build()
    ),
    MONITORED_CURRENCIES_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(CurrencyPrivilegeIds.MONITORED_CURRENCIES_READ)
            .withPrivilegeName("Count, list and read monitored currency properties")
            .withPrivilegeDescription("Allows counting, listing and reading the properties of monitored currencies")
            .build()
    ),
    MONITORED_CURRENCIES_DELETE(
        AcctPrivilege.builder()
            .withPrivilegeId(CurrencyPrivilegeIds.MONITORED_CURRENCIES_DELETE)
            .withPrivilegeName("Delete monitored currencies")
            .withPrivilegeDescription("Allows deleting monitored currencies")
            .build()
    ),
    MONITORED_CURRENCY_COLLECTORS_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(CurrencyPrivilegeIds.MONITORED_CURRENCY_COLLECTORS_READ)
            .withPrivilegeName("List available monitored currency collectors")
            .withPrivilegeDescription("Allows listing all the available monitored currency collectors")
            .build()
    );

    private final AcctPrivilege acctPrivilege;

    CurrencyPrivilege(AcctPrivilege acctPrivilege) {
        this.acctPrivilege = acctPrivilege;
    }

    public AcctPrivilege getAcctPrivilege() {
        return acctPrivilege;
    }
}

