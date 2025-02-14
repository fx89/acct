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
    ),
    ITEM_SUBCATEGORIES_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.ITEM_SUBCATEGORIES_SAVE)
            .withPrivilegeName("Save income or expense item subcategories")
            .withPrivilegeDescription("Allows registering or updating already-registered income or expense item " +
                "subcategories in the catalog")
            .build()
    ),
    ITEM_SUBCATEGORIES_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.ITEM_SUBCATEGORIES_READ)
            .withPrivilegeName("List and view income or expense item subcategories")
            .withPrivilegeDescription("Allows listing and viewing the properties of income or expense item " +
                "subcategories in the catalog")
            .build()
    ),
    ITEM_SUBCATEGORIES_DELETE(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.ITEM_SUBCATEGORIES_DELETE)
            .withPrivilegeName("Delete income or expense item subcategories")
            .withPrivilegeDescription("Allows deleting of income or expense item subcategories " +
                "in the catalog, together with the related income or expense items")
            .build()
    ),
    ITEMS_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.ITEMS_SAVE)
            .withPrivilegeName("Save income or expense items")
            .withPrivilegeDescription("Allows registering or updating already-registered income or expense items " +
                "in the catalog")
            .build()
    ),
    ITEMS_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.ITEMS_READ)
            .withPrivilegeName("List and view income or expense items")
            .withPrivilegeDescription("Allows listing and viewing the properties of income or expense items " +
                "in the catalog")
            .build()
    ),
    ITEMS_DELETE(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.ITEMS_DELETE)
            .withPrivilegeName("Delete income or expense items")
            .withPrivilegeDescription("Allows deleting of income or expense items " +
                "in the catalog")
            .build()
    ),
    BANKS_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.BANKS_SAVE)
            .withPrivilegeName("Save banks")
            .withPrivilegeDescription("Allows registering or updating already-registered banks in the catalog")
            .build()
    ),
    BANKS_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.BANKS_READ)
            .withPrivilegeName("List and view banks")
            .withPrivilegeDescription("Allows listing and viewing the properties of banks in the catalog")
            .build()
    ),
    BANKS_DELETE(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.BANKS_DELETE)
            .withPrivilegeName("Delete banks")
            .withPrivilegeDescription("Allows deleting banks from the catalog")
            .build()
    ),
    CURRENCIES_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.CURRENCIES_SAVE)
            .withPrivilegeName("Save currencies")
            .withPrivilegeDescription("Allows registering or updating already-registered currencies in the catalog")
            .build()
    ),
    CURRENCIES_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.CURRENCIES_READ)
            .withPrivilegeName("List and view currencies")
            .withPrivilegeDescription("Allows listing and viewing the properties of currencies in the catalog")
            .build()
    ),
    CURRENCIES_DELETE(
        AcctPrivilege.builder()
            .withPrivilegeId(CatalogPrivilegeIds.CURRENCIES_DELETE)
            .withPrivilegeName("Delete currencies")
            .withPrivilegeDescription("Allows deleting currencies from the catalog")
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

