import { Component, input, InputSignal } from '@angular/core';
import {v4 as uuidv4} from 'uuid';

@Component({
  selector: 'app-label',
  imports: [],
  templateUrl: './label.component.html',
  styleUrl: './label.component.less'
})
export class LabelComponent {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()

  // Properties
  width      : InputSignal<string>  = input("300px")
  height     : InputSignal<string>  = input("32px")
  color      : InputSignal<string>  = input("none")
  value      : InputSignal<string>  = input("Label")
  fontSize   : InputSignal<string>  = input("normal")
  textAlign  : InputSignal<string>  = input("left")
  flash      : InputSignal<boolean> = input(false)
  inputStyle : InputSignal<boolean> = input(false)
  style      : InputSignal<string>  = input("")

  getWidth() : string {
    return this.width()
  }

  getHeight() : string {
    return this.height()
  }

  getValue() : string {
    return this.value()
  }

  getTextAlign() : string {
    return this.textAlign()
  }

  getStyle() : string {
    return this.style()
  }

  isRed() : boolean {
    return this.color() === "red"
  }

  isBlue() : boolean {
    return this.color() === "blue"
  }

  isGreen() : boolean {
    return this.color() === "green"
  }

  isFontSmall() : boolean {
    return this.fontSize() === "small"
  }

  isFontLarge() : boolean {
    return this.fontSize() === "large"
  }

  isFlashing() : boolean {
    return this.flash()
  }

  isInputStyle() : boolean {
    return this.inputStyle()
  }

}
