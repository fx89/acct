import { Component, EventEmitter, input, InputSignal, Output, Renderer2 } from '@angular/core';
import {v4 as uuidv4} from 'uuid';
import { CardComponent } from '../card/card.component';
import { CardData } from '../cards-list/card-data';
import { CardsListComponent } from '../cards-list/cards-list.component';
import { getElementRect } from '../../utils-reusalbe/dom-utils';

@Component({
  selector: 'app-select',
  imports: [
    CardComponent,
    CardsListComponent
  ],
  templateUrl: './select.component.html',
  styleUrl: './select.component.less'
})
export class SelectComponent {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()
  public selectOptionsListId = this.id + "_select_options"
  public wrappingDivId = this.id + "_select"

  // Input properties
  width             : InputSignal<string> = input("280px")
  height            : InputSignal<string> = input("15px")
  cardImagePosition : InputSignal<string> = input("left")
  cardImageWidth    : InputSignal<string> = input("50px")
  cardImageHeight   : InputSignal<string> = input("50px")
  cardListHeightPx  : InputSignal<number> = input(300)
  cardHeightPx      : InputSignal<number> = input(50)

  // Options
  options        : InputSignal<CardData[]> = input.required()
  selectedOption : InputSignal<CardData | undefined> = input()

  // Events
  @Output() selectedOptionChange : EventEmitter<CardData | undefined> = new EventEmitter<CardData | undefined>()

  // Internal properties
  listVisible         : boolean = false
  listJustMadeVisible : boolean = false


  // Injectables
  renderer : Renderer2

  constructor(renderer : Renderer2) {
    this.renderer = renderer
  }

  public getWidth() : string {
    return this.width()
  }

  public getHeight() : string {
    return this.height()
  }

  public getOptions() : CardData[] {
    return this.options()
  }

  public getSelectedOption() : CardData | undefined {
    return this.selectedOption()
  }

  public getCardImagePosition() : string {
    return this.cardImagePosition()
  }

  public getCardImageWidth() : string {
    return this.cardImageWidth()
  }

  public getCardImageHeight() : string {
    return this.cardImageHeight()
  }

  public getSelectedOptionImageRef() : string {
    return this.selectedOption()?.imageRef ?? ""
  }

  public getSelectedOptionTitle() : string {
    return this.selectedOption()?.title ?? ""
  }

  public getCardListHeight() : string {
    return this.cardListHeightPx() + "px"
  }

  public getOptionsCount() : number {
    return this.options().length
  }

  public getOptionsCountStr() : string {
    return this.getOptionsCount() + ""
  }

  public getCardHeight() : string {
    return this.cardHeightPx() + "px"
  }

  public getTotalCardPaddingPx() : number {
    return 25
  }

  public getTotalCardPadding() : string {
    return this.getTotalCardPaddingPx() + 'px'
  }

  public isListVisible() : boolean {
    return this.listVisible
  }

  public hasOverflow() : boolean {
    const givenHeight = this.cardListHeightPx()
    const listHeight = (this.cardHeightPx() + this.getTotalCardPaddingPx()) * this.getOptionsCount()

    if (givenHeight < listHeight) {
      return true
    }

    return false
  }

  public getActualListHeightPx() : number {
    const givenHeight = this.cardListHeightPx()
    const listHeight = (this.cardHeightPx() + this.getTotalCardPaddingPx()) * this.getOptionsCount()

    if (givenHeight < listHeight) {
      return givenHeight
    }

    return listHeight
  }

  public getActualListHeight() : string {
    return this.getActualListHeightPx() + "px"
  }

  public onSelectButtonClicked() : void {
    // If the cards list is already shown, then hide it
    if (this.isListVisible()) {
      this.listVisible = false
    }

    // If the cards list is not already visible, then set its position and show it
    else {
      // Show the cards list
      this.listVisible = true

      // Later on, make a not that the list was just made visible, to enable the event handler
      // for when there's a click outside the control. Making the note right away activates
      // the event handler right away, which results in hiding the options list immediately
      // after it has been shown, which is not the desired behavior.
      window.setTimeout(() => {
        this.listJustMadeVisible = true
      }, 100)

      // Reposition the select options list
      this.repositionSelectOptionsList()
    }
  }

  public onCardSelected(card : CardData) : void {
    // Hid the cards list
    this.listVisible = false

    // Emit the change event for the selected card / option
    this.selectedOptionChange.emit(card)
  }

  private repositionSelectOptionsList() : void {
    // Get a reference to the document element
    const selectOptionsListElement : HTMLElement | null = document.getElementById(this.selectOptionsListId)

    // If there's a select options list element on screen, then reposition it
    if (selectOptionsListElement) {
      // Get the wrapping div element
      const wrappingDivElementRect : DOMRect | undefined = getElementRect(this.wrappingDivId)

      // If there's a wrapping div element on screen, then reposition the select options list
      // element relative to the wrapping div element
      if (wrappingDivElementRect) {
        // The X position is exactly the wrapping div element's left boundary
        selectOptionsListElement.style.left = wrappingDivElementRect.left + "px"

        // The top position is either the bottom boundary of the wrapping div element
        // or the uppoer boundary minus the list height, depending on the scroll position
        if (wrappingDivElementRect.bottom + selectOptionsListElement.offsetHeight > window.innerHeight) {
          selectOptionsListElement.style.top = (wrappingDivElementRect.top - selectOptionsListElement.offsetHeight) + "px"
        } else {
          selectOptionsListElement.style.top = wrappingDivElementRect.bottom + "px"
        }
      }
      

      // Set the X position of the select options list
      //selectOptionsListElement.style.left = wrappingDivElement?.style.left || "0px"
      //console.log(wrappingDivElement)

      // Compute the cards list position to be either on top of or under the selection box,
      // based on the position of the control relative to the viewport bottom
    }
  }

  public ngOnInit() : void {
    this.renderer.listen('window', 'click', (e : Event) => { 
      if (this.listJustMadeVisible) {
        this.listVisible = false
        this.listJustMadeVisible = false
      }
    })
  }

}
