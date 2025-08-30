package com.desolatetimelines.acct.reporting.springrepository;

import com.desolatetimelines.acct.reporting.model.JpaAcctDashboard;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface JpaAcctDashboardsRepository extends CrudRepository<JpaAcctDashboard, Long> {

    Collection<JpaAcctDashboard> findAllByDashboardIconUUIDIn(Collection<String> dashboardIconUUIDs);

    Collection<JpaAcctDashboard> findAllByWorkspaceUUIDIn(Collection<String> workspaceUUIDs);

    Optional<JpaAcctDashboard> findFirstByDashboardUUID(String dashboardUUID);
}
