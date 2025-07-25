import { Component, EventEmitter, input, InputSignal, OnChanges, Output } from '@angular/core';
import {v4 as uuidv4} from 'uuid';
import { ModalOverlayComponent } from '../modal-overlay/modal-overlay.component';
import { PanelComponent } from '../panel/panel.component';
import { ButtonComponent } from '../button/button.component';

@Component({
  selector: 'app-dialog',
  imports: [
    ModalOverlayComponent,
    PanelComponent,
    ButtonComponent
  ],
  templateUrl: './dialog.component.html',
  styleUrl: './dialog.component.less'
})
export class DialogComponent implements OnChanges {

  /**
   * The ID of the component is unique in the page
   */
  public id : string = uuidv4()

  // Properties
  visible     : InputSignal<boolean> = input(false)
  title       : InputSignal<string>  = input("Dialog")
  width       : InputSignal<string>  = input("400px")
  height      : InputSignal<string>  = input("250px")
  titleStyle  : InputSignal<string>  = input("text-align:center")
  closeButton : InputSignal<boolean> = input(false)

  // Internal properties
  visibileInternal : boolean = false

  // Events
  @Output() visibleChange : EventEmitter<boolean> = new EventEmitter<boolean>

  ngOnChanges(): void {
    this.visibileInternal = this.visible()
  }

  onCloseButtonClick() : void {
    this.visibileInternal = false
    this.visibleChange.emit(this.visibileInternal)
  }

  public getTitle() : string {
    return this.title()
  }

  public getWidth() : string {
    return this.width()
  }

  public getHeight() : string {
    return this.height()
  }

  public getTitleStyle() : string {
    return this.titleStyle()
  }

  public isVisible() : boolean {
    return this.visibileInternal
  }

  public hasCloseButton() : boolean {
    return this.closeButton()
  }

}
