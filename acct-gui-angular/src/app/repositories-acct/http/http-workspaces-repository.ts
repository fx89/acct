import { identity, Observable } from "rxjs";
import { WorkspaceCollections } from "../../model-acct/workspace-collections";
import { AcctWorkspacesRepository } from "../workspaces-repository";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector } from "../../services-reusable/http-connectors.service";
import { Workspace } from "../../model-acct/workspace";
import { WorkspaceUUIDResponse } from "../../model-acct/workspace-uuid-response";
import { complete } from "../../utils-reusalbe/rxjs-utils";

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
                    identity,
                    "No user-accessible workspaces were found."
                )
            )
        })
    }

    public override deleteWorkspace(workspaceUUID: string): Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.delete(
                {
                    url: "/workspaces",
                    data: {
                        params: {
                            workspaceUUID : workspaceUUID
                        }
                    }
                },
                {
                    responseHandler: () => complete(subscriber, undefined),
                    errorHandler : err => subscriber.error(err)
                }
            )
        })
    }

    public override saveWorkspace(workspace: Workspace, workspaceUUID?: string): Observable<WorkspaceUUIDResponse> {
        return new Observable<WorkspaceUUIDResponse>(subscriber => {
            // Create parameters object
            const params : Record<string, string | number | boolean | ReadonlyArray<string | number | boolean>> = {}

            // If a workspaceUUID was provided, then add it to the parameters object
            if (workspaceUUID) {
                params["workspaceUUID"] = workspaceUUID
            }

            this.httpConnector.put(
                {
                    url: "/workspaces",
                    data: {
                        params: params,
                        body: {
                            workspaceName        : workspace.workspaceName,
                            workspaceDescription : workspace.workspaceDescription,
                            workspaceIconUUID    : workspace.workspaceIconUUID,
                            defaultCurrencyUUID  : workspace.defaultCurrencyUUID
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    identity,
                    "Unable to save workspace."
                )
            )
        })
    }
    
    
}