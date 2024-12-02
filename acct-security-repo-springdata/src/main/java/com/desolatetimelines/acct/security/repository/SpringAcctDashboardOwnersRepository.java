package com.desolatetimelines.acct.security.repository;

import com.desolatetimelines.acct.security.model.AcctDashboardOwner;
import com.desolatetimelines.acct.security.model.JpaAcctDashboardOwner;
import com.desolatetimelines.acct.security.model.OwnerType;
import com.desolatetimelines.acct.security.springrepository.JpaAcctDashboardOwnersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.desolatetimelines.acct.security.util.AcctSecurityRepoSpringdataUtils.doWithJpaAcctDashboardOwner;
import static com.desolatetimelines.acct.security.util.AcctSecurityRepoSpringdataUtils.doWithJpaAcctDashboardOwnerReturning;
import static java.util.function.Function.identity;

@Service
public class SpringAcctDashboardOwnersRepository implements AcctDashboardOwnersRepository {

    private final JpaAcctDashboardOwnersRepository dashboardOwnersRepository;

    public SpringAcctDashboardOwnersRepository(JpaAcctDashboardOwnersRepository dashboardOwnersRepository) {
        this.dashboardOwnersRepository = dashboardOwnersRepository;
    }

    @Override
    public AcctDashboardOwner createNew() {
        return new JpaAcctDashboardOwner();
    }

    @Override
    public AcctDashboardOwner save(AcctDashboardOwner resourceOwner) {
        return doWithJpaAcctDashboardOwnerReturning(resourceOwner, dashboardOwnersRepository::save);
    }

    @Override
    public void delete(AcctDashboardOwner resourceOwner) {
        doWithJpaAcctDashboardOwner(resourceOwner, dashboardOwnersRepository::delete);
    }

    @Override
    public Optional<AcctDashboardOwner> findFirstByOwnerTypeAndOwnerUUIDAndResourceUUID(
        OwnerType ownerType,
        String ownerUUID,
        String resourceUUID
    ) {
        return
            dashboardOwnersRepository
                .findFirstByOwnerTypeAndOwnerUUIDAndDashboardUUID(ownerType, ownerUUID, resourceUUID)
                .map(identity());
    }

    @Override
    public Set<AcctDashboardOwner> findAllByOwnerTypeAndOwnerUUIDInAndResourceUUID(
        OwnerType ownerType,
        Collection<String> ownerUUIDs,
        String resourceUUID
    ) {
        return
            new HashSet<>(
                dashboardOwnersRepository.findAllByOwnerTypeAndOwnerUUIDInAndDashboardUUID(
                    ownerType,
                    ownerUUIDs,
                    resourceUUID
                )
            );
    }

    @Override
    public Set<AcctDashboardOwner> findAllByResourceUUIDIn(Collection<String> resourceUUIDs) {
        return new HashSet<>(dashboardOwnersRepository.findAllByDashboardUUIDIn(resourceUUIDs));
    }

    @Override
    public Set<AcctDashboardOwner> findAllByOwnerTypeInAndOwnerUUID(Collection<OwnerType> ownerTypes, String ownerUUID) {
        return new HashSet<>(dashboardOwnersRepository.findAllByOwnerTypeInAndOwnerUUID(ownerTypes, ownerUUID));
    }

    @Override
    public Set<AcctDashboardOwner> findAllByOwnerTypeAndOwnerUUIDIn(OwnerType ownerType, Collection<String> ownerUUIDs) {
        return new HashSet<>(dashboardOwnersRepository.findAllByOwnerTypeAndOwnerUUIDIn(ownerType, ownerUUIDs));
    }

    @Override
    public Set<AcctDashboardOwner> findAllByOwnerType(OwnerType ownerType) {
        return new HashSet<>(dashboardOwnersRepository.findAllByOwnerType(ownerType));
    }
}
