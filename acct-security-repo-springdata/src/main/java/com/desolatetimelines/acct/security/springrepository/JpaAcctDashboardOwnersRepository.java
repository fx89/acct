package com.desolatetimelines.acct.security.springrepository;

import com.desolatetimelines.acct.security.model.JpaAcctDashboardOwner;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Set;

public interface JpaAcctDashboardOwnersRepository extends CrudRepository<JpaAcctDashboardOwner, Long> {

    Set<JpaAcctDashboardOwner> findAllByDashboardUUIDIn(Collection<String> dashboardUUIDs);

}
