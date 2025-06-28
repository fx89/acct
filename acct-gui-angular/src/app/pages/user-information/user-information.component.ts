import { Component } from '@angular/core';
import { PanelComponent } from '../../components-gui/panel/panel.component';
import { ButtonComponent } from '../../components-gui/button/button.component';
import { MsgboxComponent } from '../../components-gui/msgbox/msgbox.component';
import { MsgboxType } from '../../components-gui/msgbox/msgbox-type';
import { InputComponent } from '../../components-gui/input/input.component';
import { SelectComponent } from '../../components-gui/select/select.component';
import { CardData } from '../../components-gui/cards-list/card-data';

type WorkspaceCardData = CardData & { additionalData : string }

@Component({
  selector: 'app-user-information',
  imports: [
    PanelComponent,
    ButtonComponent,
    MsgboxComponent,
    InputComponent,
    SelectComponent
  ],
  templateUrl: './user-information.component.html',
  styleUrl: './user-information.component.less'
})
export class UserInformationComponent {

  deletePersonalDataMessageBoxType : MsgboxType = MsgboxType.YES_NO
  deletePersonalDataMessageBoxVisible : boolean = false

  userHumanReadableName : string = ""

  userPassword : string = ""
  userPasswordConfirmation : string = ""
  userPasswordConfirmationValid : boolean = true

  commaSeparatedGroupNames : string = ""

  availableWorkspaces : WorkspaceCardData[] = []
  selectedWorkspace : WorkspaceCardData = this.availableWorkspaces[0]

  onPasswordFieldChangedEvent() : void {
    this.userPasswordConfirmationValid = (this.userPassword == this.userPasswordConfirmation)
  }

  deletePersonalAccountConfirmed() : void {
    console.log("deleting")
  }

  deletePersonalAccountCancelled() : void {
    console.log("not deleting")
  }

  saveName() : void {

  }

  saveDefaultWorkspace() : void {

  }

  savePassword() : void {

  }

  showDeletePersonalAccountMessageBox() : void {
    this.deletePersonalDataMessageBoxVisible = true
  }

  showAssignedPermissionsDialog() : void {

  }

  showIconSelectorDialog() : void {

  }

}
