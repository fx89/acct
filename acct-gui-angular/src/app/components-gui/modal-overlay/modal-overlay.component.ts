import {AfterViewInit, Component, input, InputSignal, OnDestroy } from '@angular/core';
import {v4 as uuidv4} from 'uuid';
import {getElementOrThrow} from '../../utils-reusalbe/dom-utils';

@Component({
  selector: 'app-modal-overlay',
  imports: [],
  templateUrl: './modal-overlay.component.html',
  styleUrl: './modal-overlay.component.less'
})
export class ModalOverlayComponent implements AfterViewInit, OnDestroy {

  /**
   * The ID of the component is unique in the page
   */
  public id : string = uuidv4()

  // Properties
  visible : InputSignal<boolean> = input(false)

  public isVisible() : boolean {
    return this.visible()
  }

  ngAfterViewInit(): void {
    const element : HTMLElement = getElementOrThrow(this.id, "Modal overlay not found in DOM")
    element.parentElement?.removeChild(element)
    document.body.appendChild(element)
  }

  ngOnDestroy(): void {
    const element : HTMLElement = getElementOrThrow(this.id, "Modal overlay not found in DOM")
    element.parentElement?.removeChild(element)
  }
  

}
