package com.desolatetimelines.acct.workspace.ws.endpoint;

import com.desolatetimelines.acct.common.ws.model.AcctPage;
import com.desolatetimelines.acct.common.ws.model.AcctSortDirection;
import com.desolatetimelines.acct.workspace.ws.model.*;
import jakarta.validation.constraints.NotNull;

/**
 * Specifies account records endpoint functionality that can be accessed by both clients and other ACCT services.
 * Defines both client and server functionality.
 */
public interface AccountRecordsEndpoint {

    /**
     * Creates or updates an account record of the account with the given account UUID
     * within the workspace with the given workspace UUID with the information that can
     * be found in the given account record properties. If an account record id is not
     * give then a new account record is created. Otherwise, an existing account record
     * is updated, provided the given account record id is not wrong.
     *
     * @param workspaceUUID           the given workspace UUID
     * @param accountUUID             the given account UUID
     * @param accountRecordId         the given account record id
     * @param accountRecordProperties the given account record properties
     * @return the account record ID of the created or updated account record
     */
    AccountRecordIdResponse saveAccountRecord(
        @NotNull String workspaceUUID,
        @NotNull String accountUUID,
        Long accountRecordId,
        AccountRecordProperties accountRecordProperties
    );

    /**
     * Removes the record with the given record id from the account with the given
     * account UUID, which resides within the workspace with the given workspace UUID
     * @param workspaceUUID   the given workspace UUID
     * @param accountUUID     the given account UUID
     * @param accountRecordId the given record id
     */
    void deleteAccountRecordFromAccount(
        @NotNull String workspaceUUID,
        @NotNull String accountUUID,
        @NotNull Long accountRecordId
    );

    /**
     * Returns a page of account records, filtered by the given {@link AccountRecordEnhancedDetails#accountRecordText() text}
     * pattern and sorted by {@link AccountRecordEnhancedDetails#accountRecordDate() record date} in ascending order. The page
     * of records is taken from the account with the given account UUID, which must be part of the workspace with the
     * given workspace UUID. The returned page is as large as the given page size and has the given page number.
     *
     * @param workspaceUUID the given workspaceUUID
     * @param accountUUID   the given account UUID
     * @param pattern       the given text pattern
     * @param pageNumber    the given page number
     * @param pageSize      the given page size
     */
    AcctPage<AccountRecordEnhancedDetails> findSortedPageOfAccountRecordsByTextPattern(
        @NotNull String workspaceUUID,
        @NotNull String accountUUID,
        String pattern,
        @NotNull int pageNumber,
        @NotNull int pageSize,
        @NotNull AcctSortDirection sortDirection
    );

    /**
     * Transfers a given amount from a source account to a target account of the same currency.
     * The transfer parameters are contained by the given currency transfer record.
     *
     * @param currencyTransfer the given currency transfer record
     */
    void transferAmountBetweenAccountsWithSameCurrency(
        @NotNull String workspaceUUID,
        CurrencyTransfer currencyTransfer
    );

    /**
     * Registers a purchase of a given amount of currency in the target account with a
     * computed amount of currency from the source account. The source account amount is
     * computed based on the amount purchased in the target account and the given exchange
     * rate. Both accounts must be part of the workspace referenced by the given workspace
     * UUID.
     *
     * @param workspaceUUID    the given workspace UUID
     * @param currencyExchange the given exchange rate
     */
    void currencyExchange(@NotNull String workspaceUUID, CurrencyExchange currencyExchange);
}
