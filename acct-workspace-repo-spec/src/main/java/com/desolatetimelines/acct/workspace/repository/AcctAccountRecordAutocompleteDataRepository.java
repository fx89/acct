package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.workspace.model.AcctAccount;
import com.desolatetimelines.acct.workspace.model.AcctAccountRecordAutocompleteData;

import java.util.Optional;

/**
 * Repository for loading and persisting {@link AcctAccountRecordAutocompleteData autocomplete data}
 */
public interface AcctAccountRecordAutocompleteDataRepository {

    /**
     * Returns a new instance of {@link AcctAccountRecordAutocompleteData}
     */
    AcctAccountRecordAutocompleteData createNew();

    /**
     * Persists the referenced {@link AcctAccountRecordAutocompleteData autocomplete data record}
     *
     * @param autocompleteData the referenced autocomplete data record
     * @return a reference to the persisted entity
     */
    AcctAccountRecordAutocompleteData save(AcctAccountRecordAutocompleteData autocompleteData);

    /**
     * Returns a page of {@link AcctAccountRecordAutocompleteData autocomplete data records}
     * of the given page size and with the given page number, filtering for records that match
     * the given text pattern and the given income or expense item UUID and that belong to the
     * referenced account
     *
     * @param account                  the referenced account
     * @param incomeOrExpenseItemUUID  the given income or expense item UUID
     * @param accountRecordTextPattern the given text pattern
     * @param pageNumber               the given page number
     * @param pageSize                 the given page size
     */
    Page<AcctAccountRecordAutocompleteData> findAllByAccountAndIncomeOrExpenseItemUUIDAndAccountRecordTextLike(
        AcctAccount account,
        String incomeOrExpenseItemUUID,
        String accountRecordTextPattern,
        int pageNumber,
        int pageSize
    );

    /**
     * Returns the first {@link AcctAccountRecordAutocompleteData autocomplete data record} that matches
     * the referenced account, the given income or expense item UUID and the given account record text.
     * If not found, an empty optional is returned.
     *
     * @param account                 the referenced account
     * @param incomeOrExpenseItemUUID the given income or expense item UUID
     * @param accountRecordText       the given account record text
     */
    Optional<AcctAccountRecordAutocompleteData> findFirstByAccountAndIncomeOrExpenseItemUUIDAndAccountRecordText(
        AcctAccount account,
        String incomeOrExpenseItemUUID,
        String accountRecordText
    );

}
