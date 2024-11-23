package com.desolatetimelines.acct.usage.ws.redist.service;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Aggregates responses coming from multiple instances of the {@link InUseItemsService}
 */
@Service
public class InUseItemsAggregationService {

    private final List<InUseItemsService> inUseItemServices;

    public InUseItemsAggregationService(List<InUseItemsService> inUseItemServices) {
        this.inUseItemServices = inUseItemServices;
    }

    /**
     * Returns a collection of the UUIDs of the items of the given object type that are currently
     * in use by the service and that are identified by the UUIDs in the given item UUIDs list.
     * The UUIDs are retrieved by calling the
     * {@link InUseItemsService#getInUseItemUUIDs(String, Collection) getInUseItemUUIDs} method of
     * each instance of the {@link InUseItemsService} present within the application context. <br />
     * <br />
     * See also: {@link InUseItemsService}
     *
     * @param objectType the given object type
     * @param itemUUIDs  the given item UUIDs list
     */
    public Collection<String> getInUseItemUUIDs(String objectType, Collection<String> itemUUIDs) {
        return
            inUseItemServices.stream()
                .map(service -> service.getInUseItemUUIDs(objectType, itemUUIDs))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

}
