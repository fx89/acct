import { Component, EventEmitter, input, InputSignal, Output } from '@angular/core';
import {v4 as uuidv4} from 'uuid';
import { ScrollableContentDirective, ScrollEvent } from '../directives/scrollable-content.directive';

@Component({
  selector: 'app-panel',
  imports: [
    ScrollableContentDirective
  ],
  templateUrl: './panel.component.html',
  styleUrl: './panel.component.less'
})
export class PanelComponent {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()

  panelContentElementId : string = this.id + "_content"

  width      : InputSignal<string> = input("500px")
  height     : InputSignal<string> = input("400px")
  title      : InputSignal<string> = input("")
  titleStyle : InputSignal<string> = input("")

  /**
   * The scroll event that is triggered every time the scroll position has changed significantly
   */
  @Output() scroll : EventEmitter<ScrollEvent> = new EventEmitter<ScrollEvent>

  getWidth() : string {
    return this.width()
  }

  getHeight() : string {
    return this.height()
  }

  getTitle() : string {
    return this.title()
  }

  getTitleStyle() : string {
    return this.titleStyle()
  }

  isTitleVisible() : boolean {
    const titleStr : string = this.title()
    return titleStr !== undefined && titleStr !== null && titleStr != ''
  }

  onScroll(event:ScrollEvent) : void {
    this.scroll.emit(event)
  }

}
