package com.desolatetimelines.acct.catalog.service;

import com.desolatetimelines.acct.catalog.data.service.AcctCatalogDataService;
import com.desolatetimelines.acct.catalog.exception.AcctCatalogServiceIconConstraintViolationException;
import com.desolatetimelines.acct.catalog.exception.AcctCatalogServiceIconNotFoundException;
import com.desolatetimelines.acct.catalog.exception.AcctCatalogServiceIconValidationException;
import com.desolatetimelines.acct.catalog.model.AcctIcon;
import com.desolatetimelines.acct.catalog.model.AcctIconCategory;
import com.desolatetimelines.acct.common.model.Page;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Main class of the services layer of the ACCT catalog
 */
@Service
public class AcctCatalogService {

    private final AcctCatalogDataService dataService;

    private final AcctCatalogErrorCodesRegistryService errors;

    public AcctCatalogService(
        AcctCatalogDataService dataService,
        AcctCatalogErrorCodesRegistryService errors
    ) {
        this.dataService = dataService;
        this.errors = errors;
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
     * Returns a set of all the {@link AcctIconCategory icon categories} registered in the catalog
     */
    public Set<AcctIconCategory> getIconCategories() {
        return dataService.findAllIconCategories();
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
