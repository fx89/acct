import { identity, Observable } from "rxjs";
import { AcctDashboardsRepository } from "../dashboards-repository";
import { DashboardCollections } from "../../model-acct/dashboard-collections";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector } from "../../services-reusable/http-connectors.service";
import { Dashboard } from "../../model-acct/dashboard";
import { DashboardUUIDResponse } from "../../model-acct/dashboard-uuid-response";

/**
 * Implementation of the AcctDashboardsRepository that connects to the back-end
 * services to provide functionality
 */
export class HttpAcctDashboardsRepository extends AcctDashboardsRepository {

    constructor(private httpConnector : HttpConnector) {
        super()
    }

    /**
     * Retrieves the groups of user-accessible dashboards
     */
    override findUserAccessibleDashboards(workspaceUUID:string) : Observable<DashboardCollections> {
        return new Observable<DashboardCollections>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/dashboards?workspaceUUID=" + workspaceUUID
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    identity,
                    "No user-accessible dashboards were found."
                )
            )
        })
    }

    /**
     * Saves the referenced dashboard into the referenced workspace.
     * @param workspaceUUID Unique identifier used for referencing the workspace where the dashboard needs to be saved.
     * @param dashboard Reference to the dashboard object that needs to be saved.
     * @returns A contianer for the UUID of the saved dashboard.
     */
    override saveDashboard(workspaceUUID:string, dashboard:Dashboard) : Observable<DashboardUUIDResponse> {
        return new Observable<DashboardUUIDResponse>(subscriber => {
            // Create parameters object
            const params : Record<string, string | number | boolean | ReadonlyArray<string | number | boolean>> = {}

            // Add the workspaceUUID parameter
            params["workspaceUUID"] = workspaceUUID

            // If the dashboard has an UUID, then add it to the parameters object
            if (dashboard.dashboardUUID) {
                params["dashboardUUID"] = dashboard.dashboardUUID
            }

            this.httpConnector.put(
                {
                    url: "/dashboards",
                    data: {
                        params: params,
                        body: {
                            dashboardName        : dashboard.dashboardName,
                            dashboardDescription : dashboard.dashboardDescription,
                            dashboardIconUUID    : dashboard.dashboardIconUUID
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody) => responseBody,
                    "Dashboard not saved."
                )
            )
        })
    }

    /**
     * Deletes the referenced dashboard from the referenced workspace
     * 
     * @param workspaceUUID Unique identifier used for referencing the workspace from where the dashboard needs to be removed.
     * @param dashboardUUID Unique identifier of the dashboard object that needs to be deleted.
     */
    override deleteDasboard(workspaceUUID:string, dashboardUUID:string) : Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.delete(
                {
                    url: "/dashboards",
                    data: {
                        params: {
                            workspaceUUID: workspaceUUID,
                            dashboardUUID: dashboardUUID
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