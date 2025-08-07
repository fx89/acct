import { Injectable } from '@angular/core';
import { IconifiedWorkspace, Workspace } from '../model-acct/workspace';
import { WorkspaceService } from './workspace.service';
import { acctLocalStore } from '../stores-acct/acct-local-storage';
import { acctSessionStore } from '../stores-acct/acct-session-storage';
import { UserManagementService } from './user-management.service';
import { Observable } from 'rxjs';
import { complete, errorPipingObservableConsumer, errorPipingObservableOperation } from '../utils-reusalbe/rxjs-utils';
import { UserDetails } from '../model-acct/user-details';

@Injectable({
  providedIn: 'root'
})
export class WorkspaceSelectorService {

  constructor(
    private workspaceService      : WorkspaceService,
    private userManagementService : UserManagementService
  ) { }

  /**
   * Stores the referenced workspace in local storage as the selected workspce for the current user
   * 
   * @param workspace the referenced workspace
   */
  public setSelectedWorkspace(workspace:Workspace) : void {
    // Get the current user from the stored access token or throw an error
    const currentUserUUID : string = acctSessionStore().retrieveAccessToken()?.decodedAccessToken?.userUUID ?? ""
    if (currentUserUUID == "") {
      throw new Error("Not logged in.")
    }

    // If the workspace has an UUID, then store it
    if (workspace.workspaceUUID) {
      acctLocalStore().storeUserSelectedWorkspace(currentUserUUID, workspace.workspaceUUID)
    }
    // If the workspace does not have an UUID, then throw an error
    else {
      throw new Error("Workspace not persisted.")
    }
  }

  /**
   * Returns an observable that produces the workspace selected by the current user. If
   * no workspace is selected, then the user's default workspace is used.
   */
  public findSelectedWorkspace() : Observable<IconifiedWorkspace> {
    return new Observable<IconifiedWorkspace>(subscriber => {
      // Retrieve the current user
      this.userManagementService.findCurrentUserDetails().subscribe({
        next: userDetails => {
          // Resolve the stored selected workspace for the user
          const workspaceUUID : string = this.resolveSelectedWorkspaceUUID(userDetails)

          // Retrieve the workspace
          this.workspaceService.findUserAccessibleWorkspace(workspaceUUID).subscribe({
            next: workspace => complete(subscriber, workspace),
            error: err => subscriber.error(err)
          })
        },
        error: err => subscriber.error(err)
      })
    })
  }

  private resolveSelectedWorkspaceUUID(userDetails:UserDetails) : string {
    // If there's a selected workspace in local storage for the user, then retrieve it.
    if (acctLocalStore().checkUserSelectedWorkspaceStored(userDetails.userUUID)) {
      return acctLocalStore().retrieveUserSelectedStorage(userDetails.userUUID)
    }

    // If there's no selected workspace in local storage for the user, then use the default worksapce...

    // First store it
    acctLocalStore().storeUserSelectedWorkspace(userDetails.userUUID, userDetails.defaultWorkspaceUUID)

    // And then return a reference
    return userDetails.defaultWorkspaceUUID
  }

}
