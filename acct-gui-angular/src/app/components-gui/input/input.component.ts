import { Component, input, InputSignal, Output, EventEmitter, OnChanges, AfterViewInit } from '@angular/core'; 
import {v4 as uuidv4} from 'uuid';
import { getElementOrThrow, isSpecialKey } from '../../utils-reusalbe/dom-utils';
import { Observable } from 'rxjs';
import { newObservable } from '../../utils-reusalbe/rxjs-utils';
import { DelayedTrigger } from '../../utils-reusalbe/delayed-trigger';

/**
 * Mapper for the autocomplete feature.
 * 
 * @param currentInputValue the current value of the input component
 *
 * @returns the best possible match (begins with the current input value and 
 *          continues with the rest of the matching string) or an empty string
 *          if there is no match
 */
export type AutocompleteMapper = (currentInputValue:string) => Observable<string>

@Component({
  selector: 'app-input',
  imports: [],
  templateUrl: './input.component.html',
  styleUrl: './input.component.less',
  standalone: true
})
export class InputComponent implements OnChanges, AfterViewInit {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()
  public inputElementId = this.id + "_input"

  // Properties
  enabled             : InputSignal<boolean> = input(true)
  valid               : InputSignal<boolean> = input(true)
  masked              : InputSignal<boolean> = input(false)
  value               : InputSignal<string>  = input("")
  width               : InputSignal<string>  = input("100%")
  height              : InputSignal<string>  = input("1.3em")
  hint                : InputSignal<string>  = input("")
  autocompleteEnabled : InputSignal<boolean> = input(false)
  autocompleteMapper  : InputSignal<AutocompleteMapper> = input((value:string) => newObservable(""))

  // Events
  @Output() valueChange    : EventEmitter<string> = new EventEmitter<string>()
  @Output() onValueChanged : EventEmitter<string> = new EventEmitter<string>()
  @Output() submit         : EventEmitter<string> = new EventEmitter<string>()

  // Private properties
  public  currentValue : string = ""
  private isFocused : boolean = false

  // Cached autocomplete mapper
  private cachedAutocompleteMapper! : AutocompleteMapper

  // Cached input element
  private cachedInputElement! : HTMLInputElement

  // Trigger for the autocomplete function on keyUp
  // Prevents running the autocomplete function multiple times if multiple keys are pressed in very quick succession
  private autocompleteDelayedTrigger : DelayedTrigger = new DelayedTrigger(
    200, // At least 200ms need to pass since the last keyUp event to trigger the autocomplete function
    () => this.applyAutcompleteMapper()
  )


  ngOnChanges() {
   this.currentValue = this.value()
  }

  ngAfterViewInit() {
    this.cachedInputElement = getElementOrThrow(this.inputElementId, "Input element not found in DOM") as HTMLInputElement
    this.cachedAutocompleteMapper = this.autocompleteMapper()
  }

  keyupEventHandler(event:KeyboardEvent, value:string) : void {
    // Set the current value and emit the events
    this.currentValue = this.cachedInputElement.value
    this.valueChange.emit(this.currentValue)
    this.onValueChanged.emit(this.currentValue)

    // Emit the submit event, in case the Enter key was pushed
    // This voids any further processing
    if (event.key == "Enter") {
      this.submit.emit(this.currentValue)
      return
    }

    // If the autocomplete feature is enabled, then apply it
    if (this.autocompleteEnabled()) {
      if (!isSpecialKey(event)) {
        this.autocompleteDelayedTrigger.fire()
      }
    }
  }

  private applyAutcompleteMapper() : void {
    // Get the autocomplete suggestion
    this.cachedAutocompleteMapper(this.currentValue).subscribe({
      next: (autocompleteSuggestion:string) => {
        // If the autocomplete suggestion is not empty, then put the autocomplete
        if (autocompleteSuggestion.length > 0) {
          // Append the text to the input
          setTimeout(
            () => {
              // If the autocomplete suggestion is properly returned
              if (autocompleteSuggestion) {
                // If the autocomplete suggestion is not too old
                if (autocompleteSuggestion.startsWith(this.cachedInputElement.value)) {
                  // Get the old value length (needed for setting the selection range)
                  const oldValueLength : number = this.cachedInputElement.value.length

                  // Override the value
                  this.cachedInputElement.value = autocompleteSuggestion

                  // Set the selection range
                  this.cachedInputElement.setSelectionRange(oldValueLength, autocompleteSuggestion.length)
                }
              }
            },
            50
          )
        }
      }
    })
  }

  public focusEventHandler() : void {
    this.isFocused = true
  }

  public lostFocusEventHandler() : void {
    this.isFocused = false

    this.currentValue = this.cachedInputElement.value
    this.valueChange.emit(this.currentValue)
    this.onValueChanged.emit(this.currentValue)
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

  public getInputType() : string {
    return this.masked() ? "password" : "text"
  }

  public isHintVisible() : boolean {
    const strHint = this.hint()
    const strVal = this.value()

    return (strVal === null || strVal === undefined || strVal === '') && 
          strHint !== undefined && strHint !== null && strHint !== '' && 
          !this.isFocused
  }

}


