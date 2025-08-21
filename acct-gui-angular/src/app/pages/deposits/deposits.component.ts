import { Component, OnInit } from '@angular/core';
import { BarComponent } from '../../components-gui/bar/bar.component';
import { ButtonComponent } from '../../components-gui/button/button.component';
import { SelectComponent } from '../../components-gui/select/select.component';
import { AccountCardData, BankCardData, CardDataService, CurrencyCardData } from '../../services-acct/card-data.service';
import { forkJoin, Observable } from 'rxjs';
import { complete, newObservable } from '../../utils-reusalbe/rxjs-utils';
import { CardData } from '../../components-gui/cards-list/card-data';
import { SwitchComponent } from '../../components-gui/switch/switch.component';
import { TableColumnDirective, TableComponent } from '../../components-gui/table/table.component';
import { ScrollDirection, ScrollEvent } from '../../components-gui/directives/scrollable-content.directive';
import { isDefined, isNumber } from '../../utils-reusalbe/lang-utils';
import { DialogComponent } from '../../components-gui/dialog/dialog.component';
import { DepositProperties } from '../../model-acct/deposit-modifiable-attributes';
import { LabelComponent } from '../../components-gui/label/label.component';
import { IconifiedWorkspace, Workspace } from '../../model-acct/workspace';
import { WorkspaceSelectorService } from '../../services-acct/workspace-selector.service';
import { InputComponent } from '../../components-gui/input/input.component';
import { CalendarButtonComponent } from '../../components-gui/calendar-button/calendar-button.component';
import { dateToIsoString } from '../../utils-reusalbe/date-utils';
import { WorkspaceService } from '../../services-acct/workspace.service';
import { IconifiedAccount } from '../../model-acct/account';
import { RecordsManager } from '../../utils-acct/records-manager';
import { emptyPage } from '../../model-acct/acct-page';
import { ProgressBarComponent } from '../../components-gui/progress-bar/progress-bar.component';

/**
 * The height of a record in the deposits table. Used for both displaying deposits
 * and computing the deposits table page size based on the viewport height.
 */
const DEPOSIT_RECORD_HEIGHT_PX : number = 35

/**
 * If the table's scroll position, in percent, is higher than or equal to this number,
 * then a new page is loaded.
 */
const SCROLL_POS_PAGE_LOAD_THRESHOLD : number = 0.9

type DepositFormData = {
  selectedAccount?     : AccountCardData,
  depositAccountNumber : string,
  startDate            : Date,
  endDate              : Date,
  amountStr            : string,
  interestPctStr       : string,
  depositUUID?         : string
}

function newDepositFormData(deposit? : DepositProperties) {
  return {
    depositAccountNumber : deposit?.depositAccountNumber ?? "",
    startDate            : deposit?.startDate ?? new Date(),
    endDate              : deposit?.projectedEndDate ?? new Date(new Date().setFullYear((new Date()).getFullYear() + 1)),
    amountStr            : "" + (deposit?.amount ?? 5000),
    interestPctStr       : "" + ((deposit?.interestPct ?? 0.045) * 100),
    depositUUID          : deposit?.depositUUID
  }
}

@Component({
  selector: 'app-deposits',
  imports: [
    BarComponent,
    ButtonComponent,
    SelectComponent,
    SwitchComponent,
    TableComponent,
    TableColumnDirective,
    DialogComponent,
    LabelComponent,
    InputComponent,
    CalendarButtonComponent,
    ProgressBarComponent
  ],
  templateUrl: './deposits.component.html',
  styleUrl: './deposits.component.less'
})
export class DepositsComponent implements OnInit {

  /**
   * The selected workspace, required for various operations such as loading accounts
   * and saving deposits
   */
  selectedWorkspace? : IconifiedWorkspace

  /**
   * Contains all the currencies registered in the catalog, together
   * with their icons.
   */
  registeredCurrencies : CurrencyCardData[] = []

  /**
   * Contains all the banks registered in the catalog, together with
   * their icons.
   */
  registeredBanks : BankCardData[] = []

  /**
   * Contains all the accounts registered in the selected workspace,
   * together with their icons.
   */
  registeredAccounts : AccountCardData[] = []

  /**
   * Contains all the accounts that are registered in the selected
   * workspace at the selected bank.
   */
  selectedBankAccounts : AccountCardData[] = []

  /**
   * The balance of the selected bank account, loaded once the bank
   * account is selected
   */
  selectedBankAccountBalance : number = 0

  /**
   * The bank that's selected from the registered banks array, using the banks selection box
   */
  selectedBank? : BankCardData

  /**
   * The deposit that's currently being edited
   */
  selectedDeposit : DepositFormData = newDepositFormData()


  /**
   * The number of records that should be fetched at any one time.
   * There should be enough of them to overflow the table, so that
   * scrolling is enabled, but not so many as to overflow too much.
   * This number is relative to the viewport height and represents
   * the approximate amount of records that fit within the view.
   */
  pageSize : number =  Math.floor(window.innerHeight / DEPOSIT_RECORD_HEIGHT_PX)

  depositRecordsManager : RecordsManager<DepositProperties, string> = 
    new RecordsManager<DepositProperties, string>(
      this.pageSize,
      (pageNumber,pageSize) => {
        // If the selected workspace is present and the bank is selected, then return the page
        if (this.selectedWorkspace) {
          if (this.selectedBank) {
            return this.workspaceService.findSortedPageOfDepositsByWorkspaceAndBank(
              this.selectedWorkspace,
              this.selectedBank.bank,
              pageNumber,
              pageSize
            )
          }
        }

        // If the selected workspace is not present, or if the bank is not selected, rthen return
        // an empty page
        return newObservable(emptyPage())
      },
      record => record.depositUUID ?? ""
    )

  /**
   * Flag that is set by the capitalized deposits inclusion switch
   */
  includeCapitalizedDeposits : boolean = false

  /**
   * Flag that controls the visibility of the deposit editor form dialog
   */
  depositEditorFormDialogVisible : boolean = false

  constructor(
    private cardDataService          : CardDataService,
    private workspaceSelectorService : WorkspaceSelectorService,
    private workspaceService         : WorkspaceService
  ){}

  ngOnInit() : void {
    forkJoin([
      this.loadRegisteredCurrencies(),
      this.loadRegisteredBanks(),
      this.loadSelectedWorkspace()
    ])
    .subscribe({
      next: () => {
        this.loadRegisteredAccounts(this.selectedWorkspace as IconifiedWorkspace).subscribe()
      },
      error: err => {
        // TODO: toast
        console.log(err)
      }
    })
  }

  loadRegisteredCurrencies() : Observable<void> {
    return new Observable<void>(subscriber => {
      this.cardDataService.loadRegisteredCurrencies().subscribe({
        next: registeredCurrencies => {
          // Assign the registered currencies array
          this.registeredCurrencies = registeredCurrencies

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

  loadRegisteredAccounts(selectedWorkspace:Workspace) : Observable<void> {
    return new Observable<void>(subscriber => {
      this.cardDataService.loadRegisteredAccounts(selectedWorkspace).subscribe({
        next: registeredAccounts => {
          // Assign the registered currencies array, but exclude the selected account
          this.registeredAccounts = registeredAccounts

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

  reloadDepositRecords() : Observable<void> {
    this.depositRecordsManager.reset()
    return this.depositRecordsManager.loadNextPage()
  }

  cacheSelectedBankAccounts() : void {
    this.selectedBankAccounts = 
      this.registeredAccounts.filter(account => this.selectedBank?.bank?.bankUUID == account.account.bankUUID)
  }

  cacheSelectedBankAccountBalance() : void {
    const workspace : IconifiedWorkspace = this.selectedWorkspace as IconifiedWorkspace
    const account : IconifiedAccount = this.selectedDeposit.selectedAccount?.account as IconifiedAccount

    this.workspaceService.findAccountBalance(workspace, account)
      .subscribe({
        next: balance => {
          this.selectedBankAccountBalance = balance
        },
        error: err => {
          // TODO: toast
          console.log(err)
        }
      })
  }

  onSelectedBankChange(bank : CardData | undefined) : void {
    // Assign the selected bank
    this.selectedBank = bank as BankCardData

    // Cache the accounts open at the selected bank
    this.cacheSelectedBankAccounts()

    // Reload the table data
    this.reloadDepositRecords().subscribe()
  }

  onNewDepositButtonClick() : void {
    // Create the new deposit record
    this.selectedDeposit = newDepositFormData()

    // Show the deposit editor dialog
    this.depositEditorFormDialogVisible = true
  }

  onDepositsTableScroll(scrollEvent:ScrollEvent) : void {
    if (
      scrollEvent.direction == ScrollDirection.DOWN &&
      scrollEvent.sliderPosPct > SCROLL_POS_PAGE_LOAD_THRESHOLD
    ) {
      if (!this.depositRecordsManager.areAllPagesLoaded()) {
        this.depositRecordsManager.loadNextPage().subscribe()
      }
    }
  }

  onDepositEditorFormSubmitButtonClick() : void {
    this.workspaceService.saveDeposit(
      this.selectedWorkspace as Workspace,
      {
        sourceAccountUUID    : this.selectedDeposit.selectedAccount?.account?.accountUUID ?? "",
        depositAccountNumber : this.selectedDeposit.depositAccountNumber,
        amount               : parseFloat(this.selectedDeposit.amountStr),
        interestPct          : parseFloat(this.selectedDeposit.interestPctStr) / 100,
        startDate            : this.selectedDeposit.startDate,
        projectedEndDate     : this.selectedDeposit.endDate
      }
    ).subscribe({
      next: () => {
        this.reloadDepositRecords().subscribe()
        this.depositEditorFormDialogVisible = false
      },
      error: err => {
        // TODO: Toast
        console.log(err)
      }
    })
  }

  onSelectedAccountChange(cardData : CardData | undefined) : void {
    // Assign the selection
    this.selectedDeposit.selectedAccount = cardData as AccountCardData

    // Get and cache the account balance
    this.cacheSelectedBankAccountBalance()
  }

  onEditDepositRecordButtonClick(deposit : DepositProperties) : void {
    // Create the deposit form data
    this.selectedDeposit = newDepositFormData(deposit)

    // Assigned the selected account
    this.selectedDeposit.selectedAccount = this.getAccountCardDataByAccountUUID(deposit.sourceAccountUUID)

    // Show the edit dialog
    this.depositEditorFormDialogVisible = true
  }

  getDepositRecordHeightPx() : string {
    return DEPOSIT_RECORD_HEIGHT_PX + 'px'
  }

  getDeposits() : DepositProperties[] {
    return this.depositRecordsManager.getRecords()
  }

  getDepositEditorFormError() : string {
    if (!this.isSelectedDepositSelectedAccountValid()) {
      return "Source account not selected"
    }

    if (!this.isSelectedDepositAccountNumberValid()) {
      return "Invalid deposit account number"
    }

    if (!this.isSelectedDepositPeriodValid()) {
      return "Invalid deposit period"
    }

    if (!this.isSelectedDepositAmountStrValid()) {
      return "Invalid amount"
    }

    if (!this.isSelectedDepositInterestPctStrValid()) {
      return "Invalid interest perecent"
    }

    if (!this.isRemainingAccountBalancePositive()) {
      return "Insufficinet funds"
    }

    return ""
  }

  getSelectedDepositStartDateAsString() : string {
    return dateToIsoString(this.selectedDeposit.startDate)
  }

  getSelectedDepositEndDateAsString() : string {
    return dateToIsoString(this.selectedDeposit.endDate)
  }

  getAccountCardDataByAccountUUID(accountUUID : string) : AccountCardData {
    return this.selectedBankAccounts
      .filter(accountCard => accountUUID == accountCard.account.accountUUID)
      [0]
  }

  getAccountNameByAccountUUID(accountUUID : string) : string {
    return this.getAccountCardDataByAccountUUID(accountUUID)?.account?.accountName ?? ""
  }

  getCurrencyIconByCurrencyUUID(currencyUUID : string) : string {
    return this.registeredCurrencies
      .filter(currencyCard => currencyUUID == currencyCard.currency.currencyUUID)
      .map(currencyCard => currencyCard.currency.imageData)
      [0] ?? ""
  }

  getProgressPct(deposit:DepositProperties) : number {
    const today     : number = new Date().getTime()
    const startTime : number = deposit.startDate.getTime()
    const endTime   : number = deposit.projectedEndDate.getTime()

    if (today >= endTime) {
      return 1
    }

    return (today - startTime) / (endTime - startTime)
  }

  isBankSelected() : boolean {
    return isDefined(this.selectedBank)
  }

  isSelectedDepositAccountNumberValid() : boolean {
    return this.selectedDeposit.depositAccountNumber.length >= 3
  }

  isSelectedDepositSelectedAccountValid() : boolean {
    return isDefined(this.selectedDeposit.selectedAccount)
  }

  isSelectedDepositAmountStrValid() : boolean {
    return isNumber(this.selectedDeposit.amountStr)
  }

  isSelectedDepositInterestPctStrValid() : boolean {
    // It has to be a number
    if (!isNumber(this.selectedDeposit.interestPctStr)) {
      return false
    }

    // It has to turn into a number
    const interestPct = parseFloat(this.selectedDeposit.interestPctStr)

    // It has to be between 0 and 100
    return (interestPct > 0) && (interestPct < 100)
  }

  isSelectedDepositPeriodValid() : boolean {
    // The end date is not allowed to be before the start date
    return this.selectedDeposit.startDate < this.selectedDeposit.endDate
  }

  isRemainingAccountBalancePositive() : boolean {
    // If the deposit record is just being edited, then the value is not to be
    // subtracted from the source account balance, hence no validation is needed
    if (this.isSelectedDepositAlreadyRegistered()) {
      return true
    }

    // If this is a new deposit, then the amount is subtracted from the balance
    // of the source account, hence the validation is required
    return (this.selectedBankAccountBalance - parseFloat(this.selectedDeposit.amountStr)) >= 0
  }

  isDepositEditorFormSubmitButtonEnabled() : boolean {
    return (
      this.isBankSelected() &&
      this.isSelectedDepositSelectedAccountValid() &&
      this.isSelectedDepositAccountNumberValid() &&
      this.isSelectedDepositPeriodValid() &&
      this.isSelectedDepositAmountStrValid() &&
      this.isSelectedDepositInterestPctStrValid() &&
      this.isRemainingAccountBalancePositive()
    )
  }

  isSelectedDepositAlreadyRegistered() : boolean {
    return isDefined(this.selectedDeposit.depositUUID)
  }

  formatDate(date:Date) : string {
    return dateToIsoString(date)
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

}
