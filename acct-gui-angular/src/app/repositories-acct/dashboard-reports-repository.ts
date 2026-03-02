import { Observable } from "rxjs";
import { DashboardReportExtendedProperties, DashboardReportProperties } from "../model-acct/dashboard-report-extended-properties";

/**
 * Allows creating, reading, updating and deleting dashboard reports.
 */
export abstract class DashboardReportsRepository {

    /**
     * Retrieves all the dashboard reports present in the dashboard with the referenced dashboard
     * @param dashboardUUID Uniquely identifies the referenced dashboard.
     */
    abstract findDashboardReports(dashboardUUID:string) : Observable<DashboardReportExtendedProperties[]>;

    /**
     * Persists the referenced dashboard report.
     * @param workspaceUUID Uniquely identifies the workspace where the dashboard where the report is saved resides.
     * @param dashboardUUID Uniquely identifies the dashboard where the report is saved.
     * @param dashboardReport The referenced dashboard report.
     */
    abstract saveDashboardReport(
        workspaceUUID: string,
        dashboardUUID: string,
        dashboardReport: DashboardReportProperties
    ) : Observable<void>

    /**
     * Deletes the referenced dashboard report.
     * @param workspaceUUID Uniquely identifies the workspace where the dashboard where the report is saved resides.
     * @param dashboardUUID Uniquely identifies the dashboard where the report is saved.
     * @param dashboardReport The referenced dashboard report.
     */
    abstract deleteDashboardReport(workspaceUUID: string,
        dashboardUUID: string,
        dashboardReport: DashboardReportProperties
    ) : Observable<void>

}