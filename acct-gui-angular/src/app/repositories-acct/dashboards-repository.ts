import { Observable } from "rxjs";
import { DashboardCollections } from "../model-acct/dashboard-collections";
import { Dashboard } from "../model-acct/dashboard";
import { DashboardUUIDResponse } from "../model-acct/dashboard-uuid-response";

/**
 * Specification for the dashboards repository
 */
export abstract class AcctDashboardsRepository {

    /**
     * Retrieves the groups of user-accessible dashboards
     */
    public abstract findUserAccessibleDashboards(workspaceUUID:string) : Observable<DashboardCollections>

    /**
     * Saves the referenced dashboard into the referenced workspace.
     * @param workspaceUUID Unique identifier used for referencing the workspace where the dashboard needs to be saved.
     * @param dashboard Reference to the dashboard object that needs to be saved.
     * @returns A contianer for the UUID of the saved dashboard.
     */
    public abstract saveDashboard(workspaceUUID:string, dashboard:Dashboard) : Observable<DashboardUUIDResponse>

    /**
     * Deletes the referenced dashboard from the referenced workspace
     * 
     * @param workspaceUUID Unique identifier used for referencing the workspace from where the dashboard needs to be removed.
     * @param dashboardUUID Unique identifier of the dashboard object that needs to be deleted.
     */
    public abstract deleteDasboard(workspaceUUID:string, dashboardUUID:string) : Observable<void>

}