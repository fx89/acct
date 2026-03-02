import { Component, EventEmitter, OnInit } from '@angular/core';
import { ReportingService } from '../../services-acct/reporting.service';
import { ItemsManagerCardAction, ItemsManagerCardPropertyExtractor, ItemsManagerComponent, ItemsManagerDataItem, ItemsManagerDataSet, ItemsManagerNewItemFormDirective } from '../../components-acct/items-manager/items-manager.component';
import { forkJoin, identity, map, mergeMap, Observable, switchMap, tap } from 'rxjs';
import { complete, errorConsumingObservableOperation, errorConsumingObservableTransform, newObservable, waitForCondition } from '../../utils-reusalbe/rxjs-utils';
import { Workspace } from '../../model-acct/workspace';
import { WorkspaceSelectorService } from '../../services-acct/workspace-selector.service';
import { Dashboard, IconifiedDashboard } from '../../model-acct/dashboard';
import { isDefined } from '../../utils-reusalbe/lang-utils';
import { IconProperties } from '../../model-acct/icon-properties';
import { CatalogService } from '../../services-acct/catalog.service';
import { InputComponent } from '../../components-gui/input/input.component';
import { ButtonComponent } from '../../components-gui/button/button.component';
import { DialogComponent } from '../../components-gui/dialog/dialog.component';
import { IconsManagerComponent } from '../../components-acct/icons-manager/icons-manager.component';
import { DashboardReportExtendedProperties } from '../../model-acct/dashboard-report-extended-properties';
import { ReportingDataSet } from '../../model-acct/reporting-data-set';
import { ReportViewerComponent } from '../../components-acct/report-viewer/report-viewer.component';
import { ReportExtendedProperties, ReportProperties, ReportType } from '../../model-acct/report-properties';
import { SpinboxComponent } from '../../components-gui/spinbox/spinbox.component';
import { CardData } from '../../components-gui/cards-list/card-data';
import { SelectComponent } from '../../components-gui/select/select.component';
import { PanelComponent } from '../../components-gui/panel/panel.component';

interface DashboardReportWithData {
  dashboardReport : DashboardReportExtendedProperties,
  dashboardReportDataSet? : ReportingDataSet
}

type ReportCard = CardData & { 
  report : ReportExtendedProperties
}

/**
 * Container for the properties and components of a dashboard that's been loaded
 * to be displayed to and edited by the user
 */
interface LoadedDashboard {
  dashboard : Dashboard,
  reports : (DashboardReportWithData | undefined)[][]
}

@Component({
  selector: 'app-dashboard',
  imports: [
    ItemsManagerComponent,
    ItemsManagerNewItemFormDirective,
    InputComponent,
    ButtonComponent,
    DialogComponent,
    IconsManagerComponent,
    ReportViewerComponent,
    SpinboxComponent,
    SelectComponent,
    PanelComponent
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.less'
})
export class DashboardComponent implements OnInit {

  /**
   * The workspace that contains the dashboards for which data is being displayed
   */
  selectedWorkspace! : Workspace

  /**
   * Reference to the item that's selected within the list of dashboards
   */
  dashboardsListSelectedItem? : IconifiedDashboard

  /**
   * Determines the visibility of the dashboard icon chooser
   */
  dashboardIconChooserVisible : boolean = false

  /**
   * Container for the loaded dashboard - also determines weather to display the dashboards manager or the dashboard details
   */
  loadedDashboard? : LoadedDashboard

  layoutEditorDialogVisible : boolean = false

  /**
   * Holds the reports that have been loaded for a given dashboard
   */
  loadedReports : DashboardReportWithData[] = []

  /**
   * Reference to the item that's selected within the list of dashboard reports
   */
  dashboardReportsListSelectedItem? : DashboardReportExtendedProperties

  /**
   * An array of all the reports available in the system
   */
  reports : ReportExtendedProperties[] = []

  /**
   * Report cards of all the reports available in the system, for the repot selector
   */
  reportCards : ReportCard[] = []

  /**
   * The report card selected by the repot selector
   */
  selectedReportCard! : ReportCard

  /**
   * Tells the dashboard reports manager when it's time to reload its list of dashboard reports
   */
  dashboardReportsListForceLoadEventEmitter : EventEmitter<void> = new EventEmitter<void>()

  constructor(
    private reportingService : ReportingService,
    private workspaceSelectorService : WorkspaceSelectorService,
    private catalogService : CatalogService
  ) {}


  ngOnInit(): void {
    forkJoin([
      // Load the selected workspace
      this.loadSelectedWorkspace(),
      this.loadReports()
    ]).subscribe()
  }


  private loadSelectedWorkspace() : Observable<void> {
    return new Observable<void>(subscriber => {
      this.workspaceSelectorService.findSelectedWorkspace().subscribe({
        next: selectedWorkspace => {
          this.selectedWorkspace = selectedWorkspace
          complete(subscriber, undefined)
        },
        error: err => {
          // TODO: Toast
          console.log(err)
        }
      })
    })
  }

  private loadReports() : Observable<void> {
    return new Observable<void>(subscriber => 
      this.reportingService.findAllReports().subscribe({
        next: reports => {
          this.reports = reports
          this.reportCards = this.computeReportCards(this.reports)
          subscriber.complete()
        },
        error: err => {
          // TODO: Toast
          console.log(err)
        }
      })
    )
  }

  /**
   * Extracts the card image for the item manager
   */
  dashboardCardImageRefExtractor : ItemsManagerCardPropertyExtractor =
    (item:IconifiedDashboard) => item.imageData

  /**
   * Extracts the dashboard name for the item manager
   */
  dashboardCardTitleExtractor : ItemsManagerCardPropertyExtractor =
    (item:IconifiedDashboard) => item.dashboardName

  /**
   * Extracts dashboard's description
   */
  dashboardCardTextExtractor : ItemsManagerCardPropertyExtractor = 
    (item:IconifiedDashboard) => item.dashboardDescription

  /**
   * Produces the list of dashboards for the item manager
   */
  dashboardsListProducer : (() => Observable<ItemsManagerDataSet>) = () => {
    // Wait until the selectedWorkspace is fetched
    return waitForCondition(() => isDefined(this.selectedWorkspace)).pipe(
      // Once the selected workspace has been fetched, go ahead and fetch the dashboards
      switchMap(() => this.reportingService.findUserAccessibleDashboards(this.selectedWorkspace))
    )
  }

  /**
   * Deletes a dashboard for the items manager
   */
  dashboardDeletionConsumer : ((item:ItemsManagerDataItem<Dashboard>) => Observable<void>) =
    (item:ItemsManagerDataItem<Dashboard>) => errorConsumingObservableOperation(
      this.reportingService.deleteDashboard(this.selectedWorkspace, item),
      err => {
        // TODO: toast
        console.log(err)
      }
    )

  /**
   * Initializes a new, unsaved, dashboard for the items manager
   */
  newDashboardSupplier : (() => Dashboard) = () => {
    // Create the dashboard
    const dashboard : Dashboard = {
      dashboardName: "",
      dashboardDescription: "",
      dashboardIconUUID: ""
    }

    // Store the dashboard item
    this.dashboardsListSelectedItem = dashboard as IconifiedDashboard

    // Return a reference to the newly created dashboard
    return dashboard
  }

  /**
   * Saves a dashboard for the items manager
   */
  dashboardSavingConsumer : ((item:Dashboard) => Observable<void>) =
    (item:Dashboard) => errorConsumingObservableTransform(
      this.reportingService.saveDashboard(this.selectedWorkspace, item),
      () => {},
      err => {
        // TODO: toast
        console.log(err)
      }
    )

  /**
   * Validates a dashboard for the items manager, before saving
   */
  dashboardValidator : ((item:Dashboard) => boolean) =
    (item:Dashboard) => {
      if (item) {
        return (
          isDefined(item.dashboardName) &&
          isDefined(item.dashboardDescription) &&
          isDefined(item.dashboardIconUUID)
        )
      }

      return false
    }

  /**
   * Extracts the dashboard report report card image for the item manager
   */
  dashboardReportCardImageRefExtractor : ItemsManagerCardPropertyExtractor =
    (item:DashboardReportExtendedProperties) => ""

  /**
   * Extracts the dashboard report name for the item manager
   */
  dashboardReportCardTitleExtractor : ItemsManagerCardPropertyExtractor =
    (item:DashboardReportExtendedProperties) => item?.containerName ?? ""

  /**
   * Extracts a dashboard report description
   */
  dashboardReportCardTextExtractor : ItemsManagerCardPropertyExtractor = 
    (item:DashboardReportExtendedProperties) => item?.reportName ?? ""

  /**
   * Produces the list of dashboard reports for the item manager
   */
  dashboardReportsListProducer : (() => Observable<ItemsManagerDataSet>) = () => {
    return newObservable<ItemsManagerDataSet>(
        <DashboardReportExtendedProperties[]>(
          this.loadedReports
            .filter(rep => isDefined(rep))
            .map(rep => (<DashboardReportWithData><unknown>rep).dashboardReport)
            .sort((rep1, rep2) => rep1.containerName.localeCompare(rep2.containerName))
        )
      )
  }

  /**
   * Deletes a dashboard report for the items manager
   */
  dashboardReportDeletionConsumer : ((item:ItemsManagerDataItem<DashboardReportExtendedProperties>) => Observable<void>) =
    (item:ItemsManagerDataItem<DashboardReportExtendedProperties>) => errorConsumingObservableOperation(
      this.reportingService.deleteDashboardReport(
        this.selectedWorkspace.workspaceUUID ?? "",
        this.dashboardsListSelectedItem?.dashboardUUID ?? "",
        item
      ).pipe(
        tap({
          next: () => {
            this.dashboardLoadAction(this.dashboardsListSelectedItem)
          }
        })
      ),
      err => {
        // TODO: toast
        console.log(err)
      }
    )

  /**
   * Initializes a new, unsaved, dashboard report for the items manager
   */
  newDashboardReportSupplier : (() => DashboardReportExtendedProperties) = () => {
    this.dashboardReportsListSelectedItem = {
      rowNumber : 0,
      columnNumber : 0,
      containerName : "",
      containerWidthPx : 300,
      containerHeightPx : 300,
      filters : [],
      reportName : "",
      reportDescription : "",
      reportType : ReportType.TABLE,
      reportCategoryColumnName : "",
      reportSeries : []
    }

    return this.dashboardReportsListSelectedItem
  }

  /**
   * Saves a dashboard report for the items manager
   */
  dashboardReportSavingConsumer : ((item:DashboardReportExtendedProperties) => Observable<void>) =
    (item:DashboardReportExtendedProperties) => errorConsumingObservableTransform(
      this.reportingService.saveDashboardReport(
        this.selectedWorkspace.workspaceUUID ?? "",
        this.dashboardsListSelectedItem?.dashboardUUID ?? "",
        item
      ).pipe(
        tap({
          next: item => {
            this.dashboardLoadAction(this.dashboardsListSelectedItem)
            return item
          }
        })
      ),
      () => {},
      err => {
        // TODO: toast
        console.log(err)
      }
    )

  /**
   * Validates a dashboard report for the items manager, before saving
   */
  dashboardReportValidator : ((item:DashboardReportExtendedProperties) => boolean) =
    (item:DashboardReportExtendedProperties) => {
      if (item) {
        return (
          isDefined(item.containerName) &&
          isDefined(item.containerWidthPx) &&
          isDefined(item.containerHeightPx) &&
          isDefined(item.reportUUID) &&
          isDefined(item.rowNumber) &&
          isDefined(item.columnNumber) &&
          (
            !isDefined(this.loadedDashboard?.reports[item.columnNumber]) ||
            !isDefined(this.loadedDashboard?.reports[item.columnNumber][item.rowNumber]) ||
            this.loadedDashboard?.reports[item.columnNumber][item.rowNumber]?.dashboardReport.reportUUID == item.reportUUID
          ) &&
          (
            !this.isNewDashboardReport(item) ||
            this.loadedReports.filter(r => r.dashboardReport.reportUUID == item.reportUUID).length == 0
          )
        )
      }

      return false
    }

  dashboardReportsListSelectedItemTransformingMapper : (item:ItemsManagerDataItem<ReportExtendedProperties>) => Observable<ItemsManagerDataItem<ReportExtendedProperties>> =
    (item:ItemsManagerDataItem<ReportExtendedProperties>) => {
      this.selectedReportCard = this.reportCards.filter(rc => rc.report.reportUUID == item.reportUUID)[0]
      return newObservable(item)
    }

  onChooseDashboardIconClick() : void {
    this.showDashboardIconManager()
  }

  onDashboardIconSelected(icon:IconProperties) : void {
    if (this.dashboardsListSelectedItem) {
      const selectedDashboard : IconifiedDashboard = this.dashboardsListSelectedItem

      // Set the icon UUID
      selectedDashboard.dashboardIconUUID = icon.iconUUID

      // Apply the icon
      this.catalogService.applyIcon(
        () => selectedDashboard.dashboardIconUUID,
        imageData => selectedDashboard.imageData = imageData
      ).subscribe()

      // Hide the dialog
      this.hideDashboardIconManager()
    } else {
      // TODO: Toast
      console.log("Dashboard not selected")
    }
  }

  onLayoutEditorActivationButtonClick() : void {
    this.layoutEditorDialogVisible = true
  }

  onBackButtonClick() : void {
    delete this.loadedDashboard
  }

  onReportCardSelected(event:any) : void {
    const reportCard : ReportCard = <ReportCard>event;

    this.selectedReportCard = reportCard

    if (this.dashboardReportsListSelectedItem) {
      this.dashboardReportsListSelectedItem.reportUUID = reportCard.report.reportUUID
      this.dashboardReportsListSelectedItem.reportName = reportCard.report.reportProperties.reportName
    }

  }

  private hideDashboardIconManager() : void {
    this.dashboardIconChooserVisible = false
  }

  private showDashboardIconManager() : void {
    this.dashboardIconChooserVisible = true
  }

  dashboardLoadAction : ItemsManagerCardAction = (dashboard:ItemsManagerDataItem<Dashboard>) => {
    // Load the dashboard
    this.reportingService.findDashboardReports(dashboard.dashboardUUID ?? "")
      .subscribe({
        // Upon successful load, set the loaded dashboard, which results in opening the dashboard page
        next: dashboardReports => {
          // Cache the loaded reports
          this.loadedReports = dashboardReports.map(dr => ({dashboardReport : dr}))

          // Set the loaded dashboard object
          this.loadedDashboard = {
            dashboard: dashboard,
            reports: this.arrangeLoadedReports(this.loadedReports)
          }

          // Begin loading the data for the loaded reports
          this.loadedReports.forEach(report => 
            this.loadReportData(report)
          )

          // Tell the dashboard reports manager it's time to reload its data
          this.dashboardReportsListForceLoadEventEmitter.emit()
        },
        // Upon unsuccesful load, show the error message
        error: err => {
          // TODO: Toast
          console.log(err)
        }
      })
  }

  isDashboardLoaded() : boolean {
    return isDefined(this.loadedDashboard)
  }

  isNewDashboardReport(dashboardReport : DashboardReportExtendedProperties) : boolean {
    return dashboardReport.reportDescription === ""
  }

  castAsReportProperties(object:any) : ReportProperties {
    return <ReportProperties><unknown>object
  }

  private arrangeLoadedReports(reports:DashboardReportWithData[]) : (DashboardReportWithData | undefined)[][] {
    // Get the maximum row and column numbers
    const maxRow = Math.max(...reports.map(r => r.dashboardReport.rowNumber));
    const maxCol = Math.max(...reports.map(r => r.dashboardReport.columnNumber));

    // Initialize the arranged reports array (put undefined wherever there's a missing cell)
    const arrangedReports: (DashboardReportWithData | undefined)[][] =
      Array.from({ length: maxCol + 1 }, () =>
        Array.from({ length: maxRow + 1 }, () => undefined)
      );

    // Populate the arranged reports array by putting each report in its proper cell
    for (const report of reports) {
      const { columnNumber, rowNumber } = report.dashboardReport;
      arrangedReports[columnNumber][rowNumber] = report;
    }

    // Return a reference to the arraged reports array
    return arrangedReports;
  }

  private computeReportCards(reports:ReportExtendedProperties[]) : ReportCard[] {
    return reports.map(report => ({
      report: report,
      title: report.reportProperties.reportName,
      text: report.reportProperties.reportDescription
    }))
  }

  private loadReportData(report:DashboardReportWithData) : void {
    const reportUUID : string = report.dashboardReport.reportUUID ?? ""

    // First get the report parameters and their default values
    this.reportingService.getReportRuntimeParameters(
      reportUUID
    )
    // Then get the report data using the parameter names and their default values
    .pipe(
      map(reportParameters => 
        this.reportingService.getReportDataWithRuntimeParameters(
          reportUUID,
          reportParameters.map(rp => ({ 
            parameterName: rp.parameterName,
            parameterValue: rp.parameterDefaultValue 
          }))
        )
      ),
      mergeMap(identity)
    )
    // Once the report data is fetched, add it to the report
    .subscribe({
      next: reportDataSet => {
        report.dashboardReportDataSet = reportDataSet
      },
      error: err => {
        // TODO: toast
        console.log(err)
      }
    })
  }

}
