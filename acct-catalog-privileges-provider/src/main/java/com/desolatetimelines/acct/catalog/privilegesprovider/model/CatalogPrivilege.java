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
    ),
    ICONS_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.ICONS_SAVE)
            .withPrivilegeName("Count, list and read icon properties")
            .withPrivilegeDescription("Allows counting, listing and reading the properties of icons " +
                "registered in the catalog")
            .build()
    ),
    ICONS_GET_CATEGORIES(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.ICONS_GET_CATEGORIES)
            .withPrivilegeName("Get icon categories")
            .withPrivilegeDescription("Allows listing the names of all icon categories registered in the catalog")
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

