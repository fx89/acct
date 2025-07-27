import { Component, EventEmitter, input, InputSignal, OnInit, Output } from '@angular/core';
import {v4 as uuidv4} from 'uuid';
import { ButtonComponent } from '../button/button.component';

/**
 * Defines the properties of a card's action button. If defined, action buttons are shown
 * in the top right corner of the card. They can have a text, an icon or both, depending
 * on the chosen settings. They can also have one of the colors supported by the button
 * component, or no color at all.
 */
export interface CardActionButton {
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
  onClick : () => void
}

@Component({
  selector: 'app-card',
  imports: [
    ButtonComponent
  ],
  templateUrl: './card.component.html',
  styleUrl: './card.component.less'
})
export class CardComponent implements OnInit {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()

  // Properties
  width         : InputSignal<string>  = input("280px")
  height        : InputSignal<string>  = input("100px")
  imageRef      : InputSignal<string|undefined>  = input(<string|undefined>"")
  imageWidth    : InputSignal<string>  = input("100px")
  imageHeight   : InputSignal<string>  = input("100px")
  imagePosition : InputSignal<string>  = input("left")
  title         : InputSignal<string>  = input("Card title")
  text          : InputSignal<string>  = input("Card text Card text Card text Card text Card text")
  clickable     : InputSignal<boolean> = input(false)

  // Card action buttons
  actions : InputSignal<CardActionButton[]> = input([] as CardActionButton[])

  actionsArray : CardActionButton[] = []

  ngOnInit() : void {
    this.actionsArray = this.actions()
  }

  // Events
  @Output('onClick') onClickEventEmitter: EventEmitter<void> = new EventEmitter<void>()

  public getWidth() : string {
    return this.width()
  }

  public getHeight() : string {
    return this.height()
  }

  public getImageRef() : string {
    return this.imageRef() ?? ""
  }

  public getImageWidth() : string {
    return this.imageWidth()
  }

  public getImageHeight() : string {
    return this.imageHeight()
  }

  public getImageFloat() : string {
    return this.imagePosition() === "right" ? "right" : "left"
  }

  public getContentPaddingLeft() : string {
    if (this.isImageVisible()) {
      const imageFloat = this.getImageFloat()

      if (imageFloat === "right") {
        return "0px"
      }

      return "calc(" + this.getImageWidth() + " + 10px)"
    }

    return "0px"
  }

  public getContentPaddingRight() : string {
    if (this.isImageVisible()) {
      const imageFloat = this.getImageFloat()

      if (imageFloat === "left") {
        return "0px"
      }

      return "calc(" + this.getImageWidth() + " + 10px)"
    }

    return "0px"
  }

  public getTitle() : string {
    return this.title()
  }

  public getText() : string {
    return this.text()
  }

  public isImageVisible() : boolean {
    const imgRef = this.imageRef()
    return imgRef !== undefined && imgRef !== null && imgRef !== ""
  }

  public isTitleVisible() : boolean {
    const strTitle : string = this.title()
    return strTitle !== undefined && strTitle !== null && strTitle !== ""
  }

  public isTextVisible() : boolean {
    const strText : string = this.text()
    return strText !== undefined && strText !== null && strText !== ""
  }

  public isContentVisible() : boolean {
    return this.isTitleVisible() || this.isTextVisible()
  }

  areActionsVisible() : boolean {
    return this.actionsArray.length > 0
  }

  public isClickable() : boolean {
    return this.clickable()
  }

  public onClick() : void {
    if (this.isClickable()) {
      this.onClickEventEmitter.emit();
    }
  }

}
