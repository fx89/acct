import { Component, EventEmitter, OnInit } from '@angular/core';
import { ItemsManagerCardAction, ItemsManagerCardPropertyExtractor, ItemsManagerComponent, ItemsManagerDataItem, ItemsManagerDataSet, ItemsManagerNewItemFormDirective } from '../../components-acct/items-manager/items-manager.component';
import { DialogComponent } from '../../components-gui/dialog/dialog.component';
import { InputComponent } from '../../components-gui/input/input.component';
import { SelectComponent } from '../../components-gui/select/select.component';
import { ButtonComponent } from '../../components-gui/button/button.component';
import { IconsManagerComponent } from '../../components-acct/icons-manager/icons-manager.component';
import { forkJoin, Observable, switchMap } from 'rxjs';
import { complete, errorConsumingObservableOperation, errorConsumingObservableTransform, waitForCondition } from '../../utils-reusalbe/rxjs-utils';
import { WorkspaceService } from '../../services-acct/workspace.service';
import { Account, IconifiedAccount } from '../../model-acct/account';
import { BankCardData, CardDataService, CurrencyCardData } from '../../services-acct/card-data.service';
import { WorkspaceSelectorService } from '../../services-acct/workspace-selector.service';
import { IconifiedWorkspace } from '../../model-acct/workspace';
import { isDefined } from '../../utils-reusalbe/lang-utils';
import { CardData } from '../../components-gui/cards-list/card-data';
import { IconProperties } from '../../model-acct/icon-properties';
import { CatalogService } from '../../services-acct/catalog.service';

@Component({
  selector: 'app-accounts',
  imports: [
    ItemsManagerComponent,
    ItemsManagerNewItemFormDirective,
    DialogComponent,
    InputComponent,
    SelectComponent,
    ButtonComponent,
    IconsManagerComponent
  ],
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.less'
})
export class AccountsComponent implements OnInit {

  accountsListForceReloadEventEmitter : EventEmitter<void> = new EventEmitter<void>

  accountsListSelectedItem? : IconifiedAccount

  registeredCurrencies : CurrencyCardData[] = []

  selectedCurrency? : CurrencyCardData

  registeredBanks : BankCardData[] = []
  
  selectedBank? : BankCardData

  selectedWorkspace! : IconifiedWorkspace

  selectedAccount! : Account

  accountIconChooserVisible : boolean = false

  constructor(
    private workspaceService : WorkspaceService,
    private cardDataService : CardDataService,
    private catalogService : CatalogService,
    private workspaceSelectorService : WorkspaceSelectorService
  ) {

  }

  ngOnInit() : void {
    forkJoin([
      this.loadRegisteredCurrencies(),
      this.loadRegisteredBanks(),
      this.loadSelectedWorkspace()
    ]).subscribe()
  }

  loadRegisteredBanks() : Observable<void> {
    return new Observable<void>(subscriber => {
      this.cardDataService.loadRegisteredBanks().subscribe({
        next: registeredBanks => {
          this.registeredBanks = registeredBanks
          complete(subscriber, undefined)
        },
        error: err => {
          // TODO: Toast
          console.log(err)
        }
      })
    })
  }
  
  loadRegisteredCurrencies() : Observable<void> {
    return new Observable<void>(subscriber => {
      this.cardDataService.loadRegisteredCurrencies().subscribe({
        next: registeredCurrencies => {
          this.registeredCurrencies = registeredCurrencies
          complete(subscriber, undefined)
        },
        error: err => {
          // TODO: Toast
          console.log(err)
        }
      })
    })
  }

  loadSelectedWorkspace() : Observable<void> {
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

  accountsListProducer : (() => Observable<ItemsManagerDataSet>) = () => {
      // Wait until the selectedWorkspace is fetched
      return waitForCondition(() => isDefined(this.selectedWorkspace)).pipe(
        // Once the selected workspace has been fetched, go ahead and fetch the accounts
        switchMap(() => this.workspaceService.findWorkspaceAccounts(this.selectedWorkspace))
      )
  }

  accountCardImageRefExtractor : ItemsManagerCardPropertyExtractor =
    (item:IconifiedAccount) => item.imageData ?? ""
  
  accountCardTitleExtractor : ItemsManagerCardPropertyExtractor =
    (item:IconifiedAccount) => item.accountName

  accountCardTextExtractor : ItemsManagerCardPropertyExtractor = 
    (item:IconifiedAccount) => this.registeredBanks.filter(b => item.bankUUID == b.bank.bankUUID)[0]?.bank?.bankCode + " - " + item.accountNumber

  accountDeletionConsumer : ((item:ItemsManagerDataItem<IconifiedAccount>) => Observable<void>) =
    (item:ItemsManagerDataItem<IconifiedAccount>) => errorConsumingObservableOperation(
      this.workspaceService.deleteWorkspaceAccount(this.selectedWorkspace, item),
      err => {
        // TODO: toast
        console.log(err)
      }
    )

  newAccountSupplier : (() => IconifiedAccount) = () => {
    // Create the currency
    const account : IconifiedAccount = {
      accountName     : "",
      accountNumber   : "",
      accountIconUUID : "",
      bankUUID        : "",
      currencyUUID    : ""
    }

    // Store the currency item
    this.accountsListSelectedItem = account

    // Return a reference to the newly created account
    return account
  }

  accountsSavingConsumer : ((item:IconifiedAccount) => Observable<void>) =
    (item:IconifiedAccount) => errorConsumingObservableTransform(
      this.workspaceService.saveAccount(this.selectedWorkspace, item),
      () => { },
      err => {
        // TODO: toast
        console.log(err)
      }
    )

  accountValidator : ((item:IconifiedAccount) => boolean) =
    (item:IconifiedAccount) => {
      if (item) {
        return (
          isDefined(item.accountName) &&
          isDefined(item.accountNumber) &&
          isDefined(item.accountIconUUID) &&
          isDefined(item.bankUUID) &&
          isDefined(item.currencyUUID)
        )
      }

      return false
    }

  accountSelectAction : ItemsManagerCardAction = (account:ItemsManagerDataItem<Account>) => {
    this.selectedAccount = account
    // TODO: switch to account records
  }

  onSelectedCurrencyChange(currencyCardData:CardData|undefined) : void {
    if (this.accountsListSelectedItem) {
      if (currencyCardData) {
        this.selectedCurrency = currencyCardData as CurrencyCardData
        this.accountsListSelectedItem.currencyUUID = this.selectedCurrency.currency.currencyUUID
      }
    }
  }

  onSelectedBankChange(bankCardData:CardData|undefined) : void {
    if (this.accountsListSelectedItem) {
      if (bankCardData) {
        this.selectedBank = bankCardData as BankCardData
        this.accountsListSelectedItem.bankUUID = this.selectedBank.bank.bankUUID ?? ""
      }
    }
  }

  onChooseAccountIconClick() : void {
    this.accountIconChooserVisible = true
  }

  onAccountIconSelected(icon:IconProperties) : void {
    if (this.accountsListSelectedItem) {
      // Note that the account is no longer optional
      const account : IconifiedAccount = this.accountsListSelectedItem

      // Set the icon UUID
      account.accountIconUUID = icon.iconUUID

      // Apply the icon
      this.catalogService.applyIcon(
        () => icon.iconUUID,
        imageData => account.imageData = imageData
      ).subscribe(() => {
        // Once the icon has been apploed, close the icon selection dialog
        this.accountIconChooserVisible = false
      })
    }
  }

  onNewAccount() : void {
    delete this.selectedCurrency
    delete this.selectedBank
  }

  onAccountSelectionChanged(account:IconifiedAccount) : void {
    this.selectedCurrency = this.registeredCurrencies.filter(c => account.currencyUUID == c.currency.currencyUUID)[0]
    this.selectedBank = this.registeredBanks.filter(b => account.bankUUID == b.bank.bankUUID)[0]
  }

}
