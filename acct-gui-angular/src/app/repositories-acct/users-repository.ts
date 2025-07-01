import { Observable } from "rxjs";
import { UserDetails } from "../model-acct/user-details";

/**
 * Specification for the users repository
 */
export abstract class AcctUsersRepository {

    public abstract findCurrentUserDetails() : Observable<UserDetails>

    public abstract saveCurrentUserPassword(userEncryptedPassword:string) : Observable<void>

    public abstract saveCurrentUserHumanReadableName(humanReadableName:string) : Observable<void>

    public abstract saveCurrentUserDefaultWorkspaceUUID(defaultWorkspaceUUID:string) : Observable<void>

}