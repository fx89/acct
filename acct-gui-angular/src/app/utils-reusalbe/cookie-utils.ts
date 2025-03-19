
/**
 * Sets or updates the value and expiration date of the cookie with the given cookie name
 * 
 * @param cname  the given cookie name
 * @param cvalue the value
 * @param exdays the expiration date
 */
export const setCookie = function(cname: string, cvalue: string, exdays: number): void {
    const d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    let expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

/**
 * Returns the value of the cookie with the given cookie name
 * 
 * @param cname the given cookie name
 */
export const getCookie = function(cname: string): string {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');

    for(let i = 0; i <ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }

    return "";
  }

  /**
   * Deletes the value of the cookie with the given cookie name
   * 
   * @param name the given cookie name
   */
export const deleteCookie = function(name: string): void {
    document.cookie = name + '=; Max-Age=0'
}