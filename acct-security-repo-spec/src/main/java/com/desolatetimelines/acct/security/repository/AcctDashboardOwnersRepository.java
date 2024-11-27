package com.desolatetimelines.acct.security.repository;

import com.desolatetimelines.acct.security.model.AcctDashboardOwner;

import java.util.Collection;
import java.util.Set;

/**
 * Repository for loading and persisting {@link AcctDashboardOwner dashboard owners}
 */
public interface AcctDashboardOwnersRepository {

    Set<AcctDashboardOwner> findAllByDashboardUUIDIn(Collection<String> dashboardUUIDs);

}
