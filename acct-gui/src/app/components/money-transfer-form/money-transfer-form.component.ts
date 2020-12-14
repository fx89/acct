import { Component, OnInit } from '@angular/core';
import { StateService } from 'src/app/services/state-service/state.service';
import { Account } from 'src/app/model/account';
import { DialogService } from 'src/app/services/dialog-service/dialog.service';

@Component({
  selector: 'app-money-transfer-form',
  templateUrl: './money-transfer-form.component.html',
  styleUrls: ['./money-transfer-form.component.css']
})
export class MoneyTransferFormComponent implements OnInit {

    private fromAccount : Account;
    private toAccount : Account;
    private amount : Number;

    constructor( private state : StateService, private dialogService : DialogService ) { }

    ngOnInit() {
    }

    private startMoneyTransfer() {
        this.state.moneyTransfer(this.fromAccount.id, this.toAccount.id, this.amount);
        this.dialogService.moneyTransferDialogServiceRef.close();
    }
}
