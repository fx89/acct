import { environment } from "../../environments/environment.development"
import { AcctAccessTokensRepository } from "../repositories-acct/access-tokens-repository"
import { AcctAccountRecordsRepository } from "../repositories-acct/account-records-repository"
import { AcctAccountsRepository } from "../repositories-acct/accounts-repository"
import { AcctAutocompleteRepository } from "../repositories-acct/autocomplete-repository"
import { AcctBanksRepository } from "../repositories-acct/banks-repository"
import { AcctCurrenciesRepository } from "../repositories-acct/currencies-repository"
import { HttpAcctAccessTokensRepository } from "../repositories-acct/http/http-access-tokens-repository"
import { HttpAcctAccountRecordsRepository } from "../repositories-acct/http/http-account-records-repository"
import { HttpAcctAccountsRepository } from "../repositories-acct/http/http-accounts-repository"
import { HttpAcctAutocompleteRepository } from "../repositories-acct/http/http-autocomplete-repository"
import { HttpAcctBanksRepository } from "../repositories-acct/http/http-banks-repository"
import { HttpAcctCurrenciesRepository } from "../repositories-acct/http/http-currencies-repository"
import { HttpAcctIconsRepository } from "../repositories-acct/http/http-icons-repository"
import { HttpAcctItemsRepository } from "../repositories-acct/http/http-items-repository"
import { HttpAcctMonitoredCurrenciesRepository } from "../repositories-acct/http/http-monitored-currencies-repository"
import { HttpAcctPrivilegesRepository } from "../repositories-acct/http/http-privileges-repository"
import { HttpAcctUsersRepository } from "../repositories-acct/http/http-users-repository"
import { HttpAcctWorkspacesRepository } from "../repositories-acct/http/http-workspaces-repository"
import { AcctIconsRepository } from "../repositories-acct/icons-repository"
import { AcctItemsRepository } from "../repositories-acct/items-repository"
import { MockAcctAccessTokensRepository } from "../repositories-acct/mock/mock-access-tokens-repository"
import { MockAcctAccountRecordsRepository } from "../repositories-acct/mock/mock-account-records-repository"
import { MockAcctAccountsRepository } from "../repositories-acct/mock/mock-accounts-repository"
import { MockAcctAutocompleteRepository } from "../repositories-acct/mock/mock-autocomplete-repository"
import { MockAcctBanksRepository } from "../repositories-acct/mock/mock-banks-repository"
import { MockAcctCurrenciesRepository } from "../repositories-acct/mock/mock-currencies-repository"
import { MockAcctIconsRepository } from "../repositories-acct/mock/mock-icons-repository"
import { MockAcctItemsRepository } from "../repositories-acct/mock/mock-items-repository"
import { MockAcctMonitoredCurrenciesRepository } from "../repositories-acct/mock/mock-monitored-currencies-repository"
import { MockAcctUsersRepository } from "../repositories-acct/mock/mock-users-repository"
import { MockAcctWorkspacesRepository } from "../repositories-acct/mock/mock-workspaces-repository"
import { AcctMonitoredCurrenciesRepository } from "../repositories-acct/monitored-currencies-repository"
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

export function provideAcctAccountsRepository() {
    return provideRepository(
        AcctAccountsRepository,
        new MockAcctAccountsRepository(),
        httpConnectorsService => new HttpAcctAccountsRepository(
                httpConnectorsService.getHttpConnectorByServiceName('acct-workspace')
            )
    )
}

export function provideAcctAccountRecordsRepository() {
    return provideRepository(
        AcctAccountRecordsRepository,
        new MockAcctAccountRecordsRepository(),
        httpConnectorsService => new HttpAcctAccountRecordsRepository(
                httpConnectorsService.getHttpConnectorByServiceName('acct-workspace')
            )
    )
}

export function provideAcctAutocompleteRepository() {
    return provideRepository(
        AcctAutocompleteRepository,
        new MockAcctAutocompleteRepository(),
        httpConnectorsService => new HttpAcctAutocompleteRepository(
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

export function provideAcctIconsRepository() {
    return provideRepository(
        AcctIconsRepository,
        new MockAcctIconsRepository(),
        httpConnectorsService => new HttpAcctIconsRepository(
                httpConnectorsService.getHttpConnectorByServiceName('acct-catalog')
            )
    )
}

export function provideAcctItemsRepository() {
    return provideRepository(
        AcctItemsRepository,
        new MockAcctItemsRepository(),
        httpConnectorsService => new HttpAcctItemsRepository(
                httpConnectorsService.getHttpConnectorByServiceName('acct-catalog')
            )
    )
}

export function provideAcctBanksRepository() {
    return provideRepository(
        AcctBanksRepository,
        new MockAcctBanksRepository(),
        httpConnectorsService => new HttpAcctBanksRepository(
                httpConnectorsService.getHttpConnectorByServiceName('acct-catalog')
            )
    )
}

export function provideAcctCurrenciesRepository() {
    return provideRepository(
        AcctCurrenciesRepository,
        new MockAcctCurrenciesRepository(),
        httpConnectorsService => new HttpAcctCurrenciesRepository(
                httpConnectorsService.getHttpConnectorByServiceName('acct-catalog')
            )
    )
}

export function provideAcctMonitoredCurrenciesRepository() {
    return provideRepository(
        AcctMonitoredCurrenciesRepository,
        new MockAcctMonitoredCurrenciesRepository(),
        httpConnectorsService => new HttpAcctMonitoredCurrenciesRepository(
                httpConnectorsService.getHttpConnectorByServiceName('acct-currency')
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