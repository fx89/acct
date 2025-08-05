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
import { SecurityService } from '../../services-acct/security.service';
import { Router } from '@angular/router';
import { IconsManagerComponent } from '../../components-acct/icons-manager/icons-manager.component';
import { DialogComponent } from '../../components-gui/dialog/dialog.component';
import { IconProperties } from '../../model-acct/icon-properties';
import { CatalogService } from '../../services-acct/catalog.service';

type WorkspaceCardData = CardData & { workspace : Workspace }

@Component({
  selector: 'app-user-information',
  imports: [
    PanelComponent,
    ButtonComponent,
    MsgboxComponent,
    InputComponent,
    SelectComponent,
    IconsManagerComponent,
    DialogComponent
  ],
  templateUrl: './user-information.component.html',
  styleUrl: './user-information.component.less'
})
export class UserInformationComponent implements AfterViewInit {

  deletePersonalDataMessageBoxType : MsgboxType = MsgboxType.YES_NO
  deletePersonalDataMessageBoxVisible : boolean = false

  personalDataDeletionConfirmationMessageBoxType : MsgboxType = MsgboxType.OK_ONLY
  personalDataDeletionConfirmationMessageBoxVisible : boolean = false

  passwordUpdateConfirmationMessageBoxType : MsgboxType = MsgboxType.OK_ONLY
  passwordUpdateConfirmationMessageBoxVisible : boolean = false

  humanReadableNameUpdateConfirmationMessageBoxType : MsgboxType = MsgboxType.OK_ONLY
  humanReadableNameUpdateConfirmationMessageBoxVisible : boolean = false

  defaultWorkspaceUpdateConfirmationMessageBoxType : MsgboxType = MsgboxType.OK_ONLY
  defaultWorkspaceUpdateConfirmationMessageBoxVisible : boolean = false

  privilegesMessageBoxType: MsgboxType = MsgboxType.OK_ONLY
  privilegesMessageBoxVisible : boolean = false

  iconsManagerVisible : boolean = false

  currentUserDetails : UserDetails | undefined

  userHumanReadableName : string = ""

  userIconBase64 : string = ""

  userPassword : string = ""
  userPasswordConfirmation : string = ""
  userPasswordConfirmationValid : boolean = true

  commaSeparatedGroupNames : string = ""

  commaSeparatedPrivilegeNames : string = ""

  availableWorkspaces : WorkspaceCardData[] = []
  selectedWorkspace : WorkspaceCardData = this.availableWorkspaces[0]

  constructor(
    private router : Router,
    private workspaceService : WorkspaceService,
    private userManagementService : UserManagementService,
    private securityService : SecurityService,
    private catalogService : CatalogService
  ) {

  }

  ngAfterViewInit(): void {
    // Get the current user information
    this.userManagementService.findCurrentUserDetails().subscribe({
      next : userDetails => {
        // Update the user properties
        this.currentUserDetails = userDetails
        this.userHumanReadableName = this.currentUserDetails.userName
        this.commaSeparatedGroupNames = this.currentUserDetails.userGroups.map(userGroup => userGroup.groupName).join(",")

        // If the user icon is set, then load the bytes
        if (userDetails.userIconUUID && userDetails.userIconUUID != "") {
          this.catalogService.loadIconBytesBase64(userDetails.userIconUUID).subscribe({
            next: userIconBase64 => {
              this.userIconBase64 = userIconBase64
            },
            error: err => {
              // TODO: Toast
              console.log(err)
            }
          })
        }
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

  onIconSelected(icon:IconProperties) : void {
    // Update the user's icon data
    this.userManagementService.saveCurrentUserIconUUID(icon.iconUUID).subscribe({
      // Upon successful save, trigger a reload and hide the icons manager
      next: () => {
        // Load the icon
        this.catalogService.loadIconBytesBase64(icon.iconUUID).subscribe({
          next: userIconBase64 => {
            this.userIconBase64 = userIconBase64
          },
          error: err => {
            // TODO: Toast
            console.log(err)
          }
        })

        // Hide the icons manager
        this.hideIconsManager()
      },
      error: err => {
        // TODO: Toast
        console.log(err)
      }
    })
  }

  softDeletePersonalAccountConfirmed() : void {
    this.userManagementService.softDeleteCurrentUser().subscribe({
      next: () => {
        this.personalDataDeletionConfirmationMessageBoxVisible = true
      },
      error: (error) => {
        // TODO: toast component
          throw(error)
      }
    })
  }

  softDeletePersonalAccountAcknowledged() : void {
    this.router.navigate(['/login'])
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
      this.selectedWorkspace.workspace.workspaceUUID as string
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
    this.securityService.findPrivilegesAssignedToCurrentUser().subscribe({
      next: (privileges) => {
        this.commaSeparatedPrivilegeNames = privileges.join(", ")
        this.privilegesMessageBoxVisible = true
      },
      error: (error) => {
          // TODO: toast component
          throw(error)
        }
    })
  }

  showIconsManager() : void {
    this.iconsManagerVisible = true
  }

  hideIconsManager() : void {
    this.iconsManagerVisible = false
  }

}

