package com.desolatetimelines.acct.usernamagement.repository;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.usermanagement.model.AcctUsersGroup;
import com.desolatetimelines.acct.usermanagement.repository.AcctUserGroupsRepository;
import com.desolatetimelines.acct.usernamagement.model.JpaAcctUsersGroup;
import com.desolatetimelines.acct.usernamagement.springrepository.JpaGroupsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.desolatetimelines.acct.usernamagement.util.AcctUserManagementRepoSpringDataUtils.doWithJpaAcctUsersGroup;
import static com.desolatetimelines.acct.usernamagement.util.AcctUserManagementRepoSpringDataUtils.doWithJpaAcctUsersGroupWithoutReturning;
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

    @Override
    public Page<AcctUsersGroup> findGroupsByGroupNameLike(String pattern, int pageNumber, int pageSize) {
        // Add the "%" to the pattern
        final String sqlPattern = "%" + pattern + "%";

        // Get the page
        final org.springframework.data.domain.Page<JpaAcctUsersGroup> page =
            groupsRepository.findAllByGroupNameLike(sqlPattern, PageRequest.of(pageNumber, pageSize));

        // Convert the page
        return
            new Page<>(
                page.stream().map(jpaAcctUsersGroup -> (AcctUsersGroup) jpaAcctUsersGroup).toList(),
                page.getNumberOfElements(),
                page.getTotalElements()
            );
    }

    @Override
    public void delete(AcctUsersGroup acctUsersGroup) {
        doWithJpaAcctUsersGroupWithoutReturning(acctUsersGroup, groupsRepository::delete);
    }

    @Override
    public Collection<AcctUsersGroup> findUserGroupsByGroupIconUUIDIn(Collection<String> groupIconUUIDs) {
        return
            groupsRepository.findAllByGroupIconUUIDIn(groupIconUUIDs)
                .stream()
                .map(jpaAcctUsersGroup -> (AcctUsersGroup) jpaAcctUsersGroup)
                .toList();
    }
}
