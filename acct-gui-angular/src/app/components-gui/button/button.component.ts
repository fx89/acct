import { Component, EventEmitter, input, InputSignal, OnInit, Output } from '@angular/core';
import {v4 as uuidv4} from 'uuid';

@Component({
  selector: 'app-button',
  imports: [],
  templateUrl: './button.component.html',
  styleUrl: './button.component.less'
})
export class ButtonComponent implements OnInit {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()

  // Properties
  enabled   : InputSignal<boolean> = input(true)
  value     : InputSignal<string> = input.required<string>()
  textAlign : InputSignal<string> = input("left")
  width     : InputSignal<string> = input("100%")
  height    : InputSignal<string> = input("1.3em")
  icon      : InputSignal<string> = input("")
  iconAlign : InputSignal<string> = input("left")
  color     : InputSignal<string> = input('none')

  // Internal properties
  valueDefined : boolean = false

  // Events
  @Output('onClick') onClickEventEmitter: EventEmitter<void> = new EventEmitter<void>()

  constructor() {
    
  }

  ngOnInit() : void {
    this.valueDefined = this.value() != ""
  }

  public getValue() : string {
    return this.value() ?? ''
  }

  public getTextAlign() : string {
    return this.textAlign()
  }

  public getWidth() : string {
    return this.width()
  }

  public getHeight() : string {
    return this.height()
  }

  public isIconDefined() : boolean {
    return this.icon() !== ""
  }

  public getIcon() : string {
    return this.icon()
  }

  public isIconAlignLeft() : boolean {
    return this.iconAlign() !== "right"
  }

  public isIconAlignRight() : boolean {
    return this.iconAlign() === "right"
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

  public isValueDefined() : boolean {
    return this.valueDefined
  }

  public onClick() : void {
    if (this.isEnabled()) {
      this.onClickEventEmitter.emit();
    }
  }

}
