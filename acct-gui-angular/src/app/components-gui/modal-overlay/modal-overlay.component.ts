import { AfterContentChecked, Component, input, InputSignal } from '@angular/core';
import {v4 as uuidv4} from 'uuid';

@Component({
  selector: 'app-modal-overlay',
  imports: [],
  templateUrl: './modal-overlay.component.html',
  styleUrl: './modal-overlay.component.less'
})
export class ModalOverlayComponent implements AfterContentChecked {

  /**
   * The ID of the component is unique in the page
   */
  public id : string = uuidv4()

  // Properties
  visible : InputSignal<boolean> = input(false)

  public isVisible() : boolean {
    return this.visible()
  }

  ngAfterContentChecked(): void {
    const element : HTMLElement | null = document.getElementById(this.id)

    if (element) {
      element.parentElement?.removeChild(element)
      document.body.appendChild(element)
    }
  }

}
