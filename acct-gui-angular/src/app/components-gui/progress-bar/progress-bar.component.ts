import { Component, input, InputSignal } from '@angular/core';
import {v4 as uuidv4} from 'uuid';

@Component({
  selector: 'app-progress-bar',
  imports: [],
  templateUrl: './progress-bar.component.html',
  styleUrl: './progress-bar.component.less'
})
export class ProgressBarComponent {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()

  // Properties
  width        : InputSignal<string>  = input("300px")
  height       : InputSignal<string>  = input("32px")
  color        : InputSignal<string>  = input("none")
  value        : InputSignal<number>  = input(0.5)
  loading      : InputSignal<boolean> = input(false)
  valueVisible : InputSignal<boolean> = input(false)

  getWidth() : string {
    return this.width()
  }

  getHeight() : string {
    return this.height()
  }

  getValue() : number {
    const val : number = this.value()

    if (val > 1.0) {
      return 1.0
    }

    if (val < 0) {
      return 0.0
    }

    return val
  }

  getValueStr() : string {
    return this.getValue() * 100 + "%"
  }

  getRoundedValueStr() : string {
    return (this.getValue() * 100).toFixed(2) + "%"
  }

  isRed() : boolean {
    return this.color() === "red"
  }

  isBlue() : boolean {
    return this.color() === "blue"
  }

  isLoading() : boolean {
    return this.loading()
  }

  isValueVisible() : boolean {
    return this.valueVisible()
  }

}
