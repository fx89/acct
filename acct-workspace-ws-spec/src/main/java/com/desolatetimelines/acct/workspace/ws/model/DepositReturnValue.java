package com.desolatetimelines.acct.workspace.ws.model;

/**
 * Container for the value that is returned to the source account
 * upon capitalizing a deposit
 */
public record DepositReturnValue(
    Double returnValue
) {
}
