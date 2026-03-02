import { identity, Observable } from "rxjs";
import { DashboardReportExtendedProperties, DashboardReportProperties } from "../../model-acct/dashboard-report-extended-properties";
import { DashboardReportsRepository } from "../dashboard-reports-repository";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector } from "../../services-reusable/http-connectors.service";

/**
 * Implementation of the DashboardReportsRepository that uses the HTTP client abstraction layer
 * to communicate with the Reporting back-end service
 */
export class HttpDashboardReportsRepository extends DashboardReportsRepository {

    constructor(
        private httpConnector : HttpConnector
    ) {
        super()
    }

    override findDashboardReports(dashboardUUID: string): Observable<DashboardReportExtendedProperties[]> {
        return new Observable<DashboardReportExtendedProperties[]>(subscriber => {
            // Create parameters object
            const params : Record<string, string | number | boolean | ReadonlyArray<string | number | boolean>> = {}
            params["dashboardUUID"] = dashboardUUID

            // Find the dashboard reports
            this.httpConnector.get(
                {
                    url: "/dashboards/reports",
                    data: {
                        params: params
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    identity,
                    "Dashboard reports not found."
                )
            )
        })
    }

    override saveDashboardReport(
        workspaceUUID: string,
        dashboardUUID: string,
        dashboardReport: DashboardReportProperties
    ): Observable<void> {
        return new Observable<void>(subscriber => {
            // Create parameters object
            const params : Record<string, string | number | boolean | ReadonlyArray<string | number | boolean>> = {}
            params["workspaceUUID"] = workspaceUUID
            params["dashboardUUID"] = dashboardUUID

            // Save the dashboard report
            this.httpConnector.put(
                {
                    url: "/dashboards/reports",
                    data: {
                        params: params,
                        body: dashboardReport
                    }
                },
                {
                    responseHandler: () => {
                        subscriber.next()
                        subscriber.complete()
                    },
                    errorHandler: err => subscriber.error(err)
                }
            )
        })
    }

    override deleteDashboardReport(
        workspaceUUID: string,
        dashboardUUID: string,
        dashboardReport: DashboardReportProperties
    ) : Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.delete(
                {
                    url: "/dashboards/reports",
                    data: {
                        params: {
                            workspaceUUID: workspaceUUID,
                            dashboardUUID: dashboardUUID,
                            reportUUID: dashboardReport.reportUUID ?? ""
                        }
                    }
                },
                {
                    responseHandler: () => {
                        subscriber.next()
                        subscriber.complete()
                    },
                    errorHandler: err => subscriber.error(err)
                }
            )
        })
    }

}