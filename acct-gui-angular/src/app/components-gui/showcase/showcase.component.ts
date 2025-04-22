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
import { SelectComponent } from '../select/select.component';
import { BarComponent } from '../bar/bar.component';
import { MenuComponent } from '../menu/menu.component';
import { MenuItemData } from '../menu/menu-item-data';

type ExtendedCardData = CardData & { additionalData : string }

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
    CardsListComponent,
    SelectComponent,
    BarComponent,
    MenuComponent
  ],
  templateUrl: './showcase.component.html',
  styleUrl: './showcase.component.less'
})
export class ShowcaseComponent {

  inputValue : string = 'testValue'
  switchValue : boolean = true
  selectedCard : CardData | undefined
  selectedOption : CardData | undefined
  selectSelectedOption : ExtendedCardData | undefined
  selectedMenuItem : MenuItemData | undefined

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

  selectOptions : ExtendedCardData[] = [
    {
      title: "Select option 1",
      text: "This is the first select option",
      imageRef: "pic.png",
      onClick: undefined,
      additionalData: "Option a"
    },
    {
      title: "Select option 2",
      text: "This is the second select option",
      imageRef: "favicon.ico",
      additionalData: "Option b"
    },
    {
      title: "Select option 3",
      text: "This is the third select option",
      imageRef: "",
      additionalData: "Option c"
    }
  ]

  menuItems : MenuItemData[] = [
    {
      text: "No option",
      imageRef: "pic.png",
      onSelect: undefined,
      onDeselect: undefined
    },
    {
      text: "Angular icon",
      imageRef: "favicon.ico",
      onSelect: undefined,
      onDeselect: undefined
    },
    {
      text: "Dark icon",
      imageRef: "color-schemes/dark.png",
      onSelect: undefined,
      onDeselect: undefined
    },
    {
      text: "Bright icon",
      imageRef: "color-schemes/bright.png",
      onSelect: undefined,
      onDeselect: undefined
    },
    {
      text: "Still no option",
      imageRef: "pic.png",
      onSelect: undefined,
      onDeselect: undefined
    }
  ]



  public ngOnInit() : void {
    this.selectedOption = this.cardsListData[0]
    this.selectSelectedOption = this.selectOptions[0]
    this.selectedMenuItem = this.menuItems[2]
  }

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

  public getSelectSelectedOptionValue() : string {
    return this.selectSelectedOption?.additionalData || "Nothing selected"
  }

  public selectSelectedOptionChange(event : any) : void {
    this.selectSelectedOption = <ExtendedCardData> event
  }

  public getMenuItems() : MenuItemData[] {
    return this.menuItems
  }

}
