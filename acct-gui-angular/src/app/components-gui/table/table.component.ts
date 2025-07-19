import { Component, ContentChildren, Directive, EventEmitter, InputSignal, Output, QueryList, TemplateRef, input } from '@angular/core';
import {v4 as uuidv4} from 'uuid';
import { CommonModule } from '@angular/common';
import { TableColumnSort, TableColumnSortDirection, TableSortEvent } from './table-sort-event';
import { ScrollableContentDirective, ScrollEvent } from '../directives/scrollable-content.directive';

const DEFAULT_COLUMN_WIDTH: number = 70

interface TableColumnHeader {
  columnName : string
  headerStyle : string
  isFirstCell : boolean
  isLastCell : boolean
  columnWidthStyle: string
  sortDirection : TableColumnSortDirection
}

interface TableColumn {
  columnTemplate : TemplateRef<any>
  columnStyle: string
  columnWidthStyle: string
}

@Directive({ selector: '[tableColumn]'})
export class TableColumnDirective {
  constructor(public templateRef: TemplateRef<any>) { }
}

@Component({
  selector: 'app-table',
  imports: [
    CommonModule,
    ScrollableContentDirective
  ],
  templateUrl: './table.component.html',
  styleUrl: './table.component.less'
})
export class TableComponent {

  /**
   * The ID of the component is unique in the page
   */
  public id : string = uuidv4()
  public tableBodyElementId : string = this.id + "_body"

  // Input properties - flags
  frozenHeader      : InputSignal<boolean>  = input(false)
  selectableRows    : InputSignal<boolean>  = input(false)
  zebraStripes      : InputSignal<boolean>  = input(false)
  verticalGridLines : InputSignal<boolean>  = input(false)
  sortable          : InputSignal<boolean>  = input(false)
  scrollable        : InputSignal<boolean>  = input(false)
  scrollHeight      : InputSignal<string>   = input("100px")

  // Input properties - columns config
  columnNames       : InputSignal<string[]> = input.required()
  headerStyles      : InputSignal<string[]> = input([] as string[])
  columnStyles      : InputSignal<string[]> = input([] as string[])
  columnWidths      : InputSignal<number[]> = input([] as number[])

  // Data
  data : InputSignal<any[]> = input.required()

  // Selected row
  selectedRow       : InputSignal<any> = input()
  cachedSelectedRow : any

  // Events
  @Output() rowSelected       : EventEmitter<any>              = new EventEmitter<any>
  @Output() selectedRowChange : EventEmitter<any>              = new EventEmitter<any>
  @Output() scroll            : EventEmitter<ScrollEvent>      = new EventEmitter<ScrollEvent>
  @Output() sort              : EventEmitter<TableSortEvent>   = new EventEmitter<TableSortEvent>

  // Columns
  // https://stackoverflow.com/questions/71411635/in-angular-how-to-do-content-projection-over-the-list-of-children
  // https://angular.dev/guide/templates/ng-template
  @ContentChildren(TableColumnDirective)
  private columnDirectives! : QueryList<TableColumnDirective>
  private cachedColumns : TableColumn[] = []

  // Headers
  private cachedHeaders: TableColumnHeader[] = []

  // Column widths
  private cachedColumnWidths: number[] = []

  ngAfterContentInit() : void {
    this.initColumnWidths()
    this.initColumns()
    this.initHeaders()
    this.initSelectedRow()
  }

  initColumnWidths() : void {
    // Cache the provided column widths
    this.cachedColumnWidths = this.columnWidths()

    // If there are no column widths, then initialize with empty array
    if (!this.cachedColumnWidths) {
      this.cachedColumnWidths = []
    }

    // Get the number of columns from the provided column names, if any have been provided
    const nbrCols : number = this.columnNames()?.length ?? 0

    // If there's a gap between the number of given column widths and column names, then
    // fill it with the default column width
    for (let c : number = this.cachedColumnWidths.length ; c < nbrCols ; c++) {
      this.cachedColumnWidths.push(DEFAULT_COLUMN_WIDTH)
    }

    // If any of the provided column widths is invalid, then replace it with the default value
    for (let c : number = 0 ; c < this.cachedColumnWidths.length ; c++) {
      if (this.cachedColumnWidths[c] < 5) {
        this.cachedColumnWidths[c] = DEFAULT_COLUMN_WIDTH
      }
    }
  }

  initColumns() : void {
    // Initialize the cached columns
    this.cachedColumns = []
    let columnIndex : number = 0

    // Get the defined column styles (if any)
    const columnStyles = this.columnStyles()

    // For each of the defined column directives, create a cached column with the defined style.
    // If there is no style defined for the column, then use an empty default.
    this.columnDirectives.forEach(columnDirective => {
      this.cachedColumns.push({
        columnTemplate: columnDirective.templateRef,
        columnStyle: ( (columnStyles && columnIndex < columnStyles.length) ? columnStyles[columnIndex] : "" ),
        columnWidthStyle: this.cachedColumnWidths[columnIndex] + 'px'
      })
      columnIndex++
    })
  }

  initHeaders() : void {
    // Initialize the cached headers
    this.cachedHeaders = []
    let currentCachedHeaderIndex : number = 0

    // Get the defined header styles (if any)
    const headerStyles : string[] = this.headerStyles()

    // Get the defined column names
    const columnNames : string[] = this.columnNames()

    // First defiend header style is for the first column, second one is for the second column,
    // and so on, until there are no more header styles defined
    if (headerStyles) {
      while (currentCachedHeaderIndex < headerStyles.length && currentCachedHeaderIndex < columnNames.length) {
        this.cachedHeaders.push({
          columnName: columnNames[currentCachedHeaderIndex],
          headerStyle: headerStyles[currentCachedHeaderIndex],
          isFirstCell: currentCachedHeaderIndex == 0,
          isLastCell: currentCachedHeaderIndex == columnNames.length - 1,
          columnWidthStyle: this.cachedColumnWidths[currentCachedHeaderIndex] + 'px',
          sortDirection: TableColumnSortDirection.NONE
        })
        currentCachedHeaderIndex++
      }
    }

    // If there are more columns defined than header styles, then set an empty header style for
    // each of the aforementioned columns, to avoid issues
    while (currentCachedHeaderIndex < columnNames.length) {
      this.cachedHeaders.push({
        columnName: columnNames[currentCachedHeaderIndex],
        headerStyle: "",
        isFirstCell: currentCachedHeaderIndex == 0,
        isLastCell: currentCachedHeaderIndex == columnNames.length - 1,
        columnWidthStyle: this.cachedColumnWidths[currentCachedHeaderIndex] + 'px',
        sortDirection: TableColumnSortDirection.NONE
      })
      currentCachedHeaderIndex++
    }
  }

  initSelectedRow() : void {
    this.cachedSelectedRow = this.selectedRow()
  }

  onRowClicked(row:any) : void {
    if (this.areRowsSelectable()) {
      this.cachedSelectedRow = row
      this.selectedRowChange.emit(this.cachedSelectedRow)
      this.rowSelected.emit(this.cachedSelectedRow)
    }
  }

  onSortIconCellClicked(header: TableColumnHeader) : void {
    // Cycle the sort direction
    header.sortDirection = this.cycleSortDirection(header.sortDirection)

    // Initialize an empty sorts array
    const sorts : TableColumnSort[] = []

    // Compile the sorts array (add only the columns that are sorted)
    for (let c:number = 0 ; c < this.cachedHeaders.length ; c++) {
      const cachedHeader = this.cachedHeaders[c]
      if (cachedHeader.sortDirection != TableColumnSortDirection.NONE) {
        sorts.push({
          columnNumber: c,
          columnName: cachedHeader.columnName,
          sortDirection: cachedHeader.sortDirection
        })
      }
    }

    // Create the event information and fire the event
    this.sort.emit({ columnSorts: sorts })
  }

  cycleSortDirection(sortDirection : TableColumnSortDirection) : TableColumnSortDirection {
    if (sortDirection == TableColumnSortDirection.NONE) {
      return TableColumnSortDirection.ASCENDING
    }

    if (sortDirection == TableColumnSortDirection.ASCENDING) {
      return TableColumnSortDirection.DESCENDING
    }

    if (sortDirection == TableColumnSortDirection.DESCENDING) {
      return TableColumnSortDirection.NONE
    }

    return sortDirection
  }

  onContentScroll(event:ScrollEvent) : void {
    this.scroll.emit(event)
  }

  isHeaderFrozen() : boolean {
    return this.frozenHeader()
  }

  isZebraStriping() : boolean {
    return this.zebraStripes()
  }

  isScrollable() : boolean {
    return this.scrollable()
  }

  isSortable() : boolean {
    return this.sortable()
  }

  hasVerticalGridLines() : boolean {
    return this.verticalGridLines()
  }

  areRowsSelectable() : boolean {
    return this.selectableRows()
  }

  getScrollHeight() : string {
    return this.scrollHeight()
  }

  getColumnNames() : String[] {
    return this.columnNames()
  }

  getHeaders() : TableColumnHeader[] {
    return this.cachedHeaders
  }

  getColumns() : TableColumn[] {
    return this.cachedColumns
  }

  getData() : any[] {
    return this.data()
  }

  getSelectedRow() : any {
    return this.cachedSelectedRow
  }

}
