import { Observable } from "rxjs";
import { WorkspaceCollections } from "../model-acct/workspace-collections";

/**
 * Specification for the workspaces repository
 */
export abstract class AcctWorkspacesRepository {

    /**
     * Retrieves the groups of user-accessible workspaces
     */
    public abstract findUserAccessibleWorkspaces() : Observable<WorkspaceCollections>

}