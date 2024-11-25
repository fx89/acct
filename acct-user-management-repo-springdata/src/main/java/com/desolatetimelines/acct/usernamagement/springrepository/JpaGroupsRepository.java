package com.desolatetimelines.acct.usernamagement.springrepository;

import com.desolatetimelines.acct.usernamagement.model.JpaAcctUsersGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.Set;

public interface JpaGroupsRepository extends CrudRepository<JpaAcctUsersGroup, Long> {

    Set<JpaAcctUsersGroup> findAllByUsersUserUUID(String userUUID);

    Optional<JpaAcctUsersGroup> findFirstByGroupUUID(String groupUUID);

}
