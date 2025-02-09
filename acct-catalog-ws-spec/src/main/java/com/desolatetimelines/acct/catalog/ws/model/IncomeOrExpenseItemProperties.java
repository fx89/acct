package com.desolatetimelines.acct.catalog.ws.model;

/**
 * Container for the readable properties of an income or expense item
 *
 * @param incomeOrExpenseItemUUID        unique identifier for the income or expense item within the ACCT ecosystem
 * @param incomeOrExpenseItemName        human-readable name for the income or expense item
 * @param incomeOrExpenseItemDescription human-readable description of the income or expense item
 * @param incomeOrExpenseItemIconUUID    unique identifier for the icon that represents the income or expense item
 */
public record IncomeOrExpenseItemProperties(
    String incomeOrExpenseItemUUID,
    String incomeOrExpenseItemName,
    String incomeOrExpenseItemDescription,
    String incomeOrExpenseItemIconUUID
) {
    public static IncomeOrExpenseItemPropertiesBuilder builder() {
        return new IncomeOrExpenseItemPropertiesBuilder();
    }

    public static final class IncomeOrExpenseItemPropertiesBuilder {
        private String incomeOrExpenseItemUUID;
        private String incomeOrExpenseItemName;
        private String incomeOrExpenseItemDescription;
        private String incomeOrExpenseItemIconUUID;

        private IncomeOrExpenseItemPropertiesBuilder() {
        }

        public IncomeOrExpenseItemPropertiesBuilder withIncomeOrExpenseItemUUID(String incomeOrExpenseItemUUID) {
            this.incomeOrExpenseItemUUID = incomeOrExpenseItemUUID;
            return this;
        }

        public IncomeOrExpenseItemPropertiesBuilder withIncomeOrExpenseItemName(String incomeOrExpenseItemName) {
            this.incomeOrExpenseItemName = incomeOrExpenseItemName;
            return this;
        }

        public IncomeOrExpenseItemPropertiesBuilder withIncomeOrExpenseItemDescription(String incomeOrExpenseItemDescription) {
            this.incomeOrExpenseItemDescription = incomeOrExpenseItemDescription;
            return this;
        }

        public IncomeOrExpenseItemPropertiesBuilder withIncomeOrExpenseItemIconUUID(String incomeOrExpenseItemIconUUID) {
            this.incomeOrExpenseItemIconUUID = incomeOrExpenseItemIconUUID;
            return this;
        }

        public IncomeOrExpenseItemProperties build() {
            return
                new IncomeOrExpenseItemProperties(
                    incomeOrExpenseItemUUID,
                    incomeOrExpenseItemName,
                    incomeOrExpenseItemDescription,
                    incomeOrExpenseItemIconUUID
                );
        }
    }
}
