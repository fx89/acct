import { Component, EventEmitter, input, InputSignal, Output } from '@angular/core';
import {v4 as uuidv4} from 'uuid';
import { LabelComponent } from '../label/label.component';
import { ButtonComponent } from '../button/button.component';

/**
 * This is a function that returns the string representation of the given numeric value
 */
export type NumberDisplayFunction = (value: number) => string;

@Component({
  selector: 'app-spinbox',
  imports: [
    ButtonComponent
  ],
  templateUrl: './spinbox.component.html',
  styleUrl: './spinbox.component.less'
})
export class SpinboxComponent {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()

  // Properties
  visualStyle     : InputSignal<string>                = input("box") // box, label
  width           : InputSignal<string>                = input("100px")
  height          : InputSignal<string>                = input("20px")
  value           : InputSignal<number>                = input(0)
  displayFunction : InputSignal<NumberDisplayFunction> = input((value:number) => value.toString())
  minValue        : InputSignal<number|undefined>      = input()
  maxValue        : InputSignal<number|undefined>      = input()
  increment       : InputSignal<number>                = input(1)
  overflow        : InputSignal<boolean>               = input(false)
  textAlign       : InputSignal<string>                = input("center")

  // Events
  @Output() valueChange : EventEmitter<number> = new EventEmitter<number>()
  @Output() onValueChanged : EventEmitter<number> = new EventEmitter<number>()

  // Private properties
  private currentValue : number = 0


  ngOnInit() : void {
    this.currentValue = this.applyValueConstraints(this.value())
    
  }

  applyValueConstraints(value: number) : number {
    const overflow = this.overflow()

    const minValue : number | undefined = this.minValue()
    const maxValue : number | undefined = this.maxValue()

    if ((minValue !== undefined) && (value < minValue)) {
      if (overflow && (maxValue !== undefined)) {
        return maxValue - (minValue - value) + 1
      } else {
        return minValue
      }
    }

    
    if ((maxValue !== undefined) && (value > maxValue)) {
      if (overflow && (minValue !== undefined)) {
        return minValue + (value - maxValue) - 1
      } else {
        return maxValue
      }
    }

    return value
  }

  
  leftArrowClicked() {
    const newValue : number = this.applyValueConstraints(this.currentValue - this.increment())

    if (newValue != this.currentValue) {
      this.currentValue = newValue
      this.valueChange.emit(this.currentValue)
      this.onValueChanged.emit(this.currentValue)
    }
  }
 
  rightArrowClicked() {
    const newValue : number = this.applyValueConstraints(this.currentValue + this.increment())

    if (newValue != this.currentValue) {
      this.currentValue = newValue
      this.valueChange.emit(this.currentValue)
      this.onValueChanged.emit(this.currentValue)
    }
  }

  getCurrentValue() : number {
    return this.currentValue
  }

  getCurrentValueStr() : string {
    return this.displayFunction()(this.getCurrentValue())
  }

  getWidth() : string {
    return this.width()
  }

  getHeight() : string {
    return this.height()
  }

  getTextAlign() : string {
    return this.textAlign()
  }

  isBox() : boolean {
    return this.visualStyle() === "box"
  }

  isLabel() : boolean {
    return this.visualStyle() === "label"
  }

}
