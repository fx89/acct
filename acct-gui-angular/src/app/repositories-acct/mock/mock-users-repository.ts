import { Observable } from "rxjs";
import { UserDetails } from "../../model-acct/user-details";
import { AcctUsersRepository } from "../users-repository";

/**
 * Mock implementation of the AcctUsersRepository
 */
export class MockAcctUsersRepository extends AcctUsersRepository {

    public override findCurrentUserDetails(): Observable<UserDetails> {
        throw new Error("Method not implemented.");
    }

    public override saveCurrentUserPassword(userEncryptedPassword: string): Observable<void> {
        throw new Error("Method not implemented.");
    }

    public override saveCurrentUserHumanReadableName(humanReadableName: string): Observable<void> {
        throw new Error("Method not implemented.");
    }

    public override saveCurrentUserDefaultWorkspaceUUID(defaultWorkspaceUUID: string): Observable<void> {
        throw new Error("Method not implemented.");
    }
    
}