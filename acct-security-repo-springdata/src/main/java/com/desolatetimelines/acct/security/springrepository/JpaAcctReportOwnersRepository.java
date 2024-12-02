package com.desolatetimelines.acct.security.springrepository;

import com.desolatetimelines.acct.security.model.JpaAcctReportOwner;
import com.desolatetimelines.acct.security.model.OwnerType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface JpaAcctReportOwnersRepository extends CrudRepository<JpaAcctReportOwner, Long> {

    Optional<JpaAcctReportOwner> findFirstByOwnerTypeAndOwnerUUIDAndReportUUID(
        OwnerType ownerType,
        String ownerUUID,
        String reportUUID
    );

    Set<JpaAcctReportOwner> findAllByReportUUIDIn(Collection<String> reportUUIDs);

    Set<JpaAcctReportOwner> findAllByOwnerTypeInAndOwnerUUID(Collection<OwnerType> ownerType, String ownerUUID);

    Set<JpaAcctReportOwner> findAllByOwnerTypeAndOwnerUUIDIn(OwnerType ownerType, Collection<String> ownerUUIDs);

    Set<JpaAcctReportOwner> findAllByOwnerType(OwnerType ownerType);

    Set<JpaAcctReportOwner> findAllByOwnerTypeAndOwnerUUIDInAndReportUUID(
        OwnerType ownerType,
        Collection<String> ownerUUIDs,
        String reportUUID
    );

}
