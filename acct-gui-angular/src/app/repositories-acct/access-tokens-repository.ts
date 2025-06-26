import { Observable } from "rxjs"

/**
 * Specification for the access tokens repository
 */
export abstract class AcctAccessTokensRepository {

    /**
     * Authenticates and authorizes the user with the given username and the given password
     * on behalf of the client identified by the given client id and the given client secret,
     * to obtain an access token for the user.
     * @param clientId      the given client id
     * @param clientSecret  the given client secret
     * @param username      the given username
     * @param password      the given password
     */
    public abstract createUserAccessToken(
        clientId    : string,
        clientSecret: string,
        username    : string,
        password    : string
    ) : Observable<string>

}