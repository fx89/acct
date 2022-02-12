import { Component, OnInit } from '@angular/core';
import { Bank } from 'src/app/model/bank';
import { StateService } from 'src/app/services/state-service/state.service';

@Component({
  selector: 'app-banks-list',
  templateUrl: './banks-list.component.html',
  styleUrls: ['./banks-list.component.css']
})
export class BanksListComponent implements OnInit {

    constructor(private state : StateService) { }

    private getBankName : Function = (bank => { return bank.name; });

    private newBank : Function = ( () => { return new Bank(0, "New bank"); } );

    ngOnInit() {
        this.state.loadBanks();
    }

    public selectBank(bank : Bank) {
        this.state.selectedBank = bank;
    }

    public addBank(bank : Bank) {
        this.state.selectedBank = bank;
    }

    public deleteBank(bank : Bank) {

    }

    public beginEditBank(bank : Bank) {
        this.state.selectedBank = bank;
    }

    public endEditBank(bankName : string) {
        this.state.selectedBank.name = bankName;
        this.state.saveBank(this.state.selectedBank);
    }
}
