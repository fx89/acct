import { Component, OnInit } from '@angular/core';

import { StateService } from 'src/app/services/state-service/state.service';

@Component({
  selector: 'app-accounts-list',
  templateUrl: './accounts-list.component.html',
  styleUrls: ['./accounts-list.component.css']
})
export class AccountsListComponent implements OnInit {
    constructor(private state : StateService) { }

    ngOnInit() {
    }

    public selectAccountId(accountId : number) {
        this.state.selectAccountId(accountId);
    }
}
