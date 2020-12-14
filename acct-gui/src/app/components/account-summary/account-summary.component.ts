import { Component, OnInit, Input } from '@angular/core';
import { AccountSummary } from 'src/app/model/account-summary';
import { Account } from 'src/app/model/account';

@Component({
  selector: 'app-account-summary',
  templateUrl: './account-summary.component.html',
  styleUrls: ['./account-summary.component.css']
})
export class AccountSummaryComponent implements OnInit {

    @Input() summary : AccountSummary;
    @Input() account : Account;

    constructor() { }

    ngOnInit() {
    }

}
