package com.desolatetimelines.acct.workspace.ws.endpoint;

import com.desolatetimelines.acct.common.ws.model.AcctPage;
import com.desolatetimelines.acct.workspace.ws.model.*;
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

    /**
     * Returns a page of deposits within the workspace referenced by the given workspace UUID,
     * sorted by the {@link DepositDetails#depositProjectedEndDate() projected end date} in
     * ascending order. If the optional bank UUID parameter is given then only deposits created
     * at the referenced bank are returned.
     *
     * @param workspaceUUID the given workspace UUID
     * @param bankUUID      the optional bank UUID parameter
     * @param pageNumber    the zero-based index of the page to be returned
     * @param pageSize      the number of elements to be contained by any given page
     */
    AcctPage<DepositDetails> getSortedPageOfDepositsByWorkspaceUUIDAndOptionalBankUUID(
        @NotNull String workspaceUUID,
        String bankUUID,
        int pageNumber,
        int pageSize
    );

    /**
     * Returns a page of deposits within the workspace referenced by the given workspace UUID
     * and for which the {@link DepositDetails#depositProjectedEndDate() projected end date}
     * is after the current date. The page is sorted by
     * {@link DepositDetails#depositProjectedEndDate() projected end date} in ascending order.
     *
     * @param workspaceUUID the given workspace UUID
     * @param pageNumber    the zero-based index of the page to be returned
     * @param pageSize      the number of elements to be contained by any given page
     */
    AcctPage<DepositDetails> getSortedPageOfDepositsToCapitalize(
        @NotNull String workspaceUUID,
        int pageNumber,
        int pageSize
    );

    /**
     * Returns the value of the deposit with the given deposit UUID, plus interest, into the
     * source account from the workspace with given workspace UUID. The interest is computed
     * by subtracting the deposit amount from the value contained in the given deposit return
     * value object.
     *
     * @param workspaceUUID      the given deposit UUID
     * @param depositUUID        the given workspace UUID
     * @param depositReturnValue the given deposit return value object
     */
    void capitalizeDeposit(
        @NotNull String workspaceUUID,
        @NotNull String depositUUID,
        DepositReturnValue depositReturnValue
    );

}
