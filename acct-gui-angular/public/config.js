// Environment-specific configuration variables
// ================================================================

/**
 * Configuration properties for the back-end service connectors.
 * A back-end service may have multiple instances, in which case
 * all the URLs have to be provided. 
 */
const BACKEND_SERVICES_CONFIG = {
    authorizationService: {
        urls: [
            "http://localhost:9001"
        ]
    },

    userManagementService: {
        urls: [
            "http://localhost:8081"
        ]
    },

    securityService: {
        urls: [
            "http://localhost:8082"
        ]
    },

    workspaceService: {
        urls: [
            "http://localhost:8085"
        ]
    },

    catalogService: {
        urls: [
            "http://localhost:8086"
        ]
    },

    currencyService: {
        urls: [
            "http://localhost:8087"
        ]
    },

    reportingService: {
        urls: [
            "http://localhost:8088"
        ]
    }
}

function applyConfiguration() {
    document.head.setAttribute("BACKEND_SERVICES_CONFIG", JSON.stringify(BACKEND_SERVICES_CONFIG))
}
