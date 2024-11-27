package com.desolatetimelines.acct.security.repository;

import com.desolatetimelines.acct.security.model.AcctReportOwner;

import java.util.Collection;
import java.util.Set;

/**
 * Repository for loading and persisting {@link AcctReportOwner report owners}
 */
public interface AcctReportOwnersRepository {

    Set<AcctReportOwner> findAllByReportUUIDIn(Collection<String> reportUUIDs);

}
