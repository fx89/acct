import { Injectable } from '@angular/core';
import { AcctWorkspacesRepository } from '../repositories-acct/workspaces-repository';
import { forkJoin, Observable, Subscriber } from 'rxjs';
import { WorkspaceCollections } from '../model-acct/workspace-collections';
import { IconifiedWorkspace, Workspace } from '../model-acct/workspace';
import { distinctElementsArray } from '../utils-reusalbe/array-utils';
import { complete, errorPipingObservableConsumer, errorPipingObservableOperation, errorPipingObservableTransform } from '../utils-reusalbe/rxjs-utils';
import { CatalogService } from './catalog.service';
import { WorkspaceUUIDResponse } from '../model-acct/workspace-uuid-response';

/**
 * Interface to the Workspace back-end service
 */
@Injectable({
  providedIn: 'root'
})
export class WorkspaceService {

  constructor(
    private workspacesRepository : AcctWorkspacesRepository,
    private catalogService : CatalogService
  ) { 

  }

  /**
   * Returns an observable that produces an array of workspaces,
   * complete with the image data of the related icons.
   */
  public findUserAccessibleWorkspaces(): Observable<IconifiedWorkspace[]> {
    // TODO: cache into session
    return errorPipingObservableOperation(
      this.workspacesRepository.findUserAccessibleWorkspaces(),
      (workspaceCollections:WorkspaceCollections, subscriber:Subscriber<IconifiedWorkspace[]>) => {
        // Flatten the response into an array of workspaces
        const workspaces : Workspace[] = this.workspaceCollectionsToWorkspaceArray(workspaceCollections)

        // Convert to iconified workspaces
        const iconifiedWorkspaces : IconifiedWorkspace[] = workspaces as IconifiedWorkspace[]

        // Apply the icons. Once all the icons have been applied,
        // feed the iconified workspaces array into the subscriber.
        errorPipingObservableConsumer(
          forkJoin(
            workspaces.map((workspace:IconifiedWorkspace) => {
              return this.catalogService.applyIcon(
                () => workspace.workspaceIconUUID,
                imageData => workspace.imageData = imageData
              )
            })
          ),
          subscriber,
          () => complete(subscriber, iconifiedWorkspaces)
        )
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
