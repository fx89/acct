package com.desolatetimelines.acct.usernamagement.springrepository;

import com.desolatetimelines.acct.usernamagement.model.JpaAcctUsersGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface JpaGroupsRepository extends CrudRepository<JpaAcctUsersGroup, Long> {

    Set<JpaAcctUsersGroup> findAllByUsersUserUUID(String userUUID);

    Optional<JpaAcctUsersGroup> findFirstByGroupUUID(String groupUUID);

    Page<JpaAcctUsersGroup> findAllByGroupNameLike(String groupNamePattern, Pageable page);

}
