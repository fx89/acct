package com.desolatetimelines.acct.reporting.ws.model;

/**
 * Container for the UUID of a newly created or updated report
 *
 * @param reportUUID The contained UUID
 */
public record ReportUUIDResponse(
    String reportUUID
) {
}
