import { Component, OnInit } from '@angular/core';

import { Account } from '../../model/account';
import { StateService } from 'src/app/services/state-service/state.service';

@Component({
  selector: 'app-accounts-manager',
  templateUrl: './accounts-manager.component.html',
  styleUrls: ['./accounts-manager.component.css']
})
export class AccountsManagerComponent implements OnInit {

    private selectedAccount : Account;

    constructor(private state : StateService) { }

    ngOnInit() {
        this.state.loadAccounts();
    }

    private getItemName : Function = (item => { return item.name; })

    private newItem : Function = (() => {
        let acc = new Account(0, "New account");
        return acc;
    })

    private selectItem(account : Account) {
        this.selectedAccount = account;
    }

    private addItem(account : Account) {
        this.selectedAccount = account;
    }

    private deleteItem(account : Account) {

    }

    private beginEditItem(account : Account) {
        this.selectedAccount = account;
    }

    private endEditItem(name : string) {
        this.selectedAccount.name = name;
        this.state.saveAccount(this.selectedAccount);
        this.selectedAccount = null;
    }
}
