import { Component, OnInit } from '@angular/core';
import { StateService } from 'src/app/services/state-service/state.service';
import { Account } from 'src/app/model/account';
import { Bank } from 'src/app/model/bank';

@Component({
  selector: 'app-deposits-input-form',
  templateUrl: './deposits-input-form.component.html',
  styleUrls: ['./deposits-input-form.component.css']
})
export class DepositsInputFormComponent implements OnInit {

    private sourceAccount : Account = new Account(0, "Not selected", false, 0);
    private bank : Bank = new Bank(0, "Not selected");
    private accountNumber : String;
    private startDateStr : string;
    private endDateStr : string;
    private value : Number;
    private interestPercent : Number;

    constructor(private state: StateService) { }

    ngOnInit() {
        this.state.loadAccounts();
        this.state.loadBanks();

        this.state.selectedDepositChangedObservable.subscribe(
            deposit => {
                this.selectedDepositToLocalFields();
            }
        );
    }

    private onTextFieldUp(event) {
        if (event.key == "Enter") {
            this.localFieldsToSelectedDeposit();
            this.state.saveSelectedDeposit();
        }
    }

    private onAccountNumberFieldUp(event) {
        if (event.key == "Enter") {
            this.state.selectedDeposit.accountNumber = this.accountNumber;
            this.state.saveSelectedDeposit(true);
        }
    }

    private selectedDepositToLocalFields() {
        this.sourceAccount = this.state.getAccount(this.state.selectedDeposit.sourceAccountId);
        this.bank = this.state.getBank(this.state.selectedDeposit.bankId);
        this.accountNumber = this.state.selectedDeposit.accountNumber;
        this.startDateStr = new Date(this.state.selectedDeposit.startDate).toISOString().split("T")[0];
        this.endDateStr = new Date(this.state.selectedDeposit.endDate).toISOString().split("T")[0];
        this.value = this.state.selectedDeposit.value;
        this.interestPercent = this.state.selectedDeposit.interestPercent;
    }

    private localFieldsToSelectedDeposit() {
        this.state.selectedDeposit.sourceAccountId = this.sourceAccount.id;
        this.state.selectedDeposit.accountNumber = this.accountNumber;
        this.state.selectedDeposit.bankId = this.bank.id;
        this.state.selectedDeposit.startDate = new Date(this.startDateStr);
        this.state.selectedDeposit.endDate = new Date(this.endDateStr);
        this.state.selectedDeposit.value = this.value;
        this.state.selectedDeposit.interestPercent = this.interestPercent;
    }
}
