package com.desolatetimelines.acct.workspace.ws.endpoint;

import com.desolatetimelines.acct.workspace.ws.model.AccountRecordIdResponse;
import com.desolatetimelines.acct.workspace.ws.model.AccountRecordProperties;
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

}
