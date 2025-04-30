import { Component, EventEmitter, input, InputSignal, Output } from '@angular/core';
import {v4 as uuidv4} from 'uuid';
import { TabData } from './TabData';

@Component({
  selector: 'app-tabs',
  imports: [],
  templateUrl: './tabs.component.html',
  styleUrl: './tabs.component.less'
})
export class TabsComponent {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()

  // Properties
  width       : InputSignal<string> = input("350px")
  height      : InputSignal<string> = input("200px")
  position    : InputSignal<string> = input("top") // top, right, bottom, left
  tabMinWidth : InputSignal<string> = input("80px")
  tabMaxWidth : InputSignal<string> = input("100px")
  imageWidth  : InputSignal<string> = input("20px")
  imageHeight : InputSignal<string> = input("20px")

  // Tabs
  tabs        : InputSignal<TabData[]> = input.required()
  selectedTab : InputSignal<TabData | undefined> = input()

  // Events
  @Output() selectedTabChange : EventEmitter<TabData> = new EventEmitter<TabData>()

  // Internal properties
  internalSelectedTab : TabData | undefined
  


  ngOnInit() {
    this.internalSelectedTab = this.selectedTab()
  }

  onTabClick(tab : TabData) : void {
    if (tab) {
      // Set the currently selected item
      this.internalSelectedTab = tab

      // Run the deselect function (if defined) for all items except the selected one
      for (let tab of this.tabs()) {
        if (tab !== this.internalSelectedTab) {
          if (tab.onDeselect) {
            tab.onDeselect()
          }
        }
      }

      // Run the select function (if defined) for the selected item
      if (this.internalSelectedTab.onSelect) {
        this.internalSelectedTab.onSelect()
      }

      // Emit the item selected event
      this.selectedTabChange.emit(this.internalSelectedTab)
    }
  }

  getTabs() : TabData[] {
    return this.tabs()
  }

  getSelectedTab() : TabData | undefined {
    return this.internalSelectedTab
  }

  getWidth() : string {
    return this.width()
  }

  getHeight() : string {
    return this.height()
  }

  getTabMinWidth() : string {
    return this.tabMinWidth()
  }

  getTabMaxWidth() : string {
    return this.tabMaxWidth()
  }

  getImageWidth() : string {
    return this.imageWidth()
  }

  getImageHeight() : string {
    return this.imageHeight()
  }

  isTop() : boolean {
    return this.position() === "top"
  }

  isRight() : boolean {
    return this.position() === "right"
  }

  isBottom() : boolean {
    return this.position() === "bottom"
  }

  isLeft() : boolean {
    return this.position() === "left"
  }

  isHorizontal() : boolean {
    return this.isTop() || this.isBottom()
  }

  isVertical() : boolean {
    return this.isLeft() || this.isRight()
  }
}
