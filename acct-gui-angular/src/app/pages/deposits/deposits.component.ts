import { Component, OnInit } from '@angular/core';
import { BarComponent } from '../../components-gui/bar/bar.component';
import { ButtonComponent } from '../../components-gui/button/button.component';
import { SelectComponent } from '../../components-gui/select/select.component';
import { AccountCardData, BankCardData, CardDataService } from '../../services-acct/card-data.service';
import { forkJoin, Observable } from 'rxjs';
import { complete, newObservable } from '../../utils-reusalbe/rxjs-utils';
import { CardData } from '../../components-gui/cards-list/card-data';
import { SwitchComponent } from '../../components-gui/switch/switch.component';
import { TableColumnDirective, TableComponent } from '../../components-gui/table/table.component';
import { ScrollEvent } from '../../components-gui/directives/scrollable-content.directive';
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

type DepositFormData = {
  selectedAccount?     : AccountCardData,
  depositAccountNumber : string,
  startDate            : Date,
  endDate              : Date,
  amountStr            : string,
  interestPctStr       : string
}

function newDepositFormData() {
  return {
    depositAccountNumber : "",
    startDate            : new Date(),
    endDate              : new Date(new Date().setFullYear((new Date()).getFullYear() + 1)),
    amountStr            : "5000",
    interestPctStr       : "4.5"
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
    CalendarButtonComponent
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

  loadSelectedBankDeposits() : Observable<void> {
    return newObservable(undefined) // TODO: work here
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
    this.selectedBank = bank as BankCardData
    this.cacheSelectedBankAccounts()
  }

  onNewDepositButtonClick() : void {
    // Create the new deposit record
    this.selectedDeposit = newDepositFormData()

    // Show the deposit editor dialog
    this.depositEditorFormDialogVisible = true
  }

  onDepositsTableScroll(scrollEvent:ScrollEvent) : void {

  }

  onDepositEditorFormSubmitButtonClick() : void {
    this.workspaceService.saveDeposit(
      this.selectedWorkspace as Workspace,
      {
        sourceAccountUUID    : this.selectedDeposit.selectedAccount?.account?.accountUUID ?? "",
        depositAccountNumber : this.selectedDeposit.depositAccountNumber,
        amount               : parseFloat(this.selectedDeposit.amountStr),
        interestPct          : parseFloat(this.selectedDeposit.interestPctStr),
        startDate            : this.selectedDeposit.startDate,
        projectedEndDate     : this.selectedDeposit.endDate
      }
    ).subscribe({
      next: () => {
        this.loadSelectedBankDeposits().subscribe()
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

  getDepositRecordHeightPx() : string {
    return '50px'
  }

  getDeposits() : any[] {
    return []
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

}
