import { Component, input, InputSignal, OnInit } from '@angular/core';
import { BarComponent } from '../../../components-gui/bar/bar.component';
import { ButtonComponent } from '../../../components-gui/button/button.component';
import { InputComponent } from '../../../components-gui/input/input.component';
import { TableColumnDirective, TableComponent } from '../../../components-gui/table/table.component';
import { LabelComponent } from '../../../components-gui/label/label.component';
import { Account } from '../../../model-acct/account';
import { CardDataService, CurrencyCardData } from '../../../services-acct/card-data.service';
import { Observable } from 'rxjs';
import { complete } from '../../../utils-reusalbe/rxjs-utils';

@Component({
  selector: 'app-account-records',
  imports: [
    BarComponent,
    ButtonComponent,
    InputComponent,
    TableComponent,
    TableColumnDirective,
    LabelComponent
  ],
  templateUrl: './account-records.component.html',
  styleUrl: './account-records.component.less'
})
export class AccountRecordsComponent implements OnInit {

  registeredCurrencies : CurrencyCardData[] = []

  selectedAccount : InputSignal<Account> = input.required()

  accountRecordTextToSearchFor : string = ""

  accountRecords: any[] = []

  selectedAccountRecord: any

  constructor(private cardDataService : CardDataService) {

  }

  ngOnInit(): void {
    this.loadRegisteredCurrencies().subscribe()
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
 
  onBackToAccountsButtonClick() : void {

  }

  onAccountRecordTextSearchButtonClick() : void {

  }
 
  onNewAccountRecordButtonClick() : void {

  }

  onTransferButtonClick() : void {

  }

  onCurrencyExchangeButtonClick() : void {

  }

  getAccountCurrencyIconImageData() : string {
    return this.registeredCurrencies.filter(c => this.selectedAccount().currencyUUID == c.currency.currencyUUID)[0]?.imageRef ?? ""
  }

}
