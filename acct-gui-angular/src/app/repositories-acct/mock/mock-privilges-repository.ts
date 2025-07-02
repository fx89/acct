import { Observable } from "rxjs";
import { AcctPrivilegesRepository } from "../privileges-repository";

export class MockAcctPrivilegesRepository extends AcctPrivilegesRepository {

    public override findPrivilegesAssignedToCurrentUser(): Observable<string[]> {
        throw new Error("Method not implemented.");
    }
    
}