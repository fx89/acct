package com.desolatetimelines.acct.catalog.ws.model;

/**
 * Wraps the UUID of a bank inside a container that can be returned from the banks endpoint
 */
public record BankUUIDResponse(
    String bankUUID
) {
}
