package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.workspace.model.AcctDeposit;

/**
 * Repository for loading and persisting {@link AcctDeposit deposits}
 */
public interface AcctDepositsRepository {

    /**
     * Creates a new {@link AcctDeposit deposit}
     *
     * @return a referenced to the newly created entity
     */
    AcctDeposit createNew();

    /**
     * Persists the referenced {@link AcctDeposit deposit}
     *
     * @param deposit the referenced deposit
     * @return a reference to the persisted entity
     */
    AcctDeposit save(AcctDeposit deposit);

}
