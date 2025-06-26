
/**
 * Container for the encoded and decoded versions of an ACCT user access token
 */
export interface AccessToken {
    /**
     * The encoded access token, as received from the authorization server
     */
    accessToken : string,

    /**
     * The decoded access token
     */
    decodedAccessToken : DecodedAccessToken
}

/**
 * Specifies the properties of an ACCT user access token:
 * - username          = The name that uniquely identifies the user in the user management service
 * - clientId          = The ID of the client that was used to obtain the access token
 * - scope             = An array of scopes for which the user is authorized
 * - userUUID          = The unique user ID that identifies the user across all the ACCT services
 * - humanReadableName = The display name of the user
 * - expiresAt         = The timestamp at which the access token expires
 */
export interface DecodedAccessToken {
    /**
     * The name that uniquely identifies the user in the user management service
     */
    username : string,

    /**
     * The ID of the client that was used to obtain the access token
     */
    clientId : string,

    /**
     * An array of scopes for which the user is authorized
     */
    scope : string[],

    /**
     * The unique user ID that identifies the user across all the ACCT services
     */
    userUUID : string,

    /**
     * The display name of the user
     */
    humanReadableName : string,

    /**
     * The timestamp at which the access token expires
     */
    expiresAt : number
}