package com.desolatetimelines.acct.usernamagement.repository;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.usermanagement.model.AcctUser;
import com.desolatetimelines.acct.usermanagement.repository.AcctUsersRepository;
import com.desolatetimelines.acct.usernamagement.model.JpaAcctUser;
import com.desolatetimelines.acct.usernamagement.springrepository.JpaUsersRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;

import static com.desolatetimelines.acct.usernamagement.util.AcctUserManagementRepoSpringDataUtils.doWithJpaAcctUser;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctUsersRepository} that uses Spring Data
 */
@Service
public class SpringJpaAcctUsersRepository implements AcctUsersRepository {
    private final JpaUsersRepository usersRepository;

    public SpringJpaAcctUsersRepository(JpaUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public AcctUser createNew() {
        return new JpaAcctUser();
    }

    @Override
    public AcctUser save(AcctUser acctUser) {
        return doWithJpaAcctUser(acctUser, usersRepository::save);
    }

    @Override
    public Optional<AcctUser> findUserAccountByUserUUID(String userUUID) {
        return usersRepository.findFirstByUserUUID(userUUID).map(identity());
    }

    @Override
    public Optional<AcctUser> findUserAccountByUserLoginName(String userLoginName) {
        return usersRepository.findFirstByUserLoginName(userLoginName).map(identity());
    }

    @Override
    public Page<AcctUser> findUsersByUserLoginNameLikeOrUserNameLike(String pattern, int pageNumber, int pageSize) {
        // Add the "%" to the pattern
        final String sqlPattern = "%" + pattern + "%";

        // Get the page
        final org.springframework.data.domain.Page<JpaAcctUser> page =
            usersRepository.findAllByUserLoginNameLikeOrUserNameLike(
                sqlPattern,
                sqlPattern,
                PageRequest.of(pageNumber, pageSize)
            );

        // Convert the page
        return
            new Page<>(
                page.stream().map(jpaAcctUser -> (AcctUser) jpaAcctUser).toList(),
                page.getNumberOfElements(),
                page.getTotalElements()
            );
    }

    @Override
    public Collection<AcctUser> findUsersBySoftDeletedTrueAndSoftDeletedDateLessThan(Instant referenceDate) {
        return
            usersRepository.findUsersBySoftDeletedAndSoftDeletedDateLessThan(true, referenceDate)
                .stream()
                .map(jpaAcctUser -> (AcctUser) jpaAcctUser)
                .toList();
    }

    @Override
    public void deleteUsers(Collection<AcctUser> users) {
        usersRepository.deleteAll(
            users.stream()
                .map(user -> doWithJpaAcctUser(user, identity()))
                .toList()
        );
    }

    @Override
    public Collection<AcctUser> findUsersByUserIconUUIDIn(Collection<String> userIconUUIDs) {
        return
            usersRepository.findAllByUserIconUUIDIn(userIconUUIDs)
                .stream()
                .map(jpaAcctUser -> (AcctUser) jpaAcctUser)
                .toList();
    }

    @Override
    public Collection<AcctUser> findUsersByWorkspaceUUIDIn(Collection<String> workspaceUUID) {
        return
            usersRepository.findAllByDefaultWorkspaceUUIDIn(workspaceUUID)
                .stream()
                .map(jpaAcctUser -> (AcctUser) jpaAcctUser)
                .toList();
    }
}
