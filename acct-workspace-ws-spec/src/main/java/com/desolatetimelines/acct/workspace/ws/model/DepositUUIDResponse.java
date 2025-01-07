package com.desolatetimelines.acct.workspace.ws.model;

/**
 * This is an object that wraps a deposit UUID so that it may be returned
 * as an object by a REST API
 *
 * @param depositUUID the wrapped deposit UUID
 */
public record DepositUUIDResponse(
    String depositUUID
) {
}
