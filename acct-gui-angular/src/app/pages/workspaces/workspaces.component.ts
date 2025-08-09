import { Component, EventEmitter, OnInit } from '@angular/core';
import { ItemsManagerCardAction, ItemsManagerCardPropertyExtractor, ItemsManagerComponent, ItemsManagerDataItem, ItemsManagerDataSet, ItemsManagerNewItemFormDirective } from '../../components-acct/items-manager/items-manager.component';
import { ButtonComponent } from '../../components-gui/button/button.component';
import { IconsManagerComponent } from '../../components-acct/icons-manager/icons-manager.component';
import { InputComponent } from '../../components-gui/input/input.component';
import { identity, Observable } from 'rxjs';
import { complete, errorConsumingObservableOperation, errorConsumingObservableTransform } from '../../utils-reusalbe/rxjs-utils';
import { IconifiedWorkspace, Workspace } from '../../model-acct/workspace';
import { WorkspaceService } from '../../services-acct/workspace.service';
import { isDefined } from '../../utils-reusalbe/lang-utils';
import { IconProperties } from '../../model-acct/icon-properties';
import { DialogComponent } from '../../components-gui/dialog/dialog.component';
import { CatalogService } from '../../services-acct/catalog.service';
import { CardData } from '../../components-gui/cards-list/card-data';
import { SelectComponent } from '../../components-gui/select/select.component';
import { WorkspaceSelectorService } from '../../services-acct/workspace-selector.service';
import { MsgboxComponent } from '../../components-gui/msgbox/msgbox.component';
import { MsgboxType } from '../../components-gui/msgbox/msgbox-type';
import { CardDataService, CurrencyCardData } from '../../services-acct/card-data.service';

@Component({
  selector: 'app-workspaces',
  imports: [
    ItemsManagerComponent,
    ItemsManagerNewItemFormDirective,
    ButtonComponent,
    IconsManagerComponent,
    InputComponent,
    DialogComponent,
    SelectComponent,
    MsgboxComponent
  ],
  templateUrl: './workspaces.component.html',
  styleUrl: './workspaces.component.less'
})
export class WorkspacesComponent implements OnInit {

  workspacesListForceReloadEventEmitter : EventEmitter<void> = new EventEmitter<void>

  workspacesListSelectedItem? : IconifiedWorkspace

  workspaceIconChooserVisible : boolean = false

  workspaceSelectionConfirmationMessageBoxVisible : boolean = false

  workspaceSelectionConfirmationMessageBoxType : MsgboxType = MsgboxType.OK_ONLY

  registeredCurrencies : CurrencyCardData[] = []

  selectedCurrency? : CurrencyCardData

  selectedWorkspace? : Workspace

  constructor(
    private workspaceService : WorkspaceService,
    private catalogService : CatalogService,
    private workspaceSelectorSerivce : WorkspaceSelectorService,
    private cardDataService : CardDataService
  ) {

  }

  workspacesListProducer : (() => Observable<ItemsManagerDataSet>) = () => {
    return errorConsumingObservableTransform(
      this.workspaceService.findUserAccessibleWorkspaces(),
      identity,
      err => {
        // TODO: Toast
        console.log(err)
      }
    )
  }

  workspaceCardImageRefExtractor : ItemsManagerCardPropertyExtractor =
    (item:IconifiedWorkspace) => item.imageData ?? ""
  
  workspaceCardTitleExtractor : ItemsManagerCardPropertyExtractor =
    (item:IconifiedWorkspace) => item.workspaceName

  workspaceCardTextExtractor : ItemsManagerCardPropertyExtractor = 
    (item:IconifiedWorkspace) => item.workspaceDescription

  workspaceDeletionConsumer : ((item:ItemsManagerDataItem<IconifiedWorkspace>) => Observable<void>) =
    (item:ItemsManagerDataItem<IconifiedWorkspace>) => errorConsumingObservableOperation(
      this.workspaceService.deleteWorkspace(item),
      err => {
        // TODO: toast
        console.log(err)
      }
    )

  newWorkspaceSupplier : (() => IconifiedWorkspace) = () => {
    // Create the currency
    const workspace : IconifiedWorkspace = {
      workspaceName        : "",
      workspaceDescription : "",
      workspaceIconUUID    : "",
      defaultCurrencyUUID  : "",
      imageData            : ""
    }

    // Store the currency item
    this.workspacesListSelectedItem = workspace

    // Return a reference to the newly created currency
    return workspace
  }

  workspaceSavingConsumer : ((item:IconifiedWorkspace) => Observable<void>) =
    (item:IconifiedWorkspace) => errorConsumingObservableTransform(
      this.workspaceService.saveWorkspace(item),
      () => {
        // Reload the page to force a re-initialization of all components, in case the default workspace was edited
        this.reloadPage()
      },
      err => {
        // TODO: toast
        console.log(err)
      }
    )

  workspaceValidator : ((item:IconifiedWorkspace) => boolean) =
    (item:IconifiedWorkspace) => {
      if (item) {
        return (
          isDefined(item.workspaceName) &&
          isDefined(item.workspaceDescription) &&
          isDefined(item.defaultCurrencyUUID) &&
          isDefined(item.workspaceIconUUID)
        )
      }

      return false
    }

  workspaceSelectAction : ItemsManagerCardAction = (workspace:ItemsManagerDataItem<Workspace>) => {
    this.selectedWorkspace = workspace
    this.workspaceSelectorSerivce.setSelectedWorkspace(workspace)
    this.workspaceSelectionConfirmationMessageBoxVisible = true
  }

  ngOnInit() : void {
    this.cardDataService.loadRegisteredCurrencies().subscribe({
      next: registeredCurrencies => this.registeredCurrencies = registeredCurrencies,
      error: err => {
        // TODO: Toast
        console.log(err)
      }
    })
  }

  onChooseWorkspaceIconClick() : void {
    this.workspaceIconChooserVisible = true
  }

  onWorkspaceIconSelected(icon:IconProperties) : void {
    if (this.workspacesListSelectedItem) {
      // Note that the selected workspace is no longer optional
      const selectedItem : IconifiedWorkspace = this.workspacesListSelectedItem

      // Set the icon UUID
      selectedItem.workspaceIconUUID = icon.iconUUID

      // Apply the icon
      this.catalogService.applyIcon(
        () => selectedItem.workspaceIconUUID,
        imageData => selectedItem.imageData = imageData
      ).subscribe()

      // Hide the dialog
      this.workspaceIconChooserVisible = false
    }
  }

  onSelectedCurrencyChange(currencyCardData:CardData|undefined) : void {
    if (this.workspacesListSelectedItem) {
      if (currencyCardData) {
        this.selectedCurrency = currencyCardData as CurrencyCardData
        this.workspacesListSelectedItem.defaultCurrencyUUID = this.selectedCurrency.currency.currencyUUID
      }
    }
  }

  onNewWorkspace() : void {
    delete this.selectedCurrency
  }

  onWorkspaceSelectionChanged(workspace:IconifiedWorkspace) : void {
    this.selectedCurrency = this.registeredCurrencies.filter(c => workspace.defaultCurrencyUUID == c.currency.currencyUUID)[0]
  }

  /**
   * Triggers a page reload, to force all components to re-initialize
   */
  reloadPage() : void {
    window.location.reload()
  }   

}
