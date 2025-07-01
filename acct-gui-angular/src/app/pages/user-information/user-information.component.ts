import { AfterViewInit, Component } from '@angular/core';
import { PanelComponent } from '../../components-gui/panel/panel.component';
import { ButtonComponent } from '../../components-gui/button/button.component';
import { MsgboxComponent } from '../../components-gui/msgbox/msgbox.component';
import { MsgboxType } from '../../components-gui/msgbox/msgbox-type';
import { InputComponent } from '../../components-gui/input/input.component';
import { SelectComponent } from '../../components-gui/select/select.component';
import { CardData } from '../../components-gui/cards-list/card-data';
import { WorkspaceService } from '../../services-acct/workspace.service';
import { Workspace } from '../../model-acct/workspace';
import { UserManagementService } from '../../services-acct/user-management.service';
import { UserDetails } from '../../model-acct/user-details';

type WorkspaceCardData = CardData & { workspace : Workspace }

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
export class UserInformationComponent implements AfterViewInit {

  deletePersonalDataMessageBoxType : MsgboxType = MsgboxType.YES_NO
  deletePersonalDataMessageBoxVisible : boolean = false

  passwordUpdateConfirmationMessageBoxType : MsgboxType = MsgboxType.OK_ONLY
  passwordUpdateConfirmationMessageBoxVisible : boolean = false

  humanReadableNameUpdateConfirmationMessageBoxType : MsgboxType = MsgboxType.OK_ONLY
  humanReadableNameUpdateConfirmationMessageBoxVisible : boolean = false

  defaultWorkspaceUpdateConfirmationMessageBoxType : MsgboxType = MsgboxType.OK_ONLY
  defaultWorkspaceUpdateConfirmationMessageBoxVisible : boolean = false

  currentUserDetails : UserDetails | undefined

  userHumanReadableName : string = ""

  userPassword : string = ""
  userPasswordConfirmation : string = ""
  userPasswordConfirmationValid : boolean = true

  commaSeparatedGroupNames : string = ""

  availableWorkspaces : WorkspaceCardData[] = []
  selectedWorkspace : WorkspaceCardData = this.availableWorkspaces[0]

  constructor(
    private workspaceService : WorkspaceService,
    private userManagementService : UserManagementService
  ) {

  }

  ngAfterViewInit(): void {
    // Get the current user information
    this.userManagementService.findCurrentUserDetails().subscribe({
      next : userDetails => {
        this.currentUserDetails = userDetails
        this.userHumanReadableName = this.currentUserDetails.userName
        this.commaSeparatedGroupNames = this.currentUserDetails.userGroups.map(userGroup => userGroup.groupName).join(",")
      },
      error : error => {
        // TODO: toast component
        throw(error)
      }
    })

    // Get the user accessible workspaces
    this.workspaceService.findUserAccessibleWorkspaces().subscribe({
      next : workspaces => {
        this.availableWorkspaces = this.workspacesToWorkspaceCardDataArray(workspaces)
        this.selectedWorkspace = this.availableWorkspaces[0]
      },
      error : error => {
        // TODO: toast component
        throw(error)
      }
    })


  }

  private workspacesToWorkspaceCardDataArray(workspaces:Workspace[]) : WorkspaceCardData[] {
    // Create the workspace card data array
    const ret : WorkspaceCardData[] = []

    // Transform each element from the workspaces array into a WorkspaceCardData element
    // and push it into the workspace card data array
    workspaces.forEach(workspace => {
      ret.push({
        title: workspace.workspaceName,
        text: workspace.workspaceDescription,
        imageRef: "", // List is too narrow for the image to fit
        workspace: workspace
      })
    })

    // Return a reference to the created workspace card data array
    return ret
  }

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
    this.userManagementService.saveCurrentUserHumanReadableName(this.userHumanReadableName).subscribe({
      next: () => {
        this.humanReadableNameUpdateConfirmationMessageBoxVisible = true
      },
      error: (error) => {
        // TODO: toast component
          throw(error)
      }
    })
  }

  saveDefaultWorkspace() : void {
    this.userManagementService.saveCurrentUserDefaultWorkspaceUUID(
      this.selectedWorkspace.workspace.workspaceUUID
    ).subscribe({
      next: () => {
        this.defaultWorkspaceUpdateConfirmationMessageBoxVisible = true
      },
      error: (error) => {
        // TODO: toast component
          throw(error)
      }
    })
  }

  savePassword() : void {
    if (this.userPasswordConfirmationValid) {
      this.userManagementService.saveCurrentUserPassword(this.userPassword).subscribe({
        next: () => {
          this.userPassword = ""
          this.userPasswordConfirmation = ""
          this.passwordUpdateConfirmationMessageBoxVisible = true
        },
        error: (error) => {
          // TODO: toast component
          throw(error)
        }
      })
    }
  }

  showDeletePersonalAccountMessageBox() : void {
    this.deletePersonalDataMessageBoxVisible = true
  }

  showAssignedPermissionsDialog() : void {

  }

  showIconSelectorDialog() : void {

  }

}

