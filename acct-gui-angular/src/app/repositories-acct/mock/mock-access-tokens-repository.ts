import { Observable } from "rxjs";
import { AcctAccessTokensRepository } from "../access-tokens-repository";

/**
 * Mock implementation of the AcctAccessTokensRepository
 */
export class MockAcctAccessTokensRepository extends AcctAccessTokensRepository {

    public override createUserAccessToken(
        clientId     : string,
        clientSecret : string,
        username     : string,
        password     : string
    ): Observable<string> {
        throw new Error("Method not implemented.");
    }

}