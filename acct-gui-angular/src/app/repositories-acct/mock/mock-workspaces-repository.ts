import { Observable } from "rxjs";
import { WorkspaceCollections } from "../../model-acct/workspace-collections";
import { AcctWorkspacesRepository } from "../workspaces-repository";
import { Workspace } from "../../model-acct/workspace";
import { WorkspaceUUIDResponse } from "../../model-acct/workspace-uuid-response";

/**
 * Mock implementation of the AcctWorkspacesRepository
 */
export class MockAcctWorkspacesRepository extends AcctWorkspacesRepository {

    public override deleteWorkspace(workspaceUUID: string): Observable<void> {
        throw new Error("Method not implemented.");
    }

    public override saveWorkspace(workspace: Workspace, workspaceUUID?: string): Observable<WorkspaceUUIDResponse> {
        throw new Error("Method not implemented.");
    }

    public override findUserAccessibleWorkspaces(): Observable<WorkspaceCollections> {
        throw new Error("Method not implemented.");
    }
    
}