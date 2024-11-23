package com.desolatetimelines.acct.usage.repository;

import com.desolatetimelines.acct.usage.model.AcctService;

import java.util.Optional;

/**
 * Allows reading and updating {@link AcctService registered services}
 */
public interface ServicesRepository {

    /**
     * Creates a new instance of {@link AcctService}
     */
    AcctService createNew();

    /**
     * Returns the service with the given service name or an empty optional
     * if such a service does not exist
     *
     * @param serviceName the given service name
     */
    Optional<AcctService> findFirstByServiceName(String serviceName);

    /**
     * Persists the referenced service
     */
    AcctService save(AcctService service);

}
