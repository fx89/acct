import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDetails } from '../model-acct/user-details';
import { AcctUsersRepository } from '../repositories-acct/users-repository';
import bcrypt from "bcryptjs"
/**
 * Interface to the User Management service
 */
@Injectable({
  providedIn: 'root'
})
export class UserManagementService {

  constructor(
    private usersRepository : AcctUsersRepository
  ) { }

  /**
   * Retrieves the details of the currently logged-in user
   */
  public findCurrentUserDetails() : Observable<UserDetails> {
    return this.usersRepository.findCurrentUserDetails()
  }

  /**
   * Updates the current user's password to the given value
   * @param userPassword the given value
   */
  public saveCurrentUserPassword(userPassword:string) : Observable<void> {
    // Encrypt password
    const userEncryptedPassword : string = this.encryptPassword(userPassword)

    // Save the password
    return this.usersRepository.saveCurrentUserPassword(userEncryptedPassword)
  }

  /**
   * Updates the current user's human-readable name to the given value
   * @param humanReadableName the given value
   */
  public saveCurrentUserHumanReadableName(humanReadableName:string) : Observable<void> {
    return this.usersRepository.saveCurrentUserHumanReadableName(humanReadableName)
  }

  /**
   * Updates the current users's default workspace UUID to the given value
   * @param defaultWorkspaceUUID the given value
   */
  public saveCurrentUserDefaultWorkspaceUUID(defaultWorkspaceUUID:string) : Observable<void> {
    return this.usersRepository.saveCurrentUserDefaultWorkspaceUUID(defaultWorkspaceUUID)
  }

  private encryptPassword(userPassword:string) : string {
    const saltRounds : number = 10
    const salt : string = bcrypt.genSaltSync(saltRounds)
    return "{bcrypt}" + bcrypt.hashSync(userPassword, salt)
  }

}
