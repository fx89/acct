import { Component, input, InputSignal } from '@angular/core';
import {v4 as uuidv4} from 'uuid';

@Component({
  selector: 'app-picture-frame',
  imports: [],
  templateUrl: './picture-frame.component.html',
  styleUrl: './picture-frame.component.less'
})
export class PictureFrameComponent {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()

  // Properties
  src      : InputSignal<string> = input.required()
  title    : InputSignal<string> = input("Picture title")
  width    : InputSignal<string> = input("200px")
  height   : InputSignal<string> = input("200px")
  titlePos : InputSignal<string> = input("bottom") // tob, bottom, none

  hasTitle() : boolean {
    return this.isTitleOnTop() || this.isTitleOnBottom()
  }

  isTitleOnTop() : boolean {
    return this.titlePos() == "top"
  }

  isTitleOnBottom() : boolean {
    return this.titlePos() == "bottom"
  }

  getPictureSource() : string {
    return this.src()
  }

  getTitle() : string {
    return this.title()
  }

  getWidth() : string {
    return this.width()
  }

  getHeight() : string {
    return this.height()
  }

}
