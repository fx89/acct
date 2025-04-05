import { Component } from '@angular/core';
import { ColorThemeSelectorComponent } from '../color-theme-selector/color-theme-selector.component';
import { ButtonComponent } from '../button/button.component';
import { InputComponent } from '../input/input.component';
import { SwitchComponent } from '../switch/switch.component';
import { CardComponent } from '../card/card.component';
import { ProgressBarComponent } from '../progress-bar/progress-bar.component';
import { LabelComponent } from '../label/label.component';
import { PanelComponent } from '../panel/panel.component';
import { CardsListComponent } from '../cards-list/cards-list.component';
import { CardData } from '../cards-list/card-data';

@Component({
  selector: 'app-showcase',
  imports: [
    ColorThemeSelectorComponent,
    ButtonComponent,
    InputComponent,
    SwitchComponent,
    CardComponent,
    ProgressBarComponent,
    LabelComponent,
    PanelComponent,
    CardsListComponent
  ],
  templateUrl: './showcase.component.html',
  styleUrl: './showcase.component.less'
})
export class ShowcaseComponent {

  inputValue : string = 'testValue'
  switchValue : boolean = true
  selectedCard : CardData | undefined

  cardsListData : CardData[] = [
    {
      title: "Clickable card",
      text: "This card says hello when clicked",
      imageRef: "",
      onClick: function() { alert('Hello') }
    },
    {
      title: "Not clickable card",
      text: "This card is not clickable",
      imageRef: ""
    },
    {
      title: "Another clickable card",
      text: "This card says hello world when clicked",
      imageRef: "",
      onClick: () => alert('Hello world')
    }
  ]

  public getCardsListData() : CardData[] {
    return this.cardsListData
  }

  public onButtonClick() : void {
    alert('da')
  }

  public onCardSelected(card : CardData) : void {
    this.selectedCard = card
  }

  public getSelectedCardTitle() : string {
    return <string> (this.selectedCard?.title)
  }

}
