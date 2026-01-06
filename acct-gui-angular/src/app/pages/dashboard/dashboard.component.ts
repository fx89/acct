import { Component, EventEmitter, OnInit } from '@angular/core';
import { ReportingService } from '../../services-acct/reporting.service';
import { ItemsManagerCardAction, ItemsManagerCardPropertyExtractor, ItemsManagerComponent, ItemsManagerDataItem, ItemsManagerDataSet, ItemsManagerNewItemFormDirective } from '../../components-acct/items-manager/items-manager.component';
import { Observable, switchMap } from 'rxjs';
import { complete, errorConsumingObservableOperation, errorConsumingObservableTransform, waitForCondition } from '../../utils-reusalbe/rxjs-utils';
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

@Component({
  selector: 'app-dashboard',
  imports: [
    ItemsManagerComponent,
    ItemsManagerNewItemFormDirective,
    InputComponent,
    ButtonComponent,
    DialogComponent,
    IconsManagerComponent
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

  constructor(
    private reportingService : ReportingService,
    private workspaceSelectorService : WorkspaceSelectorService,
    private catalogService : CatalogService
  ) {}


  ngOnInit(): void {
    // Load the selected workspace
    this.loadSelectedWorkspace().subscribe()
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
      // Once the selected workspace has been fetched, go ahead and fetch the accounts
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

  private hideDashboardIconManager() : void {
    this.dashboardIconChooserVisible = false
  }

  private showDashboardIconManager() : void {
    this.dashboardIconChooserVisible = true
  }

  dashboardLoadAction : ItemsManagerCardAction = (dashboard:ItemsManagerDataItem<Dashboard>) => {
    // TODO: open the dashboard page
  }

}
