package com.desolatetimelines.acct.catalog.service;

import com.desolatetimelines.acct.catalog.data.service.AcctCatalogDataService;
import com.desolatetimelines.acct.catalog.exception.*;
import com.desolatetimelines.acct.catalog.model.*;
import com.desolatetimelines.acct.common.model.ObjectTypes;
import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.common.utils.Streams;
import com.desolatetimelines.acct.usage.ws.client.RESTInUseEndpointClient;
import com.desolatetimelines.acct.usage.ws.client.RESTUsageEndpointClient;
import com.desolatetimelines.acct.usage.ws.model.ServiceItemTypesList;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Main class of the services layer of the ACCT catalog
 */
@Service
public class AcctCatalogService {

    private final AcctCatalogDataService dataService;

    private final AcctCatalogErrorCodesRegistryService errors;

    private final RESTInUseEndpointClient inUseEndpointClient;

    private final RESTUsageEndpointClient usageEndpointClient;

    private final String applicationName;

    private final String contextPath;

    public AcctCatalogService(
        AcctCatalogDataService dataService,
        AcctCatalogErrorCodesRegistryService errors,
        RESTInUseEndpointClient inUseEndpointClient,
        RESTUsageEndpointClient usageEndpointClient,
        @Value("${CATALOG_APPLICATION_NAME}") String applicationName,
        @Value("${CATALOG_SERVER_CONTEXT_PATH}") String contextPath
    ) {
        this.dataService = dataService;
        this.errors = errors;
        this.inUseEndpointClient = inUseEndpointClient;
        this.usageEndpointClient = usageEndpointClient;
        this.applicationName = applicationName;
        this.contextPath = contextPath;
    }

    /**
     * Registers in-use item types with the usage service upon startup
     */
    @SuppressWarnings("unused")
    @EventListener(ApplicationReadyEvent.class)
    protected void registerInUseObjectTypes() {
        usageEndpointClient.registerItemTypesForService(
            ServiceItemTypesList.builder()
                .withServiceName(applicationName)
                .withServiceContextPath(contextPath)
                .withItemType(List.of(
                    ObjectTypes.ICON.name()
                ))
                .build()
        );
    }

    /**
     * Returns the UUIDs of any used items of the given type and that can be found in the given list
     *
     * @param objectType the given type
     * @param itemUUIDs  the given list
     */
    public Collection<String> getInUseItemUUIDs(String objectType, Collection<String> itemUUIDs) {
        // If the object type is ICON then search banks, currencies and income or expense items,
        // item sub-categories and item categories for used icons
        if (Objects.equals(objectType, ObjectTypes.ICON.name())) {
            return
                Streams.multiConcat(
                    // Find any banks that are using any of the icons with the given UUIDs
                    dataService.findBanksByBankIconUUIDIn(itemUUIDs)
                        .stream()
                        .map(AcctBank::getBankIconUUID),

                    // Find any currencies that are using any of the icons with the given UUIDs
                    dataService.findCurrenciesByCurrencyIconUUIDIn(itemUUIDs)
                        .stream()
                        .map(AcctCurrency::getCurrencyIconUUID),

                    // Find any income or expense items that are using any of the icons with the given UUIDs
                    dataService.findIncomeOrExpenseItemsByIncomeOrExpenseItemIconUUIDIn(itemUUIDs)
                        .stream()
                        .map(AcctIncomeOrExpenseItem::getIncomeOrExpenseItemIconUUID),

                    // Find any income or expense item categories that are using any of the icons with the given UUIDs
                    dataService.findIncomeOrExpenseItemCategoriesByIncomeOrExpenseItemCategoryIconUUIDIn(itemUUIDs)
                        .stream()
                        .map(AcctIncomeOrExpenseItemCategory::getIncomeOrExpenseItemCategoryIconUUID),

                    // Find any income or expense item subcategories that are using any of the icons with the given UUIDs
                    dataService.findIncomeOrExpenseItemSubcategoriesByIncomeOrExpenseItemSubcategoryIconUUIDIn(itemUUIDs)
                        .stream()
                        .map(AcctIncomeOrExpenseItemSubcategory::getIncomeOrExpenseItemSubcategoryIconUUID)

                ).distinct().toList();
        }

        // If this point has been reached, it means that either the item type is not supported
        // or the code for handling the object type is missing from above
        throw new IllegalArgumentException("Object type [" + objectType + "] not supported");
    }

    /**
     * Returns a set of all the {@link AcctIconCategory icon categories} registered in the catalog
     */
    public Set<AcctIconCategory> getIconCategories() {
        return dataService.findAllIconCategories();
    }

    /**
     * Creates a new {@link AcctIconCategory icon category} with the given name
     *
     * @param iconCategoryName the given name
     * @return a reference to the newly created icon category
     */
    @SuppressWarnings("UnusedReturnValue")
    public AcctIconCategory createIconCategory(String iconCategoryName) {
        final AcctIconCategory iconCategory = dataService.createNewIconCategory();
        iconCategory.setIconCategoryName(iconCategoryName);
        return dataService.saveIconCategory(iconCategory);
    }

    /**
     * Removes the {@link AcctIconCategory icon category} with the given name
     * from the catalog. The category has to be empty for this to work.
     *
     * @param iconCategoryName the given name
     */
    public void deleteIconCategory(String iconCategoryName) {
        // Find the icon category. Throw an exception if not found.
        final AcctIconCategory iconCategory =
            dataService.findIconCategoryByIconCategoryName(iconCategoryName)
                .orElseThrow(() -> new AcctCatalogServiceIconCategoryNotFoundException(
                    errors, iconCategoryName)
                );

        // Delete the icon category
        dataService.deleteIconCategory(iconCategory);
    }


    /**
     * Creates an {@link AcctIcon icon} with the given details and returns a reference to the
     * newly created entity
     *
     * @param iconName         The name that uniquely identifies the icon within its category
     * @param iconCategoryName The name of the category that contains the icon (if it doesn't exist, it's created)
     * @param iconMimeType     The mime type of the picture file
     * @param iconBytesBase64  The base64-encoded bytes of the icon
     */
    @Transactional
    public AcctIcon createIcon(
        String iconName,
        String iconCategoryName,
        String iconMimeType,
        String iconBytesBase64
    ) {
        // Create the icon
        final AcctIcon newIcon = dataService.createNewIcon();

        // Resolve the icon category and assign it to the newly created icon
        newIcon.setIconCategory(
            resolveIconCategory(iconCategoryName)
        );

        // Generate a UUID for the icon
        newIcon.setIconUUID(UUID.randomUUID().toString());

        // Assign the other properties
        newIcon.setIconName(iconName);
        newIcon.setMimeType(iconMimeType);
        newIcon.setIconBytesBase64(iconBytesBase64);

        // Persist the icon and return a reference
        try {
            return dataService.saveIcon(newIcon);
        }
        // Translate constraint violations into an already existing icon with the same properties
        catch (DataIntegrityViolationException e) {
            throw new AcctCatalogServiceIconConstraintViolationException(errors, iconCategoryName, iconName, e);
        }
    }

    /**
     * Returns the base64-encoded bytes of the {@link AcctIcon icon} with the
     * given {@link AcctIcon#getIconUUID() icon UUID}. Throws an exception if
     * the icon is not found.
     *
     * @param iconUUID the given icon UUID
     */
    public String getIconBytesBase64(String iconUUID) {
        // Get the icon or throw an exception
        final AcctIcon icon =
            dataService
                .findIconByIconUUID(iconUUID)
                .orElseThrow(() -> new AcctCatalogServiceIconNotFoundException(errors, iconUUID));

        // Return the icon's base64-encoded bytes
        return icon.getIconBytesBase64();
    }

    /**
     * Deletes the {@link AcctIcon icons} identified by the {@link AcctIcon#getIconUUID() UUIDs}
     * in the given collection of icon UUIDs. Throws an exception if any icon is in use by any
     * ACCT service for any reason. Throws an exception if any of the icons cannot be found.
     *
     * @param iconUUIDs the given collection of icon UUIDs
     */
    public void deleteIcons(Collection<String> iconUUIDs) {
        // Find out if any of the icons is in use
        final Collection<String> inUseIconUUIDs =
            inUseEndpointClient.getItemsInUseOfType(ObjectTypes.ICON.name(), iconUUIDs);

        // If any of the icons is in use then throw an exception
        if (!inUseIconUUIDs.isEmpty()) {
            throw new AcctCatalogServiceIconInUseException(errors, inUseIconUUIDs);
        }

        // Find the icons
        final Collection<AcctIcon> foundIcons = dataService.findIconsByIconUUIDIn(iconUUIDs);

        // If any of the referenced icons is not found, throw an exception
        throwExceptionIfAnyEntityIsNotFound(
            foundIcons,
            AcctIcon::getIconUUID,
            iconUUIDs,
            notFoundUUIDs -> new AcctCatalogServiceIconNotFoundException(
                errors,
                notFoundUUIDs.stream().findAny().orElseThrow()
            )
        );

        // Delete the icons
        dataService.deleteIcons(foundIcons);
    }

    /**
     * Returns a count of the icons that match the given name pattern and that belong to
     * the given category name. If a name pattern is not provided then the count includes
     * icons with any name. If the category name is not provided then the count includes
     * icons from all categories.
     *
     * @param iconNamePattern  the given name pattern - optional - must be at least 3 characters long
     * @param iconCategoryName the given category name - optional
     */
    public Long countIcons(String iconNamePattern, String iconCategoryName) {
        // If an icon name pattern was provided, make sure it's the right size
        verifyIconNamePattern(iconNamePattern);

        return
            dataService.countIconsByIconNameLikeOrNameNullAndIconCategoryNameOrIconCategoryNameNull(
                iconNamePattern,
                iconCategoryName
            );
    }

    /**
     * Returns a {@link Page page} of {@link AcctIcon icons}, with the given page number and of the given
     * page size, for which the {@link AcctIcon#getIconName() icon name} matches the given name pattern
     * and which are part of the {@link AcctIconCategory icon category} with the given icon category name.
     * If the name pattern is not given then icons with all names are returned. If the icon category name
     * is not given then icons from all categories are returned.
     *
     * @param iconNamePattern  the given name pattern - optional - at least 3 characters long
     * @param iconCategoryName the given icon category name - optional
     * @param pageNumber       the given page number
     * @param pageSize         the given page size
     */
    public Page<AcctIcon> getIcons(String iconNamePattern, String iconCategoryName, int pageNumber, int pageSize) {
        // If an icon name pattern was provided, make sure it's the right size
        verifyIconNamePattern(iconNamePattern);

        // Fetch the page
        return
            dataService.findIconsByIconNameLikeOrNameNullAndIconCategoryNameOrIconCategoryNameNull(
                iconNamePattern, iconCategoryName, pageNumber, pageSize
            );
    }

    /**
     * Creates a new {@link AcctIncomeOrExpenseItemCategory income or expense item category}
     * or updates an existing one with the given properties, based on the existence of the
     * given income or expense item category UUID. If the UUID is given then the category
     * with the given UUID is looked up and updated. If it does not exist, an exception is
     * thrown. If the UUID is not given then a new category is created with a generated UUID.
     * An exception is thrown in case of constraint violation exceptions.
     *
     * @param incomeOrExpenseItemCategoryUUID        the given income or expense item category UUID
     * @param incomeOrExpenseItemCategoryName        the name to be set to the income or expense item category
     * @param incomeOrExpenseItemCategoryDescription the description to be set to the income or expense item category
     * @param incomeOrExpenseItemCategoryIconUUID    the UUID of the icon to represent the income or expense item category in the UI
     * @return a reference to the persisted {@link AcctIncomeOrExpenseItemCategory income or expense item category}
     */
    @Transactional
    public AcctIncomeOrExpenseItemCategory saveIncomeOrExpenseItemCategory(
        String incomeOrExpenseItemCategoryUUID,
        String incomeOrExpenseItemCategoryName,
        String incomeOrExpenseItemCategoryDescription,
        String incomeOrExpenseItemCategoryIconUUID
    ) {
        // Get or create the category based on the existence of the UUID
        final Optional<AcctIncomeOrExpenseItemCategory> optionalCategory =
            getOrCreateOptionalEntity(
                incomeOrExpenseItemCategoryUUID,
                dataService::findIncomeOrExpenseItemCategoryByIncomeOrExpenseItemCategoryUUID,
                dataService::createNewIncomeOrExpenseItemCategory,
                AcctIncomeOrExpenseItemCategory::setIncomeOrExpenseItemCategoryUUID
            );

        // If the category was not found, throw an exception
        if (optionalCategory.isEmpty()) {
            throw
                new AcctCatalogServiceIncomeOrExpenseItemCategoryNotFoundException(
                    errors,
                    incomeOrExpenseItemCategoryUUID
                );
        }

        // Get the category
        final AcctIncomeOrExpenseItemCategory category = optionalCategory.get();

        // Populate the category attributes
        category.setIncomeOrExpenseItemCategoryName(incomeOrExpenseItemCategoryName);
        category.setIncomeOrExpenseItemCategoryDescription(incomeOrExpenseItemCategoryDescription);
        category.setIncomeOrExpenseItemCategoryIconUUID(incomeOrExpenseItemCategoryIconUUID);

        // Persist the category and return a reference
        try {
            return dataService.saveIncomeOrExpenseItemCategory(category);
        }
        // Throw a service layer exception if a constraint violation exception occurs
        catch (DataIntegrityViolationException e) {
            throw
                new AcctCatalogServiceIncomeOrExpenseItemCategoryConstraintViolationException(
                    errors,
                    incomeOrExpenseItemCategoryName,
                    e
                );
        }
    }

    /**
     * Returns a collection of all the {@link AcctIncomeOrExpenseItemCategory income or expense item categories}
     * registered in the catalog
     */
    public Collection<AcctIncomeOrExpenseItemCategory> getIncomeOrExpenseItemCategories() {
        return dataService.findAllIncomeOrExpenseItemCategories();
    }

    /**
     * Deletes the {@link AcctIncomeOrExpenseItemCategory income or expense item categories}
     * identified by the UUIDs in the given collection of income or expense item categories.
     * Also deletes any {@link AcctIncomeOrExpenseItemSubcategory sub-categories} contained
     * by the deleted categories as well as all the {@link AcctIncomeOrExpenseItem items}
     * that are contained by the aforementioned sub-categories. <br />
     * <br />
     * If any of the referenced categories does not exist in the catalog, an exception is
     * thrown. <br />
     * <br />
     * If any of the entities to be deleted is in use, an exception is thrown
     *
     * @param incomeOrExpenseItemCategoryUUIDs the given list of income or expense item categories
     */
    @Transactional
    public void deleteIncomeOrExpenseItemCategories(Collection<String> incomeOrExpenseItemCategoryUUIDs) {
        // Make sure the UUIDs are distinct
        final Set<String> distinctCategoryUUIDs = new HashSet<>(incomeOrExpenseItemCategoryUUIDs);

        // Find the categories
        final Collection<AcctIncomeOrExpenseItemCategory> foundCategories =
            dataService.findIncomeOrExpenseItemCategoriesByIncomeOrExpenseItemCategoryUUIDIn(distinctCategoryUUIDs);

        // If any of the referenced categories is not found, throw an exception
        throwExceptionIfAnyEntityIsNotFound(
            foundCategories,
            AcctIncomeOrExpenseItemCategory::getIncomeOrExpenseItemCategoryUUID,
            incomeOrExpenseItemCategoryUUIDs,
            notFoundUUIDs -> new AcctCatalogServiceIncomeOrExpenseItemCategoryNotFoundException(
                errors,
                notFoundUUIDs.stream().findAny().orElseThrow()
            )
        );

        // Find all the sub-categories contained by the categories
        final Collection<AcctIncomeOrExpenseItemSubcategory> connectedSubcategories =
            dataService.findIncomeOrExpenseItemSubcategoriesByIncomeOrExpenseItemCategoryIn(foundCategories);

        // Find all the items contained by all the sub-categories contained by the categories
        final Collection<AcctIncomeOrExpenseItem> connectedItems =
            dataService.findIncomeOrExpenseItemsByIncomeOrExpenseItemSubcategoryIn(connectedSubcategories);

        // Get the UUIDs of any items that might be in use
        final Collection<String> inUseIncomeOrExpenseItemUUIDs =
            inUseEndpointClient.getItemsInUseOfType(
                ObjectTypes.INCOME_OR_EXPENSE_ITEM.name(),
                connectedItems.stream().map(AcctIncomeOrExpenseItem::getIncomeOrExpenseItemUUID).toList()
            );

        // If any item is in use, throw an exception
        if (!inUseIncomeOrExpenseItemUUIDs.isEmpty()) {
            throw
                new AcctCatalogServiceIncomeOrExpenseItemInUseException(
                    errors,
                    inUseIncomeOrExpenseItemUUIDs
                );
        }

        // Delete the items
        dataService.deleteIncomeOrExpenseItems(connectedItems);

        // Delete the sub-categories
        dataService.deleteIncomeOrExpenseItemSubcategories(connectedSubcategories);

        // Delete the categories
        dataService.deleteIncomeOrExpenseItemCategories(foundCategories);
    }

    /**
     * Creates a new {@link AcctIncomeOrExpenseItemSubcategory income or expense item subccategory}
     * or updates an existing one with the given properties, based on the existence of the given
     * income or expense item subcategory UUID. If the UUID is given then the subcategory with the
     * given UUID is looked up and updated. If it does not exist, an exception is thrown. If the UUID
     * is not given then a new subcategory is created with a generated UUID. An exception is thrown
     *
     * @param incomeOrExpenseItemCategoryUUID           the parent category of the subcategory being saved
     * @param incomeOrExpenseItemSubcategoryUUID        the given income or expense item subcategory UUID
     * @param incomeOrExpenseItemSubcategoryName        the name to be set to the income or expense item subcategory
     * @param incomeOrExpenseItemSubcategoryDescription the description to be set to the income or expense item subcategory
     * @param incomeOrExpenseItemSubcategoryIconUUID    the UUID of the icon to represent the income or expense item subcategory in the UI
     * @return a reference to the persisted {@link AcctIncomeOrExpenseItemCategory income or expense item category}
     */
    @Transactional
    public AcctIncomeOrExpenseItemSubcategory saveIncomeOrExpenseItemSubcategory(
        String incomeOrExpenseItemCategoryUUID,
        String incomeOrExpenseItemSubcategoryUUID,
        String incomeOrExpenseItemSubcategoryName,
        String incomeOrExpenseItemSubcategoryDescription,
        String incomeOrExpenseItemSubcategoryIconUUID
    ) {
        // Get a reference to the parent category or throw an exception
        AcctIncomeOrExpenseItemCategory parentCategory =
            findIncomeOrExpenseItemCategory(incomeOrExpenseItemCategoryUUID);

        // Get or create the category based on the existence of the UUID
        final Optional<AcctIncomeOrExpenseItemSubcategory> optionalSubcategory =
            getOrCreateOptionalEntity(
                incomeOrExpenseItemSubcategoryUUID,
                dataService::findIncomeOrExpenseItemSubcategoryByIncomeOrExpenseItemSubcategoryUUID,
                dataService::createNewIncomeOrExpenseItemSubcategory,
                AcctIncomeOrExpenseItemSubcategory::setIncomeOrExpenseItemSubcategoryIconUUID
            );

        // If the category was not found, throw an exception
        if (optionalSubcategory.isEmpty()) {
            throw
                new AcctCatalogServiceIncomeOrExpenseItemSubcategoryNotFoundException(
                    errors,
                    incomeOrExpenseItemSubcategoryUUID
                );
        }

        // Get the subcategory
        final AcctIncomeOrExpenseItemSubcategory subcategory = optionalSubcategory.get();

        // Populate the category attributes
        subcategory.setIncomeOrExpenseItemCategory(parentCategory);
        subcategory.setIncomeOrExpenseItemSubcategoryName(incomeOrExpenseItemSubcategoryName);
        subcategory.setIncomeOrExpenseItemSubcategoryDescription(incomeOrExpenseItemSubcategoryDescription);
        subcategory.setIncomeOrExpenseItemSubcategoryIconUUID(incomeOrExpenseItemSubcategoryIconUUID);

        // Persist the category and return a reference
        try {
            return dataService.saveIncomeOrExpenseItemSubcategory(subcategory);
        }
        // Throw a service layer exception if a constraint violation exception occurs
        catch (DataIntegrityViolationException e) {
            throw
                new AcctCatalogServiceIncomeOrExpenseItemSubcategoryConstraintViolationException(
                    errors,
                    incomeOrExpenseItemSubcategoryName,
                    e
                );
        }
    }

    /**
     * Returns a collection of {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategories}
     * that are part of the {@link AcctIncomeOrExpenseItemCategory income or expense item category} referenced
     * by the given income or expense item subcategory UUID. If there is no category with the given UUID then
     * an exception is throw.
     *
     * @param incomeOrExpenseItemCategoryUUID the given income or expense item category UUID
     */
    public Collection<AcctIncomeOrExpenseItemSubcategory> getIncomeOrExpenseItemSubcategories(
        String incomeOrExpenseItemCategoryUUID
    ) {
        // Find the income or expense item category or throw an exception
        final AcctIncomeOrExpenseItemCategory parentCategory =
            findIncomeOrExpenseItemCategory(incomeOrExpenseItemCategoryUUID);

        // Get and return a collection of all the subcategories in the category
        return
            dataService.findIncomeOrExpenseItemSubcategoriesByIncomeOrExpenseItemCategoryIn(
                List.of(parentCategory)
            );
    }

    /**
     * Deletes the {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategories}
     * represented by the UUIDs in the given collection of income or expense item subcategory UUIDs.<br />
     * <br />
     * If any of the referenced subcategories does not exist in the catalog, an exception is thrown.<br />
     * <br />
     * If any of the referenced entities is in use, an exception is thrown.
     *
     * @param incomeOrExpenseItemSubcategoryUUIDs the given collection of income or expense item subcategory UUIDs
     */
    public void deleteIncomeOrExpenseItemSubcategories(Collection<String> incomeOrExpenseItemSubcategoryUUIDs) {
        // Make sure the UUIDs are distinct
        final Set<String> distinctSubcategoryUUIDs = new HashSet<>(incomeOrExpenseItemSubcategoryUUIDs);

        // Find the subcategories
        final Collection<AcctIncomeOrExpenseItemSubcategory> foundSubcategories =
            dataService.findIncomeOrExpenseItemSubcategoriesByIncomeOrExpenseItemSubcategoryUUIDIn(
                distinctSubcategoryUUIDs
            );

        // If any of the referenced categories is not found, throw an exception
        throwExceptionIfAnyEntityIsNotFound(
            foundSubcategories,
            AcctIncomeOrExpenseItemSubcategory::getIncomeOrExpenseItemSubcategoryUUID,
            incomeOrExpenseItemSubcategoryUUIDs,
            notFoundUUIDs -> new AcctCatalogServiceIncomeOrExpenseItemSubcategoryNotFoundException(
                errors,
                notFoundUUIDs.stream().findAny().orElseThrow()
            )
        );

        // Find all the items contained by the referenced sub-categories
        final Collection<AcctIncomeOrExpenseItem> connectedItems =
            dataService.findIncomeOrExpenseItemsByIncomeOrExpenseItemSubcategoryIn(foundSubcategories);

        // Throw an exception if any of the connected items is in use
        verifyIncomeOrExpenseItemsUsage(connectedItems);

        // Delete the items
        dataService.deleteIncomeOrExpenseItems(connectedItems);

        // Delete the sub-categories
        dataService.deleteIncomeOrExpenseItemSubcategories(foundSubcategories);
    }

    /**
     * Persists the {@link AcctIncomeOrExpenseItem income or expense item} with the given income or expense item UUID
     * within the {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategory} with the given income
     * or expense item subcategory UUID. If no income or expense item UUID is given then a new income or expense item
     * is created. <br />
     * <br />
     * Throws an exception if there is no {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategory}
     * with the given income or expense item subcategory UUID.<br />
     * <br />
     * Throws an exception if the income or expense item UUID is given and there is no
     * {@link AcctIncomeOrExpenseItem income or expense item} with that UUID in the catalog.<br />
     * <br />
     * Throws exceptions if any constraint violation exceptions occur
     *
     * @param incomeOrExpenseItemSubcategoryUUID the given income or expense item subcategory UUID
     * @param incomeOrExpenseItemUUID            the given income or expense item UUID
     * @param incomeOrExpenseItemName            the name to set to the persisted income or expense item
     * @param incomeOrExpenseItemDescription     the description to set to the persisted income or expense item
     * @param incomeOrExpenseItemIconUUID        the icon UUID to set to the persisted income or expense item
     * @return a reference to the persisted entity
     */
    public AcctIncomeOrExpenseItem saveIncomeOrExpenseItem(
        String incomeOrExpenseItemSubcategoryUUID,
        String incomeOrExpenseItemUUID,
        String incomeOrExpenseItemName,
        String incomeOrExpenseItemDescription,
        String incomeOrExpenseItemIconUUID
    ) {
        // Get a reference to the parent subcategory or throw an exception
        AcctIncomeOrExpenseItemSubcategory parentSubcategory =
            findIncomeOrExpenseItemSubcategory(incomeOrExpenseItemSubcategoryUUID);

        // Get or create the income or expense item based on the existence of the UUID
        final Optional<AcctIncomeOrExpenseItem> optionalItem =
            getOrCreateOptionalEntity(
                incomeOrExpenseItemUUID,
                dataService::findIncomeOrExpenseItemByIncomeOrExpenseItemUUID,
                dataService::createNewIncomeOrExpenseItem,
                AcctIncomeOrExpenseItem::setIncomeOrExpenseItemUUID
            );

        // If the item was not found, throw an exception
        final AcctIncomeOrExpenseItem item =
            optionalItem.orElseThrow(
                () -> new AcctCatalogServiceIncomeOrExpenseItemNotFoundException(errors, incomeOrExpenseItemUUID)
            );

        // Set the parent subcategory if the item
        item.setIncomeOrExpenseItemSubcategory(parentSubcategory);

        // Set the properties of the acquired item
        item.setIncomeOrExpenseItemName(incomeOrExpenseItemName);
        item.setIncomeOrExpenseItemDescription(incomeOrExpenseItemDescription);
        item.setIncomeOrExpenseItemIconUUID(incomeOrExpenseItemIconUUID);

        // Persist the category and return a reference
        try {
            return dataService.saveIncomeOrExpenseItem(item);
        }
        // Throw a service layer exception if a constraint violation exception occurs
        catch (DataIntegrityViolationException e) {
            throw
                new AcctCatalogServiceIncomeOrExpenseItemConstraintViolationException(
                    errors,
                    incomeOrExpenseItemName,
                    e
                );
        }
    }

    /**
     * Returns a collection of all the {@link AcctIncomeOrExpenseItem income or expense items}
     * within the {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategory}
     * referenced by the given income or expense item subcategory UUID
     *
     * @param incomeOrExpenseItemSubcategoryUUID the given income or expense item subcategory UUID
     */
    public Collection<AcctIncomeOrExpenseItem> getIncomeOrExpenseItems(String incomeOrExpenseItemSubcategoryUUID) {
        // Get a reference to the parent subcategory or throw an exception
        AcctIncomeOrExpenseItemSubcategory parentSubcategory =
            findIncomeOrExpenseItemSubcategory(incomeOrExpenseItemSubcategoryUUID);

        // Return all the items in the subcategory
        return dataService.findIncomeOrExpenseItemsByIncomeOrExpenseItemSubcategoryIn(List.of(parentSubcategory));
    }

    /**
     * Deletes the {@link AcctIncomeOrExpenseItem income or expense items} identified by the UUIDs
     * in the given collection of income or expense item UUIDs. Throws an exception if any of the
     * referenced items does not exist or is in use.
     *
     * @param incomeOrExpenseItemUUIDs the given collection of income or expense item UUIDs
     */
    public void deleteIncomeOrExpenseItems(Collection<String> incomeOrExpenseItemUUIDs) {
        // Find the items
        final Collection<AcctIncomeOrExpenseItem> items =
            dataService.findIncomeOrExpenseItemsByIncomeOrExpenseItemUUIDIn(incomeOrExpenseItemUUIDs);

        // If any of the referenced items is not found, throw an exception
        throwExceptionIfAnyEntityIsNotFound(
            items,
            AcctIncomeOrExpenseItem::getIncomeOrExpenseItemUUID,
            incomeOrExpenseItemUUIDs,
            notFoundUUIDs -> new AcctCatalogServiceIncomeOrExpenseItemNotFoundException(
                errors,
                notFoundUUIDs.stream().findAny().orElseThrow()
            )
        );

        // Throw an exception if any of the items is in use
        verifyIncomeOrExpenseItemsUsage(items);

        // If all is well then delete the items
        dataService.deleteIncomeOrExpenseItems(items);
    }

    /**
     * Persists the bank with the given bank UUID and the given properties. If a bank UUID is not given,
     * a new entity is created. If the bank UUID is given and the referenced bank does not exist, an
     * exception is thrown. If a constraint violation occurs, an exception is thrown.
     *
     * @param bankUUID           the given bank UUID
     * @param bankCode           The unique code given to the bank in the ACCT ecosystem (i.e. ING, BNR, BT, BCR, etc.)
     * @param bankName           The unique human-readable name of the bank
     * @param internetBankingURL The optional internet banking URL for the bank
     * @param bankIconUUID       The UUID of the optional icon that represents the bank on the ACCT GUI
     * @return a reference to the persisted entity
     */
    public AcctBank saveBank(
        String bankUUID,
        String bankCode,
        String bankName,
        String internetBankingURL,
        String bankIconUUID
    ) {
        // Get or create the bank based on the existence of the UUID
        final Optional<AcctBank> optionalBank =
            getOrCreateOptionalEntity(
                bankUUID,
                dataService::findBankByBankUUID,
                dataService::createNewBank,
                AcctBank::setBankUUID
            );

        // If the bank does not exist, throw an exception
        final AcctBank bank =
            optionalBank.orElseThrow(
                () -> new AcctCatalogServiceBankNotFoundException(errors, bankUUID)
            );

        // Set the bank properties
        bank.setBankCode(bankCode);
        bank.setBankName(bankName);
        bank.setInternetBankingURL(internetBankingURL);
        bank.setBankIconUUID(bankIconUUID);

        // Persist the bank and return a reference
        try {
            return dataService.saveBank(bank);
        }
        // Throw a service layer exception if a constraint violation exception occurs
        catch (DataIntegrityViolationException e) {
            throw
                new AcctCatalogServiceBankConstraintViolationException(
                    errors,
                    bankCode,
                    e
                );
        }
    }

    /**
     * Returns a collection of all the {@link AcctBank banks} registered in the catalog
     */
    public Collection<AcctBank> getBanks() {
        return dataService.findAllBanks();
    }

    /**
     * Deletes the {@link AcctBank banks} referenced by the UUIDs in the given collection
     * of bank UUIDs. If any of the referenced banks cannot be found, an exception is thrown.
     * If any of the banks is in use, an exception is thrown.
     *
     * @param bankUUIDs the given collection of bank UUIDs.
     */
    public void deleteBanks(Collection<String> bankUUIDs) {
        // Find the banks
        final Collection<AcctBank> banks =
            dataService.findBanksByBankUUIDIn(bankUUIDs);

        // If any of the referenced items is not found, throw an exception
        throwExceptionIfAnyEntityIsNotFound(
            banks,
            AcctBank::getBankUUID,
            bankUUIDs,
            notFoundUUIDs -> new AcctCatalogServiceBankNotFoundException(
                errors,
                notFoundUUIDs.stream().findAny().orElseThrow()
            )
        );

        // Get the UUIDs of any items that might be in use
        final Collection<String> inUseBankUUIDs =
            inUseEndpointClient.getItemsInUseOfType(ObjectTypes.BANK.name(), bankUUIDs);

        // If any item is in use, throw an exception
        if (!inUseBankUUIDs.isEmpty()) {
            throw
                new AcctCatalogServiceBankInUseException(
                    errors,
                    inUseBankUUIDs
                );
        }

        // If all is well then delete the items
        dataService.deleteBanks(banks);
    }

    /**
     * Creates a new currency or updates an existing currency in the catalog with the given currency code,
     * the given currency name and the given currency icon UUID. The decision to create or update is taken
     * based on the existence of the given currency UUID. <br />
     * <br />
     * If the currency UUID is given and the referenced currency does not exist, an exception is thrown.<br />
     * <br />
     * If a currency with the given currency code already exists in the catalog, an exception is thrown.
     *
     * @param currencyUUID     the given currency UUID
     * @param currencyCode     the given currency code
     * @param currencyName     the currency name
     * @param currencyIconUUID the given currency icon UUID
     * @return a reference to the created or updated entity
     */
    public AcctCurrency saveCurrency(
        String currencyUUID,
        String currencyCode,
        String currencyName,
        String currencyIconUUID
    ) {
        // Get or create the bank based on the existence of the UUID
        final Optional<AcctCurrency> optionalCurrency =
            getOrCreateOptionalEntity(
                currencyUUID,
                dataService::findCurrencyByCurrencyUUID,
                dataService::createNewCurrency,
                AcctCurrency::setCurrencyUUID
            );

        // If the currency does not exist, throw an exception
        final AcctCurrency currency =
            optionalCurrency.orElseThrow(
                () -> new AcctCatalogServiceCurrencyNotFoundException(errors, currencyUUID)
            );

        // Set the bank properties
        currency.setCurrencyCode(currencyCode);
        currency.setCurrencyName(currencyName);
        currency.setCurrencyIconUUID(currencyIconUUID);

        // Persist the bank and return a reference
        try {
            return dataService.saveCurrency(currency);
        }
        // Throw a service layer exception if a constraint violation exception occurs
        catch (DataIntegrityViolationException e) {
            throw
                new AcctCatalogServiceCurrencyConstraintViolationException(
                    errors,
                    currencyCode,
                    e
                );
        }
    }

    /**
     * Returns a collection of all currencies in the catalog
     */
    public Collection<AcctCurrency> getCurrencies() {
        return dataService.findAllCurrencies();
    }

    /**
     * Deletes the {@link AcctCurrency currencies} referenced through the UUIDs in the
     * given collection of currency UUIDs.<br />
     * <br />
     * If any of the referenced currencies cannot be found, an exception is thrown.<br />
     * <br />
     * If any of the referenced currencies is in use, an exception is thrown.
     *
     * @param currencyUUIDs the given collection of currency UUIDs
     */
    public void deleteCurrencies(Collection<String> currencyUUIDs) {
        // Find the currencies
        final Collection<AcctCurrency> currencies =
            dataService.findCurrenciesByCurrencyUUIDIn(currencyUUIDs);

        // If any of the referenced items is not found, throw an exception
        throwExceptionIfAnyEntityIsNotFound(
            currencies,
            AcctCurrency::getCurrencyUUID,
            currencyUUIDs,
            notFoundUUIDs -> new AcctCatalogServiceCurrencyNotFoundException(
                errors,
                notFoundUUIDs.stream().findAny().orElseThrow()
            )
        );

        // Get the UUIDs of any items that might be in use
        final Collection<String> inUseCurrencyUUIDs =
            inUseEndpointClient.getItemsInUseOfType(ObjectTypes.CURRENCY.name(), currencyUUIDs);

        // If any item is in use, throw an exception
        if (!inUseCurrencyUUIDs.isEmpty()) {
            throw
                new AcctCatalogServiceCurrencyInUseException(
                    errors,
                    inUseCurrencyUUIDs
                );
        }

        // If all is well then delete the items
        dataService.deleteCurrencies(currencies);
    }

    private <T> Optional<T> getOrCreateOptionalEntity(
        String entityUUID,
        Function<String, Optional<T>> findFunction,
        Supplier<T> newEntitySupplier,
        BiConsumer<T, String> uuidSettingConsumer
    ) {
        return
            Optional
                .ofNullable(entityUUID)
                .map(findFunction)
                .orElseGet(() -> {
                    T newEntity = newEntitySupplier.get();
                    uuidSettingConsumer.accept(newEntity, UUID.randomUUID().toString());
                    return Optional.of(newEntity);
                });
    }

    private <T> void throwExceptionIfAnyEntityIsNotFound(
        Collection<T> foundEntities,
        Function<T, String> entityUUIDExtractorFunction,
        Collection<String> requestedUUIDs,
        Function<Collection<String>, RuntimeException> exceptionMapperFunction
    ) {
        // Compute the list of found entity UUIDs
        final Collection<String> foundEntityUUIDs =
            foundEntities.stream().map(entityUUIDExtractorFunction).distinct().toList();

        // Check if any entity is not found
        final Collection<String> notFoundEntityUUIDs =
            requestedUUIDs.stream()
                .filter(requestedUUID -> !foundEntityUUIDs.contains(requestedUUID))
                .toList();

        // If any entity is not found, throw an exception
        if (!notFoundEntityUUIDs.isEmpty()) {
            throw exceptionMapperFunction.apply(notFoundEntityUUIDs);
        }
    }

    private void verifyIncomeOrExpenseItemsUsage(Collection<AcctIncomeOrExpenseItem> incomeOrExpenseItems) {
        // Get the UUIDs of any items that might be in use
        final Collection<String> inUseIncomeOrExpenseItemUUIDs =
            inUseEndpointClient.getItemsInUseOfType(
                ObjectTypes.INCOME_OR_EXPENSE_ITEM.name(),
                incomeOrExpenseItems.stream().map(AcctIncomeOrExpenseItem::getIncomeOrExpenseItemUUID).toList()
            );

        // If any item is in use, throw an exception
        if (!inUseIncomeOrExpenseItemUUIDs.isEmpty()) {
            throw
                new AcctCatalogServiceIncomeOrExpenseItemInUseException(
                    errors,
                    inUseIncomeOrExpenseItemUUIDs
                );
        }
    }

    private AcctIncomeOrExpenseItemSubcategory findIncomeOrExpenseItemSubcategory(
        String incomeOrExpenseItemSubcategoryUUID
    ) {
        return
            dataService.findIncomeOrExpenseItemSubcategoryByIncomeOrExpenseItemSubcategoryUUID(
                incomeOrExpenseItemSubcategoryUUID
            ).orElseThrow(() ->
                new AcctCatalogServiceIncomeOrExpenseItemSubcategoryNotFoundException(
                    errors,
                    incomeOrExpenseItemSubcategoryUUID
                )
            );
    }

    private AcctIncomeOrExpenseItemCategory findIncomeOrExpenseItemCategory(String incomeOrExpenseItemCategoryUUID) {
        return
            dataService.findIncomeOrExpenseItemCategoryByIncomeOrExpenseItemCategoryUUID(
                incomeOrExpenseItemCategoryUUID
            ).orElseThrow(() ->
                new AcctCatalogServiceIncomeOrExpenseItemCategoryNotFoundException(
                    errors,
                    incomeOrExpenseItemCategoryUUID
                )
            );
    }

    private void verifyIconNamePattern(String iconNamePattern) {
        if (iconNamePattern != null && iconNamePattern.length() < 3) {
            throw new AcctCatalogServiceIconValidationException(
                errors.ICON_VALIDATION_NAME_PATTERN,
                Map.of("iconNamePattern", iconNamePattern)
            );
        }
    }

    /**
     * Looks up the icon category with the given name. If such an icon category is not found,
     * one is created. A reference is returned in any case.
     *
     * @param iconCategoryName the given name
     */
    private AcctIconCategory resolveIconCategory(String iconCategoryName) {
        return
            dataService.findIconCategoryByIconCategoryName(iconCategoryName)
                .orElseGet(() -> {
                    final AcctIconCategory newIconCategory = dataService.createNewIconCategory();
                    newIconCategory.setIconCategoryName(iconCategoryName);
                    return dataService.saveIconCategory(newIconCategory);
                });
    }

}
