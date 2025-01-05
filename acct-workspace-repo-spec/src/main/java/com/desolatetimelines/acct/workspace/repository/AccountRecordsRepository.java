package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.workspace.model.AcctAccount;
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

    /**
     * Retrieves a page of the given page size and with the given page number
     * of {@link AcctAccountRecord account records} belonging to the referenced
     * {@link AcctAccount account}
     *
     * @param account    the referenced account
     * @param pageNumber the given page number
     * @param pageSize   the given page size
     */
    Page<AcctAccountRecord> findAllByAccount(AcctAccount account, int pageNumber, int pageSize);

    /**
     * Retrieves a page of the given page size and with the given page number
     * of {@link AcctAccountRecord account records} belonging to the referenced
     * {@link AcctAccount account} and for which the
     * {@link AcctAccountRecord#getAccountRecordText() text} matches the given
     * pattern
     *
     * @param account     the referenced account
     * @param textPattern the given pattern
     * @param pageNumber  the given page number
     * @param pageSize    the given page size
     */
    Page<AcctAccountRecord> findAllByAccountAndTextLike(
        AcctAccount account,
        String textPattern,
        int pageNumber,
        int pageSize
    );

    /**
     * Returns the sum of the {@link AcctAccountRecord#getAccountRecordValue()} property of all
     * {@link AcctAccountRecord records} belonging to the referenced {@link AcctAccount account}
     *
     * @param account the referenced account
     */
    Double sumAccountRecordValueByAccount(AcctAccount account);
}
