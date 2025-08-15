import { Component, EventEmitter, input, InputSignal, OnInit, Output } from '@angular/core';
import { BarComponent } from '../../../components-gui/bar/bar.component';
import { ButtonComponent } from '../../../components-gui/button/button.component';
import { AutocompleteMapper, InputComponent } from '../../../components-gui/input/input.component';
import { TableColumnDirective, TableComponent } from '../../../components-gui/table/table.component';
import { LabelComponent } from '../../../components-gui/label/label.component';
import { Account } from '../../../model-acct/account';
import { BankCardData, CardDataService, CurrencyCardData, IncomeOrExpenseItemCardData, IncomeOrExpenseItemCategoryCardData, IncomeOrExpenseItemSubcategoryCardData } from '../../../services-acct/card-data.service';
import { concatAll, forkJoin, map, Observable } from 'rxjs';
import { complete, errorConsumingObservableOperation, newObservable } from '../../../utils-reusalbe/rxjs-utils';
import { AccountRecord } from '../../../model-acct/account-record';
import { WorkspaceService } from '../../../services-acct/workspace.service';
import { Workspace } from '../../../model-acct/workspace';
import { WorkspaceSelectorService } from '../../../services-acct/workspace-selector.service';
import { RecordsManager } from '../../../utils-acct/records-manager';
import { IconifiedCurrencyProperties } from '../../../model-acct/currency-properties';
import { ScrollDirection, ScrollEvent } from '../../../components-gui/directives/scrollable-content.directive';
import { extractFirstToken } from '../../../utils-reusalbe/string-utils';
import { SortDirection } from '../../../model-acct/sort-direction';
import { IconifiedBankProperties } from '../../../model-acct/bank-properties';
import { isDefined } from '../../../utils-reusalbe/lang-utils';
import { DialogComponent } from '../../../components-gui/dialog/dialog.component';
import { SelectComponent } from '../../../components-gui/select/select.component';
import { CardData } from '../../../components-gui/cards-list/card-data';
import { isNumber } from '../../../utils-reusalbe/dom-utils';
import { IconifiedIncomeOrExpenseItem } from '../../../model-acct/income-or-expense-item';
import { AutocompleteDataResponse } from '../../../model-acct/autocomplete-data-response';

/**
 * The height of a record in the account records table. Used for both displaying account records
 * and computing the account records table page size based on the viewport height.
 */
const ACCOUNT_RECORD_HEIGHT_PX : number = 50

/**
 * If the table's scroll position, in percent, is higher than or equal to this number, then a new
 * page is loaded.
 */
const SCROLL_POS_PAGE_LOAD_THRESHOLD : number = 0.9

/**
 * Container for the properties displayed on the account record properties form
 */
type AccountRecordPropertiesFormData = {
  accountRecordId? : number,
  selectedIncomeOrExpenseItemCategory? : IncomeOrExpenseItemCategoryCardData,
  selectedIncomeOrExpenseItemSubcategory? : IncomeOrExpenseItemSubcategoryCardData,
  selectedIncomeOrExpenseItem? : IncomeOrExpenseItemCardData,
  accountRecordText : string,
  accountRecordValueStr : string
}

@Component({
  selector: 'app-account-records',
  imports: [
    BarComponent,
    ButtonComponent,
    InputComponent,
    TableComponent,
    TableColumnDirective,
    LabelComponent,
    DialogComponent,
    SelectComponent
  ],
  templateUrl: './account-records.component.html',
  styleUrl: './account-records.component.less'
})
export class AccountRecordsComponent implements OnInit {

  /**
   * The account for which data is being displayed
   */
  selectedAccount : InputSignal<Account> = input.required()
  cachedSelectedAccount! : Account

  /**
   * The balance of the account is shown on the page
   */
  selectedAccountBalance : number = 0
  cachedFormattedAccountBalance : string = "0.0000"
 
  /**
   * The workspace that contains the account for which data is being displayed
   */
  selectedWorkspace! : Workspace

  /**
   * The record being edited
   */
  selectedAccountRecord : AccountRecordPropertiesFormData = this.newAccountRecordPropertiesFormData()

  /**
   * This event is triggered when the back button is clicked and has the
   * purpose of telling the parent component that it should clear the
   * account selection. 
   */
  @Output() selectedAccountCleared : EventEmitter<void> = new EventEmitter<void>()

  /**
   * Contains all the currencies registered in the catalog, together
   * with their icons.
   */
  registeredCurrencies : CurrencyCardData[] = []

  /**
   * This is where the currency of the selected account is cached,
   * to be used during rendering.
   */
  cachedAccountCurrency! : IconifiedCurrencyProperties

  /**
   * Contains all the banks registered in the catalog, together with
   * their icons.
   */
  registeredBanks : BankCardData[] = []

  /**
   * This is where the bank of the selected account is cached, to
   * be used during rendering.
   */
  cachedAccountBank! : IconifiedBankProperties

  registeredIncomeOrExpenseItems : IncomeOrExpenseItemCardData[] = []

  registeredIncomeOrExpenseItemSubcategories : IncomeOrExpenseItemSubcategoryCardData[] = []

  registeredIncomeOrExpenseItemCategories : IncomeOrExpenseItemCategoryCardData[] = []

  cachedIncomeOrExpenseItemCardsByIncomeOrExpenseItemUUID : Map<string,IncomeOrExpenseItemCardData> = new Map()

  /**
   * The list of subcategory cards that fall under the category that's selected for the
   * account record currently being edited
   */
  selectedAccountRecordSubcategoryOptions : IncomeOrExpenseItemSubcategoryCardData[] = []

  /**
   * The list of item cards that fall under the subcategory that's selected for the
   * account record currently being edited
   */
  selectedAccountRecordItemOptions : IncomeOrExpenseItemCardData[] = []

  /**
   * Flag that controls the visibility of the account record properties form
   */
  accountRecordEditDialogVisible : boolean = false

  /**
   * The sort direction can be toggled from the sort direction change button
   */
  sortDirection : SortDirection = SortDirection.ASCENDING

  /**
   * Contains the text in the search box
   */
  accountRecordTextToSearchFor : string = ""

  /**
   * The number of records that should be fetched at any one time.
   * There should be enough of them to overflow the table, so that
   * scrolling is enabled, but not so many as to overflow too much.
   * This number is relative to the viewport height and represents
   * the approximate amount of records that fit within the view.
   */
  private pageSize : number =  Math.floor(window.innerHeight / ACCOUNT_RECORD_HEIGHT_PX)

  private accountRecordsManager : RecordsManager<AccountRecord,number> = new RecordsManager<AccountRecord,number>(
    this.pageSize,
    (pageNumber,pageSize) => this.workspaceService.findSortedPageOfAccountRecordsByTextPattern(
      this.selectedWorkspace,
      this.cachedSelectedAccount,
      pageNumber,
      pageSize,
      this.sortDirection,
      this.accountRecordTextToSearchFor
    ),
    record => record.accountRecordId ?? 0
  )

  constructor(
    private cardDataService          : CardDataService,
    private workspaceService         : WorkspaceService,
    private workspaceSelectorService : WorkspaceSelectorService
  ) {

  }

  ngOnInit(): void {
    // Cache the selected account
    this.cachedSelectedAccount = this.selectedAccount()

    forkJoin([
      // Load the dependencies of the account records together with their icons
      this.loadRegisteredCurrencies(),
      this.loadRegisteredBanks(),
      this.loadRegisteredIncomeOrExpenseItemsCatalog(),

      // Load the selected workspace
      this.loadSelectedWorkspace()
    ]).subscribe(() => {
      // Once everything else is loaded, load the account balance and the first account records page
      forkJoin([
        this.loadAccountBalance(),
        this.loadAccountRecordsPage()
      ]).subscribe()
    })
  }

  loadRegisteredCurrencies() : Observable<void> {
    return new Observable<void>(subscriber => {
      this.cardDataService.loadRegisteredCurrencies().subscribe({
        next: registeredCurrencies => {
          // Assign the registered currencies array
          this.registeredCurrencies = registeredCurrencies

          // Identify the selected account's currency
          this.cacheSelectedAccountCurrency()

          // Notify subscribers that the task is done
          complete(subscriber, undefined)
        },
        error: err => {
          // TODO: Toast
          console.log(err)
        }
      })
    })
  }

  loadRegisteredBanks() : Observable<void> {
    return new Observable<void>(subscriber => {
      this.cardDataService.loadRegisteredBanks().subscribe({
        next: registeredBanks => {
          // Assign the registered currencies array
          this.registeredBanks = registeredBanks

          // Identify the selected account's currency
          this.cacheSelectedAccountBank()

          // Notify subscribers that the task is done
          complete(subscriber, undefined)
        },
        error: err => {
          // TODO: Toast
          console.log(err)
        }
      })
    })
  }

  loadRegisteredIncomeOrExpenseItemsCatalog() : Observable<void> {
    return new Observable<void>(subscriber => {
      this.cardDataService.loadRegisteredItemsCatalog().subscribe({
        next: catalog => {

          // Assign the registered catalog item arrays
          this.registeredIncomeOrExpenseItems = catalog.incomeOrExpenseItems
          this.registeredIncomeOrExpenseItemSubcategories = catalog.incomeOrExpenseItemSubcategories
          this.registeredIncomeOrExpenseItemCategories = catalog.incomeOrExpenseItemCategories

          // Cache income or expense items by income or expense item UUID
          this.cacheIncomeOrExpenseItemCardsByIncomeOrExpenseItemUUID()

          // Notify subscribers that the task is done
          complete(subscriber, undefined)
        },
        error: err => {
          // TODO: Toast
          console.log(err)
        }
      })
    })
  }

  private cacheIncomeOrExpenseItemCardsByIncomeOrExpenseItemUUID() : void {
    this.cachedIncomeOrExpenseItemCardsByIncomeOrExpenseItemUUID = new Map()

    this.registeredIncomeOrExpenseItems.forEach(item => {
      if (item.incomeOrExpenseItem.incomeOrExpenseItemUUID) {
        this.cachedIncomeOrExpenseItemCardsByIncomeOrExpenseItemUUID.set(
          item.incomeOrExpenseItem.incomeOrExpenseItemUUID,
          item
        )
      }
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

  loadAccountRecordsPage() : Observable<void> {
    return errorConsumingObservableOperation(
      this.accountRecordsManager.loadNextPage(),
      err => {
        // TODO: Toast
        console.error(err)
      }
    )
  }

  loadAccountBalance() : Observable<void> {
    return new Observable<void>(subscriber => {
      this.workspaceService.findAccountBalance(this.selectedWorkspace, this.cachedSelectedAccount).subscribe({
        next: balance => {
          this.selectedAccountBalance = balance
          this.cacheFormattedAccountBalance()
          complete(subscriber, undefined)
        },
        error: err => {
          // TODO: Toast
          console.error(err)
        }
      })
    })
  }

  private cacheSelectedAccountCurrency() : void {
    this.cachedAccountCurrency = 
      this.registeredCurrencies
        .map(currencyCard => currencyCard.currency)
        .filter(c => this.selectedAccount().currencyUUID == c.currencyUUID)
        [0]
  }

  private cacheSelectedAccountBank() : void {
    this.cachedAccountBank =
      this.registeredBanks
        .map(bankCard => bankCard.bank)
        .filter(b => this.selectedAccount().bankUUID == b.bankUUID)
        [0]
  }

  private cacheFormattedAccountBalance() : void {
    this.cachedFormattedAccountBalance = this.formatNumber(this.selectedAccountBalance)
  }

  private newAccountRecordPropertiesFormData(accountRecord?:AccountRecord) : AccountRecordPropertiesFormData {
    var item        : IncomeOrExpenseItemCardData | undefined = undefined
    var subcategory : IncomeOrExpenseItemSubcategoryCardData | undefined = undefined
    var category    : IncomeOrExpenseItemCategoryCardData | undefined = undefined

    if (accountRecord) {
      item =
        this.registeredIncomeOrExpenseItems.filter(item => 
          accountRecord.incomeOrExpenseItemUUID == item.incomeOrExpenseItem.incomeOrExpenseItemUUID
        )[0]

      subcategory =
        this.registeredIncomeOrExpenseItemSubcategories.filter(subcategory =>
          (item?.incomeOrExpenseItemSubcategory.incomeOrExpenseItemSubcategoryUUID ?? "") == subcategory.incomeOrExpenseItemSubcategory.incomeOrExpenseItemSubcategoryUUID
        )[0]

      category =
        this.registeredIncomeOrExpenseItemCategories.filter(category =>
          (item?.incomeOrExpenseItemCategory.incomeOrExpenseItemCategoryUUID ?? "") == category.incomeOrExpenseItemCategory.incomeOrExpenseItemCategoryUUID
        )[0]
    }

    return {
      accountRecordId                        : accountRecord?.accountRecordId,
      selectedIncomeOrExpenseItemSubcategory : subcategory,
      selectedIncomeOrExpenseItemCategory    : category,
      selectedIncomeOrExpenseItem            : item,
      accountRecordText                      : accountRecord?.accountRecordText ?? "",
      accountRecordValueStr                  : "" + (accountRecord?.accountRecordValue ?? 0)
    }
  }

  selectedAccountRecordTextAutocompleteMapper : AutocompleteMapper = (input => {
    // If the input is not long enough, then it does not qualify for auto-complete
    if (input.length >= 3) {
      // If there is no income or expense item UUID selected, then the auto-complete feature cannot run
      if (this.selectedAccountRecord?.selectedIncomeOrExpenseItem?.incomeOrExpenseItem) {
        // If all inputs are valid, then the auto-complete feature may run
        return this.workspaceService.findAutocompleteData(
          this.selectedWorkspace,
          this.selectedAccount(),
          this.selectedAccountRecord?.selectedIncomeOrExpenseItem?.incomeOrExpenseItem,
          input
        ).pipe(
          map(arr => newObservable(arr.length > 0 ? this.processAutocompleteResponse(arr[0]) : "")),
          concatAll()
        )
      }
    }

    // If the inputs are not valid, then return an empty string observable
    return newObservable("")
  })

  private processAutocompleteResponse(autoCompleteResponse:AutocompleteDataResponse) : string {
    this.selectedAccountRecord.accountRecordValueStr =
      this.formatNumber(autoCompleteResponse.lastUsedAccountRecordValue, 2)

    return autoCompleteResponse.accountRecordText
  }

  formatNumber(number?:number, nDigits?:number) : string {
    // If the number is not provided, then return an empty string
    if (number == undefined || number == null) {
      return ""
    }

    // Acquire the number of digits
    const nDig : number = nDigits ?? 4

    // For mat the balance to frech
    const formatted = number.toLocaleString("fr-FR", {
      minimumFractionDigits: nDig,
      maximumFractionDigits: nDig,
    });

    // Replace the coma with a dot
    return formatted.replace(",", ".")
  }

  private toggleSortDirection() : void {
    if (this.sortDirection == SortDirection.ASCENDING) {
      this.sortDirection = SortDirection.DESCENDING
    } else {
      this.sortDirection = SortDirection.ASCENDING
    }
  }

  private reloadRecords() : Observable<void> {
    this.accountRecordsManager.reset()
    return this.loadAccountRecordsPage()
  }
 
  onBackToAccountsButtonClick() : void {
    this.selectedAccountCleared.emit()
  }

  onAccountRecordTextSearchButtonClick() : void {
    if (this.isAccountRecordTextToSearchForValid()) {
      this.reloadRecords().subscribe()
    }
  }

  onAccountRecordsTableScroll(scrollEvent:ScrollEvent) : void {
    if (
      scrollEvent.direction == ScrollDirection.DOWN &&
      scrollEvent.sliderPosPct >= SCROLL_POS_PAGE_LOAD_THRESHOLD
    ) {
      this.loadAccountRecordsPage().subscribe()
    }
  }

  onSortDirectionChangeButtonClick() : void {
    if (this.isAccountRecordTextToSearchForValid()) {
      this.toggleSortDirection()
      this.accountRecordsManager.reset()
      this.accountRecordsManager.loadNextPage().subscribe()
    }
  }
 
  onNewAccountRecordButtonClick() : void {
    this.selectedAccountRecord = this.newAccountRecordPropertiesFormData()
    this.accountRecordEditDialogVisible = true
  }

  onSelectedIncomeOrExpenseItemCategoryChange(
    selectedIncomeOrExpenseItemCategory:CardData | undefined
  ) : void {
    // Assign the category
    this.selectedAccountRecord.selectedIncomeOrExpenseItemCategory = 
      selectedIncomeOrExpenseItemCategory as IncomeOrExpenseItemCategoryCardData

    // Extract the subcategory options
    this.selectedAccountRecordSubcategoryOptions = 
      this.registeredIncomeOrExpenseItemSubcategories.filter(
        subcat => 
          (subcat.incomeOrExpenseItemCategory.incomeOrExpenseItemCategoryUUID ?? "") ==
          this.selectedAccountRecord.selectedIncomeOrExpenseItemCategory?.incomeOrExpenseItemCategory?.incomeOrExpenseItemCategoryUUID
      )

    // Clear the available item options list
    this.selectedAccountRecordItemOptions = []

    // Clear the selected sub-category and item
    this.selectedAccountRecord.selectedIncomeOrExpenseItemSubcategory = undefined
    this.selectedAccountRecord.selectedIncomeOrExpenseItem = undefined
  }

  onSelectedIncomeOrExpenseItemSubcategoryChange(
    selectedIncomeOrExpenseItemSubcategory:CardData | undefined
  ) : void {
    // Assign the subcategory
    this.selectedAccountRecord.selectedIncomeOrExpenseItemSubcategory =
      selectedIncomeOrExpenseItemSubcategory as IncomeOrExpenseItemSubcategoryCardData

    // Extract the item options
    this.selectedAccountRecordItemOptions =
      this.registeredIncomeOrExpenseItems.filter(
        item =>
          (item.incomeOrExpenseItemSubcategory.incomeOrExpenseItemSubcategoryUUID ?? "") ==
          this.selectedAccountRecord.selectedIncomeOrExpenseItemSubcategory?.incomeOrExpenseItemSubcategory?.incomeOrExpenseItemSubcategoryUUID
      )

    // Clear the selected item
    this.selectedAccountRecord.selectedIncomeOrExpenseItem = undefined
  }

  onSelectedIncomeOrExpenseItemChange(selectedIncomeOrExpenseItem:CardData | undefined) : void {
    // Assign the item
    this.selectedAccountRecord.selectedIncomeOrExpenseItem =
      selectedIncomeOrExpenseItem as IncomeOrExpenseItemCardData
  }

  onDeleteAccountRecordButtonClick(record:AccountRecord) : void {
    
  }

  onEditAccountRecordButtonClick(record:AccountRecord) : void {
    this.selectedAccountRecord = this.newAccountRecordPropertiesFormData(record)
    this.accountRecordEditDialogVisible = true
  }

  //aaafindAutocompleteData

  onSelectedAccountRecordSaveButtonClick() : void {
    if (this.isSelectedAccountRecordValid()) {
      this.workspaceService.saveAccountRecord(
        this.selectedWorkspace,
        this.selectedAccount(),
        {
          accountRecordId         : this.selectedAccountRecord.accountRecordId,
          incomeOrExpenseItemUUID : (this.selectedAccountRecord.selectedIncomeOrExpenseItem?.incomeOrExpenseItem.incomeOrExpenseItemUUID ?? ""),
          accountRecordText       : this.selectedAccountRecord.accountRecordText,
          accountRecordValue      : Number.parseFloat(this.selectedAccountRecord.accountRecordValueStr)
        }
      ).pipe(
        map(() => forkJoin([
          this.loadAccountBalance(),
          this.reloadRecords()
        ])),
        concatAll()
      )
      .subscribe({
        next: () => {
          this.accountRecordEditDialogVisible = false
        },
        error: err => {
          // TODO: Toast
          console.error(err)
        }
      })
    }
  }

  onTransferButtonClick() : void {

  }

  onCurrencyExchangeButtonClick() : void {

  }

  extractDate(dateTime:string) : string {
      if (dateTime) {
        return extractFirstToken(dateTime, 'T')
      }
  
      return ""
    }

  getAccountCurrencyIconImageData() : string {
    return this.cachedAccountCurrency?.imageData ?? ""
  }

  getAccountBankIconImageData() : string {
    return this.cachedAccountBank?.imageData ?? ""
  }

  getAccountName() : string {
    return this.cachedSelectedAccount.accountName
  }

  getAccountRecords() : AccountRecord[] {
    return this.accountRecordsManager.getRecords()
  }

  getAccountRecordHeightPx() : string {
    return ACCOUNT_RECORD_HEIGHT_PX + "px"
  }

  getAccountRecordCategoryIconImageData(accountRecord:AccountRecord) : string {
    return this.cachedIncomeOrExpenseItemCardsByIncomeOrExpenseItemUUID
      .get(accountRecord.incomeOrExpenseItemUUID)
      ?.incomeOrExpenseItemCategory?.imageData ?? ""
  }

  getAccountRecordCategoryName(accountRecord:AccountRecord) : string {
    return this.cachedIncomeOrExpenseItemCardsByIncomeOrExpenseItemUUID
      .get(accountRecord.incomeOrExpenseItemUUID)
      ?.incomeOrExpenseItemCategory?.incomeOrExpenseItemCategoryName ?? ""
  }

  getAccountRecordSubcategoryIconImageData(accountRecord:AccountRecord) : string {
    return this.cachedIncomeOrExpenseItemCardsByIncomeOrExpenseItemUUID
      .get(accountRecord.incomeOrExpenseItemUUID)
      ?.incomeOrExpenseItemSubcategory?.imageData ?? ""
  }

  getAccountRecordSubcategoryName(accountRecord:AccountRecord) : string {
    return this.cachedIncomeOrExpenseItemCardsByIncomeOrExpenseItemUUID
      .get(accountRecord.incomeOrExpenseItemUUID)
      ?.incomeOrExpenseItemSubcategory?.incomeOrExpenseItemSubcategoryName ?? ""
  }

  getAccountRecordItemIconImageData(accountRecord:AccountRecord) : string {
    return this.cachedIncomeOrExpenseItemCardsByIncomeOrExpenseItemUUID
      .get(accountRecord.incomeOrExpenseItemUUID)
      ?.incomeOrExpenseItem?.imageData ?? ""
  }

  getAccountRecordItemName(accountRecord:AccountRecord) : string {
    return this.cachedIncomeOrExpenseItemCardsByIncomeOrExpenseItemUUID
      .get(accountRecord.incomeOrExpenseItemUUID)
      ?.incomeOrExpenseItem?.incomeOrExpenseItemName ?? ""
  }

  getSortDirectionIcon() : string {
    return "button-icons/" + (this.sortDirection == SortDirection.ASCENDING ? "down" : "up") + ".png"
  }

  isForeignCurrencyAccount() : boolean {
    return this.selectedWorkspace?.defaultCurrencyUUID != this.cachedAccountCurrency?.currencyUUID
  }

  isCurrencyExchangeRecord(rec:AccountRecord) : boolean {
    return isDefined(rec.exchangeRate)
  }

  isAccountRecordTextToSearchForValid() : boolean {
    return (
      this.accountRecordTextToSearchFor == "" ||
      this.accountRecordTextToSearchFor.length >= 3
    )
  }

  isSelectedAccountRecordTextValid() : boolean {
    return (this.selectedAccountRecord?.accountRecordText ?? "").length >= 3
  }

  isSelectedAccountRecordValueValid() : boolean {
    return isNumber((this.selectedAccountRecord?.accountRecordValueStr ?? 0))
  }

  isSelectedAccountRecordValid() : boolean {
    return (
      this.isSelectedAccountRecordTextValid() &&
      this.isSelectedAccountRecordValueValid() && 
      isDefined(this.selectedAccountRecord.selectedIncomeOrExpenseItem)
    )
  }

}
