import { Component } from '@angular/core';
import { ColorThemeSelectorComponent } from '../color-theme-selector/color-theme-selector.component';
import { ButtonComponent } from '../button/button.component';
import { InputComponent } from '../input/input.component';
import { SwitchComponent } from '../switch/switch.component';
import { CardComponent } from '../card/card.component';
import { ProgressBarComponent } from '../progress-bar/progress-bar.component';
import { LabelComponent } from '../label/label.component';
import { PanelComponent } from '../panel/panel.component';
import { CardsListComponent } from '../cards-list/cards-list.component';
import { CardData } from '../cards-list/card-data';
import { SelectComponent } from '../select/select.component';
import { BarComponent } from '../bar/bar.component';
import { MenuComponent } from '../menu/menu.component';
import { MenuItemData } from '../menu/menu-item-data';
import { TabsComponent } from '../tabs/tabs.component';
import { TabData } from '../tabs/TabData';
import { CalendarComponent } from '../calendar/calendar.component';
import { CalendarButtonComponent } from '../calendar-button/calendar-button.component';
import { TableColumnDirective, TableComponent } from '../table/table.component';
import { TableColumnSortDirection, TableSortEvent } from '../table/table-sort-event';
import { ModalOverlayComponent } from '../modal-overlay/modal-overlay.component';
import { DialogComponent } from '../dialog/dialog.component';
import { MsgboxComponent } from '../msgbox/msgbox.component';
import { MsgboxType } from '../msgbox/msgbox-type';
import { PictureFrameComponent } from '../picture-frame/picture-frame.component';
import { ScrollDirection, ScrollEvent } from '../directives/scrollable-content.directive';

type ExtendedCardData = CardData & { additionalData : string }

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
    PanelComponent,
    CardsListComponent,
    SelectComponent,
    BarComponent,
    MenuComponent,
    TabsComponent,
    CalendarComponent,
    CalendarButtonComponent,
    TableComponent,
    TableColumnDirective,
    ModalOverlayComponent,
    DialogComponent,
    MsgboxComponent,
    PictureFrameComponent
  ],
  templateUrl: './showcase.component.html',
  styleUrl: './showcase.component.less'
})
export class ShowcaseComponent {

  inputValue : string = 'testValue'
  switchValue : boolean = true
  selectedCard : CardData | undefined
  selectedOption : CardData | undefined
  selectSelectedOption : ExtendedCardData | undefined
  selectedMenuItem : MenuItemData | undefined
  selectedTab : TabData | undefined
  selectedDate : Date = new Date()
  selectedDate2 : Date = new Date()

  modalOverlayVisible : boolean = false
  testDialogVisible : boolean = false
  messageBoxVisible : boolean = false
  messageBoxType : MsgboxType = MsgboxType.YES_NO_CANCEL

  cardsListData : CardData[] = [
    {
      title: "Clickable card",
      text: "This card says hello when clicked",
      imageRef: "",
      onClick: function() { alert('Hello') }
    },
    {
      title: "Not clickable card",
      text: "This card is not clickable",
      imageRef: ""
    },
    {
      title: "Another clickable card",
      text: "This card says hello world when clicked",
      imageRef: "",
      onClick: () => alert('Hello world')
    }
  ]

  selectOptions : ExtendedCardData[] = [
    {
      title: "Select option 1",
      text: "This is the first select option",
      imageRef: "pic.png",
      onClick: undefined,
      additionalData: "Option a"
    },
    {
      title: "Select option 2",
      text: "This is the second select option",
      imageRef: "favicon.ico",
      additionalData: "Option b"
    },
    {
      title: "Select option 3",
      text: "This is the third select option",
      imageRef: "",
      additionalData: "Option c"
    }
  ]

  menuItems : MenuItemData[] = [
    {
      text: "No option",
      imageRef: "pic.png",
      onSelect: undefined,
      onDeselect: undefined
    },
    {
      text: "Angular icon",
      imageRef: "favicon.ico",
      onSelect: undefined,
      onDeselect: undefined
    },
    {
      text: "Dark icon",
      imageRef: "color-schemes/dark.png",
      onSelect: undefined,
      onDeselect: undefined
    },
    {
      text: "Bright icon",
      imageRef: "color-schemes/bright.png",
      onSelect: undefined,
      onDeselect: undefined
    },
    {
      text: "Still no option",
      imageRef: "pic.png",
      onSelect: undefined,
      onDeselect: undefined
    }
  ]

  tabs : TabData[] = [
    {
      text: "Tab Oneeeee !!!",
      imageRef: "pic.png",
      onSelect: undefined,
      onDeselect: undefined
    },
    {
      text: "Tab 2",
      imageRef: "pic.png",
      onSelect: undefined,
      onDeselect: undefined
    },
    {
      text: "Tab 3",
      imageRef: "pic.png",
      onSelect: undefined,
      onDeselect: undefined
    }
  ]

  tableData : any[] = [
    {
      propertyA: "A0",
      propertyB: "B0",
      propertyC: "C0"
    },
    {
      propertyA: "A1",
      propertyB: "B1",
      propertyC: "C1"
    },
    {
      propertyA: "A2",
      propertyB: "B2",
      propertyC: "C2"
    }
  ]

  selectedTableRow : any = { propertyA: "nothing" }

  scrollableTableData : any[] = [
    { rowNumber: "00", itemName: "Item 00", itemValue: Math.random() * 100 },
    { rowNumber: "01", itemName: "Item 01", itemValue: Math.random() * 100 },
    { rowNumber: "02", itemName: "Item 02", itemValue: Math.random() * 100 },
    { rowNumber: "03", itemName: "Item 03", itemValue: Math.random() * 100 },
    { rowNumber: "04", itemName: "Item 04", itemValue: Math.random() * 100 },
    { rowNumber: "05", itemName: "Item 05", itemValue: Math.random() * 100 },
    { rowNumber: "06", itemName: "Item 06", itemValue: Math.random() * 100 },
    { rowNumber: "07", itemName: "Item 07", itemValue: Math.random() * 100 },
    { rowNumber: "08", itemName: "Item 08", itemValue: Math.random() * 100 },
    { rowNumber: "09", itemName: "Item 09", itemValue: Math.random() * 100 },
    { rowNumber: "10", itemName: "Item 10", itemValue: Math.random() * 100 },
    { rowNumber: "11", itemName: "Item 11", itemValue: Math.random() * 100 },
    { rowNumber: "12", itemName: "Item 12", itemValue: Math.random() * 100 },
    { rowNumber: "13", itemName: "Item 13", itemValue: Math.random() * 100 },
    { rowNumber: "14", itemName: "Item 14", itemValue: Math.random() * 100 },
    { rowNumber: "15", itemName: "Item 15", itemValue: Math.random() * 100 },
    { rowNumber: "16", itemName: "Item 16", itemValue: Math.random() * 100 }
  ]

  scrollableTableSelectedRow : any = this.scrollableTableData[0]

  scrollableTableNumberFormat : Intl.NumberFormat = new Intl.NumberFormat('en-us', {minimumFractionDigits: 2})

  scrollableTableScrollDirection : string = "none"
  scrollableTableScrollPct       : number = 0

  scrollablePanelScrollDirection : string = "none"
  scrollablePanelScrollPct       : number = 0


  public ngOnInit() : void {
    this.selectedOption = this.cardsListData[0]
    this.selectSelectedOption = this.selectOptions[0]
    this.selectedMenuItem = this.menuItems[2]
    this.selectedTab = this.tabs[0]
  }

  public onCardListScroll(scrollEvent:ScrollEvent) : void {
    console.log(scrollEvent)
  }

  public onScrollablePanelScroll(scrollEvent:ScrollEvent) : void {
    this.scrollablePanelScrollDirection = scrollEvent.direction
    this.scrollablePanelScrollPct = scrollEvent.sliderPosPct
  }

  public onScrollableTableScroll(tableScrollEvent:ScrollEvent) : void {
    if (tableScrollEvent.direction == ScrollDirection.DOWN) {
      this.scrollableTableScrollDirection = "DOWN"
    }

    if (tableScrollEvent.direction == ScrollDirection.UP) {
      this.scrollableTableScrollDirection = "UP"
    }

    this.scrollableTableScrollPct = tableScrollEvent.sliderPosPct
  }

  /**
   * This sucks, but this is not production code, so who cares?
   */
  public onScrollableTableSort(tableSortEvent:TableSortEvent) : void {
    tableSortEvent.columnSorts.forEach(columnSort => {
      this.scrollableTableData = this.scrollableTableData.sort((rec1, rec2) => {
        if (columnSort.columnNumber == 0) {
          if (columnSort.sortDirection == TableColumnSortDirection.ASCENDING) {
            return rec1.rowNumber - rec2.rowNumber
          } else {
            return rec2.rowNumber - rec1.rowNumber
          }
        }

        if (columnSort.columnNumber == 1) {
          if (columnSort.sortDirection == TableColumnSortDirection.ASCENDING) {
            return rec1.itemName.localeCompare(rec2.itemName)
          } else {
            return rec2.itemName.localeCompare(rec1.itemName)
          }
        }

        if (columnSort.columnNumber == 2) {
          if (columnSort.sortDirection == TableColumnSortDirection.ASCENDING) {
            return rec1.itemValue - rec2.itemValue
          } else {
            return rec2.itemValue - rec1.itemValue
          }
        }

        return 0
      })
    })
  }

  public getCardsListData() : CardData[] {
    return this.cardsListData
  }

  public onButtonClick() : void {
    alert('da')
  }

  public onCardSelected(card : CardData) : void {
    this.selectedCard = card
  }

  public getSelectedCardTitle() : string {
    return <string> (this.selectedCard?.title)
  }

  public getSelectSelectedOptionValue() : string {
    return this.selectSelectedOption?.additionalData || "Nothing selected"
  }

  public selectSelectedOptionChange(event : any) : void {
    this.selectSelectedOption = <ExtendedCardData> event
  }

  public getMenuItems() : MenuItemData[] {
    return this.menuItems
  }

  public getTabs() : TabData[] {
    return this.tabs
  }

  public getScrollablePanelScrollDirection() : string {
    return this.scrollablePanelScrollDirection
  }

  public getScrollablePanelScrollPct() : string {
    return this.scrollablePanelScrollPct.toString()
  }

  public onOpenModalOverlayButtonClick() : void {
    this.modalOverlayVisible = true
  }

  public onCloseModalOverlayButtonClick() : void {
    this.modalOverlayVisible = false
  }

  public onOpenTestDialogButtonClick() : void {
    this.testDialogVisible = true
  }

  public onCloseTestDialogButtonClick() : void {
    this.testDialogVisible = false
  }

  public onOpenMessageBoxButtonClick() : void {
    this.messageBoxVisible = true
  }

  public onMessageBoxAffirmativeResponse() : void  {
    alert("Affirmative response clicked")
  }

  public onMessageBoxNegativeResponse() : void {
    alert("Negative response clicked")
  }

}
