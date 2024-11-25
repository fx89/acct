package com.desolatetimelines.acct.usernamagement.springrepository;

import com.desolatetimelines.acct.usernamagement.model.JpaAcctUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaUsersRepository extends CrudRepository<JpaAcctUser, Long> {

    Optional<JpaAcctUser> findFirstByUserUUID(String userUUID);

    Optional<JpaAcctUser> findFirstByUserLoginName(String userLoginName);

}
