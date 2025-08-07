import { Component, EventEmitter } from '@angular/core';
import { ItemsManagerCardAction, ItemsManagerCardPropertyExtractor, ItemsManagerComponent, ItemsManagerDataItem, ItemsManagerDataSet, ItemsManagerNewItemFormDirective } from '../../../components-acct/items-manager/items-manager.component';
import { Observable } from 'rxjs';
import { errorConsumingObservableOperation, errorConsumingObservableTransform } from '../../../utils-reusalbe/rxjs-utils';
import { BankProperties, IconifiedBankProperties } from '../../../model-acct/bank-properties';
import { CatalogService } from '../../../services-acct/catalog.service';
import { isDefined } from '../../../utils-reusalbe/lang-utils';
import { InputComponent } from '../../../components-gui/input/input.component';
import { ButtonComponent } from '../../../components-gui/button/button.component';
import { DialogComponent } from '../../../components-gui/dialog/dialog.component';
import { IconProperties } from '../../../model-acct/icon-properties';
import { IconsManagerComponent } from '../../../components-acct/icons-manager/icons-manager.component';

@Component({
  selector: 'app-banks',
  imports: [
    ItemsManagerComponent,
    ItemsManagerNewItemFormDirective,
    InputComponent,
    ButtonComponent,
    DialogComponent,
    IconsManagerComponent
  ],
  templateUrl: './banks.component.html',
  styleUrl: './banks.component.less'
})
export class BanksComponent {

  constructor(
    private catalogService : CatalogService
  ){

  }

  banksListSelectedItem? : IconifiedBankProperties

  banksListForceReloadEventEmitter : EventEmitter<void> = new EventEmitter<void>()

  bankIconChooserVisible : boolean = false

  /**
   * Produces the list of banks for the item manager
   */
  banksListProducer : (() => Observable<ItemsManagerDataSet>) =
    () => errorConsumingObservableOperation(
      this.catalogService.findAllBanks(),
      err => {
        // TODO: Toast
        console.log(err)
      }
    )

  /**
   * Extracts the card image for the item manager
   */
  bankCardImageRefExtractor : ItemsManagerCardPropertyExtractor =
    (item:IconifiedBankProperties) => item.imageData

  /**
   * Extracts bank name for the item manager
   */
  bankCardTitleExtractor : ItemsManagerCardPropertyExtractor =
    (item:IconifiedBankProperties) => item.bankCode

  /**
   * Extracts bank's internet banking URL for the item manager
   */
  bankCardTextExtractor : ItemsManagerCardPropertyExtractor = 
    (item:IconifiedBankProperties) => item.bankName + " - " + item.internetBankingURL

  /**
   * Deletes a bank for the items manager
   */
  bankDeletionConsumer : ((item:ItemsManagerDataItem<BankProperties>) => Observable<void>) =
    (item:ItemsManagerDataItem<BankProperties>) => errorConsumingObservableOperation(
      this.catalogService.deleteBank(item),
      err => {
        // TODO: toast
        console.log(err)
      }
    )

  /**
   * Initializes a new, unsaved, bank for the items manager
   */
  newBankSupplier : (() => BankProperties) = () => {
    // Create the bank
    const bank : BankProperties = {
      bankCode: "",
      bankName: "",
      internetBankingURL: "",
      bankIconUUID: ""
    }

    // Store the bank item
    this.banksListSelectedItem = bank as IconifiedBankProperties

    // Return a reference to the newly created bank
    return bank
  }

  /**
   * Saves a bank for the items manager
   */
  bankSavingConsumer : ((item:BankProperties) => Observable<void>) =
    (item:BankProperties) => errorConsumingObservableTransform(
      this.catalogService.saveBank(item),
      () => {},
      err => {
        // TODO: toast
        console.log(err)
      }
    )

  /**
   * Validates a bank for the items manager, before saving
   */
  bankValidator : ((item:BankProperties) => boolean) =
    (item:BankProperties) => {
      if (item) {
        return (
          isDefined(item.bankCode) &&
          isDefined(item.bankName) &&
          isDefined(item.internetBankingURL) &&
          isDefined(item.bankIconUUID)
        )
      }

      return false
    }

  onChooseBankIconClick() : void {
    this.showBankIconManager()
  }

  onBankIconSelected(icon:IconProperties) : void {
    if (this.banksListSelectedItem) {
      const selectedBank : IconifiedBankProperties = this.banksListSelectedItem

      // Set the icon UUID
      selectedBank.bankIconUUID = icon.iconUUID

      // Apply the icon
      this.catalogService.applyIcon(
        () => selectedBank.bankIconUUID,
        imageData => selectedBank.imageData = imageData
      ).subscribe()

      // Hide the dialog
      this.hideBankIconManager()
    } else {
      // TODO: Toast
      console.log("Bank not selected")
    }
  }

  private showBankIconManager() : void {
    this.bankIconChooserVisible = true
  }

  private hideBankIconManager() : void {
    this.bankIconChooserVisible = false
  }

}
