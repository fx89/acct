import { AfterContentChecked, Component, EventEmitter, input, InputSignal, OnInit, Output } from '@angular/core';
import {v4 as uuidv4} from 'uuid';
import { DialogComponent } from '../dialog/dialog.component';
import { ButtonComponent } from '../button/button.component';
import { MsgboxType } from './msgbox-type';

@Component({
  selector: 'app-msgbox',
  imports: [
    DialogComponent,
    ButtonComponent
  ],
  templateUrl: './msgbox.component.html',
  styleUrl: './msgbox.component.less'
})
export class MsgboxComponent implements AfterContentChecked {

  /**
   * The ID of the component is unique in the page
   */
  public id : string = uuidv4()

  // Properties
  visible : InputSignal<boolean>    = input(false)
  type    : InputSignal<MsgboxType> = input(MsgboxType.OK_ONLY as MsgboxType)
  title   : InputSignal<string>     = input("Dialog")
  text    : InputSignal<string>     = input("Dialog")
  width   : InputSignal<string>     = input("400px")
  height  : InputSignal<string>     = input("180px")

  // Cached properties
  cachedVisible : boolean = false
  cachedType    : MsgboxType = MsgboxType.OK_ONLY

  // Events
  @Output('onAffirmativeResponse') onAffirmativeResponseEventEmitter: EventEmitter<void> = new EventEmitter<void>()
  @Output('onNegativeResponse') onNegativeResponseEventEmitter: EventEmitter<void> = new EventEmitter<void>()
  @Output('visibleChange') visibleChangeEventEmitter: EventEmitter<boolean> = new EventEmitter<boolean>()

  public ngAfterContentChecked() : void {
    this.cachedVisible = this.visible()
    this.cachedType = this.type()
  }

  public onAffirmativeButtonClick() : void {
    this.onAffirmativeResponseEventEmitter.emit()
    this.closeDialog()
  }

  public onNegativeButtonClick() : void {
    this.onNegativeResponseEventEmitter.emit()
    this.closeDialog()
  }

  public onCancelButtonClick() : void {
    this.closeDialog()
  }

  private closeDialog() : void {
    this.cachedVisible = false
    this.visibleChangeEventEmitter.emit(this.cachedVisible)
  }

  public getTitle() : string {
    return this.title()
  }

  public getText() : string {
    return this.text()
  }

  getWidth() : string {
    return this.width()
  }

  getHeight() : string {
    return this.height()
  }

  getType() : MsgboxType {
    return this.type()
  }

  public isVisible() : boolean {
    return this.cachedVisible
  }

  public isOkButton() : boolean {
    return this.cachedType == MsgboxType.OK_ONLY || this.cachedType == MsgboxType.OK_CANCEL
  }

  public isYesButton() : boolean {
    return this.cachedType == MsgboxType.YES_NO || this.cachedType == MsgboxType.YES_NO_CANCEL
  }

  public isCancelButton() : boolean {
    return this.cachedType == MsgboxType.OK_CANCEL || this.cachedType == MsgboxType.YES_NO_CANCEL
  }

  public isNoButton() : boolean {
    return this.cachedType == MsgboxType.YES_NO || this.cachedType == MsgboxType.YES_NO_CANCEL
  }

}
