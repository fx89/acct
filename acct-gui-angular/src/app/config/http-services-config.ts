import { authorizingHttpConnectorPreRequestHook } from './hooks-http-services/authorization-hook'
import { HttpConnectorBaseURLsResolver } from '../services-reusable/http-connectors.service'
import { Router } from '@angular/router'
import { HttpClientWrapperMethodlessRequest } from '../services-reusable/http-client-wrapper.service'

type BackendServiceConfig = {
    urls: string[]
}

type BackendServicesConfig = {
    authorizationService: BackendServiceConfig,
    userManagementService: BackendServiceConfig,
    securityService: BackendServiceConfig,
    workspaceService: BackendServiceConfig,
    catalogService: BackendServiceConfig,
    currencyService: BackendServiceConfig,
    reportingService: BackendServiceConfig
}

function loadBackendServicesConfig() : BackendServicesConfig {
    return <BackendServicesConfig><any>(JSON.parse(<string>document.head.getAttribute("BACKEND_SERVICES_CONFIG")))
}

/**
 * Provides the base URLs for the authorization service.
 */
class AcctAuthorizationServiceUrlResolver implements HttpConnectorBaseURLsResolver {
    resolveBaseURLs(): string | string[] {
        const config = loadBackendServicesConfig()
        return config?.authorizationService?.urls
    }
}

/**
 * Provides the base URLs for the user management service.
 */
class AcctUserManagementServiceUrlResolver implements HttpConnectorBaseURLsResolver {
    resolveBaseURLs(): string | string[] {
        const config = loadBackendServicesConfig()
        return config?.userManagementService?.urls
    }
}

/**
 * Provides the base URLs for the workspace service.
 */
class AcctWorkspaceServiceUrlResolver implements HttpConnectorBaseURLsResolver {
    resolveBaseURLs(): string | string[] {
        const config = loadBackendServicesConfig()
        return config?.workspaceService?.urls
    }
}

/**
 * Provides the base URLs for the security service.
 */
class AcctSecurityServiceUrlResolver implements HttpConnectorBaseURLsResolver {
    resolveBaseURLs(): string | string[] {
        const config = loadBackendServicesConfig()
        return config?.securityService?.urls
    }
}

/**
 * Provides the base URLs for the catalog service.
 */
class AcctCatalogServiceUrlResolver implements HttpConnectorBaseURLsResolver {
    resolveBaseURLs(): string | string[] {
        const config = loadBackendServicesConfig()
        return config?.catalogService?.urls
    }
}

/**
 * Provides the base URLs for the currency service.
 */
class AcctCurrencyServiceUrlResolver implements HttpConnectorBaseURLsResolver {
    resolveBaseURLs(): string | string[] {
        const config = loadBackendServicesConfig()
        return config?.currencyService?.urls
    }
}

/**
 * Provides the base URLs for the reporting service.
 */
class AcctReportingServiceUrlResolver implements HttpConnectorBaseURLsResolver {
    resolveBaseURLs(): string | string[] {
        const config = loadBackendServicesConfig()
        return config?.reportingService?.urls
    }
}



/**
 * Provides the HttpServicesConfig injectable for the app configuration
 */
export function provideHttpServicesConfig() {
    return {
        provide: 'HttpServicesConfig',
        useFactory: (router:Router) =>  [
            {
                serviceName     : "acct-authorization",
                servicePath     : "/service/authorization/v1",
                urlsResolver    : new AcctAuthorizationServiceUrlResolver(),
                preRequestHooks : [ ] // Requests to the authorization service do not need to be authorized
            },
            {
                serviceName     : "acct-user-management",
                servicePath     : "/service/user-management/v1",
                urlsResolver    : new AcctUserManagementServiceUrlResolver(),
                preRequestHooks : [
                    // Requests to this service need to be authorized
                    (request:HttpClientWrapperMethodlessRequest<any>) => 
                        authorizingHttpConnectorPreRequestHook(request, router)
                ]
            },
            {
                serviceName     : "acct-workspace",
                servicePath     : "/service/workspace/v1",
                urlsResolver    : new AcctWorkspaceServiceUrlResolver(),
                preRequestHooks : [
                    // Requests to this service need to be authorized
                    (request:HttpClientWrapperMethodlessRequest<any>) => 
                        authorizingHttpConnectorPreRequestHook(request, router)
                ]
            },
            {
                serviceName     : "acct-security",
                servicePath     : "/service/security/v1",
                urlsResolver    : new AcctSecurityServiceUrlResolver(),
                preRequestHooks : [
                    // Requests to this service need to be authorized
                    (request:HttpClientWrapperMethodlessRequest<any>) => 
                        authorizingHttpConnectorPreRequestHook(request, router)
                ]
            },
            {
                serviceName     : "acct-catalog",
                servicePath     : "/service/catalog/v1",
                urlsResolver    : new AcctCatalogServiceUrlResolver(),
                preRequestHooks : [
                    // Requests to this service need to be authorized
                    (request:HttpClientWrapperMethodlessRequest<any>) => 
                        authorizingHttpConnectorPreRequestHook(request, router)
                ]
            },
            {
                serviceName     : "acct-currency",
                servicePath     : "/service/currency/v1",
                urlsResolver    : new AcctCurrencyServiceUrlResolver(),
                preRequestHooks : [
                    // Requests to this service need to be authorized
                    (request:HttpClientWrapperMethodlessRequest<any>) => 
                        authorizingHttpConnectorPreRequestHook(request, router)
                ]
            },
            {
                serviceName     : "acct-reporting",
                servicePath     : "/service/reporting/v1",
                urlsResolver    : new AcctReportingServiceUrlResolver(),
                preRequestHooks : [
                    // Requests to this service need to be authorized
                    (request:HttpClientWrapperMethodlessRequest<any>) => 
                        authorizingHttpConnectorPreRequestHook(request, router)
                ]
            }
        ],
        deps: [ Router ]
    }
}


