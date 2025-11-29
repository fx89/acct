package com.desolatetimelines.acct.reporting.dataprovider.service;

import com.desolatetimelines.acct.catalog.ws.client.RESTItemsEndpointClient;
import com.desolatetimelines.acct.catalog.ws.model.IncomeOrExpenseItemCategoryProperties;
import com.desolatetimelines.acct.catalog.ws.model.IncomeOrExpenseItemProperties;
import com.desolatetimelines.acct.catalog.ws.model.IncomeOrExpenseItemSubcategoryProperties;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingDataProviderInitializationException;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingDataProviderRuntimeException;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSet;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSetColumn;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSetColumnDataType;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class AcctReportingCatalogItemsDataProvider implements AcctReportingDataProvider {

    private final RESTItemsEndpointClient itemsEndpointClient;

    public AcctReportingCatalogItemsDataProvider(
        RESTItemsEndpointClient itemsEndpointClient
    ) {
        this.itemsEndpointClient = itemsEndpointClient;
    }

    @Override
    public void initialize(Map<String, String> dataProviderInstanceProperties) throws AcctReportingDataProviderInitializationException {

    }

    @Override
    public AcctReportingDataProviderDataSet provideData(Map<String, String> reportParameters) throws AcctReportingDataProviderRuntimeException {
        // Get the categories
        final Map<String, List<IncomeOrExpenseItemCategoryProperties>> categories =
            itemsEndpointClient.getIncomeOrExpenseItemCategories().parallelStream()
                .collect(groupingBy(IncomeOrExpenseItemCategoryProperties::incomeOrExpenseItemCategoryUUID));

        // Get the sub-categories of all categories
        final Map<String, List<ItemAndOwnerUUID<IncomeOrExpenseItemSubcategoryProperties>>> subCategories =
            categories.values().parallelStream()
                .flatMap(List::stream)
                .map(cat ->
                    itemsEndpointClient.getIncomeOrExpenseItemSubcategories(cat.incomeOrExpenseItemCategoryUUID())
                        .stream()
                        .map(subCat -> new ItemAndOwnerUUID<>(cat.incomeOrExpenseItemCategoryUUID(), subCat))
                        .toList()
                )
                .flatMap(List::stream)
                .collect(groupingBy(ItemAndOwnerUUID::ownerUUID));

        // Get the items of all sub-categories
        final List<ItemAndOwnerUUID<IncomeOrExpenseItemProperties>> items =
            subCategories.values().parallelStream()
                .flatMap(List::stream)
                .map(subCat ->
                    itemsEndpointClient.getIncomeOrExpenseItems(subCat.item().incomeOrExpenseItemSubcategoryUUID())
                        .stream()
                        .map(item -> new ItemAndOwnerUUID<>(subCat.item().incomeOrExpenseItemSubcategoryUUID(), item))
                        .toList()
                )
                .flatMap(Collection::stream)
                .toList();

        // Build the data set and return a reference
        return new AcctReportingDataProviderDataSet() {

            private final LinkedHashSet<AcctReportingDataProviderDataSetColumn> columnNames =
                new LinkedHashSet<>(List.of(
                    new AcctReportingDataProviderDataSetColumn(
                        "category_uuid",
                        AcctReportingDataProviderDataSetColumnDataType.STRING
                    ),
                    new AcctReportingDataProviderDataSetColumn(
                        "category_name",
                        AcctReportingDataProviderDataSetColumnDataType.STRING
                    ),
                    new AcctReportingDataProviderDataSetColumn(
                        "category_description",
                        AcctReportingDataProviderDataSetColumnDataType.STRING
                    ),
                    new AcctReportingDataProviderDataSetColumn(
                        "category_icon_uuid",
                        AcctReportingDataProviderDataSetColumnDataType.STRING
                    ),
                    new AcctReportingDataProviderDataSetColumn(
                        "subcategory_uuid",
                        AcctReportingDataProviderDataSetColumnDataType.STRING
                    ),
                    new AcctReportingDataProviderDataSetColumn(
                        "subcategory_name",
                        AcctReportingDataProviderDataSetColumnDataType.STRING
                    ),
                    new AcctReportingDataProviderDataSetColumn(
                        "subcategory_description",
                        AcctReportingDataProviderDataSetColumnDataType.STRING
                    ),
                    new AcctReportingDataProviderDataSetColumn(
                        "subcategory_icon_uuid",
                        AcctReportingDataProviderDataSetColumnDataType.STRING
                    ),
                    new AcctReportingDataProviderDataSetColumn(
                        "item_uuid",
                        AcctReportingDataProviderDataSetColumnDataType.STRING
                    ),
                    new AcctReportingDataProviderDataSetColumn(
                        "item_name",
                        AcctReportingDataProviderDataSetColumnDataType.STRING
                    ),
                    new AcctReportingDataProviderDataSetColumn(
                        "item_description",
                        AcctReportingDataProviderDataSetColumnDataType.STRING
                    ),
                    new AcctReportingDataProviderDataSetColumn(
                        "item_icon_uuid",
                        AcctReportingDataProviderDataSetColumnDataType.STRING
                    )
                ));

            private final String[][] dataSetRecords = createRecords();

            private String[][] createRecords() {
                // Create the records array
                final String[][] records = new String[items.size()][columnNames.size()];

                // Populate the records array with the fetched items and related sub-categories and categories
                int currentIndex = 0;
                for (ItemAndOwnerUUID<IncomeOrExpenseItemProperties> item : items) {
                    // Identify the sub-category related to the item
                    final ItemAndOwnerUUID<IncomeOrExpenseItemSubcategoryProperties> subCategory =
                        subCategories.get(item.ownerUUID()).stream().findFirst().orElseThrow();

                    // Identify the category related to the sub-category
                    final IncomeOrExpenseItemCategoryProperties category =
                        categories.get(subCategory.ownerUUID()).stream().findFirst().orElseThrow();

                    // Put the data into the array at the current index
                    records[currentIndex][0] = category.incomeOrExpenseItemCategoryUUID();
                    records[currentIndex][1] = category.incomeOrExpenseItemCategoryName();
                    records[currentIndex][2] = category.incomeOrExpenseItemCategoryDescription();
                    records[currentIndex][3] = category.incomeOrExpenseItemCategoryIconUUID();
                    records[currentIndex][4] = subCategory.item().incomeOrExpenseItemSubcategoryUUID();
                    records[currentIndex][5] = subCategory.item().incomeOrExpenseItemSubcategoryName();
                    records[currentIndex][6] = subCategory.item().incomeOrExpenseItemSubcategoryDescription();
                    records[currentIndex][7] = subCategory.item().incomeOrExpenseItemSubcategoryIconUUID();
                    records[currentIndex][8] = item.item().incomeOrExpenseItemUUID();
                    records[currentIndex][9] = item.item().incomeOrExpenseItemName();
                    records[currentIndex][10] = item.item().incomeOrExpenseItemDescription();
                    records[currentIndex][11] = item.item().incomeOrExpenseItemIconUUID();

                    // Increment the current index
                    currentIndex++;
                }

                // Return a reference to the records array
                return records;
            }

            @Override
            public int recordCount() {
                return dataSetRecords.length;
            }

            @Override
            public LinkedHashSet<AcctReportingDataProviderDataSetColumn> columns() {
                return columnNames;
            }

            @Override
            public String[][] data() {
                return dataSetRecords;
            }
        };
    }

    private record ItemAndOwnerUUID<T>(
        String ownerUUID,
        T item
    ) {
    }

}
