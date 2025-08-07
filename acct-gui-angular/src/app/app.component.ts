import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { ColorThemesService } from './services-reusable/color-themes.service';
import { MenuComponent } from './components-gui/menu/menu.component';
import { MenuItemData } from './components-gui/menu/menu-item-data';
import { ColorThemeSelectorComponent } from './components-gui/color-theme-selector/color-theme-selector.component';
import { BarComponent } from './components-gui/bar/bar.component';
import { LoginFormComponent } from './components-acct/login-form/login-form.component';
import { WorkspaceSelectorService } from './services-acct/workspace-selector.service';
import { IconifiedWorkspace } from './model-acct/workspace';
import { isDefined } from './utils-reusalbe/lang-utils';
import { IconifiedCurrencyProperties } from './model-acct/currency-properties';
import { CatalogService } from './services-acct/catalog.service';

/**
 * Extends the MenuItemData type with ACCT-specific properties
 */
type AcctMenuItemData = MenuItemData & { isWithinWorkspaceContext: boolean }

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

  menuItems                         : AcctMenuItemData[] = []
  menuItemPaths                     : string[] = []
  selectedMenuItem                  : AcctMenuItemData = this.menuItems[0] 
  selectedWorkspace!                : IconifiedWorkspace
  selectedWorkspaceDefaultCurrency! : IconifiedCurrencyProperties

  constructor(
    protected router:Router,
    protected colorThemesService:ColorThemesService,
    protected workspaceSelectorService:WorkspaceSelectorService,
    protected catalogService:CatalogService
  ) {
    this.compileMenuItemsArray()
  }

  ngOnInit(): void {
    this.identifySelectedMenuItemBasedOnActiveRoute()
    this.acquireSelectedWorkspace()
  }

  private compileMenuItemsArray() : void {
    for (let configItem of this.router.config) {
      const menuItem = configItem.data ? configItem.data['menuItem'] : undefined

      if (menuItem) {
        this.menuItems.push({
          text                     : menuItem['text'],
          imageRef                 : menuItem['imageRef'],
          isWithinWorkspaceContext : menuItem['isWithinWorkspaceContext'] ?? false,
          onSelect                 : () => this.router.navigate(['/', configItem.path])
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

  private acquireSelectedWorkspace() : void {
    this.workspaceSelectorService.findSelectedWorkspace().subscribe({
      next: workspace => {
        this.catalogService.findCurrencyByCurrencyUUID(workspace.defaultCurrencyUUID).subscribe({
          next: currency => {
            this.selectedWorkspaceDefaultCurrency = currency
            this.selectedWorkspace = workspace
          },
          error: err => {
            // TODO: Toast
            console.log(err)
          }
        })
      },
      error : err => {
        // TODO: Toast
        console.log(err)
      }
    })
  }

  public getSelectedMenuItemText() : string {
    return this.selectedMenuItem?.text ?? ""
  }

  public getSelectedWorkspace() : IconifiedWorkspace {
    return this.selectedWorkspace
  }

  public isSelectedMenuItemWithinWorkspaceContext() : boolean {
    return this.selectedMenuItem?.isWithinWorkspaceContext ?? false
  }

  public isWorkspaceSelected() : boolean {
    return isDefined(this.selectedWorkspace)
  }

}
