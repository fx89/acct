/**
 * Container for the public properties of an income or expense item category
 */
export interface IncomeOrExpenseItemCategory {
    /**
     * Unique identifier for the category
     */
    incomeOrExpenseItemCategoryUUID? : string,

    /**
     * Human-readable unique identifier for the category
     */
    incomeOrExpenseItemCategoryName : string,

    /**
     * Human-readable description of the category
     */
    incomeOrExpenseItemCategoryDescription : string,

    /**
     * Identifier for the icon representing the category in the UI
     */
    incomeOrExpenseItemCategoryIconUUID? : string
}

/**
 * Extends the IncomeOrExpenseItemCategory with the imageData property, which
 * contains the Base64-encoded image and its meta-data, ready to be displayed
 * by the browser
 */
export interface IconifiedIncomeOrExpenseItemCategory extends IncomeOrExpenseItemCategory {
  imageData : string
}