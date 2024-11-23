package com.desolatetimelines.acct.security.springrepository;

import com.desolatetimelines.acct.security.model.JpaAcctGroupPrivilege;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.Set;

public interface JpaGroupPrivilegesRepository extends PagingAndSortingRepository<JpaAcctGroupPrivilege, Long> {

    Set<JpaAcctGroupPrivilege> findAllByGroupUUIDIn(Collection<String> groupUUIDs);

}
