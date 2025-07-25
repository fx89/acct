package com.desolatetimelines.acct.catalog.privilegesprovider.service;

import com.desolatetimelines.acct.catalog.privilegesprovider.model.CatalogPrivilege;
import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;
import com.desolatetimelines.acct.privilegesprovider.service.AcctServicePrivilegesProvider;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * {@link AcctServicePrivilegesProvider privileges provider} for the Catalog service
 */
@Service
public class AcctCatalogServicePrivilegesProvider implements AcctServicePrivilegesProvider {

    @Override
    public Set<AcctPrivilege> getPrivileges() {
        return Set.of(
            CatalogPrivilege.ICONS_SAVE.getAcctPrivilege(),
            CatalogPrivilege.ICONS_READ.getAcctPrivilege(),
            CatalogPrivilege.ICONS_DELETE.getAcctPrivilege(),
            CatalogPrivilege.ICONS_GET_CATEGORIES.getAcctPrivilege(),
            CatalogPrivilege.ICONS_SAVE_CATEGORIES.getAcctPrivilege(),
            CatalogPrivilege.ICONS_DELETE_CATEGORIES.getAcctPrivilege(),
            CatalogPrivilege.ITEM_CATEGORIES_SAVE.getAcctPrivilege(),
            CatalogPrivilege.ITEM_CATEGORIES_READ.getAcctPrivilege(),
            CatalogPrivilege.ITEM_CATEGORIES_DELETE.getAcctPrivilege(),
            CatalogPrivilege.ITEM_SUBCATEGORIES_SAVE.getAcctPrivilege(),
            CatalogPrivilege.ITEM_SUBCATEGORIES_READ.getAcctPrivilege(),
            CatalogPrivilege.ITEM_SUBCATEGORIES_DELETE.getAcctPrivilege(),
            CatalogPrivilege.ITEMS_SAVE.getAcctPrivilege(),
            CatalogPrivilege.ITEMS_READ.getAcctPrivilege(),
            CatalogPrivilege.ITEMS_DELETE.getAcctPrivilege(),
            CatalogPrivilege.BANKS_SAVE.getAcctPrivilege(),
            CatalogPrivilege.BANKS_READ.getAcctPrivilege(),
            CatalogPrivilege.BANKS_DELETE.getAcctPrivilege(),
            CatalogPrivilege.CURRENCIES_SAVE.getAcctPrivilege(),
            CatalogPrivilege.CURRENCIES_READ.getAcctPrivilege(),
            CatalogPrivilege.CURRENCIES_DELETE.getAcctPrivilege()
        );
    }

}
