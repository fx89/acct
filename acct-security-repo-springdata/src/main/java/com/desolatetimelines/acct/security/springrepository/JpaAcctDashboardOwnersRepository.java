package com.desolatetimelines.acct.security.springrepository;

import com.desolatetimelines.acct.security.model.JpaAcctDashboardOwner;
import com.desolatetimelines.acct.security.model.OwnerType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface JpaAcctDashboardOwnersRepository extends CrudRepository<JpaAcctDashboardOwner, Long> {

    Optional<JpaAcctDashboardOwner> findFirstByOwnerTypeAndOwnerUUIDAndDashboardUUID(
        OwnerType ownerType,
        String ownerUUID,
        String dashboardUUID
    );

    Set<JpaAcctDashboardOwner> findAllByDashboardUUIDIn(Collection<String> dashboardUUIDs);

    Set<JpaAcctDashboardOwner> findAllByOwnerTypeInAndOwnerUUID(Collection<OwnerType> ownerType, String ownerUUID);

    Set<JpaAcctDashboardOwner> findAllByOwnerTypeAndOwnerUUIDIn(OwnerType ownerType, Collection<String> ownerUUIDs);

    Set<JpaAcctDashboardOwner> findAllByOwnerType(OwnerType ownerType);

    Set<JpaAcctDashboardOwner> findAllByOwnerTypeAndOwnerUUIDInAndDashboardUUID(
        OwnerType ownerType,
        Collection<String> ownerUUIDs,
        String dashboardUUID
    );

    void deleteByDashboardUUID(String dashboardUUID);

}
