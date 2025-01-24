package com.desolatetimelines.acct.catalog.service;

import com.desolatetimelines.acct.catalog.data.service.AcctCatalogDataService;
import com.desolatetimelines.acct.catalog.exception.AcctCatalogServiceIconConstraintViolationException;
import com.desolatetimelines.acct.catalog.model.AcctIcon;
import com.desolatetimelines.acct.catalog.model.AcctIconCategory;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
