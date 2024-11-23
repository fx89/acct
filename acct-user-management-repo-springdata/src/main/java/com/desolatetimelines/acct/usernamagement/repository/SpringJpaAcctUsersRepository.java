package com.desolatetimelines.acct.usernamagement.repository;

import com.desolatetimelines.acct.usermanagement.model.AcctUser;
import com.desolatetimelines.acct.usermanagement.repository.UserAccountsRepository;
import com.desolatetimelines.acct.usernamagement.model.JpaAcctUser;
import com.desolatetimelines.acct.usernamagement.springrepository.JpaUsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.function.Function.identity;

/**
 * Implementation of the {@link UserAccountsRepository} that uses Spring Data
 */
@Service
public class SpringJpaAcctUsersRepository implements UserAccountsRepository {
    private final JpaUsersRepository usersRepository;

    public SpringJpaAcctUsersRepository(JpaUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public AcctUser createNew() {
        return new JpaAcctUser();
    }

    @Override
    public Optional<AcctUser> findUserAccountByUserUUID(String userUUID) {
        return usersRepository.findFirstByUserUUID(userUUID).map(identity());
    }

    @Override
    public Optional<AcctUser> findUserAccountByUserLoginName(String userLoginName) {
        return usersRepository.findFirstByUserLoginName(userLoginName).map(identity());
    }
}
