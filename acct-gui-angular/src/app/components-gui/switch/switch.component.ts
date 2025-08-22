import { Component, EventEmitter, input, InputSignal, Output } from '@angular/core';
import {v4 as uuidv4} from 'uuid';

@Component({
  selector: 'app-switch',
  imports: [],
  templateUrl: './switch.component.html',
  styleUrl: './switch.component.less'
})
export class SwitchComponent {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()

  // Properties
  enabled : InputSignal<boolean> = input(true)
  value   : InputSignal<boolean> = input(false)
  width   : InputSignal<string>  = input("50px")
  height  : InputSignal<string>  = input("1.3em")
  color   : InputSignal<string>  = input('none')

  // Events
  @Output() valueChange : EventEmitter<boolean> = new EventEmitter<boolean>()
  @Output() onValueChanged : EventEmitter<boolean> = new EventEmitter<boolean>()

  // Private properties
  private currentValue : boolean = false

  ngOnInit() {
    this.currentValue = this.value()
  }

  clickEventHandler() : void {
    if (this.enabled()) {
      this.currentValue = !this.currentValue
      this.valueChange.emit(this.currentValue)
      this.onValueChanged.emit(this.currentValue)
    }
  }

  public getValue() : boolean {
    return this.currentValue
  }

  public getWidth() : string {
    return this.width()
  }

  public getHeight() : string {
    return this.height()
  }

  public isEnabled() : boolean {
    return this.enabled()
  }

  public isRed() : boolean {
    return this.isEnabled() && this.color() == "red"
  }

  public isBlue() : boolean {
    return this.isEnabled() && this.color() == "blue"
  }

}
