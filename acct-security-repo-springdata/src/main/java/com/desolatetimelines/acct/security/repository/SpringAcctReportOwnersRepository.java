package com.desolatetimelines.acct.security.repository;

import com.desolatetimelines.acct.security.model.AcctReportOwner;
import com.desolatetimelines.acct.security.model.JpaAcctReportOwner;
import com.desolatetimelines.acct.security.model.OwnerType;
import com.desolatetimelines.acct.security.springrepository.JpaAcctReportOwnersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.desolatetimelines.acct.security.util.AcctSecurityRepoSpringdataUtils.doWithJpaAcctReportOwner;
import static com.desolatetimelines.acct.security.util.AcctSecurityRepoSpringdataUtils.doWithJpaAcctReportOwnerReturning;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctReportOwnersRepository} using Spring Data
 */
@Service
public class SpringAcctReportOwnersRepository implements AcctReportOwnersRepository {

    private final JpaAcctReportOwnersRepository reportOwnersRepository;

    public SpringAcctReportOwnersRepository(JpaAcctReportOwnersRepository reportOwnersRepository) {
        this.reportOwnersRepository = reportOwnersRepository;
    }

    @Override
    public AcctReportOwner createNew() {
        return new JpaAcctReportOwner();
    }

    @Override
    public AcctReportOwner save(AcctReportOwner resourceOwner) {
        return doWithJpaAcctReportOwnerReturning(resourceOwner, reportOwnersRepository::save);
    }

    @Override
    public void delete(AcctReportOwner resourceOwner) {
        doWithJpaAcctReportOwner(resourceOwner, reportOwnersRepository::delete);
    }

    @Override
    public Optional<AcctReportOwner> findFirstByOwnerTypeAndOwnerUUIDAndResourceUUID(
        OwnerType ownerType,
        String ownerUUID,
        String resourceUUID
    ) {
        return
            reportOwnersRepository
                .findFirstByOwnerTypeAndOwnerUUIDAndReportUUID(ownerType, ownerUUID, resourceUUID)
                .map(identity());
    }

    @Override
    public Set<AcctReportOwner> findAllByOwnerTypeAndOwnerUUIDInAndResourceUUID(
        OwnerType ownerType,
        Collection<String> ownerUUIDs,
        String resourceUUID
    ) {
        return
            new HashSet<>(
                reportOwnersRepository.findAllByOwnerTypeAndOwnerUUIDInAndReportUUID(
                    ownerType,
                    ownerUUIDs,
                    resourceUUID
                )
            );
    }

    @Override
    public Set<AcctReportOwner> findAllByResourceUUIDIn(Collection<String> resourceUUIDs) {
        return new HashSet<>(reportOwnersRepository.findAllByReportUUIDIn(resourceUUIDs));
    }

    @Override
    public Set<AcctReportOwner> findAllByOwnerTypeInAndOwnerUUID(Collection<OwnerType> ownerTypes, String ownerUUID) {
        return new HashSet<>(reportOwnersRepository.findAllByOwnerTypeInAndOwnerUUID(ownerTypes, ownerUUID));
    }

    @Override
    public Set<AcctReportOwner> findAllByOwnerTypeAndOwnerUUIDIn(OwnerType ownerType, Collection<String> ownerUUIDs) {
        return new HashSet<>(reportOwnersRepository.findAllByOwnerTypeAndOwnerUUIDIn(ownerType, ownerUUIDs));
    }

    @Override
    public Set<AcctReportOwner> findAllByOwnerType(OwnerType ownerType) {
        return new HashSet<>(reportOwnersRepository.findAllByOwnerType(ownerType));
    }
}
