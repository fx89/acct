import { Injectable, Predicate } from '@angular/core';
import { AcctWorkspacesRepository } from '../repositories-acct/workspaces-repository';
import { concatAll, forkJoin, map, Observable, Subscriber } from 'rxjs';
import { WorkspaceCollections } from '../model-acct/workspace-collections';
import { IconifiedWorkspace, Workspace } from '../model-acct/workspace';
import { distinctElementsArray } from '../utils-reusalbe/array-utils';
import { errorPipingObservableTransform } from '../utils-reusalbe/rxjs-utils';
import { CatalogService } from './catalog.service';
import { WorkspaceUUIDResponse } from '../model-acct/workspace-uuid-response';
import { Account, IconifiedAccount } from '../model-acct/account';
import { AcctAccountsRepository } from '../repositories-acct/accounts-repository';
import { AccountUUIDResponse } from '../model-acct/account-uuid-response';

/**
 * Interface to the Workspace back-end service
 */
@Injectable({
  providedIn: 'root'
})
export class WorkspaceService {

  constructor(
    private workspacesRepository   : AcctWorkspacesRepository,
    private catalogService         : CatalogService,
    private acctAccountsRepository : AcctAccountsRepository
  ) { 

  }

  /**
   * Returns an observable that produces an array of workspaces, complete with the image data of the related icons.
   * If a filter is given, then the array of workspaces will contain only workspaces that match the given filter.
   * 
   * @param filter an optional predicate that can be used to select a specific sub-set of workspaces
   */
  public findUserAccessibleWorkspaces(filter?:Predicate<Workspace>): Observable<IconifiedWorkspace[]> {
    // TODO: cache into session
    return this.workspacesRepository.findUserAccessibleWorkspaces().pipe(
      // Convert the workspaces collection into a workspaces array
      map(workspaceCollections => this.workspaceCollectionsToWorkspaceArray(workspaceCollections)),

      // Optionally apply the filter
      map(workspaces => filter ? workspaces.filter(filter) : workspaces),

      // Convert to iconified accounts
      map(workspaces => workspaces as IconifiedWorkspace[]),

      // Retrieve the icon for each iconified account
      map(workspaces =>
        this.catalogService.applyIconToItems(
          workspaces,
          workspace => workspace.workspaceIconUUID,
          (workspace, imageData) => workspace.imageData = imageData
        )
      ),

      // Flatten the Observable-of-Observables resulted from the icon applying operation
      concatAll()
    )
  }

  /**
   * Returns an observable that produces the workspace identified by the given workspaceUUID.
   * If the referenced workspace is not accessible by the user, or if the workspace does not
   * exist, then an error is thrown.
   * 
   * @param workspaceUUID the given workspace UUID
   */
  public findUserAccessibleWorkspace(workspaceUUID:string) : Observable<IconifiedWorkspace> {
    return errorPipingObservableTransform(
      this.findUserAccessibleWorkspaces(ws => workspaceUUID == ws.workspaceUUID),
      workspaces => {
        // If there is no workspace in the array, then throw the error
        if (workspaces.length == 0) {
          throw new Error("Workspace not found")
        }
        
        // If there's an workspace in the array, then return a reference
        return workspaces[0]
      }
    )
  }

  /**
   * Deletes the referenced workspace
   */
  public deleteWorkspace(workspace:Workspace) : Observable<void> {
    if (workspace.workspaceUUID) {
      return this.workspacesRepository.deleteWorkspace(workspace.workspaceUUID)
    } else {
      throw new Error("Workspace UUID not provided")
    }
  }

  /**
   * Saves the referenced workspace
   * 
   * @param workspace the referenced workspace
   * @returns a container for the UUID of the saved workspace
   */
  public saveWorkspace(workspace:Workspace) : Observable<WorkspaceUUIDResponse> {
    return this.workspacesRepository.saveWorkspace(workspace, workspace.workspaceUUID)
  }

  /**
   * Returns an observable that produces an array containing the accounts defined
   * within the scope of the referenced workspace. If there are no such accounts,
   * then an empty array is produced.
   * 
   * @param workspace the referenced workspace
   */
  public findWorkspaceAccounts(workspace:Workspace) : Observable<IconifiedAccount[]> {
    if (workspace.workspaceUUID) {
      // Store the wokrspace UUID
      const workspaceUUID : string = workspace.workspaceUUID

      return this.acctAccountsRepository.findAccountsByWorkspaceUUID(workspaceUUID).pipe(
        // Convert to iconified accounts
        map(accounts => accounts as IconifiedAccount[]),

        // Retrieve the icon for each iconified account
        map(accounts =>
          this.catalogService.applyIconToItems(
            accounts,
            account => account.accountIconUUID,
            (account, imageData) => account.imageData = imageData
          )
        ),

        // Flatten the Observable-of-Observables resulted from the icon applying operation
        concatAll()
      )
    }
    // If the workspace doesn't have an UUID, then throw an error
    else {
      throw new Error("Missing workspaceUUID")
    }
  }

  /**
   * Deletes the referenced account from the referenced workspace
   * @param workspace the referenced workspace
   * @param account   the referenced account
   * @returns an observable that tells the consumer when the operation has been completed
   */
  public deleteWorkspaceAccount(workspace:Workspace, account:Account) : Observable<void> {
    if (workspace.workspaceUUID) {
      if (account.accountUUID) {
        return this.acctAccountsRepository.deleteAccount(workspace.workspaceUUID, account.accountUUID)
      }
      else {
        throw new Error("Missing account UUID")
      }
    }
    else {
      throw new Error("Missing workspace UUID")
    }
  }

  /**
   * Saves the referenced account into the referenced workspace by either creating a new
   * account entry, if the account UUID is not present, or updating an existing account
   * entry, if the account UUID is present.
   * 
   * @param workspace the referenced workspace
   * @param account   the referenced account
   * @returns an AccountUUIDResponse that contains the UUID of the persisted account
   */
  public saveAccount(workspace:Workspace, account:Account) : Observable<AccountUUIDResponse> {
    if (workspace.workspaceUUID) {
      return this.acctAccountsRepository.saveAccount(workspace.workspaceUUID, account)
    }
    else {
      throw new Error("Missing workspace UUID")
    }
  }

  private workspaceCollectionsToWorkspaceArray(workspaceCollections:WorkspaceCollections) : Workspace[] {
    return distinctElementsArray(
      workspaceCollections.groupWorkspaces.concat(
        workspaceCollections.publicWorkspaces,
        workspaceCollections.userWorkspaces
      ),
      // Workspaces are uniquely identified by the workspaceUUID
      (workspace:Workspace) => workspace.workspaceUUID
    )
  }

}
