import { Component, OnInit } from '@angular/core';
import { StateService } from 'src/app/services/state-service/state.service';

@Component({
  selector: 'app-account-properties',
  templateUrl: './account-properties.component.html',
  styleUrls: ['./account-properties.component.css']
})
export class AccountPropertiesComponent implements OnInit {

  constructor(private state : StateService) { }

  ngOnInit() {
  }

}
