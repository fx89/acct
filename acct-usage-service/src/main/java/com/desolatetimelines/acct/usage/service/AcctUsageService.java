package com.desolatetimelines.acct.usage.service;

import com.desolatetimelines.acct.usage.data.service.AcctUsageDataService;
import com.desolatetimelines.acct.usage.data.service.AcctUsedItemsDataService;
import com.desolatetimelines.acct.usage.model.AcctService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Provide access to all ACCT usage service functions
 */
@Service
public class AcctUsageService {

    private final AcctUsageDataService usageDataService;

    private final AcctUsedItemsDataService usedItemsDataService;

    public AcctUsageService(
        AcctUsageDataService usageDataService,
        AcctUsedItemsDataService usedItemsDataService
    ) {
        this.usageDataService = usageDataService;
        this.usedItemsDataService = usedItemsDataService;
    }

    /**
     * Replaces the currently registered list of possibly in-use item types for the service with
     * the given name with the given list, while updating the context path
     *
     * @param serviceName        the given name
     * @param serviceContextPath the context path
     * @param itemTypes          the given list
     */
    @Transactional
    public void registerItemTypesForService(
        String serviceName,
        String serviceContextPath,
        Collection<String> itemTypes
    ) {
        // Acquire the service (get a reference if the service already exists or create the service if it doesn't)
        final AcctService service = usageDataService.resolveService(serviceName);

        // Set the context path
        service.setServiceContextPath(serviceContextPath);
        final AcctService savedService = usageDataService.saveService(service);

        // Delete any previously-registered item types for the service
        usageDataService.deleteAllUsedItemTypesForService(service);

        // Register the given item types for the service
        usageDataService.saveAllUsedItemTypes(
            itemTypes.stream()
                .map(itemTypeName -> usageDataService.newAcctUsedItemType(savedService, itemTypeName))
                .collect(Collectors.toSet())
        );
    }

    /**
     * Identifies the services that can use the given object type and queries each one to see if
     * any of the objects with the given UUIDs are in use. Combines rhe results into a set.
     *
     * @param objectType the given object type
     * @param itemUUIDs  the given UUIDs
     * @return the combined results
     */
    public Collection<String> getInUseItemUUIDs(String objectType, Collection<String> itemUUIDs) {
        // Find the services that can use the object type,
        // ask each service if the objects are in use
        // and combine the responses
        return
            usageDataService.findServicesForObjectType(objectType)
                .parallelStream()
                .map(acctService -> usedItemsDataService.getUsedItemUUIDsForService(acctService, objectType, itemUUIDs))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
