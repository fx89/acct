package com.desolatetimelines.acct.catalog.privilegesprovider.model;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;

/**
 * Defines the privileges required by the Jobs Registry service
 */
public enum CatalogPrivilege {
    ICONS_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.ICONS_SAVE)
            .withPrivilegeName("Save an icon")
            .withPrivilegeDescription("Allows saving icons to the catalog")
            .build()
    );

    private final AcctPrivilege acctPrivilege;

    CatalogPrivilege(AcctPrivilege acctPrivilege) {
        this.acctPrivilege = acctPrivilege;
    }

    public AcctPrivilege getAcctPrivilege() {
        return acctPrivilege;
    }
}

