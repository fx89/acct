import { Injectable } from '@angular/core';
import { AcctDashboardsRepository } from '../repositories-acct/dashboards-repository';
import { CatalogService } from './catalog.service';
import { concatAll, map, Observable } from 'rxjs';
import { Dashboard, IconifiedDashboard } from '../model-acct/dashboard';
import { Workspace } from '../model-acct/workspace';
import { isDefined } from '../utils-reusalbe/lang-utils';
import { DashboardCollections } from '../model-acct/dashboard-collections';
import { distinctElementsArray } from '../utils-reusalbe/array-utils';
import { DashboardUUIDResponse } from '../model-acct/dashboard-uuid-response';
import { emptyObservable } from '../utils-reusalbe/rxjs-utils';

@Injectable({
  providedIn: 'root'
})
export class ReportingService {

  constructor(
    private dashboardsRepository : AcctDashboardsRepository,
    private catalogService : CatalogService
  ) { }

  /**
   * Returns an observable that yields the complete collection of dashboards that reside in the referenced
   * workspace and are accessible to the current user either directly or via a group. The dashboard icons
   * are added to each of the retrieved dashboards.
   * @param workspace The workspace that contains the dashboards to be retrieved.
   */
  public findUserAccessibleDashboards(workspace : Workspace) : Observable<IconifiedDashboard[]> {
    // Make sure the workspace is present and has an UUID
    const workspaceUUID : string = this.verifyWorkspaceAndReturnWorkspaceUUID(workspace)
    
    return this.dashboardsRepository.findUserAccessibleDashboards(workspaceUUID).pipe(
      // Flatten the dashboard collections into a single contiguous collection
      map(dashboardCollections => this.dashboardCollectionsTodashboardsArray(dashboardCollections)),

      // Convert to iconified dashboards
      map(dashboards => dashboards as IconifiedDashboard[]),

      // Load the icon for each of the dashboards
      map(dashboards =>
        this.catalogService.applyIconToItems(
          dashboards,
          dashboard => dashboard.dashboardIconUUID,
          (dashboard, imageData) => dashboard.imageData = imageData
        )
      ),

      // Flatten the Observable-of-Observables resulted from the icon applying operation
      concatAll()
    )
  }

  /**
   * Saves the referenced dashboard
   * 
   * @param workspace the workspace where the dashboard needs to be saved
   * @param dashboard the referenced dashboard
   */
  public saveDashboard(workspace: Workspace, dashboard: Dashboard): Observable<DashboardUUIDResponse> {
    const workspaceUUID : string = this.verifyWorkspaceAndReturnWorkspaceUUID(workspace)
    return this.dashboardsRepository.saveDashboard(workspaceUUID, dashboard)
  }

  /**
   * Deletes the referenced dashboard from the repository
   * 
   * @param workspace the workspace that contains the dashboard to be delted
   * @param dashboard the referenced dashboard
   */
  public deleteDashboard(workspace: Workspace, dashboard: Dashboard): Observable<void> {
    const workspaceUUID : string = this.verifyWorkspaceAndReturnWorkspaceUUID(workspace)

    if (dashboard.dashboardUUID) {
      return this.dashboardsRepository.deleteDasboard(workspaceUUID, dashboard.dashboardUUID)
    }

    throw new Error("The referenced dashboard does not have an UUID")
  }

  /**
   * Makes sure that the referenced workspace is defined and has a workspaceUUID that is also defined.
   * If everything is in order, then the workspaceUUID is returned. If not, then an exception is thrown.
   * @param workspace the workspace to be verified.
   * @returns the UUID of the workspace.
   */
  private verifyWorkspaceAndReturnWorkspaceUUID(workspace? : Workspace) : string {
    if (!isDefined(workspace?.workspaceUUID)) {
      throw "Workspace UUID not provided"
    }

    return (workspace?.workspaceUUID ?? "")
  }

  private dashboardCollectionsTodashboardsArray(dashboardCollections : DashboardCollections) : Dashboard[] {
    return distinctElementsArray(
          dashboardCollections.userDashboards.concat(
            dashboardCollections.groupDashboards
          ),
          // Dashboards are uniquely identified by the dashboardUUID
          (dashboard:Dashboard) => dashboard.dashboardUUID
        )
  }

}
