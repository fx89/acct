import { Injectable } from '@angular/core';
import { AcctWorkspacesRepository } from '../repositories-acct/workspaces-repository';
import { Observable } from 'rxjs';
import { WorkspaceCollections } from '../model-acct/workspace-collections';
import { Workspace } from '../model-acct/workspace';
import { distinctElementsArray } from '../utils-reusalbe/array-utils';
import { errorPipingObservableTransform } from '../utils-reusalbe/rxjs-utils';

/**
 * Interface to the Workspace back-end service
 */
@Injectable({
  providedIn: 'root'
})
export class WorkspaceService {

  constructor(
    private workspacesRepository : AcctWorkspacesRepository
  ) { 

  }

  public findUserAccessibleWorkspaces(): Observable<Workspace[]> {
    // TODO: cache into session
    return errorPipingObservableTransform(
      this.workspacesRepository.findUserAccessibleWorkspaces(),
      (workspaceCollections:WorkspaceCollections) => this.workspaceCollectionsToWorkspaceArray(workspaceCollections)
    )
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
