import { Component, input, InputSignal } from '@angular/core';
import {v4 as uuidv4} from 'uuid';

@Component({
  selector: 'app-modal-overlay',
  imports: [],
  templateUrl: './modal-overlay.component.html',
  styleUrl: './modal-overlay.component.less'
})
export class ModalOverlayComponent {

  /**
   * The ID of the component is unique in the page
   */
  public id : string = uuidv4()

  // Properties
  visible : InputSignal<boolean> = input(false)

  public isVisible() : boolean {
    return this.visible()
  }

}
