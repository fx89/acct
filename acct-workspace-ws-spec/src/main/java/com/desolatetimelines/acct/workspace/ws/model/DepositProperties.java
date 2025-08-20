package com.desolatetimelines.acct.workspace.ws.model;

import java.time.Instant;

/**
 * Contains the basic properties that can be transferred from the front-end to the back-end
 * with a deposit creation request.
 *
 * @param sourceAccountUUID    the UUID of the account from where the money is transferred to the new deposit
 * @param depositAccountNumber the account number of the newly created deposit
 * @param amount               the amount of money taken from the source account and stored into the deposit account
 * @param startDate            the date when the deposit was created
 * @param projectedEndDate     the end date when the deposit is expected to yield interest
 * @param interestPct          the interest percentage of the deposit
 */
public record DepositProperties(
    String sourceAccountUUID,
    String depositAccountNumber,
    Double amount,
    Instant startDate,
    Instant projectedEndDate,
    Double interestPct
) {
}
