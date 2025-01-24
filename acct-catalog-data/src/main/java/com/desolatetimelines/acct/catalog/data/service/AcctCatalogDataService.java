package com.desolatetimelines.acct.catalog.data.service;

import com.desolatetimelines.acct.catalog.model.AcctIcon;
import com.desolatetimelines.acct.catalog.model.AcctIconCategory;
import com.desolatetimelines.acct.catalog.repository.AcctIconCategoriesRepository;
import com.desolatetimelines.acct.catalog.repository.AcctIconsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Facade for the data layer of the catalog service, allowing for the
 * loading and persisting of catalog data objects
 */
@Service
public class AcctCatalogDataService {

    private final AcctIconCategoriesRepository iconCategoriesRepository;

    private final AcctIconsRepository iconsRepository;

    public AcctCatalogDataService(AcctIconCategoriesRepository iconCategoriesRepository, AcctIconsRepository iconsRepository) {
        this.iconCategoriesRepository = iconCategoriesRepository;
        this.iconsRepository = iconsRepository;
    }

    /**
     * Creates a new {@link AcctIconCategory icon category}
     *
     * @return a reference to the newly created entity
     */
    public AcctIconCategory createNewIconCategory() {
        return iconCategoriesRepository.createNew();
    }

    /**
     * Persists the referenced icon category
     *
     * @param iconCategory the referenced icon category
     * @return a reference to the persisted entity
     */
    public AcctIconCategory saveIconCategory(AcctIconCategory iconCategory) {
        return iconCategoriesRepository.save(iconCategory);
    }

    /**
     * Returns a reference to the {@link AcctIconCategory icon category} with the given name
     * or, if such an icon category does not exist, an empty optional
     *
     * @param iconCategoryName the given name
     */
    public Optional<AcctIconCategory> findIconCategoryByIconCategoryName(String iconCategoryName) {
        return iconCategoriesRepository.findByIconCategoryName(iconCategoryName);
    }

    /**
     * Creates a new instance of {@link AcctIcon}
     *
     * @return a reference to the newly created entity
     */
    public AcctIcon createNewIcon() {
        return iconsRepository.createNew();
    }

    /**
     * Persists the referenced {@link AcctIcon icon}
     *
     * @param icon the referenced icon
     * @return a reference to the persisted entity
     */
    public AcctIcon saveIcon(AcctIcon icon) {
        return iconsRepository.save(icon);
    }

}
