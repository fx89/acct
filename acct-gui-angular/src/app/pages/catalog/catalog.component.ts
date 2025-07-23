import { Component } from '@angular/core';
import { TabsComponent } from '../../components-gui/tabs/tabs.component';
import { TabData } from '../../components-gui/tabs/TabData';
import { BanksComponent } from './banks/banks.component';
import { CurrenciesComponent } from './currencies/currencies.component';
import { IncomeOrExpenseItemsComponent } from './income-or-expense-items/income-or-expense-items.component';
import { IconsComponent } from './icons/icons.component';
import { LabelComponent } from '../../components-gui/label/label.component';

@Component({
  selector: 'app-catalog',
  imports: [
    TabsComponent,
    LabelComponent,
    BanksComponent,
    CurrenciesComponent,
    IncomeOrExpenseItemsComponent,
    IconsComponent
  ],
  templateUrl: './catalog.component.html',
  styleUrl: './catalog.component.less'
})
export class CatalogComponent {

  /**
   * The tabs available on the page
   */
  tabs : TabData[] = [
    {
      text: "Banks",
      imageRef: "button-icons/banks.png",
      onSelect: undefined,
      onDeselect: undefined
    },
    {
      text: "Currencies",
      imageRef: "button-icons/currencies.png",
      onSelect: undefined,
      onDeselect: undefined
    },
    {
      text: "Income or Expense Items",
      imageRef: "button-icons/income-or-expense-items.png",
      onSelect: undefined,
      onDeselect: undefined
    },
    {
      text: "Icons",
      imageRef: "button-icons/icons.png",
      onSelect: undefined,
      onDeselect: undefined
    }
  ]

  /**
   * The tab that's selected (if any)
   */
  selectedTab : TabData | undefined


}
