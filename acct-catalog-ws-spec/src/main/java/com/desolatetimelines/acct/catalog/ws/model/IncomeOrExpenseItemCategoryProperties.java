package com.desolatetimelines.acct.catalog.ws.model;

/**
 * Container for the public properties of an income or expense item category
 *
 * @param incomeOrExpenseItemCategoryUUID        unique identifier for the category
 * @param incomeOrExpenseItemCategoryName        human-readable unique identifier for the category
 * @param incomeOrExpenseItemCategoryDescription human-readable description of the category
 * @param incomeOrExpenseItemCategoryIconUUID    identifier for the icon representing the category in the UI
 */
public record IncomeOrExpenseItemCategoryProperties(
    String incomeOrExpenseItemCategoryUUID,
    String incomeOrExpenseItemCategoryName,
    String incomeOrExpenseItemCategoryDescription,
    String incomeOrExpenseItemCategoryIconUUID
) {

    public static IncomeOrExpenseItemCategoryPropertiesBuilder builder() {
        return new IncomeOrExpenseItemCategoryPropertiesBuilder();
    }

    public static final class IncomeOrExpenseItemCategoryPropertiesBuilder {
        private String incomeOrExpenseItemCategoryUUID;
        private String incomeOrExpenseItemCategoryName;
        private String incomeOrExpenseItemCategoryDescription;
        private String incomeOrExpenseItemCategoryIconUUID;

        private IncomeOrExpenseItemCategoryPropertiesBuilder() {
        }

        public IncomeOrExpenseItemCategoryPropertiesBuilder withIncomeOrExpenseItemCategoryUUID(String incomeOrExpenseItemCategoryUUID) {
            this.incomeOrExpenseItemCategoryUUID = incomeOrExpenseItemCategoryUUID;
            return this;
        }

        public IncomeOrExpenseItemCategoryPropertiesBuilder withIncomeOrExpenseItemCategoryName(String incomeOrExpenseItemCategoryName) {
            this.incomeOrExpenseItemCategoryName = incomeOrExpenseItemCategoryName;
            return this;
        }

        public IncomeOrExpenseItemCategoryPropertiesBuilder withIncomeOrExpenseItemCategoryDescription(String incomeOrExpenseItemCategoryDescription) {
            this.incomeOrExpenseItemCategoryDescription = incomeOrExpenseItemCategoryDescription;
            return this;
        }

        public IncomeOrExpenseItemCategoryPropertiesBuilder withIncomeOrExpenseItemCategoryIconUUID(String incomeOrExpenseItemCategoryIconUUID) {
            this.incomeOrExpenseItemCategoryIconUUID = incomeOrExpenseItemCategoryIconUUID;
            return this;
        }

        public IncomeOrExpenseItemCategoryProperties build() {
            return
                new IncomeOrExpenseItemCategoryProperties(
                    incomeOrExpenseItemCategoryUUID,
                    incomeOrExpenseItemCategoryName,
                    incomeOrExpenseItemCategoryDescription,
                    incomeOrExpenseItemCategoryIconUUID
                );
        }
    }
}
