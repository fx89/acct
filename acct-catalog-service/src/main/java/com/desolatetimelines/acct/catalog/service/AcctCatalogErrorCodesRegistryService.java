package com.desolatetimelines.acct.catalog.service;

import com.desolatetimelines.acct.common.service.AbstractErrorCodesRegistryService;
import org.springframework.stereotype.Service;

@Service
public class AcctCatalogErrorCodesRegistryService extends AbstractErrorCodesRegistryService {

    public String ICON_ALREADY_EXISTS;

    public String ICON_VALIDATION_NAME_PATTERN;

    public String ICON_NOT_FOUND;

    public String ICON_IN_USE;

    public String ICON_CATEGORY_NOT_FOUND;

    public String INCOME_OR_EXPENSE_ITEM_CATEGORY_NOT_FOUND;

    public String INCOME_OR_EXPENSE_ITEM_CATEGORY_ALREADY_EXISTS;

    public String INCOME_OR_EXPENSE_ITEM_SUBCATEGORY_NOT_FOUND;

    public String INCOME_OR_EXPENSE_ITEM_SUBCATEGORY_ALREADY_EXISTS;

    public String INCOME_OR_EXPENSE_ITEM_ALREADY_EXISTS;

    public String INCOME_OR_EXPENSE_ITEM_NOT_FOUND;

    public String INCOME_OR_EXPENSE_ITEM_IN_USE;

    public String BANK_NOT_FOUND;

    public String BANK_ALREADY_EXISTS;

    public String BANK_IN_USE;

    public String CURRENCY_NOT_FOUND;

    public String CURRENCY_ALREADY_EXISTS;

    public String CURRENCY_IN_USE;

    protected AcctCatalogErrorCodesRegistryService() {
        super(Integer.parseInt(System.getenv("CATALOG_SERVICE_NUMBER")));
    }

    @Override
    protected void initializeServiceSpecificErrorCodes() {
        final String CAT_NAME_CONSTRAINT_VIOLATIONS = "Constraint violations";
        final String CAT_NAME_BUSINESS_RULES_VALIDATION = "Business rules Validation";
        final String CAT_NAME_NOT_FOUND = "Not found exceptions";

        ICON_ALREADY_EXISTS = resolveErrorCode(
            CAT_NAME_CONSTRAINT_VIOLATIONS,
            "Icon already exists",
            "An icon with the same name as the one that is being created already exists within " +
                "the referenced icons category"
        );

        ICON_VALIDATION_NAME_PATTERN = resolveErrorCode(
            CAT_NAME_BUSINESS_RULES_VALIDATION,
            "The icon name pattern is not correct",
            "The provided icon name pattern is either too short or otherwise incorrect"
        );

        ICON_NOT_FOUND = resolveErrorCode(
            CAT_NAME_NOT_FOUND,
            "The icon was not found",
            "An operation was requested for an icon that cannot be found"
        );

        ICON_IN_USE = resolveErrorCode(
            CAT_NAME_BUSINESS_RULES_VALIDATION,
            "One or more icons are in use",
            "An operation was requested for one or more icons which are in use by various services"
        );

        INCOME_OR_EXPENSE_ITEM_CATEGORY_NOT_FOUND = resolveErrorCode(
            CAT_NAME_NOT_FOUND,
            "The income or expense item category was not found",
            "An operation was requested for an income or expense item category that cannot be found"
        );

        INCOME_OR_EXPENSE_ITEM_CATEGORY_ALREADY_EXISTS = resolveErrorCode(
            CAT_NAME_CONSTRAINT_VIOLATIONS,
            "Income or expense item category already exists",
            "An income or expense item category with the same name as the one that is being created " +
                "already exists in the catalog"
        );

        INCOME_OR_EXPENSE_ITEM_SUBCATEGORY_ALREADY_EXISTS = resolveErrorCode(
            CAT_NAME_CONSTRAINT_VIOLATIONS,
            "Income or expense item subcategory already exists",
            "An income or expense item subcategory with the same name as the one that is being created " +
                "already exists within the referenced income or expense item category"
        );

        INCOME_OR_EXPENSE_ITEM_SUBCATEGORY_NOT_FOUND = resolveErrorCode(
            CAT_NAME_NOT_FOUND,
            "The income or expense item subcategory was not found",
            "An operation was requested for an income or expense item subcategory that cannot be found"
        );

        INCOME_OR_EXPENSE_ITEM_IN_USE = resolveErrorCode(
            CAT_NAME_BUSINESS_RULES_VALIDATION,
            "The income or expense item is in use",
            "An operation cannot be executed on one or more income or expense items because they are " +
                "referenced by other entities stored in either the catalog service or the other services"
        );

        INCOME_OR_EXPENSE_ITEM_NOT_FOUND = resolveErrorCode(
            CAT_NAME_NOT_FOUND,
            "The income or expense item was not found",
            "An operation was requested for an income or expense item that cannot be found"
        );

        INCOME_OR_EXPENSE_ITEM_ALREADY_EXISTS = resolveErrorCode(
            CAT_NAME_CONSTRAINT_VIOLATIONS,
            "Income or expense item already exists",
            "An income or expense item with the same name as the one that is being created " +
                "already exists in the referenced income or expense item subcategory"
        );

        BANK_NOT_FOUND = resolveErrorCode(
            CAT_NAME_NOT_FOUND,
            "The bank was not found",
            "An operation was requested for a bank that cannot be found"
        );

        BANK_ALREADY_EXISTS = resolveErrorCode(
            CAT_NAME_CONSTRAINT_VIOLATIONS,
            "Bank already exists",
            "A bank with the same name as the one that is being created " +
                "already exists in the catalog"
        );

        BANK_IN_USE = resolveErrorCode(
            CAT_NAME_BUSINESS_RULES_VALIDATION,
            "One or more banks are in use",
            "An operation was requested for one or more banks which are in use by various services"
        );

        CURRENCY_NOT_FOUND = resolveErrorCode(
            CAT_NAME_NOT_FOUND,
            "The currency was not found",
            "An operation was requested for a currency that cannot be found"
        );

        CURRENCY_ALREADY_EXISTS = resolveErrorCode(
            CAT_NAME_NOT_FOUND,
            "The currency already exists",
            "A currency with the same name as the one that is being created " +
                "already exists in the catalog"
        );

        CURRENCY_IN_USE = resolveErrorCode(
            CAT_NAME_BUSINESS_RULES_VALIDATION,
            "One or more currencies are in use",
            "An operation was requested for one or more currencies which are in use by various services"
        );

        ICON_CATEGORY_NOT_FOUND = resolveErrorCode(
            CAT_NAME_NOT_FOUND,
            "Icon category not found",
            "The icon category with the given name was not found"
        );
    }

}
