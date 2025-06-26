import { AccessToken } from "../model-acct/access-token"
import { localObjectStorage } from "../utils-reusalbe/storage-utils"

/**
 * The key under which the access token can be found within the local storage
 */
const LOCAL_STORAGE_ACCESS_TOKEN_KEY : string = "ACCESS_TOKEN"

/**
 * Interface for the localStorage, specialized on ACCT data types
 */
class AcctLocalStore {

    /**
     * Stores the referenced access token into the local storage.
     * @param accessToken the referenced access token
     */
    public storeAccessToken(accessToken : AccessToken) : void {
        localObjectStorage().setItem(LOCAL_STORAGE_ACCESS_TOKEN_KEY, accessToken)
    }

    /**
     * Returns true if there is an access token stored into the local storage.
     * Returns false otherwise.
     */
    public checkAccessTokenStored() : boolean {
        return localObjectStorage().hasItem(LOCAL_STORAGE_ACCESS_TOKEN_KEY)
    }

    /**
     * Returns the stored access token from the local storage. If there is non
     * an access token stored into the local storage, then null is returned.
     */
    public retrieveAccessToken() : AccessToken | null {
        return localObjectStorage().getItem(LOCAL_STORAGE_ACCESS_TOKEN_KEY)
    }

}

const acctLocalStorage : AcctLocalStore = new AcctLocalStore()

/**
 * Provides a reference to the AcctLocalStore, which is an
 * interface for the localStorage, specialized on ACCT data types.
 */
export function acctLocalStore() : AcctLocalStore {
    return acctLocalStorage
}