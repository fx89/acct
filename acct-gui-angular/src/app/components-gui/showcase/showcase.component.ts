import { Component } from '@angular/core';
import { ColorThemeSelectorComponent } from '../color-theme-selector/color-theme-selector.component';
import { ButtonComponent } from '../button/button.component';
import { InputComponent } from '../input/input.component';
import { SwitchComponent } from '../switch/switch.component';
import { CardComponent } from '../card/card.component';
import { ProgressBarComponent } from '../progress-bar/progress-bar.component';
import { LabelComponent } from '../label/label.component';
import { PanelComponent } from '../panel/panel.component';

@Component({
  selector: 'app-showcase',
  imports: [
    ColorThemeSelectorComponent,
    ButtonComponent,
    InputComponent,
    SwitchComponent,
    CardComponent,
    ProgressBarComponent,
    LabelComponent,
    PanelComponent
  ],
  templateUrl: './showcase.component.html',
  styleUrl: './showcase.component.less'
})
export class ShowcaseComponent {

  inputValue : string = 'testValue'
  switchValue : boolean = true

  public onButtonClick() : void {
    alert('da')
  }

}
