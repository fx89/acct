package com.desolatetimelines.acct.workspace.ws.endpoint;

import com.desolatetimelines.acct.workspace.ws.model.DepositModifiableAttributes;
import com.desolatetimelines.acct.workspace.ws.model.DepositProperties;
import com.desolatetimelines.acct.workspace.ws.model.DepositUUIDResponse;
import jakarta.validation.constraints.NotNull;

/**
 * Specifies deposits endpoint functionality that can be accessed by both clients and other ACCT services.
 * Defines both client and server functionality.
 */
public interface DepositsEndpoint {

    /**
     * Registers a new deposit in the workspace with the given workspace UUID
     * according to the given properties
     *
     * @param workspaceUUID     the given workspace UUID
     * @param depositProperties the given properties
     * @return the UUID of the newly registered deposit, wrapped inside an object
     */
    DepositUUIDResponse createDepositFromSourceAccount(
        @NotNull String workspaceUUID,
        DepositProperties depositProperties
    );

    /**
     * Updates the modifiable properties of the deposit referenced by the given
     * deposit UUID within the workspace with the given workspace UUID based on
     * the given deposit modifiable attributes record
     *
     * @param workspaceUUID        the given workspace UUID
     * @param depositUUID          the given deposit UUID
     * @param modifiableAttributes the given deposit modifiable attributes record
     */
    void updateDepositAttributes(
        @NotNull String workspaceUUID,
        @NotNull String depositUUID,
        DepositModifiableAttributes modifiableAttributes
    );

}
