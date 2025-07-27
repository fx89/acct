/**
 * Container for the public properties of an income or expense item sub-category
 */
export interface IncomeOrExpenseItem {
    /**
     * Unique identifier for the item
     */
    incomeOrExpenseItemUUID? : string,

    /**
     * Human-readable unique identifier for the item
     */
    incomeOrExpenseItemName : string,

    /**
     * Human-readable description of the item
     */
    incomeOrExpenseItemDescription : string,

    /**
     * Identifier for the icon representing the item in the UI
     */
    incomeOrExpenseItemIconUUID? : string
}

/**
 * Extends the IncomeOrExpenseItem with the imageData property, which contains the
 * Base64-encoded image and its meta-data, ready to be displayed by the browser
 */
export interface IconifiedIncomeOrExpenseItem extends IncomeOrExpenseItem {
  imageData : string
}