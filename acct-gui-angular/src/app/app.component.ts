import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { ColorThemesService } from './services-reusable/color-themes.service';
import { MenuComponent } from './components-gui/menu/menu.component';
import { MenuItemData } from './components-gui/menu/menu-item-data';
import { ColorThemeSelectorComponent } from './components-gui/color-theme-selector/color-theme-selector.component';
import { BarComponent } from './components-gui/bar/bar.component';
import { LoginFormComponent } from './components-acct/login-form/login-form.component';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    MenuComponent,
    ColorThemeSelectorComponent,
    BarComponent,
    LoginFormComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.less'
})
export class AppComponent implements OnInit {
  title = 'acct-gui-angular'

  menuItems        : MenuItemData[] = []
  menuItemPaths    : string[] = []
  selectedMenuItem : MenuItemData = this.menuItems[0] 

  constructor(protected router:Router, protected colorThemesService:ColorThemesService) {
    this.compileMenuItemsArray()
  }

  ngOnInit(): void {
    this.identifySelectedMenuItemBasedOnActiveRoute()
  }

  private compileMenuItemsArray() : void {
    for (let configItem of this.router.config) {
      const menuItem = configItem.data ? configItem.data['menuItem'] : undefined

      if (menuItem) {
        this.menuItems.push({
          text: menuItem['text'],
          imageRef: menuItem['imageRef'],
          onSelect: () => this.router.navigate(['/', configItem.path])
        })

        this.menuItemPaths.push("/" + (configItem.path ?? ""))
      }
    }
  }

  private identifySelectedMenuItemBasedOnActiveRoute() {
    for (let i = 0 ; i < this.menuItems.length ; i++) {
      if (document.location.pathname.startsWith(this.menuItemPaths[i])) {1
        this.selectedMenuItem = this.menuItems[i]
        break
      }
    }
  }

  public getSelectedMenuItemText() : string {
    return this.selectedMenuItem?.text ?? ""
  }

}
