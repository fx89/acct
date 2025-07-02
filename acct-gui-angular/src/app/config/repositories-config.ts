import { environment } from "../../environments/environment.development"
import { AcctAccessTokensRepository } from "../repositories-acct/access-tokens-repository"
import { HttpAcctAccessTokensRepository } from "../repositories-acct/http/http-access-tokens-repository"
import { HttpAcctPrivilegesRepository } from "../repositories-acct/http/http-privileges-repository"
import { HttpAcctUsersRepository } from "../repositories-acct/http/http-users-repository"
import { HttpAcctWorkspacesRepository } from "../repositories-acct/http/http-workspaces-repository"
import { MockAcctAccessTokensRepository } from "../repositories-acct/mock/mock-access-tokens-repository"
import { MockAcctUsersRepository } from "../repositories-acct/mock/mock-users-repository"
import { MockAcctWorkspacesRepository } from "../repositories-acct/mock/mock-workspaces-repository"
import { AcctPrivilegesRepository } from "../repositories-acct/privileges-repository"
import { AcctUsersRepository } from "../repositories-acct/users-repository"
import { AcctWorkspacesRepository } from "../repositories-acct/workspaces-repository"
import { HttpConnectorsService } from "../services-reusable/http-connectors.service"



export function provideAcctAccessTokensRepository() {
    return provideRepository(
        AcctAccessTokensRepository,
        new MockAcctAccessTokensRepository(),
        httpConnectorsService => new HttpAcctAccessTokensRepository(
                httpConnectorsService.getHttpConnectorByServiceName('acct-authorization')
            )
    )
}

export function provideAcctWorkspacesRepository() {
    return provideRepository(
        AcctWorkspacesRepository,
        new MockAcctWorkspacesRepository(),
        httpConnectorsService => new HttpAcctWorkspacesRepository(
                httpConnectorsService.getHttpConnectorByServiceName('acct-workspace')
            )
    )
}

export function provideAcctUsersRepository() {
    return provideRepository(
        AcctUsersRepository,
        new MockAcctUsersRepository(),
        httpConnectorsService => new HttpAcctUsersRepository(
                httpConnectorsService.getHttpConnectorByServiceName('acct-user-management')
            )
    )
}

export function provideAcctPrivilegesRepository() {
    return provideRepository(
        AcctPrivilegesRepository,
        new MockAcctAccessTokensRepository(),
        httpConnectorsService => new HttpAcctPrivilegesRepository(
                httpConnectorsService.getHttpConnectorByServiceName('acct-security')
            )
    )
}


function provideRepository(
    providedType:any,
    mockInstance:any,
    httpInstanceProvider:((httpConnectorsService:HttpConnectorsService)=>any) 
) : any {
    return {
        provide: providedType,
        useFactory: (httpConnectorsService:HttpConnectorsService) => {
            if (environment.useMockRepositories) {
                return mockInstance
            }

            return httpInstanceProvider(httpConnectorsService)
        },
        deps: [
            HttpConnectorsService
        ]
    }
}