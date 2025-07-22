import { Observable } from "rxjs";
import { AcctPrivilegesRepository } from "../privileges-repository";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector } from "../../services-reusable/http-connectors.service";

export class HttpAcctPrivilegesRepository extends AcctPrivilegesRepository {


    constructor(private httpConnector : HttpConnector) {
        super()
    }

    public override findPrivilegesAssignedToCurrentUser(): Observable<string[]> {
        return new Observable<string[]>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/privileges/currentUserPrivileges"
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:string[]) => responseBody,
                    "Password not updated."
                )
            )
        })
    }
    
}