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
    ICONS_DELETE(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.ICONS_DELETE)
            .withPrivilegeName("Delete icons")
            .withPrivilegeDescription("Allows deleting icons from the catalog")
            .build()
    ),
    ICONS_GET_CATEGORIES(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.ICONS_GET_CATEGORIES)
            .withPrivilegeName("Get icon categories")
            .withPrivilegeDescription("Allows listing the names of all icon categories registered in the catalog")
            .build()
    ),
    ITEM_CATEGORIES_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.ITEM_CATEGORIES_SAVE)
            .withPrivilegeName("Save income or expense item categories")
            .withPrivilegeDescription("Allows registering or updating already-registered income or expense item " +
                "categories in the catalog")
            .build()
    ),
    ITEM_CATEGORIES_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.ITEM_CATEGORIES_READ)
            .withPrivilegeName("List and view income or expense item categories")
            .withPrivilegeDescription("Allows listing and viewing the properties of income or expense item " +
                "categories in the catalog")
            .build()
    ),
    ITEM_CATEGORIES_DELETE(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.ITEM_CATEGORIES_DELETE)
            .withPrivilegeName("Delete income or expense item categories")
            .withPrivilegeDescription("Allows deleting of income or expense item categories " +
                "in the catalog, together with the contained income and expense item sub-categories and " +
                "related income or expense items")
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

