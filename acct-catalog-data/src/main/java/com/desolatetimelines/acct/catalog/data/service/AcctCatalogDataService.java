package com.desolatetimelines.acct.catalog.data.service;

import com.desolatetimelines.acct.catalog.model.*;
import com.desolatetimelines.acct.catalog.repository.*;
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

    private final AcctIncomeOrExpenseItemSubcategoriesRepository incomeOrExpenseItemSubcategoriesRepository;

    private final AcctIncomeOrExpenseItemsRepository incomeOrExpenseItemsRepository;

    public AcctCatalogDataService(
        AcctIconCategoriesRepository iconCategoriesRepository,
        AcctIconsRepository iconsRepository,
        AcctIncomeOrExpenseItemCategoriesRepository incomeOrExpenseItemCategoriesRepository,
        AcctIncomeOrExpenseItemSubcategoriesRepository incomeOrExpenseItemSubcategoriesRepository,
        AcctIncomeOrExpenseItemsRepository incomeOrExpenseItemsRepository
    ) {
        this.iconCategoriesRepository = iconCategoriesRepository;
        this.iconsRepository = iconsRepository;
        this.incomeOrExpenseItemCategoriesRepository = incomeOrExpenseItemCategoriesRepository;
        this.incomeOrExpenseItemSubcategoriesRepository = incomeOrExpenseItemSubcategoriesRepository;
        this.incomeOrExpenseItemsRepository = incomeOrExpenseItemsRepository;
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

    /**
     * Returns a list of all {@link AcctIncomeOrExpenseItemCategory income or expense item categories}
     */
    public Collection<AcctIncomeOrExpenseItemCategory> findAllIncomeOrExpenseItemCategories() {
        return incomeOrExpenseItemCategoriesRepository.findAll();
    }

    /**
     * Returns a collection of {@link AcctIncomeOrExpenseItemCategory income or expense item categories}
     * identified by the UUIDs in the given collection of income or expense item category UUIDs
     *
     * @param incomeOrExpenseItemCategoryUUIDs the given collection of income or expense item category UUIDs
     */
    public Collection<AcctIncomeOrExpenseItemCategory> findIncomeOrExpenseItemCategoryByIncomeOrExpenseItemCategoryUUIDIn(
        Collection<String> incomeOrExpenseItemCategoryUUIDs
    ) {
        return
            incomeOrExpenseItemCategoriesRepository.findByIncomeOrExpenseItemCategoryUUIDIn(
                incomeOrExpenseItemCategoryUUIDs
            );
    }

    /**
     * Deletes the {@link AcctIncomeOrExpenseItemCategory income or expense item categories} in the given
     * collection of income or expense item categories
     *
     * @param incomeOrExpenseItemCategories the given collection of income or expense item categories
     */
    public void deleteIncomeOrExpenseItemCategories(
        Collection<AcctIncomeOrExpenseItemCategory> incomeOrExpenseItemCategories
    ) {
        incomeOrExpenseItemCategoriesRepository.deleteAll(incomeOrExpenseItemCategories);
    }

    /**
     * Creates a new instance of {@link AcctIncomeOrExpenseItemSubcategory}
     *
     * @return a reference to the newly created entity
     */
    public AcctIncomeOrExpenseItemSubcategory createNewIncomeOrExpenseItemSubcategory() {
        return incomeOrExpenseItemSubcategoriesRepository.createNew();
    }

    /**
     * Persists the referenced {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategory}
     *
     * @param incomeOrExpenseItemSubcategory the referenced income or expense item subcategory
     * @return a reference to the persisted entity
     */
    public AcctIncomeOrExpenseItemSubcategory saveIncomeOrExpenseItemSubcategory(
        AcctIncomeOrExpenseItemSubcategory incomeOrExpenseItemSubcategory
    ) {
        return incomeOrExpenseItemSubcategoriesRepository.save(incomeOrExpenseItemSubcategory);
    }

    /**
     * Returns a collection of {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategories}
     * contained by the {@link AcctIncomeOrExpenseItemCategory income or expense item categories} in the given
     * collection of income or expense item categories
     *
     * @param incomeOrExpenseItemCategories the given collection of income or expense item categories
     */
    public Collection<AcctIncomeOrExpenseItemSubcategory> findIncomeOrExpenseItemSubcategoriesByIncomeOrExpenseItemCategoryIn(
        Collection<AcctIncomeOrExpenseItemCategory> incomeOrExpenseItemCategories
    ) {
        return
            incomeOrExpenseItemSubcategoriesRepository.findAllByByIncomeOrExpenseItemCategoryIn(
                incomeOrExpenseItemCategories
            );
    }

    /**
     * Returns a reference to the {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategory}
     * with the given income or expense item subcategory UUID or an empty optional if such a subcategory does
     * not exist
     *
     * @param incomeOrExpenseItemSubcategoryUUID the given income or expense item category UUID
     */
    public Optional<AcctIncomeOrExpenseItemSubcategory> findIncomeOrExpenseItemSubcategoryByIncomeOrExpenseItemSubcategoryUUID(
        String incomeOrExpenseItemSubcategoryUUID
    ) {
        return
            incomeOrExpenseItemSubcategoriesRepository
                .findFirstByIncomeOrExpenseItemSubcategoryUUID(incomeOrExpenseItemSubcategoryUUID);
    }

    /**
     * Returns a collection of {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategories}
     * for the UUIDs in the given collection of income or expense item subcategory UUIDs
     *
     * @param incomeOrExpenseItemSubcategoryUUIDs the given collection of income or expense item subcategory UUIDs
     */
    public Collection<AcctIncomeOrExpenseItemSubcategory>
    findIncomeOrExpenseItemSubcategoryByIncomeOrExpenseItemSubcategoryUUIDIn(
        Collection<String> incomeOrExpenseItemSubcategoryUUIDs
    ) {
        return
            incomeOrExpenseItemSubcategoriesRepository
                .findByIncomeOrExpenseItemSubcategoryUUIDIn(incomeOrExpenseItemSubcategoryUUIDs);
    }

    /**
     * Returns a collection of {@link AcctIncomeOrExpenseItem income or expense items} contained by the
     * {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategories} in the given collection
     * of income or expense item subcategories
     *
     * @param incomeOrExpenseItemSubcategories the given collection of income or expense item subcategories
     */
    public Collection<AcctIncomeOrExpenseItem> findIncomeOrExpenseItemsByIncomeOrExpenseItemSubcategoryIn(
        Collection<AcctIncomeOrExpenseItemSubcategory> incomeOrExpenseItemSubcategories
    ) {
        return
            incomeOrExpenseItemsRepository.findAllByIncomeOrExpenseItemSubcategoryIn(
                incomeOrExpenseItemSubcategories
            );
    }

    /**
     * Deletes the {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategories} in the
     * given collection of income or expense item subcategories
     *
     * @param incomeOrExpenseItemSubcategories the given collection of income or expense item subcategories
     */
    public void deleteIncomeOrExpenseItemSubcategories(
        Collection<AcctIncomeOrExpenseItemSubcategory> incomeOrExpenseItemSubcategories
    ) {
        incomeOrExpenseItemSubcategoriesRepository.deleteAll(incomeOrExpenseItemSubcategories);
    }

    /**
     * Creates a new instance of {@link AcctIncomeOrExpenseItem income or expense item}
     *
     * @return a reference to the created instance
     */
    public AcctIncomeOrExpenseItem createNewIncomeOrExpenseItem() {
        return incomeOrExpenseItemsRepository.createNew();
    }

    /**
     * Returns the {@link AcctIncomeOrExpenseItem income or expense item} with the given
     * income or expense item UUID or an empty optional if such an item does not exist.
     *
     * @param incomeOrExpenseItemUUID the given income or expense item UUID
     */
    public Optional<AcctIncomeOrExpenseItem> findIncomeOrExpenseItemByIncomeOrExpenseItemUUID(
        String incomeOrExpenseItemUUID
    ) {
        return incomeOrExpenseItemsRepository.findFirstByIncomeOrExpenseItemUUID(incomeOrExpenseItemUUID);
    }

    /**
     * Returns a collection of the {@link AcctIncomeOrExpenseItem income or expense items}
     * identified by the UUIDs in the given collection of income or expense item UUIDs
     *
     * @param incomeOrExpenseItemUUIDs the given collection of income or expense item UUIDs
     */
    public Collection<AcctIncomeOrExpenseItem> findIncomeOrExpenseItemsByIncomeOrExpenseItemUUIDIn(
        Collection<String> incomeOrExpenseItemUUIDs
    ) {
        return incomeOrExpenseItemsRepository.findAllByIncomeOrExpenseItemUUIDIn(incomeOrExpenseItemUUIDs);
    }

    /**
     * Persists the referenced {@link AcctIncomeOrExpenseItem income or expense item}
     *
     * @param incomeOrExpenseItem the referenced income or expense item
     * @return a reference to the persisted entity
     */
    public AcctIncomeOrExpenseItem saveIncomeOrExpenseItem(AcctIncomeOrExpenseItem incomeOrExpenseItem) {
        return incomeOrExpenseItemsRepository.save(incomeOrExpenseItem);
    }

    /**
     * Deletes the {@link AcctIncomeOrExpenseItem income or expense items} in the given collection
     * of income or expense items
     *
     * @param incomeOrExpenseItems the given collection of income or expense items
     */
    public void deleteIncomeOrExpenseItems(Collection<AcctIncomeOrExpenseItem> incomeOrExpenseItems) {
        incomeOrExpenseItemsRepository.deleteAll(incomeOrExpenseItems);
    }

}
