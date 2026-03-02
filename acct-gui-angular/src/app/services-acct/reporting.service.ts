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
import { DataProvider } from '../model-acct/data-provider';
import { AcctDataProvidersRepository } from '../repositories-acct/data-providers-repository';
import { DataProviderInstance } from '../model-acct/data-provider-instance';
import { DataProviderInstanceUUIDResponse } from '../model-acct/data-provider-instance-uuid-response';
import { AcctDataProviderInstancesRepository } from '../repositories-acct/data-provider-instances-repository';
import { DataProviderInstanceProperties, DataProviderInstanceRuntimeParameter } from '../model-acct/data-provider-instance-properties';
import { ReportingDataSet } from '../model-acct/reporting-data-set';
import { ReportExtendedProperties } from '../model-acct/report-properties';
import { AcctReportsRepository } from '../repositories-acct/reports-repository';
import { ReportParameter } from '../model-acct/report-parameter';
import { DashboardReportsRepository } from '../repositories-acct/dashboard-reports-repository';
import { DashboardReportExtendedProperties, DashboardReportProperties } from '../model-acct/dashboard-report-extended-properties';

// Constants that define the boundaries of a page that's supposed to contain all elements ever to
// have been created in the entire ACCT ecosystem.
const ALL_RESULTS_PAGE_NUMBER : number = 0
const ALL_RESULTS_PAGE_SIZE : number = 10000000

@Injectable({
  providedIn: 'root'
})
export class ReportingService {

  constructor(
    private dashboardsRepository : AcctDashboardsRepository,
    private dataProvidersRepository : AcctDataProvidersRepository,
    private dataProviderInstancesRepository : AcctDataProviderInstancesRepository,
    private reportsRepository : AcctReportsRepository,
    private dashboardReportsRepository : DashboardReportsRepository,
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
   * Retrieves a set of all the data providers available in the system
   */
  public findAllDataProviders() : Observable<DataProvider[]> {
    return this.dataProvidersRepository.findAllDataProviders()
  }

  /**
   * Fetches the data set produced by the referenced data provider for the given
   * instance properties and runime parameters
   * 
   * @param dataProviderUUID Unique identifier for the referenced data provider
   * @param instanceProperties Optional properties to be used when initializing the data provider instance
   * @param runtimeParameters Optional parameters to be used when running the data provider instance
   */
  public fetchDataProviderDataSet(
    dataProviderUUID:string,
    instanceProperties?:Map<string, string>,
    runtimeParameters?:Map<string, string>
  ) : Observable<ReportingDataSet>
  {
    return this.dataProvidersRepository.fetchDataDataSet(
      dataProviderUUID,
      instanceProperties ?? new Map<string,string>,
      runtimeParameters ?? new Map<string,string>
    )
  }

  public saveDataProviderInstance(
    dataProviderInstance : DataProviderInstanceProperties,
    dataProviderInstanceUUID? : string
  ) : Observable<DataProviderInstanceUUIDResponse>
  {
    return this.dataProviderInstancesRepository
      .saveDataProviderInstance(
        dataProviderInstance,
        dataProviderInstanceUUID
      )
  }

  /**
   * Returns an observable that produces a set of all the data provider instances that exist in the system
   */
  public findAllDataProviderInstances() : Observable<DataProviderInstance[]> {
    return this.dataProviderInstancesRepository.findAllDataProviderInstances()
  }

  /**
   * Returns an observable that produces the details for the referenced data provider instance
   * @param dataProviderInstance refernece to the data provider instance for which the details need to be fetched
   */
  public findDataProviderInstanceDetails(dataProviderInstanceUUID : string) : Observable<DataProviderInstanceProperties> {
    return this.dataProviderInstancesRepository.findDataProviderInstanceProperties(dataProviderInstanceUUID)
  }

  public deleteDataProviderInstance(dataProviderInstance : DataProviderInstance) : Observable<void> {
    if (dataProviderInstance.dataProviderInstanceUUID) {
      return this.dataProviderInstancesRepository.deleteDataProviderInstance(
        dataProviderInstance.dataProviderInstanceUUID
      )
    } else {
      throw new Error("Data provider instance does not have an UUID")
    }
  }

  /**
   * Fetches the data set produced by the referenced data provider instance for the given
   * runime parameters
   * 
   * @param dataProviderInstanceUUID Unique identifier for the referenced data provider instance
   * @param runtimeParameters Optional parameters to be used when running the data provider instance
   */
  public fetchDataProviderInstanceDataSet(
    dataProviderInstanceUUID:string,
    runtimeParameters?:Map<string, string>
  ) : Observable<ReportingDataSet>
  {
    return this.dataProviderInstancesRepository.fetchDataProviderInstanceDataSet(
      dataProviderInstanceUUID,
      runtimeParameters ?? new Map<string,string>
    )
  }

  /**
   * Returns an observable that produces an array of all the {@link ReportExtendedProperties reports}
   * that are owned by or accessible to the current user within the ACCT ecosystem.
   */
  public findAllReports() : Observable<ReportExtendedProperties[]> {
    return this.reportsRepository
      .findSortedPageOfUserAccessibleReports(ALL_RESULTS_PAGE_NUMBER, ALL_RESULTS_PAGE_SIZE)
      .pipe(
        map(page => page.data)
      )
  }

  public saveReport(report : ReportExtendedProperties) : Observable<void> {
    return this.reportsRepository
      .saveReport(
        report.reportProperties,
        report.reportUUID
      )
      .pipe(map(() => {}))
  }

  public deleteReport(report:ReportExtendedProperties) : Observable<void> {
    // Get the report UUID
    const reportUUID = this.verifyReportAndReturnReportUUID(report)

    //Delete the report
    return this.reportsRepository.deleteReport(reportUUID)
  }

  /**
   * Retrieves a set of all the runtime parameters accepted by the referenced report.
   * 
   * @param reportUUID Uniquely identifies the report.
   */
  public getReportRuntimeParameters(reportUUID:string) : Observable<DataProviderInstanceRuntimeParameter[]> {
    return this.reportsRepository.getReportRuntimeParameters(reportUUID)
  }

  /**
   * Runs the referenced report for the given parameters and retrieves the resulting data set
   * @param reportUUID Uniquely identifies the referenced report
   * @param runtimeParmaters The given runtime parameters
   * @returns 
   */
  public getReportDataWithRuntimeParameters(reportUUID:string, runtimeParmaters:ReportParameter[]) : Observable<ReportingDataSet> {
    return this.reportsRepository.getReportDataWithRuntimeParameters(
      reportUUID,
      runtimeParmaters
    )
  }

  /**
   * Retrieves all the dashboard reports present in the dashboard with the referenced dashboard
   * @param dashboardUUID Uniquely identifies the referenced dashboard.
   */
  public findDashboardReports(dashboardUUID:string) : Observable<DashboardReportExtendedProperties[]> {
    return this.dashboardReportsRepository.findDashboardReports(dashboardUUID)
  }

  /**
   * Persists the referenced dashboard report.
   * @param dashboardReport The referenced dashboard report.
   */
  public saveDashboardReport(
    workspaceUUID:string,
    dashboardUUID:string,
    dashboardReport:DashboardReportProperties
  ) : Observable<void> {
    return this.dashboardReportsRepository.saveDashboardReport(
      workspaceUUID,
      dashboardUUID,
      dashboardReport
    )
  }

  /**
   * Deletes the referenced dashboard report.
   * @param workspaceUUID Uniquely identifies the workspace that the dashboard is part of.
   * @param dashboardUUID Uniquely identifies the dashboard where the dashboard report resides.
   * @param dashboardReport The dashboard report to be deleted.
   * @returns An observable that marks the end of the operation.
   */
  public deleteDashboardReport(
    workspaceUUID:string,
    dashboardUUID:string,
    dashboardReport:DashboardReportProperties
  ) : Observable<void> {
    return this.dashboardReportsRepository.deleteDashboardReport(
      workspaceUUID,
      dashboardUUID,
      dashboardReport
    )
  }

  /**
   * Makes sure that the referenced report is defined and has a reportUUID that is also defined.
   * If everything is in order, then the reportUUID is returned. If not, then an exception is thrown.
   * @param report the report to be verified.
   * @returns the UUID of the report.
   */
  private verifyReportAndReturnReportUUID(report? : ReportExtendedProperties) : string {
    if (!isDefined(report?.reportUUID)) {
      throw "Report UUID not provided"
    }

    return (report?.reportUUID ?? "")
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
