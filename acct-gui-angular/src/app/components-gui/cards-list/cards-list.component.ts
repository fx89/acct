import { Component, EventEmitter, input, InputSignal, Output } from '@angular/core';
import { CardData } from './card-data';
import { PanelComponent } from '../panel/panel.component';
import { CardComponent } from '../card/card.component';
import {v4 as uuidv4} from 'uuid';

@Component({
  selector: 'app-cards-list',
  imports: [
    PanelComponent,
    CardComponent
  ],
  templateUrl: './cards-list.component.html',
  styleUrl: './cards-list.component.less'
})
export class CardsListComponent {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()

  // Panel properties
  width        : InputSignal<string>  = input("600px")
  height       : InputSignal<string>  = input("400px")
  title        : InputSignal<string>  = input("")
  emptyMessage : InputSignal<string>  = input("The list is empty")
  selectable   : InputSignal<boolean> = input(false)
  clickable    : InputSignal<boolean> = input(false)

  // Card layout properties
  cardWidth         : InputSignal<string> = input("280px")
  cardHeight        : InputSignal<string> = input("100px")
  cardImageWidth    : InputSignal<string> = input("100px")
  cardImageHeight   : InputSignal<string> = input("100px")
  cardImagePosition : InputSignal<string> = input("left")
  cardSpacing       : InputSignal<string> = input("5px")

  // Card data
  cards : InputSignal<CardData[]> = input.required()

  // Events
  @Output() selectionChange : EventEmitter<CardData> = new EventEmitter<CardData>()

  // Internal properties
  selectedCard : CardData | undefined

  getCards() : CardData[] {
    return this.cards() || []
  }

  getWidth() : string {
    return this.width()
  }

  getHeight() : string {
    return this.height()
  }

  getTitle() : string {
    return this.title()
  }

  getCardWidth() : string {
    return this.cardWidth()
  }

  getCardHeight() : string {
    return this.cardHeight()
  }

  getCardImageWidth() : string {
    return this.cardImageWidth()
  }

  getCardImageHeight() : string {
    return this.cardImageHeight()
  }

  getCardImagePosition() : string {
    return this.cardImagePosition()
  }

  getCardSpacing() : string {
    return this.cardSpacing()
  }

  getEmptyMessage() : string {
    return this.emptyMessage()
  }

  isSelectable() : boolean {
    return this.selectable()
  }

  isClickable() : boolean {
    return this.clickable()
  }

  isCardClickable(card : CardData) : boolean {
      return typeof card.onClick === 'function'
  }

  isCardSelected(card : CardData) : boolean {
    return this.isSelectable() && card == this.selectedCard
  }

  onCardClicked(card : CardData) : void {
    if (this.isSelectable()) {
      this.selectedCard = card
      this.selectionChange.emit(card)
    }

    if (this.isClickable() && this.isCardClickable(card) && card.onClick) {
      card.onClick()
    }
  }

}
