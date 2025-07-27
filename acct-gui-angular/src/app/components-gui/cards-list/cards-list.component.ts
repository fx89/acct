import { Component, EventEmitter, input, InputSignal, OnInit, Output } from '@angular/core';
import { CardData } from './card-data';
import { PanelComponent } from '../panel/panel.component';
import { CardActionButton, CardComponent } from '../card/card.component';
import {v4 as uuidv4} from 'uuid';
import { ScrollEvent } from '../directives/scrollable-content.directive';

/**
 * Contains all the properties of the card action button, except the click event is replaced
 * by a consumer that consumes the item represented by the card owning the button that has
 * been clicked.
 */
export interface ItemAwareCardActionButton<T> {
  /**
   * Optional text to show on the button. If not set, then no text is displayed.
   */
  text? : string,

  /**
   * Optional color for the button. If not set, then the button will have the the standard color.
   */
  color? : string,

  /**
   * The width of the button. Can be set to anything, including overflowing values, so be
   * careful.
   */
  width : string,

  /**
   * Optional icon "src". If not set, then the no icon is displayed on the button.
   */
  icon? : string,

  /**
   * Callback for the action to be performed when the button is clicked.
   */
  onClick : (item:T) => void
}

@Component({
  selector: 'app-cards-list',
  imports: [
    PanelComponent,
    CardComponent
  ],
  templateUrl: './cards-list.component.html',
  styleUrl: './cards-list.component.less'
})
export class CardsListComponent implements OnInit {

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

  // Action buttons
  actionButtons : InputSignal<ItemAwareCardActionButton<any>[]> = input([] as ItemAwareCardActionButton<any>[])

  // Card data
  cards : InputSignal<CardData[]> = input.required()

  // Events
  @Output() selectionChange : EventEmitter<CardData> = new EventEmitter<CardData>()
  @Output() scroll : EventEmitter<ScrollEvent> = new EventEmitter<ScrollEvent>

  // Internal properties
  selectedCard : CardData | undefined
  cardActionButtons : CardActionButton[] = []

  ngOnInit(): void {
    this.initCardActionButtons()
  }

  private initCardActionButtons() {
    // For each action button defined by the consumer, create an internal card action button,
    // with an "onClick" callback that calls the "onClick" callback of the consumer-defined
    // action button while giving it the selected card data.
    this.cardActionButtons = this.actionButtons().map(actionButton => {
      return {
        width   : actionButton.width,
        color   : actionButton.color,
        icon    : actionButton.icon,
        text    : actionButton.text,
        onClick : () => {
          if (actionButton.onClick) {
            // A timeout is needed to allow the selectedCard property to be populated
            setTimeout(() => {
              actionButton.onClick(this.selectedCard)
            }, 50)
          }
        }
      }
    })
  }

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

  onScroll(scrollEvent : ScrollEvent) : void {
    this.scroll.emit(scrollEvent)
  }

}
