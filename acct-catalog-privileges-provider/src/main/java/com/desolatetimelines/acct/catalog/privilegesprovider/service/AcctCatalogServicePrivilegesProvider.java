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
            CatalogPrivilege.ITEM_CATEGORIES_SAVE.getAcctPrivilege(),
            CatalogPrivilege.ITEM_CATEGORIES_READ.getAcctPrivilege(),
            CatalogPrivilege.ITEM_CATEGORIES_DELETE.getAcctPrivilege()
        );
    }

}
