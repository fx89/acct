import { Observable } from "rxjs";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector, HttpConnectorsService } from "../../services-reusable/http-connectors.service";
import { AcctAccessTokensRepository } from "../access-tokens-repository";

export class HttpAcctAccessTokensRepository extends AcctAccessTokensRepository {

    constructor(private readonly httpConnector : HttpConnector) {
        super();
    }

    public createUserAccessToken(
        clientId    : string,
        clientSecret: string,
        username    : string,
        password    : string
    ) : Observable<string> {
        return new Observable<string>(subscriber => {
            this.httpConnector.post(
                {
                    url: "/custom-login",
                    data: {
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: {
                            'clientId'     : clientId,
                            'clientSecret' : clientSecret,
                            'username'     : username,
                            'password'     : password
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:any) => responseBody['userAccessToken'],
                    "User access token not received."
                )
            )
        })
    }
    
}