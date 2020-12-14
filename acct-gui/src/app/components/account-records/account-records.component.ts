import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { StateService } from 'src/app/services/state-service/state.service';
import { AccountRecord } from 'src/app/model/account-record';

@Component({
  selector: 'app-account-records',
  templateUrl: './account-records.component.html',
  styleUrls: ['./account-records.component.css']
})
export class AccountRecordsComponent implements OnInit {

    constructor(private state : StateService) { }

    ngOnInit() {
    }

}
