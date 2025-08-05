import { Observable } from "rxjs";
import { WorkspaceCollections } from "../model-acct/workspace-collections";
import { WorkspaceUUIDResponse } from "../model-acct/workspace-uuid-response";
import { Workspace } from "../model-acct/workspace";

/**
 * Specification for the workspaces repository
 */
export abstract class AcctWorkspacesRepository {

    /**
     * Retrieves the groups of user-accessible workspaces
     */
    public abstract findUserAccessibleWorkspaces() : Observable<WorkspaceCollections>

    /**
     * Deletes the workspace with the given workspace UUID
     * 
     * @param workspaceUUID the given workspace UUID
     */
    public abstract deleteWorkspace(workspaceUUID : string) : Observable<void>

    /**
     * Updates the workspace with the given workspace UUID or creates a new workspce, if the given workspace UUID
     * is missing or empty. The updated or newly created workspace will contain the data in the referenced
     * workspace object
     * 
     * @param workspace     the referenced workspace object
     * @param workspaceUUID the given workspace UUID
     */
    public abstract saveWorkspace(workspace:Workspace, workspaceUUID? : string) : Observable<WorkspaceUUIDResponse>

}