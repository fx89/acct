import { Component, OnInit } from '@angular/core';
import { Account } from '../../model-acct/account';
import { isDefined } from '../../utils-reusalbe/lang-utils';
import { AccountSelectionComponent } from './account-selection/account-selection.component';
import { AccountRecordsComponent } from './account-records/account-records.component';

@Component({
  selector: 'app-accounts',
  imports: [
    AccountSelectionComponent,
    AccountRecordsComponent
  ],
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.less'
})
export class AccountsComponent implements OnInit {

  selectedAccount? : Account

  ngOnInit(): void {
    
  }

  onSelectedAccountChange(selectedAccount:Account) : void {
    this.selectedAccount = selectedAccount
  }

  isAccountSelected() : boolean {
    return isDefined(this.selectedAccount)
  }

}
