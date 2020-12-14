import { Component, OnInit } from '@angular/core';
import { StateService } from 'src/app/services/state-service/state.service';
import { MatDialog } from '@angular/material';
import { AccountsManagerComponent } from '../accounts-manager/accounts-manager.component';
import { MoneyTransferFormComponent } from '../money-transfer-form/money-transfer-form.component';
import { DialogService } from 'src/app/services/dialog-service/dialog.service';


@Component({
  selector: 'app-section-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsSectionComponent implements OnInit {

    private sourceXValueMapper : (rec) => any = (rec) => { return rec.recordYearMonth.toString(); };
    private sourceYValueMapper : (rec) => any = (rec) => { return rec.incomingBalance + rec.outgoingBalance; };

    constructor(private state : StateService, private dialog: MatDialog, private dialogService : DialogService) { }

    ngOnInit() {
        this.state.loadIncomeOrExpenseItems();

        this.state.loadAccounts().subscribe((ret => {
            if (this.state.accounts && this.state.accounts[0]) {
                this.state.selectAccountId(this.state.accounts[0].id);
            }
        }));
    }


    private showAccountsManager() {
        this.dialog.open(AccountsManagerComponent, {
            width: '300px',
            height: '300px'
        });
    }

    private showMoneyTransferForm() {
        this.dialogService.moneyTransferDialogServiceRef = 
            this.dialog.open(MoneyTransferFormComponent, {
                width: '340px',
                height: '200px'
            }); 
    }
}
