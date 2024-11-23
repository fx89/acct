package com.desolatetimelines.acct.usage.data.service;

import com.desolatetimelines.acct.usage.model.AcctService;
import com.desolatetimelines.acct.usage.model.AcctUsedItemType;
import com.desolatetimelines.acct.usage.repository.ServicesRepository;
import com.desolatetimelines.acct.usage.repository.UsedItemTypesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Facade for the data layer of the usage service, allowing for the
 * loading and persisting of usage data objects
 */
@Service
public class AcctUsageDataService {

    private final ServicesRepository servicesRepository;

    private final UsedItemTypesRepository usedItemTypesRepository;


    public AcctUsageDataService(
        ServicesRepository servicesRepository,
        UsedItemTypesRepository usedItemTypesRepository
    ) {
        this.servicesRepository = servicesRepository;
        this.usedItemTypesRepository = usedItemTypesRepository;
    }

    /**
     * Returns a reference to the {@link AcctService service} with the given service name.
     * If such a service does not exist, one is created.
     *
     * @param serviceName the given service name
     */
    public AcctService resolveService(String serviceName) {
        return
            servicesRepository.findFirstByServiceName(serviceName)
                .orElseGet(() -> {
                    final AcctService acctService = servicesRepository.createNew();
                    acctService.setServiceName(serviceName);
                    acctService.setServiceContextPath("");
                    return servicesRepository.save(acctService);
                });
    }

    /**
     * Persists the referenced {@link AcctService service} and returns a reference to the persisted entity
     */
    public AcctService saveService(AcctService acctService) {
        return servicesRepository.save(acctService);
    }

    /**
     * Deletes all {@link AcctUsedItemType used item types} mapped to the referenced service
     *
     * @param service the referenced service
     */
    public void deleteAllUsedItemTypesForService(AcctService service) {
        usedItemTypesRepository.deleteByService(service);
    }

    /**
     * Persists all the {@link AcctUsedItemType used item types} in the referenced collection
     *
     * @param usedItemTypes the referenced collection
     */
    public void saveAllUsedItemTypes(Collection<AcctUsedItemType> usedItemTypes) {
        usedItemTypesRepository.saveAll(usedItemTypes);
    }

    /**
     * Creates a new instance of {@link AcctUsedItemType} and sets the properties
     */
    public AcctUsedItemType newAcctUsedItemType(AcctService service, String itemTypeName) {
        final AcctUsedItemType usedItemType = usedItemTypesRepository.createNew();

        usedItemType.setService(service);
        usedItemType.setUsedItemTypeName(itemTypeName);

        return usedItemType;
    }

    /**
     * Returns a set of services that can use the referenced object type
     *
     * @param objectType the referenced object type
     */
    public Set<AcctService> findServicesForObjectType(String objectType) {
        return
            usedItemTypesRepository.findAllByUsedItemTypeNameIn(List.of(objectType))
                .stream()
                .map(AcctUsedItemType::getService)
                .collect(Collectors.toSet());
    }
}
