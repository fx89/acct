import { AfterViewInit, Component, EventEmitter, input, InputSignal, Output, Renderer2 } from '@angular/core';
import {v4 as uuidv4} from 'uuid';
import { CalendarComponent } from '../calendar/calendar.component';
import { findScrollPosition, getElementOrThrow, getElementRect, getElementRectOrThrow, Point2D } from '../../utils-reusalbe/dom-utils';

@Component({
  selector: 'app-calendar-button',
  imports: [
    CalendarComponent
  ],
  templateUrl: './calendar-button.component.html',
  styleUrl: './calendar-button.component.less'
})
export class CalendarButtonComponent implements AfterViewInit {

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
  currentValue              : Date    = this.value()
  visible                   : boolean = false
  wasJustMadeVisible        : boolean = false
  calendarButtonId          : string  = this.id + "_calendar_button"
  calendarButtonContainerId : string  = this.id + "_calendar_container"

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

  ngAfterViewInit(): void {
    this.reparentCalendarContainerElement()
  }

  reparentCalendarContainerElement() : void {
    const element : HTMLElement = this.findCalendarContainerElement()
    element.parentElement?.removeChild(element)
    document.body.appendChild(element)
    element.style.zIndex = '99999'
  }

  findCalendarContainerElement() : HTMLElement {
    return getElementOrThrow(this.getCalendarButtonContainerId(), "Calendar container not found")
  }

  showCalendar() : void {
    this.visible = true
    this.adjustCalendarPosition()
    this.scheduleVisibilityMark()
  }

  adjustCalendarPosition() : void {
    // Get a reference to the calendar button RECT
    const rect : DOMRect = getElementRectOrThrow(this.getCalendarButtonId(), "Calendar button not found")

    // Get the window dimensions
    const windowWidth : number = window.innerWidth
    const windowHeight : number = window.innerHeight

    // Get the scroll position of the document/window
    const scrollPosition : Point2D = findScrollPosition()

    // Find out where the calendar should be situated in relation to the button
    const leftOfButton : boolean = (rect.left + 270 > windowWidth)
    const topOfButton : boolean = (rect.top + 250 > windowHeight)

    // Compute the coordinates of the calendar container based on the relative position to the button
    const left : number = scrollPosition.left + (leftOfButton ? rect.right - 270 : rect.left)
    const top : number = scrollPosition.top + (topOfButton ? rect.top - 250 : rect.bottom + 3)

    // Get a reference to the calendar container
    const calendarContainer : HTMLElement = this.findCalendarContainerElement()

    // Set the calendar container position to the coordinates computed above
    calendarContainer.style.left = left + 'px'
    calendarContainer.style.top = top + 'px'
  }

  hideCalendar() : void {
    this.wasJustMadeVisible = false
    this.visible = false

    const calendarContainer : HTMLElement = this.findCalendarContainerElement()
    calendarContainer.style.top = '-2000px'
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

  getCalendarButtonContainerId() : string {
    return this.calendarButtonContainerId
  }

  isVisible() : boolean {
    return this.visible
  }

}
