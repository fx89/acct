package com.desolatetimelines.acct.security.repository;

import com.desolatetimelines.acct.security.model.AcctDashboardOwner;
import com.desolatetimelines.acct.security.springrepository.JpaAcctDashboardOwnersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class SpringAcctDashboardOwnersRepository implements AcctDashboardOwnersRepository {

    private final JpaAcctDashboardOwnersRepository dashboardOwnersRepository;

    public SpringAcctDashboardOwnersRepository(JpaAcctDashboardOwnersRepository dashboardOwnersRepository) {
        this.dashboardOwnersRepository = dashboardOwnersRepository;
    }

    @Override
    public Set<AcctDashboardOwner> findAllByDashboardUUIDIn(Collection<String> dashboardUUIDs) {
        return new HashSet<>(dashboardOwnersRepository.findAllByDashboardUUIDIn(dashboardUUIDs));
    }
}
