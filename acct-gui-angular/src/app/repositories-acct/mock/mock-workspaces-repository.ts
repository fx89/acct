import { Observable } from "rxjs";
import { WorkspaceCollections } from "../../model-acct/workspace-collections";
import { AcctWorkspacesRepository } from "../workspaces-repository";

/**
 * Mock implementation of the AcctWorkspacesRepository
 */
export class MockAcctWorkspacesRepository extends AcctWorkspacesRepository {

    public override findUserAccessibleWorkspaces(): Observable<WorkspaceCollections> {
        throw new Error("Method not implemented.");
    }
    
}