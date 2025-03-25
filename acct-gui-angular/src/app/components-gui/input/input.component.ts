import { Component, input, InputSignal, Output, EventEmitter } from '@angular/core'; 
import {v4 as uuidv4} from 'uuid';

@Component({
  selector: 'app-input',
  imports: [],
  templateUrl: './input.component.html',
  styleUrl: './input.component.less',
  standalone: true
})
export class InputComponent {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()

  // Properties
  enabled : InputSignal<boolean> = input(true)
  valid   : InputSignal<boolean> = input(true)
  value   : InputSignal<string>  = input("")
  width   : InputSignal<string>  = input("100%")
  height  : InputSignal<string>  = input("1.3em")
  hint    : InputSignal<string>  = input("")

  // Events
  @Output() valueChange : EventEmitter<string> = new EventEmitter<string>()

  // Private properties
  private currentValue : string = ""
  private isFocused : boolean = false



  ngOnInit() {
    if (this.isHintVisible()) {
      this.currentValue = this.getHint()
    } else {
      this.currentValue = this.value()
    }
  }

  keyupEventHandler(value: string) : void {
    this.currentValue = value
    this.valueChange.emit(this.currentValue)
  }

  public focusEventHandler() : void {  
    this.isFocused = true
  }

  public lostFocusEventHandler() : void {
    this.isFocused = false
  }

  public isEnabled() : boolean {
    return this.enabled()
  }

  public isValid() : boolean {
    return this.valid()
  }

  public getCurrentValue() : string {
    return this.currentValue
  }

  public getWidth() : string {
    return this.width()
  }

  public getHeight() : string {
    return this.height()
  }

  public getHint() : string {
    return this.hint()
  }

  public isHintVisible() : boolean {
    const strHint = this.hint()
    const strVal = this.value()

    return (strVal === null || strVal === undefined || strVal === '') && 
          strHint !== undefined && strHint !== null && strHint !== '' && 
          !this.isFocused
  }

}


