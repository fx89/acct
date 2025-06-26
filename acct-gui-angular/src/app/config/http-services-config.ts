import { authorizingHttpConnectorPreRequestHook } from './hooks-http-services/authorization-hook'
import { HttpConnectorBaseURLsResolver } from '../services-reusable/http-connectors.service'

/**
 * Provides the base URLs for the authorization service.
 */
class AcctAuthorizationServiceUrlResolver implements HttpConnectorBaseURLsResolver {
    resolveBaseURLs(): string | string[] {
        return "http://localhost:9001"
    }
}

/**
 * Provides the base URLs for the user management service.
 */
class AcctUserManagementServiceUrlResolver implements HttpConnectorBaseURLsResolver {
    resolveBaseURLs(): string | string[] {
        return "http://localhost:8081"
    }
}

/**
 * Provides the HttpServicesConfig injectable for the app configuration
 */
export function provideHttpServicesConfig() {
    return {
        provide: 'HttpServicesConfig',
        useValue:  [
            {
                serviceName     : "acct-authorization",
                servicePath     : "/service/authorization/v1",
                urlsResolver    : new AcctAuthorizationServiceUrlResolver(),
                preRequestHooks : [ authorizingHttpConnectorPreRequestHook ] // Requests to the authorization service do not need to be authorized
            },
            {
                serviceName     : "acct-user-management-service",
                servicePath     : "/service/user-management/v1",
                urlsResolver    : new AcctUserManagementServiceUrlResolver(),
                preRequestHooks : [
                    authorizingHttpConnectorPreRequestHook // Requests to this service need to be authorized
                ]
            }
        ],
        deps: [  ]
    }
}


