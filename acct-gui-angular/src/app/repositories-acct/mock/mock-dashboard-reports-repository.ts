import { Observable } from "rxjs";
import { DashboardReportExtendedProperties, DashboardReportProperties } from "../../model-acct/dashboard-report-extended-properties";
import { DashboardReportsRepository } from "../dashboard-reports-repository";

/**
 * Mock implementation of the DashboardReportsRepository
 */
export class MockDashboardReportsRepository extends DashboardReportsRepository {

    override findDashboardReports(dashboardUUID: string): Observable<DashboardReportExtendedProperties[]> {
        throw new Error("Method not implemented.");
    }

    override saveDashboardReport(
        workspaceUUID: string,
        dashboardUUID: string,
        dashboardReport: DashboardReportProperties
    ): Observable<void> {
        throw new Error("Method not implemented.");
    }

    override deleteDashboardReport(workspaceUUID: string,
        dashboardUUID: string,
        dashboardReport:DashboardReportProperties
    ) : Observable<void> {
        throw new Error("Method not implemented.");
    }

}