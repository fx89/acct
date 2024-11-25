package com.desolatetimelines.acct.usernamagement.repository;

import com.desolatetimelines.acct.usermanagement.model.AcctUsersGroup;
import com.desolatetimelines.acct.usermanagement.repository.AcctUserGroupsRepository;
import com.desolatetimelines.acct.usernamagement.model.JpaAcctUsersGroup;
import com.desolatetimelines.acct.usernamagement.springrepository.JpaGroupsRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.desolatetimelines.acct.usernamagement.util.AcctUserManagementRepoSpringDataUtils.doWithJpaAcctUsersGroup;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctUserGroupsRepository} that uses Spring Data
 */
@Service
public class SpringJpaAcctUserGroupsRepository implements AcctUserGroupsRepository {

    private final JpaGroupsRepository groupsRepository;

    public SpringJpaAcctUserGroupsRepository(JpaGroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    @Override
    public AcctUsersGroup createNew() {
        return new JpaAcctUsersGroup();
    }

    @Override
    public Set<AcctUsersGroup> findUserGroupByUserUUID(String userUUID) {
        return new HashSet<>(groupsRepository.findAllByUsersUserUUID(userUUID));
    }

    @Override
    public Optional<AcctUsersGroup> findFirstByGroupUUID(String groupUUID) {
        return groupsRepository.findFirstByGroupUUID(groupUUID).map(identity());
    }

    @Override
    public AcctUsersGroup save(AcctUsersGroup usersGroup) {
        return doWithJpaAcctUsersGroup(usersGroup, groupsRepository::save);
    }
}
