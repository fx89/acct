import { Dashboard } from "./dashboard";

/**
 * A response object that contains the details of all dashboards accessible to a given owner,
 * grouped into 2 categories: user dashboards and group dashboards.
 */
export interface DashboardCollections {

    /**
     * A collection of dashboards directly-accessible to the owner
     */
    userDashboards : Dashboard[]

    /**
     * A collection of dashboards owned by a group that the owner is part of
     */
    groupDashboards : Dashboard[]

}