import { Component, input, InputSignal } from '@angular/core';
import {v4 as uuidv4} from 'uuid';

@Component({
  selector: 'app-panel',
  imports: [],
  templateUrl: './panel.component.html',
  styleUrl: './panel.component.less'
})
export class PanelComponent {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()

  width      : InputSignal<string> = input("500px")
  height     : InputSignal<string> = input("400px")
  title      : InputSignal<string> = input("")
  titleStyle : InputSignal<string> = input("")

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

}
