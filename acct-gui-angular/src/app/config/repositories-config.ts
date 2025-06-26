import { environment } from "../../environments/environment.development"
import { AcctAccessTokensRepository } from "../repositories-acct/access-tokens-repository"
import { HttpAcctAccessTokensRepository } from "../repositories-acct/http/http-access-tokens-repository"
import { MockAcctAccessTokensRepository } from "../repositories-acct/mock/mock-access-tokens-repository"
import { HttpConnectorsService } from "../services-reusable/http-connectors.service"

export function provideAcctAccessTokensRepository() {
    return {
        provide: AcctAccessTokensRepository,
        useFactory: (httpConnectorsService:HttpConnectorsService) => {
            if (environment.useMockRepositories) {
                return new MockAcctAccessTokensRepository()
            }

            return new HttpAcctAccessTokensRepository(httpConnectorsService)
        },
        deps: [
            HttpConnectorsService
        ]
    }
}