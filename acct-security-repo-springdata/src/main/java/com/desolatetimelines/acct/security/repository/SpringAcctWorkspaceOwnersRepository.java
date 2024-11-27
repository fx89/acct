package com.desolatetimelines.acct.security.repository;

import com.desolatetimelines.acct.security.model.AcctWorkspaceOwner;
import com.desolatetimelines.acct.security.springrepository.JpaAcctWorkspaceOwnersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
    public Set<AcctWorkspaceOwner> findAllByWorkspaceUUIDIn(Collection<String> workspaceUUIDs) {
        return new HashSet<>(workspaceOwnersRepository.findAllByWorkspaceUUIDIn(workspaceUUIDs));
    }

}
