package com.desolatetimelines.acct.catalog.data.service;

import com.desolatetimelines.acct.catalog.model.AcctIcon;
import com.desolatetimelines.acct.catalog.model.AcctIconCategory;
import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.catalog.repository.AcctIconCategoriesRepository;
import com.desolatetimelines.acct.catalog.repository.AcctIconsRepository;
import com.desolatetimelines.acct.catalog.repository.AcctIncomeOrExpenseItemCategoriesRepository;
import com.desolatetimelines.acct.common.model.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * Facade for the data layer of the catalog service, allowing for the
 * loading and persisting of catalog data objects
 */
@Service
public class AcctCatalogDataService {

    private final AcctIconCategoriesRepository iconCategoriesRepository;

    private final AcctIconsRepository iconsRepository;

    private final AcctIncomeOrExpenseItemCategoriesRepository incomeOrExpenseItemCategoriesRepository;

    public AcctCatalogDataService(
        AcctIconCategoriesRepository iconCategoriesRepository,
        AcctIconsRepository iconsRepository,
        AcctIncomeOrExpenseItemCategoriesRepository incomeOrExpenseItemCategoriesRepository
    ) {
        this.iconCategoriesRepository = iconCategoriesRepository;
        this.iconsRepository = iconsRepository;
        this.incomeOrExpenseItemCategoriesRepository = incomeOrExpenseItemCategoriesRepository;
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
     * Returns a set of all the {@link AcctIconCategory icon categories} registered in the catalog
     */
    public Set<AcctIconCategory> findAllIconCategories() {
        return iconCategoriesRepository.findAll();
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

    /**
     * Returns a count of all icons that match the given name pattern and are part of the given category
     * name. If a name pattern is not given then icons with all names are counted. If a category name is
     * not given then icons from all categories are counted.
     *
     * @param iconNamePattern  the given name pattern
     * @param iconCategoryName the given category name
     */
    public Long countIconsByIconNameLikeOrNameNullAndIconCategoryNameOrIconCategoryNameNull(
        String iconNamePattern,
        String iconCategoryName
    ) {
        return
            iconsRepository.countByIconNameLikeOrNameNullAndIconCategoryNameOrIconCategoryNameNull(
                iconNamePattern,
                iconCategoryName
            );
    }

    /**
     * Returns a page of all icons that match the given name pattern and are part of the given category
     * name. If a name pattern is not given then icons with all names are counted. If a category name is
     * not given then icons from all categories are counted.
     *
     * @param iconNamePattern  the given name pattern
     * @param iconCategoryName the given category name
     * @param pageNumber       the 0-based number of the page to be returned
     * @param pageSize         the size of the page to be returned
     */
    public Page<AcctIcon> findIconsByIconNameLikeOrNameNullAndIconCategoryNameOrIconCategoryNameNull(
        String iconNamePattern,
        String iconCategoryName,
        int pageNumber,
        int pageSize
    ) {
        return
            iconsRepository.findAllByIconNameLikeOrNameNullAndIconCategoryNameOrIconCategoryNameNull(
                iconNamePattern,
                iconCategoryName,
                pageNumber,
                pageSize
            );
    }

    /**
     * Returns the {@link AcctIcon icon} with the given {@link AcctIcon#getIconUUID() icon UUID}
     * or an empty optional
     *
     * @param iconUUID the given icon UUID
     */
    public Optional<AcctIcon> findIconByIconUUID(String iconUUID) {
        return iconsRepository.findFirstIconByIconUUID(iconUUID);
    }

    /**
     * Returns a collection of {@link AcctIcon icons} for which the {@link AcctIcon#getIconUUID() UUID}
     * can be found in the given collection of icon UUIDs
     *
     * @param iconUUIDs the given collection of icon UUIDs
     */
    public Collection<AcctIcon> findIconsByIconUUIDIn(Collection<String> iconUUIDs) {
        return iconsRepository.findAllByIconUUIDIn(iconUUIDs);
    }

    /**
     * Deletes all the icons in the given collection of icons
     *
     * @param icons the given collection of icons
     */
    public void deleteIcons(Collection<AcctIcon> icons) {
        iconsRepository.delete(icons);
    }

    /**
     * Creates a new instance of {@link AcctIncomeOrExpenseItemCategory income or expense item category}
     */
    public AcctIncomeOrExpenseItemCategory createNewIncomeOrExpenseItemCategory() {
        return incomeOrExpenseItemCategoriesRepository.createNew();
    }

    /**
     * Returns a reference to the {@link AcctIncomeOrExpenseItemCategory income or expense item category}
     * with the given income or expense item category UUID or an empty optional if such a category does
     * not exist
     *
     * @param incomeOrExpenseItemCategoryUUID the given income or expense item category UUID
     */
    public Optional<AcctIncomeOrExpenseItemCategory> findIncomeOrExpenseItemCategoryByIncomeOrExpenseItemCategoryUUID(
        String incomeOrExpenseItemCategoryUUID
    ) {
        return
            incomeOrExpenseItemCategoriesRepository
                .findFirstByIncomeOrExpenseItemCategoryUUID(incomeOrExpenseItemCategoryUUID);
    }

    /**
     * Persists the referenced {@link AcctIncomeOrExpenseItemCategory income or expense item category}
     *
     * @param incomeOrExpenseItemCategory the referenced income or expense item category
     * @return a reference to the persisted entity
     */
    public AcctIncomeOrExpenseItemCategory saveIncomeOrExpenseItemCategory(
        AcctIncomeOrExpenseItemCategory incomeOrExpenseItemCategory
    ) {
        return incomeOrExpenseItemCategoriesRepository.save(incomeOrExpenseItemCategory);
    }

}
