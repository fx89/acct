import { Observable } from "rxjs";

export abstract class AcctPrivilegesRepository {

    public abstract findPrivilegesAssignedToCurrentUser() : Observable<string[]>

}