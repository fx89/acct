import { Component, OnInit } from '@angular/core';
import { DialogService } from 'src/app/services/dialog-service/dialog.service';

@Component({
  selector: 'app-dialog-error',
  templateUrl: './dialog-error.component.html',
  styleUrls: ['./dialog-error.component.css']
})
export class DialogErrorComponent implements OnInit {

  constructor(private dialogService : DialogService) { }

  ngOnInit() {
  }

}
