import { Component, OnInit } from '@angular/core';
import { StateService } from 'src/app/services/state-service/state.service';
import { Deposit } from 'src/app/model/deposit';
import { Bank } from 'src/app/model/bank';
import { Account } from 'src/app/model/account';

@Component({
  selector: 'app-deposits-table',
  templateUrl: './deposits-table.component.html',
  styleUrls: ['./deposits-table.component.css']
})
export class DepositsTableComponent implements OnInit {

    constructor(private state : StateService) { }

    ngOnInit() {
        this.state.loadBanks();
        this.state.loadAccounts();
    }

    private getSourceAccountName(deposit : Deposit) : String {
        let acc : Account = this.state.getAccount(deposit.sourceAccountId);
        return acc ? acc.name : "No source account";
    }

    private getBankName(deposit : Deposit) : String{ 
        let bnk : Bank = this.state.getBank(deposit.bankId);
        return bnk ? bnk.name : "No bank";
    }

    private computeProgress(rec : Deposit) : number {
        let startToEnd   : number = (new Date(rec.endDate)).getTime() - (new Date(rec.startDate)).getTime();
        let startToToday : number = (new Date(           )).getTime() - (new Date(rec.startDate)).getTime();

        return Math.round(startToToday / startToEnd * 100);
    }

}
