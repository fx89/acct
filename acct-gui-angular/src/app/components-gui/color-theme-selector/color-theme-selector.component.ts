import { Component } from '@angular/core';
import { ColorThemesService } from '../../services-reusable/color-themes.service';
import {v4 as uuidv4} from 'uuid';
import { CardData } from '../cards-list/card-data';
import { SelectComponent } from '../select/select.component';

type ColorThemeSelectorCardData = CardData & { 
  file : string,
  selector: string
}

@Component({
  selector: 'app-color-theme-selector',
  imports: [
    SelectComponent
  ],
  templateUrl: './color-theme-selector.component.html',
  styleUrl: './color-theme-selector.component.less'
})
export class ColorThemeSelectorComponent {

  themeCards: ColorThemeSelectorCardData[] = []

  selectedThemeCard : ColorThemeSelectorCardData | undefined

  public id = uuidv4()

  constructor(private colorThemesServices: ColorThemesService) {
    
  }

  public ngOnInit() : void {
    this.colorThemesServices.getThemesLoadedEvent().subscribe(themes => {
      var themeIndex = 0

      for (let themeName in themes) {
        this.themeCards[themeIndex] =
          {
            title: themeName,
            text: themes[themeName].description,
            imageRef: themes[themeName].icon,
            file: themes[themeName].file,
            selector: themes[themeName].selector
          }
        themeIndex++
      }

      this.selectedThemeCard = this.getThemeCardByName(this.colorThemesServices.getSelectedThemeName())
    })
  }

  private getThemeCardByName(themeCardName : string) : ColorThemeSelectorCardData | undefined {
    for (let themeCard of this.themeCards) {
      if (themeCard?.title == themeCardName) {
        return themeCard
      }
    }

    return undefined
  }

  public getThemeCards() : ColorThemeSelectorCardData[] {
    return this.themeCards
  }

  public onThemeCardSelected(card : CardData | undefined): void {
    const themeCard : ColorThemeSelectorCardData = <ColorThemeSelectorCardData> card
    this.selectedThemeCard = themeCard
    this.colorThemesServices.setTheme(themeCard.title)
  }

  public getSelectedThemeName() : string {
    return this.colorThemesServices.getSelectedThemeName()
  }

}
