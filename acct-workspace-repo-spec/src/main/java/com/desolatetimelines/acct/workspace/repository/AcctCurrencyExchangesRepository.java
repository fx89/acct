package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.workspace.model.AcctAccountRecord;
import com.desolatetimelines.acct.workspace.model.AcctCurrencyExchange;

import java.util.Collection;

/**
 * Repository for loading and persisting {@link AcctCurrencyExchange currency exchange records}
 */
public interface AcctCurrencyExchangesRepository {

    /**
     * Retrieves a collection of {@link AcctCurrencyExchange currency exchange records}
     * related to the {@link AcctAccountRecord account records} in the given collection
     *
     * @param accountRecords the given collection
     */
    Collection<AcctCurrencyExchange> findAllByTargetAccountRecordIn(Collection<AcctAccountRecord> accountRecords);

}
