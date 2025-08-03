import { Component, EventEmitter, input, InputSignal, OnInit, Output } from '@angular/core';
import { SpinboxComponent } from '../spinbox/spinbox.component';
import {v4 as uuidv4} from 'uuid';

/**
 * Enumeration of all the possible hours in a day
 */
export type HourOfDay =
  | 0  | 1  | 2  | 3  | 4  | 5
  | 6  | 7  | 8  | 9  | 10 | 11
  | 12 | 13 | 14 | 15 | 16 | 17
  | 18 | 19 | 20 | 21 | 22 | 23

  /**
   * Enumeration of all the possible minutes in an hour
   */
export type MinuteOfHour =
  | 0  | 1  | 2  | 3  | 4  | 5  | 6  | 7  | 8  | 9
  | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18 | 19
  | 20 | 21 | 22 | 23 | 24 | 25 | 26 | 27 | 28 | 29
  | 30 | 31 | 32 | 33 | 34 | 35 | 36 | 37 | 38 | 39
  | 40 | 41 | 42 | 43 | 44 | 45 | 46 | 47 | 48 | 49
  | 50 | 51 | 52 | 53 | 54 | 55 | 56 | 57 | 58 | 59

  /**
   * Enumeration of all the possible seconds in a minute
   */
export type SecondOfMinute =
  | 0  | 1  | 2  | 3  | 4  | 5  | 6  | 7  | 8  | 9
  | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18 | 19
  | 20 | 21 | 22 | 23 | 24 | 25 | 26 | 27 | 28 | 29
  | 30 | 31 | 32 | 33 | 34 | 35 | 36 | 37 | 38 | 39
  | 40 | 41 | 42 | 43 | 44 | 45 | 46 | 47 | 48 | 49
  | 50 | 51 | 52 | 53 | 54 | 55 | 56 | 57 | 58 | 59

/**
 * Defines a time of day, given by an hour, a minute and, optionally, a second.
 */
export class TimeOfDay {

  constructor(
    public hour    :HourOfDay,
    public minute  :MinuteOfHour,
    public second? :SecondOfMinute
  ) {

  }

  /**
   * Returns the current date at the time set within this object. If the second
   * is not specified, then it defaults to 0.
   */
  toDate() : Date {
    const date : Date = new Date()

    date.setHours(this.hour)
    date.setMinutes(this.minute)

    if (this.second) {
      date.setSeconds(this.second)
    } else {
      date.setSeconds(0)
    }

    return date
  }

  toString() : string {
    const hour   : number = this.getHour()
    const minute : number = this.getMinute()

    const strHour   : string = (hour < 10 ? "0" : "") + hour
    const strMinute : string = (minute < 10 ? "0" : "") + minute

    let ret = strHour + ":" + strMinute

    if (this.second) {
      const second    : number = this.getSecond()
      const strSecond : string = (second < 10 ? "0" : "") + second

      ret += ":" + strSecond
    }

    return ret
  }

  /**
   * Returns the stored hour of day as a number
   */
  getHour() : number {
    return this.hour
  }

  /**
   * Returns the stored minute of hour as a number
   */
  getMinute() : number {
    return this.minute
  }

  /**
   * Returns the stored second of minute as a number
   * or zero if the second is not stored
   */
  getSecond() : number {
    return this.second ?? 0
  }

  static atCurrentTime() : TimeOfDay {
    const currentDate = new Date()
    return new TimeOfDay(
      currentDate.getHours() as HourOfDay,
      currentDate.getMinutes() as MinuteOfHour,
      currentDate.getSeconds() as SecondOfMinute
    )
  }
}

@Component({
  selector: 'app-time-chooser',
  imports: [
    SpinboxComponent
  ],
  templateUrl: './time-chooser.component.html',
  styleUrl: './time-chooser.component.less'
})
export class TimeChooserComponent implements OnInit {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()

  // Properties
  displaySeconds : InputSignal<boolean> = input(false)
  value          : InputSignal<TimeOfDay> = input.required()

  // Internal properties
  hourOfDay!      : number
  minuteOfHour!   : number
  secondOfMinute! : number

  // Events
  @Output() valueChange : EventEmitter<TimeOfDay> = new EventEmitter<TimeOfDay>()

  ngOnInit() : void {
    const internalValue = this.value()
    this.hourOfDay      = internalValue?.getHour()   ?? 0
    this.minuteOfHour   = internalValue?.getMinute() ?? 0
    this.secondOfMinute = internalValue?.getSecond() ?? 0
  }

  isdisplayingSeconds() : boolean {
    return this.displaySeconds()
  }

  onHourOfDayChange(hourOfDay:number) : void {
    this.hourOfDay = hourOfDay
    this.onChange()
  }

  onMinuteOfHourChange(minuteOfHour:number) : void {
    this.minuteOfHour = minuteOfHour
    this.onChange()
  }

  onSecondOfMinuteChange(secondOfMinute:number) : void {
    this.secondOfMinute = secondOfMinute
    this.onChange()
  }

  private onChange() : void {
    this.valueChange.emit(
      new TimeOfDay(
        this.hourOfDay      as HourOfDay,
        this.minuteOfHour   as MinuteOfHour,
        this.secondOfMinute as SecondOfMinute
      )
    )
  }

}
