/**
 * Container for the public properties of an income or expense item sub-category
 */
export interface IncomeOrExpenseItemSubcategory {
    /**
     * Unique identifier for the sub-category
     */
    incomeOrExpenseItemSubcategoryUUID? : string,

    /**
     * Human-readable unique identifier for the sub-category
     */
    incomeOrExpenseItemSubcategoryName : string,

    /**
     * Human-readable description of the sub-category
     */
    incomeOrExpenseItemSubcategoryDescription : string,

    /**
     * Identifier for the icon representing the sub-category in the UI
     */
    incomeOrExpenseItemSubcategoryIconUUID? : string
}

/**
 * Extends the IncomeOrExpenseItemSubcategory with the imageData property, which
 * contains the Base64-encoded image and its meta-data, ready to be displayed by
 * the browser
 */
export interface IconifiedIncomeOrExpenseItemSubcategory extends IncomeOrExpenseItemSubcategory {
  imageData : string
}