import { Injectable } from '@angular/core';
import { AcctAccessTokensRepository } from '../repositories-acct/access-tokens-repository';
import { Observable } from 'rxjs';
import { AccessToken, DecodedAccessToken } from '../model-acct/access-token';
import { errorPipingObservableTransform } from '../utils-reusalbe/rxjs-utils';
import { acctSessionStore } from '../stores-acct/acct-session-storage';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private readonly CLIENT_ID     : string = "frontend-client"
  private readonly CLIENT_SECRET : string = "lkjdwsf23odnwqkld23odfbqweorh23oihd"


  constructor(
    private acctAccessTokensRepository : AcctAccessTokensRepository
  ) { 

  }

  /**
   * Sends the given credentials to the authorization service to retrieve
   * the access token.
   * 
   * @param username the given username
   * @param password the given password
   * @returns an observable that produces the received user access token
   */
  public login(username:string, password:string) : Observable<AccessToken> {
    return errorPipingObservableTransform(
      // Create an user access token with the repository
      this.acctAccessTokensRepository.createUserAccessToken(
        this.CLIENT_ID,
        this.CLIENT_SECRET,
        username,
        password
      ),
      // When the repository successfully completes the operation,
      // process the produced user access token
      userAccessToken => {
        // Decode the access token and create the access token object
        const accessToken : AccessToken = {
          accessToken        : userAccessToken,
          decodedAccessToken : this.decodeAccessToken(userAccessToken)
        }

        // Store the access token object for the session
        acctSessionStore().storeAccessToken(accessToken)

        // Return a reference to the access token object
        return accessToken
      }
    )
  }

  /**
   * Decodes the referenced access token and extracts the properties of
   * interest to the ACCT client front-end
   */
  private decodeAccessToken(accessToken : string) : DecodedAccessToken {
    // Get the token payload
    const tokenParts   = accessToken.split('.');
    const tokenPayload = JSON.parse(atob(tokenParts[1]));

    // Map the token payload to the properties of the produced data type
    return {
      username          : tokenPayload["sub"],
      clientId          : tokenPayload["aud"],
      scope             : tokenPayload["scp"],
      userUUID          : tokenPayload["userUUID"],
      humanReadableName : tokenPayload["humanReadableName"],
      expiresAt         : tokenPayload["exp"]
    }
  }

  

}

