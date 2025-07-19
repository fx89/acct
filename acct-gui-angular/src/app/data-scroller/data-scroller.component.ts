import { AfterContentInit, Component, ContentChildren, Directive, EventEmitter, input, InputSignal, OnChanges, OnInit, Output, QueryList, SimpleChanges, TemplateRef } from '@angular/core';
import { CardData } from '../components-gui/cards-list/card-data';
import { PanelComponent } from '../components-gui/panel/panel.component';
import { v4 as uuidv4 } from 'uuid';
import { ScrollDirection, ScrollEvent } from '../components-gui/directives/scrollable-content.directive';
import { Observable } from 'rxjs';
import { SelectComponent } from '../components-gui/select/select.component';
import { InputComponent } from '../components-gui/input/input.component';
import { CommonModule } from '@angular/common';
import { removeArrayElement } from '../utils-reusalbe/array-utils';

/**
 * Defines the properties an UI element that is displayed as a dropdown combobox and
 * acts as a filter for the data set
 */
export interface DataScrollerFilter {
  /**
   * The name of the filter, as shown on the filters bar and reported by the event
   */
  filterName : string

  /**
   * Array of cards representing the possible values for the filter
   */
  possibleValueCards : CardData[]
}

/**
 * Defies the selected value for a given filter
 */
export interface DataScrollerFilterSelectedValue {
  filterName : string

  selectedCardData : CardData
}

/**
 * Defines the elements required for fetching a page of data
 */
export interface DataScrollerPageRequest {
  /**
   * The page number
   */
  pageNumber : number

  /**
   * The number of elements on the page
   */
  pageSize : number

  /**
   * An array of the selected filter values (empty if no filters are selected)
   */
  appliedFiltersValues : DataScrollerFilterSelectedValue[]

  /**
   * The value of the searchbox (empty if no value)
   */
  searchBoxValue : string
}

/**
 * Specifies the page information that the data scroller expects to get from the
 * page request callback. Contains both data and meta-data.
 */
export interface DataScrollerPageResponse<T> {
  /**
   * The size of the entire data set, comprised of all the elements on every page.
   * This number may be different 
   */
  dataSetSize : number

  /**
   * The actual data on the page.
   */
  pageData : T[]
}

/**
 * Adds the selectedCardData to the DataScrollerFilter. The selectedCardData is not
 * available when users of the data-scroller component define the filter, but it is
 * needed to achieve the functionality of the data-scroller's filter bar.
 */
interface DataScrollerFilterWithValue extends DataScrollerFilter {
  /**
   * The value that has been selected by the user via the select control
   */
  selectedCardData : CardData | undefined
}

/**
 * Adds meta-data to consumer-defined items
 */
interface DataScrollerItemContainer {
  /**
   * Reference to the consumer-defined item
   */
  item : any
 
  /**
   * Switch to tell the component if the item is selected or not
   */
  selected : boolean
}

/**
 * If the user scrolls down to more than x% ofthge total scrollable content
 * height, then triger a new page load.
 */
const PAGE_REQUEST_TRIGGER_THRESHOLD_PCT : number = 0.9

/**
 * The minimum length requirement for the search box value to trigger a page request
 */
const SEARCH_BOX_VALUE_MIN_LENGTH : number = 3

@Directive({ selector: '[dataScrollerItem]'})
export class DataScrollerItemDirective {
  constructor(public templateRef: TemplateRef<any>) { }
}

@Component({
  selector: 'app-data-scroller',
  imports: [
    CommonModule,
    PanelComponent,
    SelectComponent,
    InputComponent
  ],
  templateUrl: './data-scroller.component.html',
  styleUrl: './data-scroller.component.less'
})
export class DataScrollerComponent implements OnInit, OnChanges, AfterContentInit {

  /**
   * The ID of the component is unique in the page
   */
  public id : string = uuidv4()

  /**
   * The ID of the filter bar
   */
  public filterBarId : string = this.id + "_filterBar"

  // Properties
  width            : InputSignal<string>  = input("300px")
  height           : InputSignal<string>  = input("400px")
  title            : InputSignal<string>  = input("")
  pageSize         : InputSignal<number>  = input(30)
  searchBoxHint    : InputSignal<string> = input("")
  allowSelect      : InputSignal<boolean> = input(true)
  allowMultiSelect : InputSignal<boolean> = input(false)
  
  /**
   * The array of filters to be applied to the data scroller (leave empty for none)
   */
  filters : InputSignal<DataScrollerFilter[]> = input(<DataScrollerFilter[]>[])

  /**
   * The page request callback is called whenever the data scroller needs a new page.
   */
  pageRequestCallback : InputSignal<(pageRequest:DataScrollerPageRequest) => Observable<DataScrollerPageResponse<any>>> = input.required()

  /**
   * The element unique key function extracts a key uniquely and consistently identifies
   * an element in the data set. This helps the data scroller ascertain if an element
   * contained in a given page has already been added to the data set or not. This is
   * required for cases when the data set is modified in between scrolls.
   */
  elementUniqueKeyFunction : InputSignal<(element:any) => any> = input.required()

  // Events
  @Output() selectionChange : EventEmitter<any[]> = new EventEmitter<any[]>

  // The content template
  @ContentChildren(DataScrollerItemDirective)
  private itemDirectives! : QueryList<DataScrollerItemDirective>

  // Internal properties
  searchBarVisible : boolean = false
  searchBoxVisible : boolean = false
  searchBoxHintStr : string = ""
  filtersArray     : DataScrollerFilterWithValue[] = []
  searchBoxValue   : string = ""
  itemDirective!   : TemplateRef<any>
  selectable!      : boolean
  multiSelectable! : boolean


  /**
   * Consumer-defined function to extract the unique key of consumer-defined data element,
   * to help in identifying elements that have been loaded twice
   */
  elementUniqueKeyFunc : (element:any) => any = (e) => ""

  /**
   * The data store
   */
  data : DataScrollerItemContainer[] = []

  /**
   * The last loaded page information
   */
  lastLoadedPageNumber : number = -1

  /**
   * This array contains any items the user selects (if selection is enabled)
   */
  selectedItems : any[] = []

  ngOnInit(): void {
    this.initSearchBoxProperties()
    this.initFilters()
    this.selectable = this.allowSelect() || this.allowMultiSelect()
    this.multiSelectable = this.allowSelect() && this.allowMultiSelect()
  }

  ngAfterContentInit() : void {
    this.initItemDirective()
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.elementUniqueKeyFunc = this.elementUniqueKeyFunction()
    this.requestPage()
  }

  initSearchBoxProperties() : void {
    this.searchBoxHintStr = this.searchBoxHint()
    this.searchBoxVisible = this.searchBoxHintStr != ""
  }

  initFilters() : void {
    this.filtersArray = this.dataScrollerFilterToDataScrollerFilterWithValue(this.filters() ?? [])
    this.searchBarVisible = this.filtersArray.length > 0 || this.searchBoxVisible
  }

  initItemDirective() : void {
    if (this.itemDirectives && this.itemDirectives.length > 0) {
      this.itemDirective = <TemplateRef<any>>(this.itemDirectives.get(0)?.templateRef)
    }
  }

  dataScrollerFilterToDataScrollerFilterWithValue(filters : DataScrollerFilter[]) : DataScrollerFilterWithValue[] {
    // Initialize the filters with values array
    const filtersWithValues : DataScrollerFilterWithValue[] = []

    // Populate the filters with values array
    filters.forEach(filter =>
      filtersWithValues.push(<DataScrollerFilterWithValue>filter)
    )

    // Return a reference to the filters with values array
    return filtersWithValues
  }

  extractSelectedFilterValues(filters:DataScrollerFilterWithValue[]) : DataScrollerFilterSelectedValue[] {
    // Initialize the selected values array
    const selectedValues : DataScrollerFilterSelectedValue[] = []

    // Populate the selected values array with the selected filter values
    filters.forEach(filter => {
      if (filter.selectedCardData) {
        selectedValues.push({
          filterName       : filter.filterName,
          selectedCardData : filter.selectedCardData
        })
      }
    })

    // Return a reference to the selected values array
    return selectedValues
  }

  onFilterValueChange() : void {
    setTimeout(() => {
      this.reset()
      this.requestPage()
    }, 100)
  }

  onSearchBarValueChanged(newValue:string) : void {
    if (newValue?.length == 0 || newValue?.length >= SEARCH_BOX_VALUE_MIN_LENGTH) {
      this.reset()
      this.requestPage()
    }
  }

  onPanelScroll(event:ScrollEvent) : void {
    // If the user scrolled down and reached the bottom of the panel
    if (event.direction == ScrollDirection.DOWN && event.sliderPosPct >= PAGE_REQUEST_TRIGGER_THRESHOLD_PCT) {
      // Request the next page
      this.requestPage()
    }
  }

  onItemContainerClick(itemContainer:DataScrollerItemContainer) : void {
    // This code executes only if the data scroller is selectable
    if (this.isSelectable()) {
      // If the data scroller is not multi-selectable, then de-select all items
      // while preserving the selected state of the current items
      if (!this.isMultiSelectable()) {
        // Store the selected state of the current item
        const selected : boolean = itemContainer.selected

        // De-select all items
        this.deselectAll()

        // Re-apply the stored selected state to the current item
        itemContainer.selected = selected
      }

      // Toggle the selected state of the item
        this.toggleSelection(itemContainer)

      // Fire the selectionChange event
      this.selectionChange.emit(this.selectedItems)
    }
  }

  /**
   * De-selects all elements in both the UI state and the selected items array
   */
  private deselectAll() : void {
    this.selectedItems = []
    this.data.forEach(itemContainer => itemContainer.selected = false)
  }

  /**
   * Toggles the selected state of the referenced item in the UI, while also
   * adding to or removing it from the selected items array
   */
  private toggleSelection(itemContainer:DataScrollerItemContainer) : void {
      // Remove the item from the selection, if present
      removeArrayElement(this.selectedItems, itemContainer.item)

      // Toggle the selected state of the item
      itemContainer.selected = !itemContainer.selected

      // If the item is now selected, then add it to the selection
      if (itemContainer.selected) {
        this.selectedItems.push(itemContainer.item)
      }
  }

  private reset() : void {
    this.lastLoadedPageNumber = -1
    this.data = []
  }

  private requestPage() : void {
    // TODO: Give feedback

    // Request the page
    this.pageRequestCallback()(
      {
        pageNumber           : this.lastLoadedPageNumber + 1,
        pageSize             : this.pageSize(),
        appliedFiltersValues : this.extractSelectedFilterValues(this.filtersArray),
        searchBoxValue       : this.searchBoxValue
      }
    ).subscribe({
        // Upon successful response
        next: result => {
          // Merge the new page data into the data scroller's data array
          this.addPageValuesToDataArray(result.dataSetSize, result.pageData)

          // Update the last loaded page number
          this.lastLoadedPageNumber = this.lastLoadedPageNumber + 1
        },
        error: error => {
          // TODO: toast
        }
      })
  }

  private addPageValuesToDataArray(dataSetSize:number, pageData:any[]) : void {
    // Bubble trouble...
    // For each element in the new page
    for (let pageDataItem of pageData) {
      // If the item does not already exist in the data set, then add it now
      if (this.itemIsNew(pageDataItem)) {
        this.data.push({item: pageDataItem, selected: false})
      }
    }
  }

  private itemIsNew(item:any) : boolean {
    // Get the item unique key
    const itemUniqueKey : any = this.elementUniqueKeyFunc(item)

    // The item is new if there is no other item with the same unique key of
    // the current item already in the data set.
    return this.data.filter(e => itemUniqueKey == this.elementUniqueKeyFunc(e.item)).length == 0
  }

  getWidth() : string {
    return this.width()
  }

  getHeight() : string {
    return this.height()
  }

  getTitle() : string {
    return this.title()
  }

  getSearchBoxHint() : string {
    return this.searchBoxHintStr
  }

  isFilterBarVisible() : boolean {
    return this.searchBarVisible
  }

  isSearchBarVisible() : boolean {
    return this.searchBoxVisible
  }

  isSelectable() : boolean {
    return this.selectable
  }

  isMultiSelectable() : boolean {
    return this.multiSelectable
  }

  hasItemDirective() : boolean {
    if (this.itemDirective) {
      return true
    }

    return false
  }



}
