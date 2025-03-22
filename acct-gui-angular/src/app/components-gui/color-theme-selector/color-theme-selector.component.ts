import { Component } from '@angular/core';
import { ColorThemesService } from '../../services-reusable/color-themes.service';
import {v4 as uuidv4} from 'uuid';

@Component({
  selector: 'app-color-theme-selector',
  imports: [],
  templateUrl: './color-theme-selector.component.html',
  styleUrl: './color-theme-selector.component.less'
})
export class ColorThemeSelectorComponent {

  private themeNames: string[] = []

  public id = uuidv4()

  constructor(private colorThemesServices: ColorThemesService) {
    
  }

  public getThemeNames() : string[] {
    if (this.themeNames.length == 0) {
      this.computeThemeNames()
    }

    return this.themeNames
  }

  public onThemeNameSelected(event: any): void {
    this.colorThemesServices.setTheme(event.target.value)
  }

  public getSelectedThemeName() : string {
    console.log(this.colorThemesServices.getSelectedThemeName())
    return this.colorThemesServices.getSelectedThemeName()
  }

  private computeThemeNames() : void {
    var themeIndex = 0
    for (let themeName in this.colorThemesServices.getThemes()) {
      this.themeNames[themeIndex] = themeName
      themeIndex++
    }
  }

}
