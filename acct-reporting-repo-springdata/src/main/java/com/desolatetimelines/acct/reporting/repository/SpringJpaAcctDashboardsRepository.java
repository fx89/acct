package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctDashboard;
import com.desolatetimelines.acct.reporting.model.JpaAcctDashboard;
import com.desolatetimelines.acct.reporting.springrepository.JpaAcctDashboardsRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static com.desolatetimelines.acct.reporting.util.AcctReportingRepoSpringDataUtils.doWithJpaAcctDashboardReturning;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctDashboardsRepository dashboards repository} that uses
 * Spring Data JPA to persist dashboards to the database
 */
@Service
public class SpringJpaAcctDashboardsRepository implements AcctDashboardsRepository {

    private final JpaAcctDashboardsRepository jpaAcctDashboardsRepository;

    public SpringJpaAcctDashboardsRepository(JpaAcctDashboardsRepository jpaAcctDashboardsRepository) {
        this.jpaAcctDashboardsRepository = jpaAcctDashboardsRepository;
    }

    @Override
    public AcctDashboard createNew() {
        return new JpaAcctDashboard();
    }

    @Override
    public AcctDashboard save(AcctDashboard dashboard) {
        return doWithJpaAcctDashboardReturning(dashboard, jpaAcctDashboardsRepository::save);
    }

    @Override
    public Collection<AcctDashboard> findAllByDashboardIconUUIDIn(Collection<String> dashboardIconUUIDs) {
        return castAllToGeneric(jpaAcctDashboardsRepository.findAllByDashboardIconUUIDIn(dashboardIconUUIDs));
    }

    @Override
    public Collection<AcctDashboard> findAllByWorkspaceUUIDIn(Collection<String> workspaceUUIDs) {
        return castAllToGeneric(jpaAcctDashboardsRepository.findAllByWorkspaceUUIDIn(workspaceUUIDs));
    }

    @Override
    public Optional<AcctDashboard> findFirstByDashboardUUID(String dashboardUUID) {
        return jpaAcctDashboardsRepository.findFirstByDashboardUUID(dashboardUUID).map(identity());
    }

    @Override
    public Collection<AcctDashboard> findAllByWorkspaceUUIDAndDashboardUUIDIn(
        String workspaceUUID,
        Collection<String> dashboardUUIDs
    ) {
        return
            jpaAcctDashboardsRepository.findAllByWorkspaceUUIDAndDashboardUUIDIn(workspaceUUID, dashboardUUIDs).stream()
                .map(dash -> (AcctDashboard) dash)
                .toList();
    }

    private static Collection<AcctDashboard> castAllToGeneric(Collection<JpaAcctDashboard> jpaDashboards) {
        return
            jpaDashboards.stream()
                .map(dash -> (AcctDashboard) dash)
                .toList();
    }

}
