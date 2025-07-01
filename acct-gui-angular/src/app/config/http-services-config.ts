import { authorizingHttpConnectorPreRequestHook } from './hooks-http-services/authorization-hook'
import { HttpConnectorBaseURLsResolver } from '../services-reusable/http-connectors.service'
import { Router } from '@angular/router'
import { HttpClientWrapperMethodlessRequest } from '../services-reusable/http-client-wrapper.service'

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
 * Provides the base URLs for the workspace service.
 */
class AcctWorkspaceServiceUrlResolver implements HttpConnectorBaseURLsResolver {
    resolveBaseURLs(): string | string[] {
        return "http://localhost:8085"
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
            }
        ],
        deps: [ Router ]
    }
}


