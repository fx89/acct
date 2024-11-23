package com.desolatetimelines.acct.usage.repository;

import com.desolatetimelines.acct.usage.model.AcctService;
import com.desolatetimelines.acct.usage.model.AcctUsedItemType;

import java.util.Collection;

/**
 * Allows reading and updating {@link AcctUsedItemType used itrem types}
 */
public interface UsedItemTypesRepository {

    /**
     * Returns a new instance of {@link AcctUsedItemType}
     */
    AcctUsedItemType createNew();

    /**
     * Deletes all used item types mapped to the referenced service
     *
     * @param service the referenced service
     */
    void deleteByService(AcctService service);

    /**
     * Saves each of the used item types in the referenced collection
     *
     * @param usedItemTypes the referenced collection
     */
    void saveAll(Collection<AcctUsedItemType> usedItemTypes);

    /**
     * Finds all {@link AcctUsedItemType used item types} having the
     * {@link AcctUsedItemType#getUsedItemTypeName() used item type name}
     * in the given list of used item type names
     *
     * @param usedItemTypeNames the given list of used item type names
     */
    Collection<AcctUsedItemType> findAllByUsedItemTypeNameIn(Collection<String> usedItemTypeNames);

}
