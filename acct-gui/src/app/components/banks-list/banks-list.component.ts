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

    private selectedBank : Bank;

    ngOnInit() {
        this.state.loadBanks();
    }

    public selectBank(bank : Bank) {
        this.selectedBank = bank;
    }

    public addBank(bank : Bank) {
        this.selectedBank = bank;
    }

    public deleteBank(bank : Bank) {

    }

    public beginEditBank(bank : Bank) {
        this.selectedBank = bank;
    }

    public endEditBank(bankName : string) {
        this.selectedBank.name = bankName;
        this.state.saveBank(this.selectedBank);
    }
}
