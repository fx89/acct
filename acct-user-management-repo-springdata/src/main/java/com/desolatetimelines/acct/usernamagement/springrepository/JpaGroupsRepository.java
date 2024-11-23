package com.desolatetimelines.acct.usernamagement.springrepository;

import com.desolatetimelines.acct.usernamagement.model.JpaAcctUsersGroup;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;

public interface JpaGroupsRepository extends PagingAndSortingRepository<JpaAcctUsersGroup, Long> {

    Set<JpaAcctUsersGroup> findAllByUsersUserUUID(String userUUID);

}
