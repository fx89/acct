package com.desolatetimelines.acct.workspace.ws.model;

import java.time.Instant;

/**
 * Contains deposit properties that can be modified after the deposit has been created
 *
 * @param depositAccountNumber the account number of the deposit
 * @param projectedEndDate     the date when the deposit is expected to capitalize
 */
public record DepositModifiableAttributes(
    String depositAccountNumber,
    Instant projectedEndDate
) {
}
