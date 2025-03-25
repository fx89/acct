import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { SidebarComponent } from './components-gui/sidebar/sidebar.component';
import { ColorThemesService } from './services-reusable/color-themes.service';
import { ColorThemeSelectorComponent } from './components-gui/color-theme-selector/color-theme-selector.component';
import { ButtonComponent } from './components-gui/button/button.component';
import { InputComponent } from './components-gui/input/input.component';
import { SwitchComponent } from './components-gui/switch/switch.component';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    SidebarComponent,
    ColorThemeSelectorComponent,
    ButtonComponent,
    InputComponent,
    SwitchComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.less'
})
export class AppComponent {
  title = 'acct-gui-angular'

  inputValue : string = 'testValue'
  switchValue : boolean = true

  constructor(protected router:Router, protected colorThemesService:ColorThemesService) {
    
  }

  public onButtonClick() : void {
    alert('da')
  }

}
