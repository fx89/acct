import { Injectable } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { DialogErrorComponent } from 'src/app/components/dialog-error/dialog-error.component';
import { DialogYesNoComponent } from 'src/app/components/dialog-yes-no/dialog-yes-no.component';
import { Observable, Subject } from 'rxjs';
import { MoneyTransferFormComponent } from 'src/app/components/money-transfer-form/money-transfer-form.component';

@Injectable({
  providedIn: 'root'
})
export class DialogService {

    public errDialogRef : MatDialogRef<DialogErrorComponent>;
    public yesNoDialogRef : MatDialogRef<DialogYesNoComponent>;
    public dialogMessage : string;

    public moneyTransferDialogServiceRef : MatDialogRef<MoneyTransferFormComponent>;

    private dialogYesNoResponseSubject : Subject<boolean> = new Subject<boolean>();
    public dialogYesNoResponseObservable : Observable<boolean> = this.dialogYesNoResponseSubject.asObservable();

    constructor() { 

    }

    public closeErrDialog() {
        this.errDialogRef.close();
        this.dialogMessage = "";
    }

    public closeYesNoDialog(answer: boolean) {
        this.yesNoDialogRef.close();
        this.dialogMessage = "";

        this.dialogYesNoResponseSubject.next(answer);
        

        // Make sure other services only subscribe once to this thing
        this.dialogYesNoResponseSubject = new Subject<boolean>();
        this.dialogYesNoResponseObservable = this.dialogYesNoResponseSubject.asObservable();
    }



}
