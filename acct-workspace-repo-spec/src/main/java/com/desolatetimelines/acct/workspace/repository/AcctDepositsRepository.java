package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.workspace.model.AcctDeposit;
import com.desolatetimelines.acct.workspace.model.AcctWorkspace;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;

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

    /**
     * Retrieves the {@link AcctDeposit deposit} with the given deposit UUID
     * or an empty optional if such a deposit is not found
     *
     * @param depositUUID the given deposit UUID
     */
    Optional<AcctDeposit> findFirstByDepositUUID(String depositUUID);

    /**
     * Returns a {@link Page page} with the given page number and page size of {@link AcctDeposit deposits}
     * that belong to the {@link AcctWorkspace workspace} with the given workspace UUID and for which the
     * projected end date is after the given projected end date
     *
     * @param workspaceUUID    the given workspace UUID
     * @param projectedEndDate the given projected end date
     * @param pageNumber       the given page number
     * @param pageSize         the given page size
     */
    Page<AcctDeposit> findAllByWorkspaceUUIDAndProjectedEndDateGreaterThanEqual(
        String workspaceUUID, Instant projectedEndDate, int pageNumber, int pageSize
    );

    /**
     * Returns a {@link Page page} with the given page number and page size of {@link AcctDeposit deposits}
     * that belong to the {@link AcctWorkspace workspace} with the given workspace UUID, have been opened
     * at the bank with the given bank UUID, and for which the projected end date is after the given projected
     * end date
     *
     * @param workspaceUUID    the given workspace UUID
     * @param bankUUID         the given bank UUID
     * @param projectedEndDate the given projected end date
     * @param pageNumber       the given page number
     * @param pageSize         the given page size
     */
    Page<AcctDeposit> findAllByWorkspaceUUIDAndBankUUIDAndProjectedEndDateGreaterThanEqual(
        String workspaceUUID, String bankUUID, Instant projectedEndDate, int pageNumber, int pageSize
    );

    /**
     * Returns a {@link Page page} with the given page number and page size of {@link AcctDeposit deposits}
     * that belong to the {@link AcctWorkspace workspace} with the given workspace UUID, and for which the
     * following conditions are fulfilled: <ul>
     * <li>The deposit does not yet have an {@link AcctDeposit#getDepositInterestAccountRecord() interest record}</li>
     * <li>The deposit's {@link AcctDeposit#getDepositProjectedEndDate() projected end date} is before the given date</li>
     * </ul>
     * Only deposits at the bank with the given bank UUID are returned.
     *
     * @param workspaceUUID    the given workspace UUID
     * @param bankUUID         the given bank UUID
     * @param projectedEndDate the given date
     * @param pageNumber       the given page number
     * @param pageSize         the given page size
     */
    Page<AcctDeposit> findAllByWorkspaceUUIDAndBankUUIDAndDepositInterestAccountRecordNullAndDepositProjectedEndDateLessThan(
        String workspaceUUID, String bankUUID, Instant projectedEndDate, int pageNumber, int pageSize
    );

    /**
     * Returns a {@link Page page} with the given page number and page size of {@link AcctDeposit deposits}
     * that belong to the {@link AcctWorkspace workspace} with the given workspace UUID, and for which the
     * following conditions are fulfilled: <ul>
     * <li>The deposit does not yet have an {@link AcctDeposit#getDepositInterestAccountRecord() interest record}</li>
     * <li>The deposit's {@link AcctDeposit#getDepositProjectedEndDate() projected end date} is before the given date</li>
     * </ul>
     *
     * @param workspaceUUID    the given workspace UUID
     * @param projectedEndDate the given date
     * @param pageNumber       the given page number
     * @param pageSize         the given page size
     */
    Page<AcctDeposit> findAllByWorkspaceUUIDAndDepositInterestAccountRecordNullAndDepositProjectedEndDateLessThan(
        String workspaceUUID, Instant projectedEndDate, int pageNumber, int pageSize
    );

    /**
     * Retrieves a collection of {@link AcctDeposit deposits} for which the
     * {@link AcctDeposit#getBankUUID() bank UUID} is one of the given UUIDs
     *
     * @param bankUUIDs the given UUIDs
     */
    Collection<AcctDeposit> findAllByBankUUIDIn(Collection<String> bankUUIDs);

    /**
     * Retrieves a collection of {@link AcctDeposit deposits} for which the
     * {@link AcctDeposit#getCurrencyUUID() currency UUID} is one of the
     * given UUIDs. In case there is no match, an empty collection is returned.
     *
     * @param currencyUUIDs the given UUIDs
     */
    Collection<AcctDeposit> findAllByCurrencyUUIDIn(Collection<String> currencyUUIDs);

}
