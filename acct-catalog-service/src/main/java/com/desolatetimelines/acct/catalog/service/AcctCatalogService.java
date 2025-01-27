package com.desolatetimelines.acct.catalog.service;

import com.desolatetimelines.acct.catalog.data.service.AcctCatalogDataService;
import com.desolatetimelines.acct.catalog.exception.*;
import com.desolatetimelines.acct.catalog.model.AcctIcon;
import com.desolatetimelines.acct.catalog.model.AcctIconCategory;
import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.common.model.ObjectTypes;
import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.usage.ws.client.RESTInUseEndpointClient;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Main class of the services layer of the ACCT catalog
 */
@Service
public class AcctCatalogService {

    private final AcctCatalogDataService dataService;

    private final AcctCatalogErrorCodesRegistryService errors;

    private final RESTInUseEndpointClient inUseEndpointClient;

    public AcctCatalogService(
        AcctCatalogDataService dataService,
        AcctCatalogErrorCodesRegistryService errors,
        RESTInUseEndpointClient inUseEndpointClient
    ) {
        this.dataService = dataService;
        this.errors = errors;
        this.inUseEndpointClient = inUseEndpointClient;
    }

    /**
     * Returns a set of all the {@link AcctIconCategory icon categories} registered in the catalog
     */
    public Set<AcctIconCategory> getIconCategories() {
        return dataService.findAllIconCategories();
    }

    /**
     * Creates an {@link AcctIcon icon} with the given details and returns a reference to the
     * newly created entity
     *
     * @param iconName         The name that uniquely identifies the icon within its category
     * @param iconCategoryName The name of the category that contains the icon (if it doesn't exist, it's created)
     * @param iconBytesBase64  The base64-encoded bytes of the icon
     */
    @Transactional
    public AcctIcon createIcon(String iconName, String iconCategoryName, String iconBytesBase64) {
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
        final Set<String> foundIconUUIDs = foundIcons.stream().map(AcctIcon::getIconUUID).collect(Collectors.toSet());

        // Check if there are any icons that are not found
        final List<String> notFoundIconUUIDs =
            iconUUIDs.stream()
                .filter(iconUUID -> !foundIconUUIDs.contains(iconUUID))
                .toList();

        // If any of the icons could not be found, throw an exception
        if (!notFoundIconUUIDs.isEmpty()) {
            throw new AcctCatalogServiceIconNotFoundException(errors, notFoundIconUUIDs.get(0));
        }

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
    public AcctIncomeOrExpenseItemCategory saveIncomeOrExpenseItem(
        String incomeOrExpenseItemCategoryUUID,
        String incomeOrExpenseItemCategoryName,
        String incomeOrExpenseItemCategoryDescription,
        String incomeOrExpenseItemCategoryIconUUID
    ) {
        // Get or create the category based on the existence of the UUID
        final Optional<AcctIncomeOrExpenseItemCategory> optionalCategory =
            Optional
                .ofNullable(incomeOrExpenseItemCategoryUUID)
                .map(dataService::findIncomeOrExpenseItemCategoryByIncomeOrExpenseItemCategoryUUID)
                .orElseGet(() -> {
                    AcctIncomeOrExpenseItemCategory newCategory = dataService.createNewIncomeOrExpenseItemCategory();
                    newCategory.setIncomeOrExpenseItemCategoryUUID(UUID.randomUUID().toString());
                    return Optional.of(newCategory);
                });

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
