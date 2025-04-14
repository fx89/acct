import { Component, input, InputSignal } from '@angular/core';
import {v4 as uuidv4} from 'uuid';

@Component({
  selector: 'app-bar',
  imports: [],
  templateUrl: './bar.component.html',
  styleUrl: './bar.component.less'
})
export class BarComponent {

   /**
   * The ID of the component is unique in the page
   */
   public id = uuidv4()

   // Properties
   height     : InputSignal<string>  = input("60px")
   floating   : InputSignal<boolean> = input(false)
   barType    : InputSignal<string>  = input("top")
   autoHiding : InputSignal<boolean> = input(false)


   public getHeight() : string {
    return this.height()
   }

   public getSeparatorHeight() : string {
    return '3px'
   }

   public getStickOutHeight() : string {
    return '10px'
   }

   public isFloating() : boolean {
    return this.floating()
   }

   public isAutoHiding() : boolean {
    return this.autoHiding()
   }

   public isTopBarType() : boolean {
    return this.barType() === "top"
   }

   public isBottomBarType() : boolean {
    return this.barType() === "bottom"
   }

}
