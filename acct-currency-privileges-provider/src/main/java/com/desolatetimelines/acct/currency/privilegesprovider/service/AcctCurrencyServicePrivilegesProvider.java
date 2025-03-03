package com.desolatetimelines.acct.currency.privilegesprovider.service;

import com.desolatetimelines.acct.currency.privilegesprovider.model.CurrencyPrivilege;
import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;
import com.desolatetimelines.acct.privilegesprovider.service.AcctServicePrivilegesProvider;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * {@link AcctServicePrivilegesProvider privileges provider} for the Catalog service
 */
@Service
public class AcctCurrencyServicePrivilegesProvider implements AcctServicePrivilegesProvider {

    @Override
    public Set<AcctPrivilege> getPrivileges() {
        return Set.of(
            CurrencyPrivilege.MONITORED_CURRENCIES_SAVE.getAcctPrivilege(),
            CurrencyPrivilege.MONITORED_CURRENCIES_READ.getAcctPrivilege(),
            CurrencyPrivilege.MONITORED_CURRENCIES_DELETE.getAcctPrivilege(),
            CurrencyPrivilege.MONITORED_CURRENCY_COLLECTORS_READ.getAcctPrivilege()
        );
    }

}
