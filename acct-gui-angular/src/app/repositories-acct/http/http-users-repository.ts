import { Observable } from "rxjs";
import { UserDetails } from "../../model-acct/user-details";
import { AcctUsersRepository } from "../users-repository";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector } from "../../services-reusable/http-connectors.service";

export class HttpAcctUsersRepository extends AcctUsersRepository {

    constructor(private readonly httpConnector : HttpConnector) {
        super();
    }

    public override findCurrentUserDetails(): Observable<UserDetails> {
        return new Observable<UserDetails>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/users/currentUser"
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:UserDetails) => responseBody,
                    "Current user details not found."
                )
            )
        })
    }

    public override saveCurrentUserPassword(userEncryptedPassword: string): Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.post(
                {
                    url: "/users/currentUser",
                    data: {
                        headers: {
                            'Content-Type' : 'application/json'
                        },
                        body: {
                            userEncryptedPassword : userEncryptedPassword
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:void) => responseBody,
                    "Password not updated."
                )
            )
        })
    }

    public override saveCurrentUserHumanReadableName(humanReadableName: string): Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.post(
                {
                    url: "/users/currentUser/userName",
                    data: {
                        headers: {
                            'Content-Type' : 'application/json'
                        },
                        body: {
                            userName : humanReadableName
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:void) => responseBody,
                    "Password not updated."
                )
            )
        })
    }

    public override saveCurrentUserDefaultWorkspaceUUID(defaultWorkspaceUUID: string): Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.post(
                {
                    url: "/users/currentUser/defaultWorkspace",
                    data: {
                        headers: {
                            'Content-Type' : 'application/json'
                        },
                        body: {
                            defaultWorkspaceUUID : defaultWorkspaceUUID
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:void) => responseBody,
                    "Password not updated."
                )
            )
        })
    }

    public override softDeleteCurrentUser(): Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.delete(
                {
                    url: "/users/currentUser/softDelete"
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:void) => responseBody,
                    "Current user not deleted."
                )
            )
        })
    }
    
}