import { Component, EventEmitter, input, InputSignal, Output } from '@angular/core';
import {v4 as uuidv4} from 'uuid';
import { MenuItemData } from './menu-item-data';
import { getCookie, setCookie } from '../../utils-reusalbe/cookie-utils';

const UNFOLDING_DURATION_MS : number = 200 // TODO: find a way to synchronize this with the CSS transition found in the .less file

@Component({
  selector: 'app-menu',
  imports: [],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.less'
})
export class MenuComponent {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()

  // Properties
  position        : InputSignal<string>  = input("left") // left, right
  width           : InputSignal<string>  = input("200px")
  height          : InputSignal<string>  = input("")
  folding         : InputSignal<boolean> = input(true)
  initiallyFolded : InputSignal<boolean> = input(false)
  showingIcons    : InputSignal<boolean> = input(true)
  iconWidth       : InputSignal<string> = input("40px")
  itemHeight      : InputSignal<string> = input("40px")

  // Menu items
  items        : InputSignal<MenuItemData[]> = input.required()
  selectedItem : InputSignal<MenuItemData | undefined> = input()

  // Events
  @Output() foldedSateChange : EventEmitter<boolean> = new EventEmitter<boolean>()
  @Output() selectedItemChange : EventEmitter<MenuItemData> = new EventEmitter<MenuItemData>()

  // Internal properties
  folded : boolean = this.initiallyFolded()
  showingContent : boolean = true
  internalSelectedItem : MenuItemData | undefined = this.selectedItem()
  foldedCookieName : string | undefined

  ngOnInit() : void {
    // Initialize the selected item
    this.internalSelectedItem = this.selectedItem()

    // Initialize the folded property
    this.foldedCookieName = this.isOnLeft() ? "menu_left_folded" : "menu_right_folded"
    this.folded = getCookie(this.foldedCookieName)?.toLowerCase() === 'true'
  }

  toggleFolding() : void {
    this.folded = !this.folded
    setCookie((this.foldedCookieName ?? ''), (this.folded ? 'true' : 'false'), 900)

    this.showingContent = false
    setTimeout(() => { this.showingContent = true }, UNFOLDING_DURATION_MS)

    this.foldedSateChange.emit(this.folded)
  }

  onMenuItemClick(item : MenuItemData) : void {
    // Set the currently selected item
    this.internalSelectedItem = item

    // Run the deselect function (if defined) for all items except the selected one
    for (let item of this.items()) {
      if (item !== this.internalSelectedItem) {
        if (item.onDeselect) {
          item.onDeselect()
        }
      }
    }

    // Run the select function (if defined) for the selected item
    if (this.internalSelectedItem.onSelect) {
      this.internalSelectedItem.onSelect()
    }

    // Emit the item selected event
    this.selectedItemChange.emit(this.internalSelectedItem)
  }

  getWidth() : string {
    return this.width()
  }

  getFoldingTriggerWidth() : string {
    return "10px"
  }

  getFoldingTriggerHeight() : string {
    return "80px"
  }

  getIconWidth() : string {
    return this.iconWidth()
  }

  getItemHeight() : string {
    return this.itemHeight()
  }

  getItems() : MenuItemData[] {
    return this.items()
  }

  getItemsCount() : number {
    return this.items().length
  }

  getSelectedItem() : MenuItemData | undefined {
    return this.internalSelectedItem
  }

  isFolding() : boolean {
    return this.folding()
  }

  isFolded() : boolean {
    return this.folded
  }

  isShowingIcons() : boolean {
    return this.showingIcons()
  }

  isShowingContent() : boolean {
    return this.showingContent
  }

  isOnLeft() : boolean {
    return this.position() === "left"
  }

  isOnRight() : boolean {
    return this.position() === "right"
  }

}
