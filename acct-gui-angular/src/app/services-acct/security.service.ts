import { Injectable } from '@angular/core';
import { AcctPrivilegesRepository } from '../repositories-acct/privileges-repository';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  constructor(private acctPrivilegesRepository : AcctPrivilegesRepository) { }

  public findPrivilegesAssignedToCurrentUser(): Observable<string[]> {
    return this.acctPrivilegesRepository.findPrivilegesAssignedToCurrentUser()
  }

}
