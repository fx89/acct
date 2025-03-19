import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Theme } from '../model-reusable/theme';
import { setCookie, getCookie, deleteCookie } from '../utils-reusalbe/cookie-utils';

const THEMES_BASE_PATH: string = 'color-schemes';
const THEMES_COOKIE_THEME_NAME: string = 'themes-service-selected-theme-name';

@Injectable({
  providedIn: 'root'
})
export class ColorThemesService {

  themes: Theme[] = []

  selectedThemeName: string = ""

  constructor(private http: HttpClient) { 
    // Begin loading the themes index
    this.http.get(THEMES_BASE_PATH + '/_index.json')
    // When done loading:
    .subscribe(res => {
      // Assign the loaded themes object for future use
      this.themes = <Theme[]> res;

      // Load the themes into the head element
      for (let themeName in this.themes) {
        this.loadThemeFile(this.themes[themeName].file)
      }

      // Assign the selected theme or, if none selected, assign the first loaded theme
      this.setTheme(this.resolveUserThemeName())
    });
  }

  /**
   * @returns the loaded themes configuration object
   */
  public getThemes() : Theme[] {
    return this.themes
  }

  public getSelectedThemeName() : string {
    return this.selectedThemeName
  }

  /**
   * Applies the theme with the given theme name. If the given theme name does not identify
   * an existing theme, all an unhandled exception occurs. If no themes are loaded, the same
   * unhandled exception occurs. 
   * 
   * @param targetThemeName the given theme name
   */
  public setTheme(targetThemeName: string) : void {
    // Only if the themes have been loaded
    if (this.themes) {
      // Remove the selectors defined by each theme
      for (let themeName in this.themes) {
        document.body.classList.remove(this.themes[themeName].selector)
      }

      // Assign the named theme
      document.body.classList.add(this.themes[<any>targetThemeName].selector)

      // Save the theme to the cookie
      setCookie(THEMES_COOKIE_THEME_NAME, targetThemeName, 999)

      // Set the local var
      this.selectedThemeName = targetThemeName
    }
  }

  private resolveUserThemeName() : string {
    // Get the theme name cookie
    const themeName: string = getCookie(THEMES_COOKIE_THEME_NAME)

    // If the theme name cookie is set then update its expiration date and return its value
    if (themeName) {
      setCookie(THEMES_COOKIE_THEME_NAME, themeName, 999)
      return themeName;
    }

    // If the theme name cookie is not set...

    // Determine the name of the first loaded theme
    var firstLoadedThemeName: string = ''
    for (let tName in this.themes) {
      firstLoadedThemeName = <string> tName
      break
    }
  
    // Set the theme name cookie
    setCookie(THEMES_COOKIE_THEME_NAME, firstLoadedThemeName, 999)

    // Return the name of the first loaded theme
    return firstLoadedThemeName
  }

  private loadThemeFile(themeFileName: String) : void {
    let node = document.createElement('link')
    node.rel = "stylesheet"
    node.href = THEMES_BASE_PATH + '/' + themeFileName
    document.getElementsByTagName('head')[0].appendChild(node)
  }

}
