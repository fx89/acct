package com.desolatetimelines.acct.workspace.ws.model;

/**
 * Describes a transfer of currency between two accounts
 *
 * @param sourceAccountUUID The UUID of the account from which the amount is transferred
 * @param targetAccountUUID The UUID of the account to which the amount ins transferred
 * @param amount            The amount that is transferred, in the target account's currency
 */
public record CurrencyTransfer(
    String sourceAccountUUID,
    String targetAccountUUID,
    Double amount
) {

}
