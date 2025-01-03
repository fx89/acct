package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.workspace.model.AcctAccountRecord;

import java.util.Optional;

/**
 * Repository for loading and persisting {@link AcctAccountRecord account records}
 */
public interface AccountRecordsRepository {

    /**
     * Creates a new instance of {@link AcctAccountRecord}
     */
    AcctAccountRecord createNew();

    /**
     * Persists the referenced {@link AcctAccountRecord account record}
     *
     * @param accountRecord the referenced account record
     * @return a reference to the persisted entity
     */
    AcctAccountRecord save(AcctAccountRecord accountRecord);

    /**
     * Retrieves the {@link AcctAccountRecord account record} with the given
     * {@link AcctAccountRecord#getAccountRecordId() account record ID} or an
     * empty optional in case the account record is not found
     *
     * @param accountRecordId the given account record ID
     * @return a reference to the retrieved entity
     */
    Optional<AcctAccountRecord> findFirstByAccountRecordId(Long accountRecordId);

}
