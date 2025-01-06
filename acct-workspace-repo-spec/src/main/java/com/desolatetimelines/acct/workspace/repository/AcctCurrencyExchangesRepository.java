package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.workspace.model.AcctAccountRecord;
import com.desolatetimelines.acct.workspace.model.AcctCurrencyExchange;

import java.util.Collection;
import java.util.Optional;

/**
 * Repository for loading and persisting {@link AcctCurrencyExchange currency exchange records}
 */
public interface AcctCurrencyExchangesRepository {

    /**
     * Creates a new instance of {@link AcctCurrencyExchange}
     *
     * @return the newly created instance
     */
    AcctCurrencyExchange createNew();

    /**
     * Persists the referenced {@link AcctCurrencyExchange currency exchange record}
     *
     * @return a reference to the persisted entity
     */
    AcctCurrencyExchange save(AcctCurrencyExchange currencyExchange);

    /**
     * Retrieves a collection of {@link AcctCurrencyExchange currency exchange records}
     * related to the {@link AcctAccountRecord account records} in the given collection
     *
     * @param accountRecords the given collection
     */
    Collection<AcctCurrencyExchange> findAllByTargetAccountRecordIn(Collection<AcctAccountRecord> accountRecords);

    /**
     * Retrieves the {@link AcctCurrencyExchange currency exchange record} for which the
     * {@link AcctCurrencyExchange#getCurrencyExchangeTargetAccountRecord() target account record}
     * has the given {@link AcctAccountRecord#getAccountRecordId() account record id}.
     * If such a currency exchange record does not exist, an empty optional is returned.
     *
     * @param accountRecordId the given account record id
     */
    Optional<AcctCurrencyExchange> findFirstByTargetAccountRecordId(Long accountRecordId);

}
