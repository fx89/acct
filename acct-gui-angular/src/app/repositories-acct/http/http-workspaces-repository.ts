import { Observable } from "rxjs";
import { WorkspaceCollections } from "../../model-acct/workspace-collections";
import { AcctWorkspacesRepository } from "../workspaces-repository";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector } from "../../services-reusable/http-connectors.service";

export class HttpAcctWorkspacesRepository extends AcctWorkspacesRepository {

    constructor(private readonly httpConnector : HttpConnector) {
        super()
    }

    public override findUserAccessibleWorkspaces(): Observable<WorkspaceCollections> {
        return new Observable<WorkspaceCollections>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/workspaces/currentUser"
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:WorkspaceCollections) => responseBody,
                    "No user-accessible workspaces were found."
                )
            )
        })
    }
    
}