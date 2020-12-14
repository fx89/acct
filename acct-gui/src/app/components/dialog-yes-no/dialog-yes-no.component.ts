import { Component, OnInit } from '@angular/core';
import { DialogService } from 'src/app/services/dialog-service/dialog.service';

@Component({
  selector: 'app-dialog-yes-no',
  templateUrl: './dialog-yes-no.component.html',
  styleUrls: ['./dialog-yes-no.component.css']
})
export class DialogYesNoComponent implements OnInit {

  constructor(private dialogService : DialogService) { }

  ngOnInit() {
  }

}
