package com.desolatetimelines.acct.catalog.ws.model;

/**
 * Container for the public properties of an income or expense item subcategory
 *
 * @param incomeOrExpenseItemSubcategoryUUID        unique identifier for the subcategory
 * @param incomeOrExpenseItemSubcategoryName        human-readable unique identifier for the subcategory
 * @param incomeOrExpenseItemSubcategoryDescription human-readable description of the subcategory
 * @param incomeOrExpenseItemSubcategoryIconUUID    identifier for the icon representing the subcategory in the UI
 */
public record IncomeOrExpenseItemSubcategoryProperties(
    String incomeOrExpenseItemSubcategoryUUID,
    String incomeOrExpenseItemSubcategoryName,
    String incomeOrExpenseItemSubcategoryDescription,
    String incomeOrExpenseItemSubcategoryIconUUID
) {

    public static IncomeOrExpenseItemSubcategoryPropertiesBuilder builder() {
        return new IncomeOrExpenseItemSubcategoryPropertiesBuilder();
    }

    public static final class IncomeOrExpenseItemSubcategoryPropertiesBuilder {
        private String incomeOrExpenseItemSubcategoryUUID;
        private String incomeOrExpenseItemSubcategoryName;
        private String incomeOrExpenseItemSubcategoryDescription;
        private String incomeOrExpenseItemSubcategoryIconUUID;

        private IncomeOrExpenseItemSubcategoryPropertiesBuilder() {
        }

        public IncomeOrExpenseItemSubcategoryPropertiesBuilder withIncomeOrExpenseItemSubcategoryUUID(String incomeOrExpenseItemSubcategoryUUID) {
            this.incomeOrExpenseItemSubcategoryUUID = incomeOrExpenseItemSubcategoryUUID;
            return this;
        }

        public IncomeOrExpenseItemSubcategoryPropertiesBuilder withIncomeOrExpenseItemSubcategoryName(String incomeOrExpenseItemSubcategoryName) {
            this.incomeOrExpenseItemSubcategoryName = incomeOrExpenseItemSubcategoryName;
            return this;
        }

        public IncomeOrExpenseItemSubcategoryPropertiesBuilder withIncomeOrExpenseItemSubcategoryDescription(String incomeOrExpenseItemSubcategoryDescription) {
            this.incomeOrExpenseItemSubcategoryDescription = incomeOrExpenseItemSubcategoryDescription;
            return this;
        }

        public IncomeOrExpenseItemSubcategoryPropertiesBuilder withIncomeOrExpenseItemSubcategoryIconUUID(String incomeOrExpenseItemSubcategoryIconUUID) {
            this.incomeOrExpenseItemSubcategoryIconUUID = incomeOrExpenseItemSubcategoryIconUUID;
            return this;
        }

        public IncomeOrExpenseItemSubcategoryProperties build() {
            return
                new IncomeOrExpenseItemSubcategoryProperties(
                    incomeOrExpenseItemSubcategoryUUID,
                    incomeOrExpenseItemSubcategoryName,
                    incomeOrExpenseItemSubcategoryDescription,
                    incomeOrExpenseItemSubcategoryIconUUID
                );
        }
    }
}
