import { Component, EventEmitter, input, InputSignal, Output, Renderer2 } from '@angular/core';
import {v4 as uuidv4} from 'uuid';
import { CalendarComponent } from '../calendar/calendar.component';
import { getElementRect } from '../../utils-reusalbe/dom-utils';

@Component({
  selector: 'app-calendar-button',
  imports: [
    CalendarComponent
  ],
  templateUrl: './calendar-button.component.html',
  styleUrl: './calendar-button.component.less'
})
export class CalendarButtonComponent {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()

  // Properties
  value                : InputSignal<Date>    = input(new Date())
  minYear              : InputSignal<number>  = input(1900)
  maxYear              : InputSignal<number>  = input(3000)
  isMondayFirstWeekDay : InputSignal<boolean> = input(true)

  // Events
  @Output() valueChange : EventEmitter<Date> = new EventEmitter<Date>()

  // Private properties
  currentValue       : Date    = this.value()
  visible            : boolean = false
  wasJustMadeVisible : boolean = false
  leftOfButton       : boolean = false
  topOfButton        : boolean = false
  calendarButtonId   : string  = this.id + "_calendar_button_container"

  // Injectables
  renderer : Renderer2

  constructor(renderer : Renderer2) {
    this.renderer = renderer
  }

  ngOnInit() : void {
    this.currentValue = this.value()

    this.renderer.listen('window', 'click', (e : Event) => { 
      if (this.wasJustMadeVisible) {
        this.hideCalendar()
      }
    })
  }

  showCalendar() : void {
    this.adjustCalendarPosition()
    this.visible = true
    this.scheduleVisibilityMark()
  }

  adjustCalendarPosition() : void {
    const rect : DOMRect | undefined = getElementRect(this.getCalendarButtonId())
    const windowWidth : number = window.innerWidth
    const windowHeight : number = window.innerHeight

    if (rect) {
      if (rect.left + 270 > windowWidth) {
        this.leftOfButton = true
      } else {
        this.leftOfButton = false
      }

      if (rect.top + 270 > windowHeight) {
        this.topOfButton = true
      } else {
        this.topOfButton = false
      }
    }
  }

  hideCalendar() : void {
    this.wasJustMadeVisible = false
    this.visible = false
  }

  scheduleVisibilityMark() : void {
    setTimeout(() => {
      this.wasJustMadeVisible = true
    }, 100)
  }

  onCalendarButtonClick() : void {
    if (this.visible) {
      this.hideCalendar()
    } else {
      this.showCalendar()
    }
  }

  onCalendarValueChange(value : Date) {
    this.currentValue = value
    this.hideCalendar()
    this.valueChange.emit(this.currentValue)
  }

  onCalendarClicked() : void {
    this.wasJustMadeVisible = false
    this.scheduleVisibilityMark()
  }

  getCalendarButtonId() : string {
    return this.calendarButtonId
  }

  isVisible() : boolean {
    return this.visible
  }

  isLeftOfButton() : boolean {
    return this.visible && this.leftOfButton
  }

  isRightOfButton() : boolean {
    return this.visible && !this.leftOfButton
  }

  isTopOfButton() : boolean {
    return this.visible && this.topOfButton
  }

  isBottomOfButton() : boolean {
    return this.visible && !this.topOfButton
  }

}
