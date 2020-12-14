import { Component, OnInit } from '@angular/core';
import { StateService } from 'src/app/services/state-service/state.service';
import { Deposit } from 'src/app/model/deposit';

@Component({
  selector: 'app-section-deposits',
  templateUrl: './deposits.component.html',
  styleUrls: ['./deposits.component.css']
})
export class DepositsSectionComponent implements OnInit {

    constructor(private state : StateService) { }

    ngOnInit() {
        this.state.deposits = null;
        this.state.loadDeposits();
        this.state.selectedDeposit = null;
    }

    private getTotalDepositsValue() : number {
        return this.state.totalDepositsValue;
    }

    private getTotalDepositsInterest() : number {
        return this.state.totalDepositsInterest;
    }

    private getFirstDeposit() : Deposit {
        return this.state.firstDepositToReachMaturity;
    }
}
