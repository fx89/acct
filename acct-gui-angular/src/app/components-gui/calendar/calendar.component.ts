import { Component, EventEmitter, input, InputSignal, Output } from '@angular/core';
import {v4 as uuidv4} from 'uuid';
import { SpinboxComponent } from '../spinbox/spinbox.component';

const months : string[] = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December"
]

interface CalendarData {
  monthNumber              : number
  year                     : number
  firstDayOfMonth          : Date
  firstWeekDayOfMonth      : number
  lastDayNumberOfMonth     : number
  lastWeekDayOfMonth       : number
  lastDayNumberOfLastMonth : number
  claendarDays             : CalendarDay[][]
}

interface CalendarDay {
  dayNumberOfMonth : number
  isPreviousMonth  : boolean
  isCurrentMonth   : boolean
  isNextMonth      : boolean
  date             : Date
}

@Component({
  selector: 'app-calendar',
  imports: [
    SpinboxComponent
  ],
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.less'
})
export class CalendarComponent {


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
  @Output() yearChange : EventEmitter<number> = new EventEmitter<number>()
  @Output() monthNumberChange : EventEmitter<number> = new EventEmitter<number>()
  @Output() calendarClicked : EventEmitter<void> = new EventEmitter<void>()

  // Private properties
  currentValue : Date         = this.value()
  data         : CalendarData = this.compileCalendarData(this.currentValue.getFullYear(), this.currentValue.getMonth())
  

  ngOnInit() : void {
    this.currentValue = this.value()
    this.data = this.compileCalendarData(this.currentValue.getFullYear(), this.currentValue.getMonth())
  }

  compileCalendarData(year : number, monthNumber : number) : CalendarData {
    const firstDayOfMonth          : Date   = new Date(year, monthNumber, 1)
    const lastDayOfMonth           : Date   = new Date(year, monthNumber + 1, 0)
    const lastDayOfLastMonth       : Date   = new Date(year, monthNumber, 0)
    const firstWeekDayOfMonth      : number = firstDayOfMonth.getDay()
    const lastDayNumberOfMonth     : number = lastDayOfMonth.getDate()
    const lastDayNumberOfLastMonth : number = lastDayOfLastMonth.getDate()

    const claendarDays : CalendarDay[][] =
      this.compileCalendarDays(
        year,
        monthNumber,
        firstWeekDayOfMonth,
        lastDayNumberOfMonth,
        lastDayNumberOfLastMonth
    )

    return {
      monthNumber              : monthNumber,
      year                     : year,
      firstDayOfMonth          : firstDayOfMonth,
      firstWeekDayOfMonth      : firstWeekDayOfMonth,
      lastDayNumberOfMonth     : lastDayNumberOfMonth,
      lastWeekDayOfMonth       : lastDayOfMonth.getDay(),
      lastDayNumberOfLastMonth : lastDayNumberOfLastMonth,
      claendarDays             : claendarDays
    }
  }

  compileCalendarDays(
    year:number,
    monthNumber:number,
    firstWeekDayOfMonth:number,
    lastDayNumberOfMonth:number,
    lastDayNumberOfLastMonth:number
  ) : CalendarDay[][]
  {
    // Initialize the calendar days array (one day for each calendar slot)
    const days : CalendarDay[][] = []

    // Initialize the day counter as the first day in the calendar
    // It will be incremented as each calendar slot is iterated
    var dayCounter : number = -firstWeekDayOfMonth + 1

    // Add one if the first day of the week is Monday
    if (this.isMondayFirstWeekDay()) {
      dayCounter++
    }

    // If the day counter starts at 1 or 2 (first line in the calender is the first week of the month),
    // then subtract 7 days (to show the last week of the previous month as the first line in the calendar)
    if (dayCounter == 1 || dayCounter == 2) {
      dayCounter -= 7
    }

    // For each row in the calendar days array
    for (let rowIndex = 0 ; rowIndex < 6 ; rowIndex++) {
      // Initialize the row
      days[rowIndex] = []

      // For each column in the row
      for (let colIndex = 0 ; colIndex < 7 ; colIndex++) {
        // Initialize the properties of the calendar day as they would be set
        // if the current day was in the displayed calendar month
        let dayNumberOfMonth : number  = dayCounter
        let isPreviousMonth  : boolean = false
        let isCurrentMonth   : boolean = true
        let isNextMonth      : boolean = false

        // If the current day is in the previous calendar month, set the properties accordingly
        if (dayCounter < 1) {
          dayNumberOfMonth = lastDayNumberOfLastMonth + dayCounter
          isPreviousMonth  = true
          isCurrentMonth   = false
        }

        // If the current day is in the next calendar month, set the properties accordingly
        if (dayCounter > lastDayNumberOfMonth) {
          dayNumberOfMonth = dayCounter - lastDayNumberOfMonth
          isNextMonth      = true
          isCurrentMonth   = false
        }

        // Populate the current slot of the calendar days array with the properties of the current day
        days[rowIndex][colIndex] = {
          dayNumberOfMonth : dayNumberOfMonth,
          isPreviousMonth  : isPreviousMonth,
          isCurrentMonth   : isCurrentMonth,
          isNextMonth      : isNextMonth,
          date             : new Date(year, monthNumber + (isPreviousMonth ? -1 : 0) + (isNextMonth ? 1 : 0), dayNumberOfMonth)
        }

        // Don't forget to increment the day counter
        dayCounter++
      }
    }

    // Return a reference to the now populated calendar days array
    return days
  }

  public monthDisplayFunction(value : number) : string {
    return months[value]
  }

  public onMonthNumberChange(monthNumber : number) : void {
    this.data = this.compileCalendarData(this.data.year, monthNumber)
    this.monthNumberChange.emit(monthNumber)
  }

  public onYearChange(year : number) : void {
    this.data = this.compileCalendarData(year, this.data.monthNumber)
    this.yearChange.emit(year)
  }

  public onDaySlotClick(rowIndex : number, colIndex : number) : void {
    const calendarDay : CalendarDay = this.data.claendarDays[rowIndex][colIndex]

    if (calendarDay.isCurrentMonth) {
      this.currentValue = calendarDay.date
      this.data = this.compileCalendarData(this.currentValue.getFullYear(), this.currentValue.getMonth())
      this.valueChange.emit(this.currentValue)
    }
  }

  public onCalendarClicked() : void {
    this.calendarClicked.emit()
  }

  getMinYear() : number {
    return this.minYear()
  }

  getMaxYear() : number {
    return this.maxYear()
  }

  getYear() : number {
    return this.data.year
  }

  getMonthNumber() : number {
    return this.data.monthNumber
  }

  getDayNumberAtIndex(rowIndex : number, colIndex : number) {
    return this.data.claendarDays[rowIndex][colIndex].dayNumberOfMonth
  }

  isDayInPreviousMonth(rowIndex : number, colIndex : number) : boolean {
    return this.data.claendarDays[rowIndex][colIndex].isPreviousMonth
  }

  isDayInNextMonth(rowIndex : number, colIndex : number) : boolean {
    return this.data.claendarDays[rowIndex][colIndex].isNextMonth
  }

  isDayNotInCurrentMonth(rowIndex : number, colIndex : number) {
    return !this.data.claendarDays[rowIndex][colIndex].isCurrentMonth
  }

  isDaySelected(rowIndex : number, colIndex : number) {
    const selectedDate = this.currentValue

    return (
      this.data.year === selectedDate.getFullYear() &&
      this.data.monthNumber === selectedDate.getMonth() &&
      this.data.claendarDays[rowIndex][colIndex].isCurrentMonth &&
      this.data.claendarDays[rowIndex][colIndex].dayNumberOfMonth === selectedDate.getDate()
    )
  }

}
