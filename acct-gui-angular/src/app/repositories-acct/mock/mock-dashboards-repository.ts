import { Observable } from "rxjs";
import { AcctDashboardsRepository } from "../dashboards-repository";
import { DashboardCollections } from "../../model-acct/dashboard-collections";
import { Dashboard } from "../../model-acct/dashboard";
import { DashboardUUIDResponse } from "../../model-acct/dashboard-uuid-response";

/**
 * Mock implementation of the AcctDashboardsRepository
 */
export class MockAcctDashboardsRepository extends AcctDashboardsRepository {

    /**
     * Retrieves the groups of user-accessible dashboards
     */
    override findUserAccessibleDashboards(workspaceUUID:string) : Observable<DashboardCollections> {
        throw new Error("Method not implemented.");
    }

    /**
     * Saves the referenced dashboard into the referenced workspace.
     * @param workspaceUUID Unique identifier used for referencing the workspace where the dashboard needs to be saved.
     * @param dashboard Reference to the dashboard object that needs to be saved.
     * @returns A contianer for the UUID of the saved dashboard.
     */
    override saveDashboard(workspaceUUID:string, dashboard:Dashboard) : Observable<DashboardUUIDResponse> {
        throw new Error("Method not implemented.");
    }

    /**
     * Deletes the referenced dashboard from the referenced workspace
     * 
     * @param workspaceUUID Unique identifier used for referencing the workspace from where the dashboard needs to be removed.
     * @param dashboardUUID Unique identifier of the dashboard object that needs to be deleted.
     */
    override deleteDasboard(workspaceUUID:string, dashboardUUID:string) : Observable<void> {
        throw new Error("Method not implemented.");
    }

}