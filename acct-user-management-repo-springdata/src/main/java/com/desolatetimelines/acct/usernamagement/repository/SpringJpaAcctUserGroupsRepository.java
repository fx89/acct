package com.desolatetimelines.acct.usernamagement.repository;

import com.desolatetimelines.acct.usermanagement.model.AcctUsersGroup;
import com.desolatetimelines.acct.usermanagement.repository.UserGroupsRepository;
import com.desolatetimelines.acct.usernamagement.model.JpaAcctUsersGroup;
import com.desolatetimelines.acct.usernamagement.springrepository.JpaGroupsRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of the {@link UserGroupsRepository} that uses Spring Data
 */
@Service
public class SpringJpaAcctUserGroupsRepository implements UserGroupsRepository {

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
}
