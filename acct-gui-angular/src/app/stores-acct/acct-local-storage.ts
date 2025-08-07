import { localObjectStorage } from "../utils-reusalbe/storage-utils"

const LOCAL_STORAGE_SELECTED_WORKSPACE_KEY : string = "SELECTED_WORKSPACE"

/**
 * Interface for the localStorage, specialized on ACCT data types
 */
class AcctLocalStorage {

    /**
     * Saves the given worksapceUUID for the given userUUID into the local storage
     * 
     * @param userUUID      the given userUUID
     * @param workspaceUUID the given workspaceUUID
     */
    public storeUserSelectedWorkspace(userUUID:string, workspaceUUID:string) : void {
        localObjectStorage().setItem(
            this.computeUserSelectedWorkspaceKey(userUUID),
            workspaceUUID
        )
    }

    /**
     * Returns true if there is a selected workspace stored in the local storage for
     * the user with the given UUID. Returns false otherwise.
     * 
     * @param userUUID the given UUID
     */
    public checkUserSelectedWorkspaceStored(userUUID:string) : boolean {
        return localObjectStorage().hasItem(this.computeUserSelectedWorkspaceKey(userUUID))
    }

    /**
     * Retrieves the selected workspace for the user with the given userUUID from
     * local storage. If there is no such workspace defined, then an error is thrown.
     * 
     * @param userUUID the given userUUID
     */
    public retrieveUserSelectedStorage(userUUID:string) : string {
        const workspaceUUID : string | null = localObjectStorage().getItem(
            this.computeUserSelectedWorkspaceKey(userUUID)
        )

        if (workspaceUUID) {
            return workspaceUUID
        }

        throw new Error(
            "There is no selected workspace registered for the refernced user. Use checkUserSelectedWorkspaceStored()."
        )
    }

    private computeUserSelectedWorkspaceKey(userUUID:string) : string {
        return LOCAL_STORAGE_SELECTED_WORKSPACE_KEY + "_" + userUUID
    }

}

const acctLocalStorage : AcctLocalStorage = new AcctLocalStorage()

export function acctLocalStore() : AcctLocalStorage {
    return acctLocalStorage
}