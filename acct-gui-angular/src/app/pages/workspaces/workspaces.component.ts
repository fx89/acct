import { Component, EventEmitter, OnInit } from '@angular/core';
import { ItemsManagerCardPropertyExtractor, ItemsManagerComponent, ItemsManagerDataItem, ItemsManagerDataSet, ItemsManagerNewItemFormDirective } from '../../components-acct/items-manager/items-manager.component';
import { ButtonComponent } from '../../components-gui/button/button.component';
import { IconsManagerComponent } from '../../components-acct/icons-manager/icons-manager.component';
import { InputComponent } from '../../components-gui/input/input.component';
import { identity, Observable } from 'rxjs';
import { complete, errorConsumingObservableOperation, errorConsumingObservableTransform } from '../../utils-reusalbe/rxjs-utils';
import { IconifiedWorkspace } from '../../model-acct/workspace';
import { WorkspaceService } from '../../services-acct/workspace.service';
import { isDefined } from '../../utils-reusalbe/lang-utils';
import { IconProperties } from '../../model-acct/icon-properties';
import { DialogComponent } from '../../components-gui/dialog/dialog.component';
import { CatalogService } from '../../services-acct/catalog.service';
import { IconifiedCurrencyProperties } from '../../model-acct/currency-properties';
import { CardData } from '../../components-gui/cards-list/card-data';
import { SelectComponent } from '../../components-gui/select/select.component';

type CurrencyCardData = CardData & { currency : IconifiedCurrencyProperties }

@Component({
  selector: 'app-workspaces',
  imports: [
    ItemsManagerComponent,
    ItemsManagerNewItemFormDirective,
    ButtonComponent,
    IconsManagerComponent,
    InputComponent,
    DialogComponent,
    SelectComponent
  ],
  templateUrl: './workspaces.component.html',
  styleUrl: './workspaces.component.less'
})
export class WorkspacesComponent implements OnInit {

  workspacesListForceReloadEventEmitter : EventEmitter<void> = new EventEmitter<void>

  workspacesListSelectedItem? : IconifiedWorkspace

  workspaceIconChooserVisible : boolean = false

  registeredCurrencies : CurrencyCardData[] = []

  selectedCurrency? : CurrencyCardData

  constructor(
    private workspaceService : WorkspaceService,
    private catalogService : CatalogService
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
      () => {},
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

  ngOnInit() : void {
    this.loadRegisteredCurrencies().subscribe()
  }

  private loadRegisteredCurrencies() : Observable<void> {
      return new Observable<void>(subscriber => {
        this.catalogService.findAllCurrencies().subscribe({
          next: currencies => {
            this.registeredCurrencies = currencies.map(currency => {
              return {
                currency : currency,
                title    : currency.currencyCode,
                text     : currency.currencyName,
                imageRef : currency.imageData
              }
            })
  
            complete(subscriber, undefined)
          },
          error: err => {
            // TODO: Toast
            console.log(err)
          }
        })
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

}
