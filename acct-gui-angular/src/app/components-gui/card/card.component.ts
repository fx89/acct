import { Component, input, InputSignal } from '@angular/core';
import {v4 as uuidv4} from 'uuid';

@Component({
  selector: 'app-card',
  imports: [],
  templateUrl: './card.component.html',
  styleUrl: './card.component.less'
})
export class CardComponent {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()

  // Properties
  width         : InputSignal<string> = input("280px")
  height        : InputSignal<string> = input("100px")
  imageRef      : InputSignal<string> = input("")
  imageWidth    : InputSignal<string> = input("100px")
  imageHeight   : InputSignal<string> = input("100px")
  imagePosition : InputSignal<string> = input("left")
  title         : InputSignal<string> = input("Card title")
  text          : InputSignal<string> = input("Card text Card text Card text Card text Card text")

  public getWidth() : string {
    return this.width()
  }

  public getHeight() : string {
    return this.height()
  }

  public getImageRef() : string {
    return this.imageRef()
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

}
