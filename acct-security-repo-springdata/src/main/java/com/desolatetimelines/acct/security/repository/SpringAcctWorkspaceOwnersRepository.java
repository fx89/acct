package com.desolatetimelines.acct.security.repository;

import com.desolatetimelines.acct.security.model.AcctWorkspaceOwner;
import com.desolatetimelines.acct.security.model.JpaAcctWorkspaceOwner;
import com.desolatetimelines.acct.security.model.OwnerType;
import com.desolatetimelines.acct.security.springrepository.JpaAcctWorkspaceOwnersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.desolatetimelines.acct.security.util.AcctSecurityRepoSpringdataUtils.doWithJJpaAcctWorkspaceOwner;

/**
 * Implementation of the {@link AcctWorkspaceOwnersRepository} based on Spring Data
 */
@Service
public class SpringAcctWorkspaceOwnersRepository implements AcctWorkspaceOwnersRepository {

    private final JpaAcctWorkspaceOwnersRepository workspaceOwnersRepository;

    public SpringAcctWorkspaceOwnersRepository(JpaAcctWorkspaceOwnersRepository workspaceOwnersRepository) {
        this.workspaceOwnersRepository = workspaceOwnersRepository;
    }

    @Override
    public AcctWorkspaceOwner createNew() {
        return new JpaAcctWorkspaceOwner();
    }

    @Override
    public AcctWorkspaceOwner save(AcctWorkspaceOwner acctWorkspaceOwner) {
        return doWithJJpaAcctWorkspaceOwner(acctWorkspaceOwner, workspaceOwnersRepository::save);
    }

    @Override
    public Set<AcctWorkspaceOwner> findAllByWorkspaceUUIDIn(Collection<String> workspaceUUIDs) {
        return new HashSet<>(workspaceOwnersRepository.findAllByWorkspaceUUIDIn(workspaceUUIDs));
    }

    @Override
    public Set<AcctWorkspaceOwner> findAllByOwnerTypeInAndOwnerUUID(Collection<OwnerType> ownerTypes, String ownerUUID) {
        return new HashSet<>(workspaceOwnersRepository.findAllByOwnerTypeInAndOwnerUUID(ownerTypes, ownerUUID));
    }

    @Override
    public Set<AcctWorkspaceOwner> findAllByOwnerTypeAndOwnerUUIDIn(OwnerType ownerType, Collection<String> ownerUUIDs) {
        return new HashSet<>(workspaceOwnersRepository.findAllByOwnerTypeAndOwnerUUIDIn(ownerType, ownerUUIDs));
    }

    @Override
    public Set<AcctWorkspaceOwner> findAllByOwnerType(OwnerType ownerType) {
        return new HashSet<>(workspaceOwnersRepository.findAllByOwnerType(ownerType));
    }

}
