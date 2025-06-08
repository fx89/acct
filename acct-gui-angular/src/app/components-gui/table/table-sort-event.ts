/**
 * Defines the direction of the sorting applied on one or more columns
 */
export enum TableColumnSortDirection {
  NONE = "NONE",
  ASCENDING = "ASCENDING",
  DESCENDING = "DESCENDING"
}

/**
 * Defines the sort direction for a column with a given column name.
 * The column number is also included, to allow for differentiating
 * between multiple columns with the same names. 
 */
export interface TableColumnSort {
  columnNumber: number,
  columnName: string,
  sortDirection: TableColumnSortDirection
}

/**
 * Container for an array of column sorts. Contains only the sorts
 * for the columns that are sorted.
 */
export interface TableSortEvent {
  columnSorts: TableColumnSort[]
}