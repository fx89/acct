package com.desolatetimelines.acct.reporting.ws.model;

/**
 * The types of reports supported by ACCT: <ul>
 * <li><b>TABLE</b> means the report will be displayed as a table.</li>
 * <li><b>SERIES means the report will be displayed as a line, area or column chart.</b></li>
 * <li><b>PIE means the report will be displayed as a pie chart.</b></li>
 * </ul><br />
 * Each particular type of report requires specific settings.
 *
 */
public enum ReportType {
    TABLE,
    SERIES,
    PIE
}
