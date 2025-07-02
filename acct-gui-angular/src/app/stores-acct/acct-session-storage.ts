import { AccessToken } from "../model-acct/access-token"
import { sessionObjectStorage } from "../utils-reusalbe/storage-utils"

/**
 * The key under which the access token can be found within the local storage
 */
const SESSION_STORAGE_ACCESS_TOKEN_KEY : string = "ACCESS_TOKEN"

/**
 * Interface for the sessionStorage, specialized on ACCT data types
 */
class AcctSessionStorage {

    /**
     * Stores the referenced access token into the local storage.
     * @param accessToken the referenced access token
     */
    public storeAccessToken(accessToken : AccessToken) : void {
        sessionObjectStorage().setItem(SESSION_STORAGE_ACCESS_TOKEN_KEY, accessToken)
    }

    /**
     * Returns true if there is an access token stored into the local storage.
     * Returns false otherwise.
     */
    public checkAccessTokenStored() : boolean {
        return sessionObjectStorage().hasItem(SESSION_STORAGE_ACCESS_TOKEN_KEY)
    }

    /**
     * Returns the stored access token from the local storage. If there is non
     * an access token stored into the local storage, then null is returned.
     */
    public retrieveAccessToken() : AccessToken | null {
        return sessionObjectStorage().getItem(SESSION_STORAGE_ACCESS_TOKEN_KEY)
    }

    public removeAccessToken() : void {
        return sessionObjectStorage().removeItem(SESSION_STORAGE_ACCESS_TOKEN_KEY)
    }

}

const acctSessionStorage : AcctSessionStorage = new AcctSessionStorage()

/**
 * Provides a reference to the AcctSessionStore, which is an
 * interface for the localStorage, specialized on ACCT data types.
 */
export function acctSessionStore() : AcctSessionStorage {
    return acctSessionStorage
}