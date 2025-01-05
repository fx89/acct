package com.desolatetimelines.acct.workspace.ws.endpoint;

import com.desolatetimelines.acct.workspace.ws.model.AccountBalanceResponse;
import com.desolatetimelines.acct.workspace.ws.model.AccountExtendedProperties;
import com.desolatetimelines.acct.workspace.ws.model.AccountProperties;
import com.desolatetimelines.acct.workspace.ws.model.AccountUUIDResponse;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

/**
 * Specifies accounts endpoint functionality that can be accessed by both clients and other ACCT services.
 * Defines both client and server functionality.
 */
public interface AccountsEndpoint {

    /**
     * Persists the given properties for the account with the given account UUID within the workspace
     * with the given workspace UUID. If an account UUID is not provided then a new account is created
     * with the given properties within the referenced workspace.
     *
     * @param workspaceUUID     the given workspace UUID
     * @param accountUUID       the given account UUID
     * @param accountProperties the given properties
     */
    AccountUUIDResponse saveAccount(
        @NotNull String workspaceUUID,
        String accountUUID,
        @NotNull AccountProperties accountProperties
    );

    /**
     * Retrieves a collection of all the accounts that belong to the workspace
     * with the given workspace UUID
     *
     * @param workspaceUUID the given workspace UUID
     */
    Collection<AccountExtendedProperties> getAccountsInWorkspace(@NotNull String workspaceUUID);

    /**
     * Deletes the account with the given account UUID from the workspace with the given workspace UUID
     *
     * @param workspaceUUID the given account UUID
     * @param accountUUID   the given workspace UUID
     */
    void deleteAccountFromWorkspace(@NotNull String workspaceUUID, @NotNull String accountUUID);

    /**
     * Computes the balance of the account with the specified account UUID
     * within the workspace with the specified workspace UUID
     *
     * @param workspaceUUID the specified workspace UUID
     * @param accountUUID   the specified account UUID
     * @return the computed account balance, expressed in the account's currency
     */
    AccountBalanceResponse computeAccountBalance(@NotNull String workspaceUUID, @NotNull String accountUUID);

}
