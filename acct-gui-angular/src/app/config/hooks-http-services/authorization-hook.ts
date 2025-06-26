import { AccessToken } from "../../model-acct/access-token"
import { HttpClientWrapperMethodlessRequest, setRequestHeader } from "../../services-reusable/http-client-wrapper.service"
import { acctLocalStore } from "../../stores-acct/acct-local-storage"
import { currentTimestampInSeconds } from "../../utils-reusalbe/date-utils"

const AUTH_HEADER_KEY : string = "Authorization"
const AUTH_TOKEN_TYPE : string = "Bearer"

/**
 * Adds the authorization header to the referenced request. The authorization
 * header contains the access token obtained through the login process. If no
 * access token is stored, or if the stored access token is expired, then the
 * user is redirected to the login form.
 * 
 * TODO: refresh the token
 * 
 * @param request the referenced request
 * @returns the modifeid version of the referenced request
 */
export function authorizingHttpConnectorPreRequestHook(
    request : HttpClientWrapperMethodlessRequest<any>
) : HttpClientWrapperMethodlessRequest<any>
{
    // If there is an access token stored
    if (acctLocalStore().checkAccessTokenStored()) {
        // Get the stored acess token
        const accessToken : AccessToken = retrieveStoredAccessToken()

        // If the stored access token is not expired
        if (currentTimestampInSeconds() < accessToken.decodedAccessToken.expiresAt) {

        // Authorize the request using the access token's raw value
        setRequestHeader(
            request,
            AUTH_HEADER_KEY,
            AUTH_TOKEN_TYPE + " " + accessToken.accessToken
        )

        // Allow the request to be sent
        return request
        }
    }

    // Redirect to the login form
    throw "Not logged in or session expired"
}

function retrieveStoredAccessToken() : AccessToken {
    // Retrieve the access token (if any)
    const accessToken : AccessToken | null = acctLocalStore().retrieveAccessToken()

    // If the access token was retrieved, then return a reference
    if (accessToken) {
        return accessToken
    }

    // If the access token was not retrieved, then throw an exception
    throw "There is no access token stored"
}